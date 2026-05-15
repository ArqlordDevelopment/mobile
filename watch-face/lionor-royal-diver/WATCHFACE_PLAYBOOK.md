# Luxury Watch Face Playbook
# Built from: Lionor Royal Diver (com.lionor.royaldiver)
# Last updated: 2026-05-14

This document captures everything learned building a premium WFF watch face end-to-end —
from blank canvas to Play Store. Use this as the starting point for every future luxury watch face.

---

## 1. FORMAT CHOICE — WFF vs APK

Always use **Watch Face Format (WFF)** for Wear OS 3+ watch faces published to Play Store.

- WFF = declarative XML, no Kotlin/Java code required
- `android:hasCode="false"` in AndroidManifest.xml
- Resource-only APK/AAB — all assets live in `res/`
- Required for Wear OS 3+ (API 33+) Play Store compliance
- Smaller bundle, faster review, no runtime crashes

DO NOT use the old Watchface API (canvas-based Kotlin) for new projects unless targeting Wear OS 2.

---

## 2. PROJECT STRUCTURE

```
mywatch/
  app/
    src/main/
      AndroidManifest.xml          # WFF manifest
      res/
        drawable-nodpi/            # ALL PNG assets go here (no compression)
          dial_base.png
          hour_hand.png
          minute_hand.png
          second_hand.png
          center_cap.png
          date_blank.png           # white rectangle matching dial date window
          black_background.png     # solid black 450x450 for base layer
        raw/
          watchface.xml            # WFF scene definition
        values/
          strings.xml              # app_name
    build.gradle.kts
  build.gradle.kts                 # root — AGP + Kotlin versions
  settings.gradle.kts
  gradle.properties
  local.properties
  mywatch-release.jks              # signing keystore (do NOT commit to git)
  store/                           # Play Store assets
    icon_512.png
    feature_graphic_1024x500.png
    screenshot_wearos_1000x1000.png
```

---

## 3. ANDROIDMANIFEST.XML

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <uses-feature android:name="android.hardware.type.watch" />
    <application
        android:allowBackup="false"
        android:directBootAware="true"
        android:hasCode="false"
        android:icon="@drawable/ic_yourapp"
        android:label="@string/app_name"
        android:supportsRtl="true">
        <property
            android:name="com.google.wear.watchface.format.version"
            android:value="1" />
        <meta-data
            android:name="com.google.android.wearable.standalone"
            android:value="true" />
        <property
            android:name="com.google.wear.watchface.format.publisher"
            android:value="YourStudio-WFF" />
    </application>
</manifest>
```

Key flags:
- `android:hasCode="false"` — mandatory for WFF, prevents dex compilation
- `android.hardware.type.watch` — required for Wear OS Play Store track routing
- `directBootAware="true"` — watch face available before device unlock

---

## 4. ROOT build.gradle.kts

```kotlin
plugins {
    id("com.android.application") version "8.7.3" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false
}
```

AGP 8.7.3 + Kotlin 2.0.21 is a confirmed working combination as of 2026-05.

---

## 5. APP build.gradle.kts (FULL TEMPLATE)

```kotlin
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

plugins {
    id("com.android.application")
}

android {
    namespace = "com.yourstudio.yourwatch"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.yourstudio.yourwatch"
        minSdk = 33        // Wear OS 3 minimum
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    signingConfigs {
        create("release") {
            storeFile = file("../yourwatch-release.jks")
            storePassword = "YourPassword"
            keyAlias = "yourwatch"
            keyPassword = "YourPassword"
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
        }
    }

    androidResources {
        noCompress += "png"    // keep PNGs uncompressed for WFF rendering
    }
}

