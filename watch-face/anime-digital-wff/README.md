# Anime Digital Faces — WFF Starter

This is a no-code **Watch Face Format (WFF)** starter project for Wear OS.

## What is included

- WFF `watchface.xml`
- 8 original anime-inspired background assets:
  - `face_01_sunset_boy.png`
  - `face_02_twilight_girl.png`
  - `face_03_blue_lightning.png`
  - `face_04_red_flame.png`
  - `face_05_green_forest.png`
  - `face_06_purple_curse.png`
  - `face_07_gold_sky.png`
  - `face_08_cyber_neon.png`
- Fixed digital layout:
  - large time
  - date
  - bottom battery / steps / weather slots

## How to switch the base face manually

Open:

`app/src/main/res/raw/watchface.xml`

Find:

```xml
<Image resource="face_01_sunset_boy" />
```

Change it to one of:

```xml
<Image resource="face_02_twilight_girl" />
<Image resource="face_03_blue_lightning" />
<Image resource="face_04_red_flame" />
<Image resource="face_05_green_forest" />
<Image resource="face_06_purple_curse" />
<Image resource="face_07_gold_sky" />
<Image resource="face_08_cyber_neon" />
```

## Important next step

The bottom battery/steps/weather values are placeholder starter text.
For production, wire those areas to WFF complication slots / data providers.

## Publishing note

This project uses:

```xml
<property
    android:name="com.google.wear.watchface.format.version"
    android:value="1" />
```

That matches a conservative WFF v1 starter target.


## UI update pass

This version updates the WFF layout to better match the anime reference:

- Added duplicated shadow text layers for AM/PM, hour, minute, date, and bottom values.
- Warmed the typography colors to a cream/champagne palette.
- Replaced the bottom icons with clean 128x128 transparent badge PNGs.
- Repositioned the bottom row so icons do not clip inside the circular watch bounds.
- Strengthened the bottom gradient for readability.

Font note:
- No external font file is included in this zip.
- If you want the exact Watch Face Studio look, add your licensed/allowed `Oswald-Medium.ttf` to `res/font/oswald_medium.ttf`, then replace `family="SYNC_TO_DEVICE"` with `family="oswald_medium"` in `watchface.xml`.
