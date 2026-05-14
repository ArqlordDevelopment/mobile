import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'dart:ui';

void main() {
  runApp(const AnimeWallpaperApp());
}

class AnimeWallpaperApp extends StatelessWidget {
  const AnimeWallpaperApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Anime Vault',
      theme: ThemeData(
        useMaterial3: true,
        brightness: Brightness.dark,
        scaffoldBackgroundColor: const Color(0xFF0F1117),
        colorScheme: ColorScheme.fromSeed(
          seedColor: const Color(0xFFD8E2FF),
          brightness: Brightness.dark,
        ),
      ),
      home: const WallpaperGalleryPage(),
    );
  }
}

class Wallpaper {
  const Wallpaper({
    required this.title,
    required this.series,
    required this.colors,
    required this.accent,
    required this.assetPath,
  });

  final String title;
  final String series;
  final List<Color> colors;
  final Color accent;
  final String assetPath;
}

const wallpapers = <Wallpaper>[
  Wallpaper(
    title: 'Golden Warrior',
    series: 'Dragon Ball Z',
    colors: [Color(0xFF120A00), Color(0xFF7A4500), Color(0xFFFFD700)],
    accent: Color(0xFFFFD700),
    assetPath: 'assets/wallpapers/01_golden_aura_warrior.png',
  ),
  Wallpaper(
    title: 'Rasengan Duel',
    series: 'Naruto',
    colors: [Color(0xFF050C14), Color(0xFF1A4A7A), Color(0xFF60BFFF)],
    accent: Color(0xFF60BFFF),
    assetPath: 'assets/wallpapers/02_dual_energy_ninja_attack.png',
  ),
  Wallpaper(
    title: 'Royal Crimson',
    series: 'Dragon Ball Z',
    colors: [Color(0xFF0D0101), Color(0xFF6B0000), Color(0xFFFF2020)],
    accent: Color(0xFFFF2020),
    assetPath: 'assets/wallpapers/03_red_aura_armored_warrior.png',
  ),
  Wallpaper(
    title: 'Valley Charge',
    series: 'Naruto',
    colors: [Color(0xFF0A0801), Color(0xFF4A3000), Color(0xFF38BDF8)],
    accent: Color(0xFF38BDF8),
    assetPath: 'assets/wallpapers/04_blonde_ninja_earth_style.png',
  ),
  Wallpaper(
    title: 'Cursed Eye',
    series: 'Naruto',
    colors: [Color(0xFF080010), Color(0xFF3D0066), Color(0xFFAB20FD)],
    accent: Color(0xFFAB20FD),
    assetPath: 'assets/wallpapers/05_purple_storm_ninja.png',
  ),
  Wallpaper(
    title: 'King of Pirates',
    series: 'One Piece',
    colors: [Color(0xFF001020), Color(0xFF003B6F), Color(0xFF38BDF8)],
    accent: Color(0xFF38BDF8),
    assetPath: 'assets/wallpapers/06_pirate_ocean_punch.png',
  ),
  Wallpaper(
    title: 'Water Vortex',
    series: 'Demon Slayer',
    colors: [Color(0xFF001018), Color(0xFF00375A), Color(0xFF38BDF8)],
    accent: Color(0xFF38BDF8),
    assetPath: 'assets/wallpapers/07_water_vortex_swordsman.png',
  ),
  Wallpaper(
    title: 'City Rampage',
    series: 'Chainsaw Man',
    colors: [Color(0xFF120000), Color(0xFF5A0000), Color(0xFFEF4444)],
    accent: Color(0xFFEF4444),
    assetPath: 'assets/wallpapers/08_chainsaw_city_battle.png',
  ),
  Wallpaper(
    title: 'Off Duty',
    series: 'Chainsaw Man',
    colors: [Color(0xFF120D08), Color(0xFF3D2010), Color(0xFF60A5FA)],
    accent: Color(0xFF60A5FA),
    assetPath: 'assets/wallpapers/31_aki_off_duty.jpg',
  ),
  Wallpaper(
    title: 'Infinite Void',
    series: 'Jujutsu Kaisen',
    colors: [Color(0xFF08001A), Color(0xFF3D0080), Color(0xFF8B5CF6)],
    accent: Color(0xFF8B5CF6),
    assetPath: 'assets/wallpapers/09_purple_blindfold_sorcerer.png',
  ),
  Wallpaper(
    title: 'Storm Caller',
    series: 'One Piece',
    colors: [Color(0xFF001228), Color(0xFF00427A), Color(0xFFF97316)],
    accent: Color(0xFFF97316),
    assetPath: 'assets/wallpapers/10_nami_ship_deck_staff.png',
  ),
  Wallpaper(
    title: 'Three Sword Style',
    series: 'One Piece',
    colors: [Color(0xFF001200), Color(0xFF004000), Color(0xFF22C55E)],
    accent: Color(0xFF22C55E),
    assetPath: 'assets/wallpapers/11_green_swordsman_three_swords.png',
  ),
  Wallpaper(
    title: 'Diable Jambe',
    series: 'One Piece',
    colors: [Color(0xFF0A0300), Color(0xFF5A2000), Color(0xFFF97316)],
    accent: Color(0xFFF97316),
    assetPath: 'assets/wallpapers/12_fire_kick_blonde_fighter.png',
  ),
  Wallpaper(
    title: 'Thousand Birds',
    series: 'Naruto',
    colors: [Color(0xFF000818), Color(0xFF001A4A), Color(0xFF60A5FA)],
    accent: Color(0xFF60A5FA),
    assetPath: 'assets/wallpapers/13_blue_lightning_masked_ninja.png',
  ),
  Wallpaper(
    title: 'Sharingan Crow',
    series: 'Naruto',
    colors: [Color(0xFF0A0000), Color(0xFF5A0000), Color(0xFFEF4444)],
    accent: Color(0xFFEF4444),
    assetPath: 'assets/wallpapers/14_red_crows_cloak_ninja.png',
  ),
  Wallpaper(
    title: "Humanity's Strongest",
    series: 'Attack on Titan',
    colors: [Color(0xFF020A02), Color(0xFF0A2A0A), Color(0xFF4ADE80)],
    accent: Color(0xFF4ADE80),
    assetPath: 'assets/wallpapers/15_rooftop_scout_swordsman.png',
  ),
  Wallpaper(
    title: 'Flame Hashira',
    series: 'Demon Slayer',
    colors: [Color(0xFF100200), Color(0xFF6B1400), Color(0xFFF97316)],
    accent: Color(0xFFF97316),
    assetPath: 'assets/wallpapers/16_flame_swordsman_strike.png',
  ),
  Wallpaper(
    title: 'Bankai Unleashed',
    series: 'Bleach',
    colors: [Color(0xFF0A0200), Color(0xFF5A1A00), Color(0xFFFB923C)],
    accent: Color(0xFFFB923C),
    assetPath: 'assets/wallpapers/17_orange_flame_soul_swordsman.png',
  ),
  Wallpaper(
    title: 'Serious Series',
    series: 'One Punch Man',
    colors: [Color(0xFF0A0400), Color(0xFF5A2800), Color(0xFFEAB308)],
    accent: Color(0xFFEAB308),
    assetPath: 'assets/wallpapers/18_bald_hero_punch.png',
  ),
  Wallpaper(
    title: 'Godspeed',
    series: 'Hunter x Hunter',
    colors: [Color(0xFF000818), Color(0xFF001850), Color(0xFF60A5FA)],
    accent: Color(0xFF60A5FA),
    assetPath: 'assets/wallpapers/19_blue_electric_young_warrior.png',
  ),
  Wallpaper(
    title: 'Legendary Super Saiyan',
    series: 'Dragon Ball Z',
    colors: [Color(0xFF001000), Color(0xFF003A00), Color(0xFF4ADE80)],
    accent: Color(0xFF4ADE80),
    assetPath: 'assets/wallpapers/20_green_rage_muscle_warrior.png',
  ),
  Wallpaper(
    title: 'King of Curses',
    series: 'Jujutsu Kaisen',
    colors: [Color(0xFF0A0005), Color(0xFF5A0020), Color(0xFFF43F5E)],
    accent: Color(0xFFF43F5E),
    assetPath: 'assets/wallpapers/21_pink_cursed_demon_king.png',
  ),
  Wallpaper(
    title: 'Water Breath',
    series: 'Demon Slayer',
    colors: [Color(0xFF001020), Color(0xFF003A5A), Color(0xFF38BDF8)],
    accent: Color(0xFF38BDF8),
    assetPath: 'assets/wallpapers/22_water_breathing_swordsman.png',
  ),
  Wallpaper(
    title: 'Butterfly Hashira',
    series: 'Demon Slayer',
    colors: [Color(0xFF080010), Color(0xFF3D006A), Color(0xFFA855F7)],
    accent: Color(0xFFA855F7),
    assetPath: 'assets/wallpapers/23_butterfly_swordswoman_purple.png',
  ),
  Wallpaper(
    title: 'Set Your Heart Ablaze',
    series: 'Demon Slayer',
    colors: [Color(0xFF0A0200), Color(0xFF6B1400), Color(0xFFF97316)],
    accent: Color(0xFFF97316),
    assetPath: 'assets/wallpapers/24_flame_hashira_portrait.png',
  ),
  Wallpaper(
    title: 'Shadow Monarch',
    series: 'Solo Leveling',
    colors: [Color(0xFF000818), Color(0xFF001A5A), Color(0xFF60A5FA)],
    accent: Color(0xFF60A5FA),
    assetPath: 'assets/wallpapers/25_blue_shadow_hunter.png',
  ),
  Wallpaper(
    title: 'Arise',
    series: 'Solo Leveling',
    colors: [Color(0xFF060010), Color(0xFF2A0060), Color(0xFF8B5CF6)],
    accent: Color(0xFF8B5CF6),
    assetPath: 'assets/wallpapers/26_purple_shadow_summoner.png',
  ),
  Wallpaper(
    title: "Monarch's Gaze",
    series: 'Solo Leveling',
    colors: [Color(0xFF000A1A), Color(0xFF001040), Color(0xFF38BDF8)],
    accent: Color(0xFF38BDF8),
    assetPath: 'assets/wallpapers/27_blue_shadow_hunter_profile.png',
  ),
  Wallpaper(
    title: 'Limitless',
    series: 'Jujutsu Kaisen',
    colors: [Color(0xFF080010), Color(0xFF3A0070), Color(0xFF8B5CF6)],
    accent: Color(0xFF8B5CF6),
    assetPath: 'assets/wallpapers/28_purple_blindfold_sorcerer_alt.png',
  ),
  Wallpaper(
    title: 'Incineration Cannon',
    series: 'One Punch Man',
    colors: [Color(0xFF0A0500), Color(0xFF5A2A00), Color(0xFFEAB308)],
    accent: Color(0xFFEAB308),
    assetPath: 'assets/wallpapers/29_cyborg_energy_blast.png',
  ),
  Wallpaper(
    title: 'Sunset Feast',
    series: 'One Piece',
    colors: [Color(0xFF1A0200), Color(0xFF8B2000), Color(0xFFFF6B00)],
    accent: Color(0xFFFF6B00),
    assetPath: 'assets/wallpapers/30_luffy_sunset_feast.png',
  ),
  Wallpaper(
    title: 'Tokyo Squad',
    series: 'Jujutsu Kaisen',
    colors: [Color(0xFF0A1020), Color(0xFF1E3A6E), Color(0xFF60A5FA)],
    accent: Color(0xFF60A5FA),
    assetPath: 'assets/wallpapers/32_jjk_tokyo_squad.jpg',
  ),
];

