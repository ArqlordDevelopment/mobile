"""Switch active weather icon in watchface.xml: sunny, cloudy, or rain."""
import re
import sys

ICONS = {"sunny", "cloudy", "rain"}
choice = sys.argv[1].lower()
if choice not in ICONS:
    raise SystemExit("Usage: python switch_weather.py sunny|cloudy|rain")

xml_path = r"app\src\main\res\raw\watchface.xml"
with open(xml_path, "r", encoding="utf-8") as f:
    xml = f.read()

for icon in ICONS:
    alpha = "255" if icon == choice else "0"
    xml = re.sub(
        rf'(name="weather_icon_{icon}"[^>]+alpha=")(\d+)(")',
        rf'\g<1>{alpha}\g<3>',
        xml,
    )

with open(xml_path, "w", encoding="utf-8") as f:
    f.write(xml)

print(f"Switched weather icon to {choice}")