// WFF CRITICAL: Play Store rejects AABs containing dex files.
// AGP always generates classes.dex even with hasCode=false.
// This task strips it automatically after every release bundle build.
afterEvaluate {
    tasks.named("bundleRelease") {
        doLast {
            val aab = file("build/outputs/bundle/release/app-release.aab")
            if (!aab.exists()) return@doLast
            val tmp = file("build/outputs/bundle/release/app-release-nodex.aab")
            val zipIn  = ZipFile(aab)
            val zipOut = ZipOutputStream(tmp.outputStream().buffered())
            var removed = 0
            zipIn.entries().asSequence().forEach { entry: ZipEntry ->
                if (entry.name.endsWith(".dex")) {
                    removed++
                    println("WFF dex strip: removing ${entry.name}")
                } else {
                    zipOut.putNextEntry(ZipEntry(entry.name))
                    zipIn.getInputStream(entry).copyTo(zipOut)
                    zipOut.closeEntry()
                }
            }
            zipIn.close()
            zipOut.close()
            if (removed > 0) {
                aab.delete()
                tmp.renameTo(aab)
                println("WFF dex strip: removed $removed dex file(s) from ${aab.name}")
            } else {
                tmp.delete()
                println("WFF dex strip: no dex files found, bundle is already clean")
            }
        }
    }
}
```

---

## 6. WATCHFACE.XML — SCENE STRUCTURE

Canvas is always **450x450**. Center = (225, 225).

```xml
<?xml version="1.0" encoding="utf-8"?>
<WatchFace width="450" height="450" clipShape="CIRCLE">
    <Scene>

        <!-- Layer 1: Solid black base (prevents transparent bleed) -->
        <PartImage x="0" y="0" width="450" height="450">
            <Image resource="black_background" />
        </PartImage>

        <!-- Layer 2: Dial — dims in ambient mode -->
        <Group name="dial_layer" x="5" y="5" width="440" height="440">
            <Variant mode="AMBIENT" target="alpha" value="145" />
            <PartImage x="0" y="0" width="440" height="440">
                <Image resource="dial_base" />
            </PartImage>
        </Group>

        <!-- Layer 3: Date window blank (white fill behind date number) -->
        <PartImage name="date_blank" x="320" y="203" width="44" height="35">
            <Image resource="date_blank" />
        </PartImage>

        <!-- Layer 4: Date text -->
        <PartText name="date_number" x="320" y="203" width="44" height="35">
            <Text align="CENTER">
                <Font family="SYNC_TO_DEVICE" size="22" weight="BOLD" color="#222222">
                    <Template><![CDATA[%d]]><Parameter expression="[DAY]" /></Template>
                </Font>
            </Text>
        </PartText>

        <!-- Layer 5: Analog hands — all share the same bounding box as the canvas -->
        <AnalogClock name="hands" x="0" y="0" width="450" height="450">
            <HourHand   resource="hour_hand"   x="209" y="113" width="32"  height="125" pivotX="0.5" pivotY="0.900" />
            <MinuteHand resource="minute_hand" x="211" y="83"  width="28"  height="158" pivotX="0.5" pivotY="0.900" />
            <SecondHand resource="second_hand" x="214" y="92"  width="22"  height="238" pivotX="0.5" pivotY="0.560">
                <Variant mode="AMBIENT" target="alpha" value="0" />
            </SecondHand>
        </AnalogClock>

        <!-- Layer 6: Center cap (covers hand pivot hardware) -->
        <PartImage name="center_cap" x="211" y="211" width="28" height="28">
            <Image resource="center_cap" />
        </PartImage>

    </Scene>
</WatchFace>
```

---

## 7. PIVOT MATH — MOST CRITICAL CONCEPT

The pivot point must land exactly on canvas center (225, 225).

**Formula:**
```
x + (width  * pivotX) = 225
y + (height * pivotY) = 225
```

**To find pivot from a hand PNG:**
- Scan the PNG row by row for the widest opaque row — that is the mounting ring center
- That Y position (within the PNG) divided by the PNG height = pivotY in WFF coordinates
- The hand PNG must be oriented vertically pointing UP (12 o'clock)

**Example (hour hand 32x125 px on 450 canvas):**
```
pivotX = 0.5  (centered horizontally)
pivotY = 0.9  (mounting ring near bottom of hand image)