const _categories = <(String, String)>[
  ('All', ''),
  ('DBZ', 'Dragon Ball Z'),
  ('Naruto', 'Naruto'),
  ('One Piece', 'One Piece'),
  ('Demon Slayer', 'Demon Slayer'),
  ('Chainsaw Man', 'Chainsaw Man'),
  ('JJK', 'Jujutsu Kaisen'),
  ('AOT', 'Attack on Titan'),
  ('Bleach', 'Bleach'),
  ('OPM', 'One Punch Man'),
  ('HxH', 'Hunter x Hunter'),
  ('Solo Lvl', 'Solo Leveling'),
];

class WallpaperGalleryPage extends StatefulWidget {
  const WallpaperGalleryPage({super.key});

  @override
  State<WallpaperGalleryPage> createState() => _WallpaperGalleryPageState();
}

class _WallpaperGalleryPageState extends State<WallpaperGalleryPage> {
  final _searchController = TextEditingController();
  String _searchQuery = '';
  String _selectedCategory = 'All';

  @override
  void dispose() {
    _searchController.dispose();
    super.dispose();
  }

  List<Wallpaper> get _filtered {
    var result = wallpapers;
    if (_selectedCategory != 'All') {
      final series = _categories
          .firstWhere((c) => c.$1 == _selectedCategory)
          .$2;
      result = result.where((w) => w.series == series).toList();
    }
    if (_searchQuery.isNotEmpty) {
      final q = _searchQuery.toLowerCase();
      result = result
          .where(
            (w) =>
                w.title.toLowerCase().contains(q) ||
                w.series.toLowerCase().contains(q),
          )
          .toList();
    }
    return result;
  }

