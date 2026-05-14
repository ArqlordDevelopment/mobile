import 'package:flutter_test/flutter_test.dart';

import 'package:anime_wallpaper_hd/main.dart';

void main() {
  testWidgets('shows anime wallpaper gallery', (WidgetTester tester) async {
    await tester.pumpWidget(const AnimeWallpaperApp());

    expect(find.text('Anime Wallpaper Vault'), findsOneWidget);
    expect(find.text('Daily\nwallpaper'), findsNothing);
    expect(find.text('Tap to turn on'), findsNothing);
  });
}