x = 225 - (32  * 0.5) = 225 - 16   = 209
y = 225 - (125 * 0.9) = 225 - 112.5 = 112.5 → 113
```

**PowerShell pivot scanner** (scan PNG row widths to find mounting ring):
```powershell
Add-Type -AssemblyName System.Drawing
$bmp = [System.Drawing.Bitmap]::FromFile("C:\path\to\hand.png")
$maxWidth = 0; $maxRow = 0
for ($row = 0; $row -lt $bmp.Height; $row++) {
    $left = $bmp.Width; $right = 0
    for ($col = 0; $col -lt $bmp.Width; $col++) {
        $px = $bmp.GetPixel($col, $row)
        if ($px.A -gt 30) { if ($col -lt $left) { $left = $col }; if ($col -gt $right) { $right = $col } }
    }
    if ($right -ge $left) {
        $w = $right - $left + 1
        if ($w -gt $maxWidth) { $maxWidth = $w; $maxRow = $row }
    }
}
$bmp.Dispose()
Write-Host "Widest opaque row: $maxRow of $($bmp.Height) → pivotY = $([math]::Round($maxRow / $bmp.Height, 3))"
```

---

## 8. ASSET PREPARATION

### Required assets and sizes
| Asset            | Size          | Notes                                      |
|------------------|---------------|--------------------------------------------|
| dial_base.png    | 1254x1254+    | Transparent PNG, scaled to 440x440 in WFF  |
| hour_hand.png    | Any           | Vertical, pointing UP, transparent bg      |
| minute_hand.png  | Any           | Vertical, pointing UP, transparent bg      |
| second_hand.png  | Any           | Vertical, pointing UP, transparent bg      |
| center_cap.png   | 300x300       | Transparent PNG, circular cap              |
| date_blank.png   | Match window  | White/cream fill matching dial date window |
| black_background | 450x450       | Solid black PNG, no transparency           |

### Background removal (PowerShell — flood fill from edges)
Use this when ChatGPT/AI tools deliver hands with solid white or black backgrounds:

```powershell
Add-Type -AssemblyName System.Drawing
$bmp = [System.Drawing.Bitmap]::FromFile("C:\path\to\hand.png")
$bmp = $bmp.Clone([System.Drawing.Rectangle]::new(0,0,$bmp.Width,$bmp.Height),
       [System.Drawing.Imaging.PixelFormat]::Format32bppArgb)