  @override
  Widget build(BuildContext context) {
    final filtered = _filtered;
    return Scaffold(
      backgroundColor: const Color(0xFF0F1117),
      body: SafeArea(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // ── Header ──────────────────────────────────
            Padding(
              padding: const EdgeInsets.fromLTRB(20, 16, 20, 14),
              child: Row(
                children: [
                  RichText(
                    text: const TextSpan(
                      style: TextStyle(
                        fontSize: 30,
                        fontWeight: FontWeight.w900,
                        letterSpacing: -0.5,
                      ),
                      children: [
                        TextSpan(
                          text: 'Anime ',
                          style: TextStyle(color: Colors.white),
                        ),
                        TextSpan(
                          text: 'Vault',
                          style: TextStyle(color: Color(0xFFFFB703)),
                        ),
                      ],
                    ),
                  ),
                  const Spacer(),
                  const Icon(
                    Icons.workspace_premium,
                    color: Color(0xFFFFB703),
                    size: 28,
                  ),
                ],
              ),
            ),
            // ── Search bar ───────────────────────────────
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 16),
              child: TextField(
                controller: _searchController,
                style: const TextStyle(color: Colors.white, fontSize: 15),
                onChanged: (v) => setState(() => _searchQuery = v),
                decoration: InputDecoration(
                  hintText: 'Search anime, character, style...',
                  hintStyle: const TextStyle(
                    color: Color(0xFF6B7280),
                    fontSize: 15,
                  ),
                  prefixIcon: const Icon(
                    Icons.search,
                    color: Color(0xFF6B7280),
                  ),
                  suffixIcon: _searchQuery.isNotEmpty
                      ? IconButton(
                          icon: const Icon(
                            Icons.close,
                            color: Color(0xFF6B7280),
                            size: 18,
                          ),
                          onPressed: () {
                            _searchController.clear();
                            setState(() => _searchQuery = '');
                          },
                        )
                      : null,
                  filled: true,
                  fillColor: const Color(0xFF1C1D24),
                  contentPadding: const EdgeInsets.symmetric(vertical: 14),
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(14),
                    borderSide: BorderSide.none,
                  ),
                  focusedBorder: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(14),
                    borderSide: const BorderSide(
                      color: Color(0xFFFFB703),
                      width: 1.5,
                    ),
                  ),
                ),
              ),
            ),
            const SizedBox(height: 14),
            // ── Category chips ───────────────────────────
            SizedBox(
              height: 36,
              child: ListView.separated(
                scrollDirection: Axis.horizontal,
                padding: const EdgeInsets.symmetric(horizontal: 16),
                itemCount: _categories.length,
                separatorBuilder: (_, __) => const SizedBox(width: 8),
                itemBuilder: (context, i) {
                  final label = _categories[i].$1;
                  final isSelected = _selectedCategory == label;
                  return GestureDetector(
                    onTap: () => setState(() => _selectedCategory = label),
                    child: AnimatedContainer(
                      duration: const Duration(milliseconds: 180),
                      curve: Curves.easeOut,
                      padding: const EdgeInsets.symmetric(horizontal: 16),
                      decoration: BoxDecoration(
                        color: isSelected
                            ? const Color(0xFFFFB703)
                            : const Color(0xFF1C1D24),
                        borderRadius: BorderRadius.circular(18),
                        border: Border.all(
                          color: isSelected
                              ? const Color(0xFFFFB703)
                              : Colors.white24,
                          width: 1,
                        ),
                      ),
                      child: Center(
                        child: Text(
                          label,
                          style: TextStyle(
                            color: isSelected
                                ? const Color(0xFF111827)
                                : Colors.white,
                            fontWeight: FontWeight.w700,
                            fontSize: 13,
                          ),
                        ),
                      ),
                    ),
                  );
                },
              ),
            ),
            const SizedBox(height: 10),
            // ── Grid ─────────────────────────────────────
            Expanded(
              child: filtered.isEmpty
                  ? const Center(
                      child: Text(
                        'No wallpapers found',
                        style: TextStyle(color: Color(0xFF6B7280)),
                      ),
                    )
                  : GridView.builder(
                      padding: const EdgeInsets.all(4),
                      gridDelegate:
                          const SliverGridDelegateWithFixedCrossAxisCount(
                            crossAxisCount: 3,
                            mainAxisSpacing: 4,
                            crossAxisSpacing: 4,
                            childAspectRatio: 0.54,
                          ),
                      itemCount: filtered.length,
                      itemBuilder: (context, index) {
                        final wallpaper = filtered[index];
                        final globalIndex = wallpapers.indexOf(wallpaper);
                        return _WallpaperTile(
                          wallpaper: wallpaper,
                          onTap: () => Navigator.of(context).push(
                            MaterialPageRoute(
                              builder: (_) => WallpaperPreviewPage(
                                initialIndex: globalIndex,
                              ),
                            ),
                          ),
                        );
                      },
                    ),
            ),
          ],
        ),
      ),
    );
  }
}

