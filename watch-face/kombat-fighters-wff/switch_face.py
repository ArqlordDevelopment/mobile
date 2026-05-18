"""Switch active face in watchface.xml by face number (1-4)."""
import re
import sys

FACES = {
    1: {"bg": "bg_face01", "prefix": "f01", "fill": "#A0392A40", "strokes": ("#FFF2FF", "#F0A6FF", "#B8E7FF")},
    2: {"bg": "bg_face02", "prefix": "f02", "fill": "#A0392A40", "strokes": ("#B8E7FF", "#F0A6FF", "#4488FF")},
    3: {"bg": "bg_face03", "prefix": "f03", "fill": "#A0392A40", "strokes": ("#FFE6A7", "#FF9A2E", "#FFD36A")},
    4: {"bg": "bg_face04", "prefix": "f04", "fill": "#AA1C1005", "strokes": ("#8B6C14", "#6B4E10", "#A07820")},
}

face_num = int(sys.argv[1])
target = FACES[face_num]
xml_path = r"app\src\main\res\raw\watchface.xml"

with open(xml_path, "r", encoding="utf-8") as f:
    xml = f.read()

# Switch background image visibility
for info in FACES.values():
    alpha = "255" if info["bg"] == target["bg"] else "0"
    xml = re.sub(
        rf'(name="{info["bg"]}" x="0" y="0" width="450" height="450" alpha=")(\d+)(")',
        rf'\g<1>{alpha}\g<3>',
        xml,
    )

# Switch text/shadow visibility
text_parts = "ampm_shadow|ampm_label|hour_shadow|hour_text|minute_shadow|minute_text|date_shadow|date_text"
for info in FACES.values():
    alpha = "255" if info["prefix"] == target["prefix"] else "0"
    xml = re.sub(
        rf'(name="(?:{text_parts})_{info["prefix"]}"[^>]+alpha=")(\d+)(")',
        rf'\g<1>{alpha}\g<3>',
        xml,
    )

# Switch badge fill color (alpha encoded in 8-digit hex #AARRGGBB)
for name in ("battery_badge", "steps_badge", "weather_badge"):
    xml = re.sub(
        rf'(name="{name}".*?<Fill color=")#[0-9A-Fa-f]{{8}}(")',
        rf'\g<1>{target["fill"]}\g<2>',
        xml,
        flags=re.S,
    )

# Switch badge stroke colors (thickness="1")
for name, color in zip(("battery_badge", "steps_badge", "weather_badge"), target["strokes"]):
    xml = re.sub(
        rf'(name="{name}".*?<Stroke color=")#[0-9A-Fa-f]{{6}}(" thickness="1" />)',
        rf'\g<1>{color}\g<2>',
        xml,
        flags=re.S,
    )

with open(xml_path, "w", encoding="utf-8") as f:
    f.write(xml)

print(f"Switched to Kombat fighters face {face_num} ({target['bg']})")
