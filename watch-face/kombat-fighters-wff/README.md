# Kombat Fighters Faces - WFF Starter

Separate Wear OS Watch Face Format project for a four-face kombat fighters pack.

## Face image slots

Replace these 450x450 PNG files when the final base art is ready:

- `app/src/main/res/drawable-nodpi/kombat_fighter_face_01_arena.png`
- `app/src/main/res/drawable-nodpi/kombat_fighter_face_02_neon.png`
- `app/src/main/res/drawable-nodpi/kombat_fighter_face_03_gold.png`
- `app/src/main/res/drawable-nodpi/kombat_fighter_face_04_shadow.png`

## Layout

- Large stacked digital time with AM/PM centered above the hour.
- Date under the time stack.
- Three compact bottom badges: battery, steps, weather.
- Included weather icon assets: sunny, cloudy, rain. The XML currently uses sunny by default. Switch variants with `python switch_weather.py sunny`, `python switch_weather.py cloudy`, or `python switch_weather.py rain`.

## Switch preview face

```powershell
python switch_face.py 1
python switch_face.py 2
python switch_face.py 3
python switch_face.py 4
```

## Build

```powershell
.\gradlew.bat assembleDebug
```

Release signing is a placeholder and should be replaced before publishing.