class _WallpaperTile extends StatelessWidget {
  const _WallpaperTile({required this.wallpaper, required this.onTap});

  final Wallpaper wallpaper;
  final VoidCallback onTap;

  @override
  Widget build(BuildContext context) {
    return _PressableScale(
      onTap: onTap,
      hoverScale: 1.02,
      pressedScale: 0.97,
      borderRadius: BorderRadius.zero,
      hoverDecoration: BoxDecoration(
        border: Border.all(color: Colors.white.withValues(alpha: 0.16)),
        boxShadow: const [
          BoxShadow(
            color: Color(0x66000000),
            blurRadius: 14,
            offset: Offset(0, 6),
          ),
        ],
      ),
      pressedDecoration: BoxDecoration(
        border: Border.all(color: Colors.white.withValues(alpha: 0.26)),
      ),
      child: Stack(
        fit: StackFit.expand,
        children: [
          WallpaperArtwork(wallpaper: wallpaper),
          const _TileHoverSheen(),
        ],
      ),
    );
  }
}

class _TileHoverSheen extends StatelessWidget {
  const _TileHoverSheen();

  @override
  Widget build(BuildContext context) {
    return IgnorePointer(
      child: DecoratedBox(
        decoration: BoxDecoration(
          border: Border.all(color: Colors.white.withValues(alpha: 0.04)),
        ),
      ),
    );
  }
}

class WallpaperArtwork extends StatelessWidget {
  const WallpaperArtwork({super.key, required this.wallpaper});

  final Wallpaper wallpaper;

  @override
  Widget build(BuildContext context) {
    return _BlurUpAssetImage(assetPath: wallpaper.assetPath);
  }
}

class _BlurUpAssetImage extends StatelessWidget {
  const _BlurUpAssetImage({required this.assetPath});

  final String assetPath;

  @override
  Widget build(BuildContext context) {
    return Image.asset(
      assetPath,
      fit: BoxFit.cover,
      frameBuilder: (context, child, frame, wasSynchronouslyLoaded) {
        if (wasSynchronouslyLoaded || frame != null) {
          return AnimatedOpacity(
            opacity: 1,
            duration: const Duration(milliseconds: 180),
            curve: Curves.easeOut,
            child: child,
          );
        }

        return Stack(
          fit: StackFit.expand,
          children: [
            Image.asset(
              assetPath,
              fit: BoxFit.cover,
              cacheWidth: 80,
              errorBuilder: (_, __, ___) =>
                  const ColoredBox(color: Color(0xFF0F1117)),
            ),
            BackdropFilter(
              filter: ImageFilter.blur(sigmaX: 18, sigmaY: 18),
              child: ColoredBox(color: Colors.black.withValues(alpha: 0.24)),
            ),
          ],
        );
      },
      errorBuilder: (_, __, ___) => const SizedBox.shrink(),
    );
  }
}

enum WallpaperTarget { home, lock, both }

class WallpaperSetter {
  static const _channel = MethodChannel('anime_wallpaper_hd/wallpaper');

