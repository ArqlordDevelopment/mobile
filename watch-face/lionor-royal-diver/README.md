# LIONOR Royal Diver

Watch Face Format implementation for an original luxury diver-style analog face.

## What is included

- Android Studio resource-only WFF project structure.
- `res/raw/watchface.xml` declarative watch face definition.
- `drawable-nodpi` PNG layers for dial, hour hand, minute hand, second hand, center cap, and date magnifier.
- Dynamic current date text using the WFF `[DAY]` expression.
- Ambient mode variants dim the dial and hide the seconds hand / magnifier overlay.

## Platform note

As of January 2026, Watch Face Format is required for installing watch faces on Wear OS devices. This project is now structured as a WFF resource-only bundle with `android:hasCode="false"` and no Kotlin renderer in the publishable app module.

## Tweak points

Most design layout values live in:

`app/src/main/res/raw/watchface.xml`

Look for:

- `dial_layer`
- `date_number`
- `date_magnifier`
- `lionor_analog_hands`
- `center_cap`

## Open in Android Studio

Open this folder directly:

`C:\dev\mobile\watch-face\lionor-royal-diver`

Android Studio can sync the Gradle project and install it to a Wear OS emulator or Samsung watch from there.