# Flood fill from all 4 edges — tune R/G/B thresholds for white (240) or black (40)
$queue = [System.Collections.Generic.Queue[System.Drawing.Point]]::new()
for ($x = 0; $x -lt $bmp.Width;  $x++) { $queue.Enqueue([System.Drawing.Point]::new($x,0)); $queue.Enqueue([System.Drawing.Point]::new($x,$bmp.Height-1)) }
for ($y = 0; $y -lt $bmp.Height; $y++) { $queue.Enqueue([System.Drawing.Point]::new(0,$y)); $queue.Enqueue([System.Drawing.Point]::new($bmp.Width-1,$y)) }
while ($queue.Count -gt 0) {
    $p = $queue.Dequeue()
    $px = $bmp.GetPixel($p.X,$p.Y)
    if ($px.A -gt 0 -and $px.R -gt 240 -and $px.G -gt 240 -and $px.B -gt 240) {  # white threshold
        $bmp.SetPixel($p.X,$p.Y,[System.Drawing.Color]::Transparent)
        @([System.Drawing.Point]::new($p.X-1,$p.Y),[System.Drawing.Point]::new($p.X+1,$p.Y),
          [System.Drawing.Point]::new($p.X,$p.Y-1),[System.Drawing.Point]::new($p.X,$p.Y+1)) |
        Where-Object { $_.X -ge 0 -and $_.X -lt $bmp.Width -and $_.Y -ge 0 -and $_.Y -lt $bmp.Height } |
        ForEach-Object { $queue.Enqueue($_) }
    }
}
$bmp.Save("C:\path\to\hand_clean.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()
```

### Hands must point UP (12 o'clock)
If AI delivers hands pointing right (3 o'clock), rotate -90° CCW:
```powershell
$bmp.RotateFlip([System.Drawing.RotateFlipType]::Rotate270FlipNone)
```

---

## 9. DATE WINDOW ALIGNMENT

To align `PartText` date inside the dial's date rectangle:
1. Identify the pixel coordinates of the date window on the dial at 440x440 scale
2. Scale from dial PNG size: `pixel * (440 / dialPngSize)`
3. Adjust by the Group offset (x=5, y=5 for the dial layer)
4. Fine-tune by pixel (±1–2px) after emulator screenshot

The date_blank image should exactly match the dial's white date rectangle color and size.
Use `PartImage` + `PartText` stacked at the same x,y,w,h.

---

## 10. BUILD AND DEPLOY

### Generate Gradle wrapper (first time, no gradlew in project)
```powershell
$gradle = 'C:\Users\rocky\.gradle\wrapper\dists\gradle-8.13-bin\...\gradle-8.13\bin\gradle.bat'
Set-Location 'C:\dev\mobile\watch-face\yourwatch'
& $gradle wrapper
```

### Build signed release AAB
```powershell
$gradle = 'C:\Users\rocky\.gradle\wrapper\dists\gradle-8.13-bin\...\gradle-8.13\bin\gradle.bat'
Set-Location 'C:\dev\mobile\watch-face\yourwatch'
& $gradle bundleRelease
```
Output: `app/build/outputs/bundle/release/app-release.aab`
The dex-stripping task in build.gradle.kts runs automatically.

### Deploy to emulator (debug APK)
```powershell
& $gradle assembleDebug
$adb = "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe"
& $adb -s emulator-5554 install --no-streaming -r app\build\outputs\apk\debug\app-debug.apk
```

### Take emulator screenshot
```powershell
$adb = "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe"
& $adb -s emulator-5554 shell screencap /data/local/tmp/ss.png
& $adb -s emulator-5554 pull /data/local/tmp/ss.png "$env:USERPROFILE\Downloads\ss.png"
```

---

## 11. KEYSTORE CREATION

Run once per app (or reuse studio keystore across apps):
```powershell
$keytool = "$env:JAVA_HOME\bin\keytool.exe"
& $keytool -genkeypair -v -keystore yourwatch-release.jks -alias yourwatch `
  -keyalg RSA -keysize 2048 -validity 10000 `
  -dname "CN=Your Name, OU=Studio, O=Company, L=City, S=State, C=US" `
  -storepass YourPassword -keypass YourPassword
```

IMPORTANT: Never commit `.jks` files to git. Add to `.gitignore`.

---

## 12. PLAY STORE — WEAR OS PUBLISHING

### Known gotchas
1. **Dex error**: "Watch face cannot have any dex files" — fixed by the afterEvaluate dex-strip task in build.gradle.kts (included in template above).
2. **Wrong track error**: "This APK requires android.hardware.type.watch — remove this artifact" — you uploaded to the phone track. Fix: Setup → Advanced settings → Form factors → Add Wear OS. Then upload under the Wear OS Production track.
3. **Version code already used**: Every upload (even rejected ones) consumes a version code. Bump `versionCode` in build.gradle.kts and rebuild.

### Store assets required
| Asset                    | Size        | Format |
|--------------------------|-------------|--------|
| App icon                 | 512x512     | PNG    |
| Feature graphic          | 1024x500    | PNG/JPG|
| Wear OS screenshot       | 1000x1000+  | PNG/JPG|
| Short description        | ≤80 chars   | text   |
| Full description         | ≤4000 chars | text   |
| Privacy policy URL       | required    | URL    |

### Privacy policy
Use the `android-release` Python toolchain at `C:\dev\ai\android-release\`:
1. Copy `briefs/example.py` → `briefs/yourwatch.py`, fill in app details
2. Run `python upload_yourwatch_privacy.py`
3. Privacy page goes live at `http://bakerapps.com/android/{slug}/privacy.html`

### Generate store graphics (PowerShell)
See `C:\Users\rocky\AppData\Local\Temp\make_graphics.ps1` for the pattern:
- Resize dial PNG to 512x512 for icon
- Composite dial on black 1024x500 canvas for feature graphic
- Screencap from emulator → crop to 1000x1000 for Wear OS screenshot

### Play Console release details template
```
Release name:  Your Watch Name 1.0
Release notes:
<en-US>
Initial release of [Name] — [1-2 sentence description of luxury features].
Built with Watch Face Format (WFF) for Wear OS 3+.
</en-US>
```

---

## 13. WFF CUSTOMIZATION (no companion app needed)

For multiple styles in one app, add to watchface.xml:

```xml
<UserConfigurations>
    <UserConfiguration id="dial_style" displayName="Dial Style" defaultValue="classic">
        <ListConfiguration>
            <Option id="classic" displayName="Classic Gold" />
            <Option id="stealth" displayName="Stealth Black" />
            <Option id="blue"    displayName="Ocean Blue" />
        </ListConfiguration>
    </UserConfiguration>
    <UserConfiguration id="show_seconds" displayName="Second Hand" defaultValue="true">
        <BooleanConfiguration />
    </UserConfiguration>
</UserConfigurations>
```

Then in Scene, reference with expressions:
```xml
<PartImage x="0" y="0" width="440" height="440">
    <Image resource="dial_classic">
        <Variant mode="CONFIGURATION" target="resource"
            condition="[CONFIGURATION.dial_style] == 'stealth'"
            value="dial_stealth" />
    </Image>
</PartImage>
```

User sets preferences in the watch face editor (watch or Wear OS phone app). No companion app needed.

**When you DO need a companion app:**
- Premium unlock / paid customization
- Downloading new dial packs from a server
- User accounts / sync

---

## 14. AMBIENT MODE

- Dim dial: `<Variant mode="AMBIENT" target="alpha" value="145" />`  (145 ≈ 57% opacity)
- Hide second hand: `<Variant mode="AMBIENT" target="alpha" value="0" />`
- Keep hour/minute hands visible in ambient (no Variant = always visible)
- Avoid bright colors in ambient — burn-in protection on OLED

---

## 15. WFF LAYER ORDER (painter's model, top = rendered last = on top)

```
black_background     ← always first, prevents bleed-through
dial_base            ← main dial image
date_blank           ← white fill behind date
date_number          ← date text on top of blank
AnalogClock (hands)  ← hour, minute, second
center_cap           ← always last, covers pivot hardware
```

---

## 16. COMMON EXPRESSIONS

```
[DAY]          — day of month (1–31)
[MONTH]        — month number (1–12)
[HOUR]         — 12-hour clock hour
[HOUR_0_23]    — 24-hour clock hour
[MINUTE]       — minutes (0–59)
[SECOND]       — seconds (0–59)
[DAY_OF_WEEK]  — 1=Sunday … 7=Saturday
[BATTERY_PERCENT] — battery level 0–100
```

---

## 17. CHECKLIST — NEW LUXURY WATCH FACE

- [ ] Create package name: `com.yourstudio.yourwatch`
- [ ] Copy project structure from lionor-royal-diver
- [ ] Paste build.gradle.kts template (includes dex-strip task)
- [ ] Generate keystore, add to .gitignore
- [ ] Design dial PNG (1254x1254+, transparent)
- [ ] Design hands (vertical, pointing UP, transparent PNG)
- [ ] Scan PNG pivot rows, calculate WFF x/y/pivotX/pivotY
- [ ] Design/identify date window position on dial
- [ ] Write watchface.xml with correct pivot math
- [ ] Build debug APK, deploy to emulator, screenshot
- [ ] Iterate on pivot/position until aligned
- [ ] Build release AAB (dex strip runs automatically)
- [ ] Create Play Console app listing (Wear OS form factor)
- [ ] Generate store assets (icon 512, feature 1024x500, screenshot 1000x1000)
- [ ] Generate privacy page (android-release Python toolchain)
- [ ] Upload AAB to Wear OS Production track (NOT phone track)
- [ ] Set countries/regions (all or major markets)
- [ ] Submit for review