  static Future<void> setWallpaper({
    required String assetPath,
    required WallpaperTarget target,
  }) async {
    await _channel.invokeMethod<void>('setWallpaper', {
      'assetPath': assetPath,
      'target': target.name,
    });
  }

  static Future<void> closeApp() async {
    await _channel.invokeMethod<void>('closeApp');
  }
}

class WallpaperPreviewPage extends StatefulWidget {
  const WallpaperPreviewPage({super.key, required this.initialIndex});

  final int initialIndex;

  @override
  State<WallpaperPreviewPage> createState() => _WallpaperPreviewPageState();
}

class _WallpaperPreviewPageState extends State<WallpaperPreviewPage> {
  late final PageController _pageController;
  late int currentIndex;
  bool isSetting = false;

  Wallpaper get wallpaper => wallpapers[currentIndex];

  @override
  void initState() {
    super.initState();
    currentIndex = widget.initialIndex;
    _pageController = PageController(initialPage: currentIndex);
  }

  @override
  void dispose() {
    _pageController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        fit: StackFit.expand,
        children: [
          PageView.builder(
            controller: _pageController,
            itemCount: wallpapers.length,
            onPageChanged: (index) => setState(() => currentIndex = index),
            itemBuilder: (context, index) {
              return WallpaperArtwork(wallpaper: wallpapers[index]);
            },
          ),
          DecoratedBox(
            decoration: BoxDecoration(
              gradient: LinearGradient(
                begin: Alignment.topCenter,
                end: Alignment.bottomCenter,
                colors: [
                  Colors.black.withValues(alpha: 0.14),
                  Colors.transparent,
                  Colors.black.withValues(alpha: 0.78),
                ],
                stops: const [0, 0.45, 1],
              ),
            ),
          ),
          SafeArea(
            child: Padding(
              padding: const EdgeInsets.fromLTRB(22, 12, 22, 24),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  IconButton(
                    onPressed: () => Navigator.of(context).pop(),
                    icon: const Icon(Icons.arrow_back, size: 34),
                    tooltip: 'Back',
                  ),
                  const Spacer(),
                  Text(
                    wallpaper.title,
                    maxLines: 1,
                    overflow: TextOverflow.ellipsis,
                    style: Theme.of(context).textTheme.headlineLarge?.copyWith(
                      fontWeight: FontWeight.w900,
                      shadows: const [
                        Shadow(
                          color: Color(0xAA000000),
                          blurRadius: 12,
                          offset: Offset(0, 3),
                        ),
                      ],
                    ),
                  ),
                  const SizedBox(height: 6),
                  Text(
                    wallpaper.series,
                    style: TextStyle(
                      color: wallpaper.accent,
                      fontSize: 18,
                      fontWeight: FontWeight.w900,
                      shadows: const [
                        Shadow(
                          color: Color(0xCC000000),
                          blurRadius: 10,
                          offset: Offset(0, 2),
                        ),
                      ],
                    ),
                  ),
                  const SizedBox(height: 18),
                  Row(
                    children: [
                      Expanded(
                        child: _PreviewModeButton(
                          icon: Icons.lock_outline,
                          label: 'Lock Preview',
                          onTap: () => _openPreview(PhonePreviewMode.lock),
                        ),
                      ),
                      const SizedBox(width: 12),
                      Expanded(
                        child: _PreviewModeButton(
                          icon: Icons.phone_android,
                          label: 'Home Preview',
                          onTap: () => _openPreview(PhonePreviewMode.home),
                        ),
                      ),
                    ],
                  ),
                  const SizedBox(height: 18),
                  SizedBox(
                    width: double.infinity,
                    child: _PressableScale(
                      onTap: isSetting ? null : _showSetOptions,
                      child: DecoratedBox(
                        decoration: BoxDecoration(
                          color: const Color(0xFFFFB703),
                          borderRadius: BorderRadius.circular(28),
                          boxShadow: [
                            BoxShadow(
                              color: const Color(
                                0xFFFFB703,
                              ).withValues(alpha: 0.36),
                              blurRadius: 20,
                              offset: const Offset(0, 8),
                            ),
                          ],
                        ),
                        child: Padding(
                          padding: const EdgeInsets.symmetric(vertical: 17),
                          child: Text(
                            isSetting ? 'SETTING...' : 'SET WALLPAPER',
                            textAlign: TextAlign.center,
                            style: const TextStyle(
                              color: Color(0xFF111827),
                              fontWeight: FontWeight.w900,
                              letterSpacing: 0,
                            ),
                          ),
                        ),
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }

  void _openPreview(PhonePreviewMode mode) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (_) =>
            FullWallpaperPreviewPage(initialIndex: currentIndex, mode: mode),
      ),
    );
  }

  void _showSetOptions() {
    showModalBottomSheet<void>(
      context: context,
      backgroundColor: Colors.transparent,
      builder: (context) {
        return Padding(
          padding: const EdgeInsets.fromLTRB(16, 0, 16, 28),
          child: Container(
            decoration: BoxDecoration(
              color: const Color(0xFF1C1D24),
              borderRadius: BorderRadius.circular(34),
            ),
            child: SafeArea(
              top: false,
              child: Padding(
                padding: const EdgeInsets.fromLTRB(24, 24, 24, 18),
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const Text(
                      'Set wallpaper',
                      style: TextStyle(
                        fontSize: 24,
                        fontWeight: FontWeight.w900,
                      ),
                    ),
                    const SizedBox(height: 18),
                    _SetOption(
                      icon: Icons.home_outlined,
                      label: 'Home screen',
                      onTap: () => _setWallpaper(WallpaperTarget.home),
                      enabled: !isSetting,
                    ),
                    _SetOption(
                      icon: Icons.lock_outline,
                      label: 'Lock screen',
                      onTap: () => _setWallpaper(WallpaperTarget.lock),
                      enabled: !isSetting,
                    ),
                    _SetOption(
                      icon: Icons.phone_android,
                      label: 'Home screen and lock screen',
                      onTap: () => _setWallpaper(WallpaperTarget.both),
                      enabled: !isSetting,
                    ),
                  ],
                ),
              ),
            ),
          ),
        );
      },
    );
  }

  Future<void> _setWallpaper(WallpaperTarget target) async {
    Navigator.of(context).pop();
    setState(() => isSetting = true);

    final selectedWallpaper = wallpaper;

    _showApplyingDialog(target);

    try {
      await WallpaperSetter.setWallpaper(
        assetPath: selectedWallpaper.assetPath,
        target: target,
      );
      if (!mounted) return;
      Navigator.of(context, rootNavigator: true).pop();
      await WallpaperSetter.closeApp();
    } on PlatformException catch (error) {
      if (!mounted) return;
      Navigator.of(context, rootNavigator: true).pop();
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text(
            error.message ??
                'Add the image file to assets/wallpapers, then rebuild.',
          ),
        ),
      );
    } finally {
      if (mounted) {
        setState(() => isSetting = false);
      }
    }
  }

  void _showApplyingDialog(WallpaperTarget target) {
    showDialog<void>(
      context: context,
      barrierDismissible: false,
      builder: (context) {
        return PopScope(
          canPop: false,
          child: Dialog(
            backgroundColor: const Color(0xFF1C1D24),
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(8),
            ),
            child: Padding(
              padding: const EdgeInsets.fromLTRB(24, 26, 24, 24),
              child: Row(
                children: [
                  const SizedBox(
                    width: 28,
                    height: 28,
                    child: CircularProgressIndicator(strokeWidth: 3),
                  ),
                  const SizedBox(width: 20),
                  Expanded(
                    child: Text(
                      'Setting ${_targetLabel(target).toLowerCase()}...',
                      style: const TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.w800,
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
        );
      },
    );
  }

  String _targetLabel(WallpaperTarget target) {
    return switch (target) {
      WallpaperTarget.home => 'Home screen',
      WallpaperTarget.lock => 'Lock screen',
      WallpaperTarget.both => 'Home screen and lock screen',
    };
  }
}

class FullWallpaperPreviewPage extends StatefulWidget {
  const FullWallpaperPreviewPage({
    super.key,
    required this.initialIndex,
    required this.mode,
  });

  final int initialIndex;
  final PhonePreviewMode mode;

  @override
  State<FullWallpaperPreviewPage> createState() =>
      _FullWallpaperPreviewPageState();
}

class _FullWallpaperPreviewPageState extends State<FullWallpaperPreviewPage> {
  late final PageController _pageController;
  late int currentIndex;

  bool get isLock => widget.mode == PhonePreviewMode.lock;
  Wallpaper get wallpaper => wallpapers[currentIndex];

  @override
  void initState() {
    super.initState();
    currentIndex = widget.initialIndex;
    _pageController = PageController(initialPage: currentIndex);
  }

  @override
  void dispose() {
    _pageController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        fit: StackFit.expand,
        children: [
          PageView.builder(
            controller: _pageController,
            itemCount: wallpapers.length,
            onPageChanged: (index) => setState(() => currentIndex = index),
            itemBuilder: (context, index) {
              return WallpaperArtwork(wallpaper: wallpapers[index]);
            },
          ),
          DecoratedBox(
            decoration: BoxDecoration(
              gradient: LinearGradient(
                begin: Alignment.topCenter,
                end: Alignment.bottomCenter,
                colors: [
                  Colors.black.withValues(alpha: 0.30),
                  Colors.transparent,
                  Colors.black.withValues(alpha: isLock ? 0.28 : 0.12),
                ],
              ),
            ),
          ),
          SafeArea(
            child: Padding(
              padding: const EdgeInsets.fromLTRB(22, 12, 22, 24),
              child: isLock
                  ? _FullLockPreviewOverlay(
                      onClose: () => Navigator.of(context).pop(),
                    )
                  : _FullHomePreviewOverlay(
                      onClose: () => Navigator.of(context).pop(),
                    ),
            ),
          ),
        ],
      ),
    );
  }
}

class _FullLockPreviewOverlay extends StatelessWidget {
  const _FullLockPreviewOverlay({required this.onClose});

  final VoidCallback onClose;

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Row(
          children: [
            _RoundCloseButton(onTap: onClose),
            const Spacer(),
            const Icon(Icons.wifi, size: 20),
            const SizedBox(width: 8),
            const Icon(Icons.battery_full, size: 20),
          ],
        ),
        const SizedBox(height: 52),
        const Text(
          '12:43',
          style: TextStyle(
            fontSize: 78,
            height: 0.95,
            fontWeight: FontWeight.w900,
            shadows: [Shadow(color: Color(0x99000000), blurRadius: 16)],
          ),
        ),
        const SizedBox(height: 8),
        const Text(
          'Wednesday, May 13',
          style: TextStyle(
            fontSize: 20,
            fontWeight: FontWeight.w900,
            shadows: [Shadow(color: Color(0x99000000), blurRadius: 12)],
          ),
        ),
        const Spacer(),
        const _PreviewBadge(
          icon: Icons.lock_outline,
          label: 'Lock Screen Preview',
        ),
      ],
    );
  }
}

class _FullHomePreviewOverlay extends StatelessWidget {
  const _FullHomePreviewOverlay({required this.onClose});

  final VoidCallback onClose;

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Row(
          children: [
            const Text(
              '12:43',
              style: TextStyle(fontSize: 20, fontWeight: FontWeight.w900),
            ),
            const Spacer(),
            const Icon(Icons.wifi, size: 20),
            const SizedBox(width: 8),
            const Icon(Icons.battery_full, size: 20),
            const SizedBox(width: 12),
            _RoundCloseButton(onTap: onClose),
          ],
        ),
        const SizedBox(height: 38),
        const _HomeIconGrid(),
        const Spacer(),
        DecoratedBox(
          decoration: BoxDecoration(
            color: Colors.black.withValues(alpha: 0.28),
            borderRadius: BorderRadius.circular(30),
            border: Border.all(color: Colors.white.withValues(alpha: 0.14)),
          ),
          child: const Padding(
            padding: EdgeInsets.symmetric(horizontal: 24, vertical: 12),
            child: Row(
              mainAxisSize: MainAxisSize.min,
              children: [
                _HomeIcon(icon: Icons.call, compact: true),
                SizedBox(width: 18),
                _HomeIcon(icon: Icons.chat_bubble, compact: true),
                SizedBox(width: 18),
                _HomeIcon(icon: Icons.explore, compact: true),
                SizedBox(width: 18),
                _HomeIcon(icon: Icons.music_note, compact: true),
              ],
            ),
          ),
        ),
        const SizedBox(height: 14),
        const _PreviewBadge(icon: Icons.home, label: 'Home Screen Preview'),
      ],
    );
  }
}

class _HomeIconGrid extends StatelessWidget {
  const _HomeIconGrid();

  @override
  Widget build(BuildContext context) {
    const icons = [
      Icons.photo,
      Icons.camera_alt,
      Icons.schedule,
      Icons.settings,
      Icons.calendar_today,
      Icons.map,
      Icons.cloud,
      Icons.note,
      Icons.play_arrow,
      Icons.graphic_eq,
      Icons.forum,
      Icons.sports_esports,
    ];

    return Wrap(
      spacing: 22,
      runSpacing: 24,
      children: [for (final icon in icons) _HomeIcon(icon: icon)],
    );
  }
}

class _RoundCloseButton extends StatelessWidget {
  const _RoundCloseButton({required this.onTap});

  final VoidCallback onTap;

  @override
  Widget build(BuildContext context) {
    return _PressableScale(
      onTap: onTap,
      child: DecoratedBox(
        decoration: BoxDecoration(
          color: Colors.black.withValues(alpha: 0.36),
          shape: BoxShape.circle,
          border: Border.all(color: Colors.white.withValues(alpha: 0.14)),
        ),
        child: const SizedBox(
          width: 44,
          height: 44,
          child: Icon(Icons.close, size: 26),
        ),
      ),
    );
  }
}

enum PhonePreviewMode { lock, home }

class _PreviewModeButton extends StatelessWidget {
  const _PreviewModeButton({
    required this.icon,
    required this.label,
    required this.onTap,
    this.isSelected = false,
  });

  final IconData icon;
  final String label;
  final VoidCallback onTap;
  final bool isSelected;

  @override
  Widget build(BuildContext context) {
    final borderColor = isSelected
        ? const Color(0xFFFFB703)
        : Colors.white.withValues(alpha: 0.24);
    final fillColor = isSelected
        ? const Color(0xFFFFB703).withValues(alpha: 0.14)
        : Colors.white.withValues(alpha: 0.10);

    return _PressableScale(
      onTap: onTap,
      hoverScale: 1.02,
      pressedScale: 0.96,
      borderRadius: BorderRadius.circular(8),
      hoverDecoration: BoxDecoration(
        color: Colors.white.withValues(alpha: 0.11),
        borderRadius: BorderRadius.circular(8),
      ),
      pressedDecoration: BoxDecoration(
        color: Colors.white.withValues(alpha: 0.18),
        borderRadius: BorderRadius.circular(8),
      ),
      child: DecoratedBox(
        decoration: BoxDecoration(
          color: fillColor,
          border: Border.all(color: borderColor, width: isSelected ? 2 : 1),
          borderRadius: BorderRadius.circular(8),
        ),
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 13),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Icon(icon, size: 17, color: const Color(0xFFE4E7F0)),
              const SizedBox(width: 6),
              Flexible(
                child: Text(
                  label,
                  maxLines: 1,
                  overflow: TextOverflow.ellipsis,
                  style: const TextStyle(
                    color: Color(0xFFE4E7F0),
                    fontSize: 12,
                    fontWeight: FontWeight.w900,
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class _PreviewBadge extends StatelessWidget {
  const _PreviewBadge({required this.icon, required this.label});

  final IconData icon;
  final String label;

  @override
  Widget build(BuildContext context) {
    return DecoratedBox(
      decoration: BoxDecoration(
        color: Colors.black.withValues(alpha: 0.44),
        borderRadius: BorderRadius.circular(18),
      ),
      child: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 14, vertical: 8),
        child: Row(
          mainAxisSize: MainAxisSize.min,
          children: [
            Icon(icon, size: 15, color: Colors.white),
            const SizedBox(width: 7),
            Text(
              label,
              style: const TextStyle(fontSize: 13, fontWeight: FontWeight.w900),
            ),
          ],
        ),
      ),
    );
  }
}

class _HomeIcon extends StatelessWidget {
  const _HomeIcon({required this.icon, this.compact = false});

  final IconData icon;
  final bool compact;

  @override
  Widget build(BuildContext context) {
    final size = compact ? 34.0 : 52.0;
    return DecoratedBox(
      decoration: BoxDecoration(
        color: Colors.white.withValues(alpha: 0.22),
        borderRadius: BorderRadius.circular(compact ? 12 : 16),
      ),
      child: SizedBox(
        width: size,
        height: size,
        child: Icon(icon, size: compact ? 19 : 27, color: Colors.white),
      ),
    );
  }
}

class _SetOption extends StatelessWidget {
  const _SetOption({
    required this.icon,
    required this.label,
    required this.onTap,
    this.enabled = true,
  });

  final IconData icon;
  final String label;
  final VoidCallback onTap;
  final bool enabled;

  @override
  Widget build(BuildContext context) {
    return _PressableScale(
      onTap: enabled ? onTap : null,
      hoverScale: 1.01,
      pressedScale: 0.97,
      borderRadius: BorderRadius.circular(12),
      hoverDecoration: BoxDecoration(
        color: const Color(0xFFFFB703).withValues(alpha: 0.10),
        borderRadius: BorderRadius.circular(12),
        border: Border.all(
          color: const Color(0xFFFFB703).withValues(alpha: 0.50),
          width: 1.5,
        ),
      ),
      pressedDecoration: BoxDecoration(
        color: const Color(0xFFFFB703).withValues(alpha: 0.18),
        borderRadius: BorderRadius.circular(12),
        border: Border.all(color: const Color(0xFFFFB703), width: 1.5),
      ),
      child: Opacity(
        opacity: enabled ? 1 : 0.45,
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 14, vertical: 16),
          child: Row(
            children: [
              Container(
                width: 46,
                height: 46,
                decoration: BoxDecoration(
                  color: const Color(0xFFFFB703).withValues(alpha: 0.14),
                  borderRadius: BorderRadius.circular(12),
                ),
                child: Icon(icon, size: 24, color: const Color(0xFFFFB703)),
              ),
              const SizedBox(width: 18),
              Expanded(
                child: Text(
                  label,
                  style: const TextStyle(
                    fontSize: 18,
                    fontWeight: FontWeight.w700,
                    color: Color(0xFFE4E7F0),
                  ),
                ),
              ),
              const Icon(
                Icons.chevron_right,
                color: Color(0xFF6B7280),
                size: 22,
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class _PressableScale extends StatefulWidget {
  const _PressableScale({
    required this.child,
    required this.onTap,
    this.hoverScale = 1,
    this.pressedScale = 0.96,
    this.borderRadius,
    this.hoverDecoration,
    this.pressedDecoration,
  });

  final Widget child;
  final VoidCallback? onTap;
  final double hoverScale;
  final double pressedScale;
  final BorderRadius? borderRadius;
  final BoxDecoration? hoverDecoration;
  final BoxDecoration? pressedDecoration;

  @override
  State<_PressableScale> createState() => _PressableScaleState();
}

class _PressableScaleState extends State<_PressableScale> {
  bool isPressed = false;
  bool isHovered = false;

  @override
  Widget build(BuildContext context) {
    final enabled = widget.onTap != null;
    final scale = isPressed
        ? widget.pressedScale
        : isHovered && enabled
        ? widget.hoverScale
        : 1.0;
    final decoration = isPressed
        ? widget.pressedDecoration
        : isHovered && enabled
        ? widget.hoverDecoration
        : null;

    return MouseRegion(
      cursor: enabled ? SystemMouseCursors.click : SystemMouseCursors.basic,
      onEnter: (_) => setState(() => isHovered = true),
      onExit: (_) => setState(() {
        isHovered = false;
        isPressed = false;
      }),
      child: GestureDetector(
        behavior: HitTestBehavior.opaque,
        onTapDown: !enabled
            ? null
            : (_) {
                HapticFeedback.selectionClick();
                setState(() => isPressed = true);
              },
        onTapCancel: () => setState(() => isPressed = false),
        onTapUp: (_) => setState(() => isPressed = false),
        onTap: widget.onTap,
        child: AnimatedScale(
          scale: scale,
          duration: const Duration(milliseconds: 100),
          curve: Curves.easeOut,
          child: AnimatedOpacity(
            opacity: isPressed
                ? 0.72
                : isHovered && enabled
                ? 0.9
                : 1,
            duration: const Duration(milliseconds: 100),
            child: AnimatedContainer(
              duration: const Duration(milliseconds: 120),
              decoration: decoration,
              clipBehavior: decoration == null || widget.borderRadius == null
                  ? Clip.none
                  : Clip.antiAlias,
              child: widget.child,
            ),
          ),
        ),
      ),
    );
  }
}
