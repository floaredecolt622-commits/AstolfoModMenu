package com.example.astolfomod;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class AstolfoMenuScreen extends Screen {
    private final List<AstolfoToggle> toggles = new ArrayList<>();
    private final List<AstolfoTheme> themes = new ArrayList<>();

    protected AstolfoMenuScreen() {
        super(Text.of("Astolfo Mod Menu"));
        buildModules();
        buildThemes();
    }

    private void buildModules() {
        String[] names = new String[] {
            "Fullbright", "No Water Fog", "No Lava Fog", "Auto Sprint", "Zoom", "Night Vision",
            "FPS Counter", "Armor HUD", "Coords HUD", "Chat Tweaks", "Compact HUD", "Jump Assist",
            "Better Crosshair", "Item Names", "Drop Counter", "Ping HUD", "Mini Map", "Chunk Grid",
            "Waypoints", "Potion HUD", "Skin Preview", "Scoreboard Cleaner", "Boss Bar Slim", "Tab List Tweaks",
            "FreeLook", "Camera Boost", "Fast Sneak", "Sprint Reset", "Cinematic Mode", "Quick Equip",
            "Low Fire", "Clean GUI", "No Rain", "Fast Tool", "Safe Break", "Quick Craft", "Recipe Glow",
            "Particle Purge", "Hit Marker", "Cooldown HUD", "Item Glow", "Pickaxe Swing", "Damage Text", "Recipe Search",
            "Glow Overlay", "Entity Labels", "Villager Info", "Chunk Finder", "Biome Highlighter", "Stronghold Radar",
            "Portal Tracker", "Base Finder", "Ore Glint", "Loot Visuals", "Treasure Guide", "Fortress Map",
            "Nether Glow", "Mushroom Finder", "Hidden Lures", "Fishing Info", "Skull Popups", "Mob Radar",
            "Hopper Helper", "Beacon HUD", "Rail Helper", "Map Filter", "Dungeon Guide", "Rarity Tags",
            "Vignette Off", "Dirt Color Fix", "Menu Glow", "Volume Mixer", "Screen Shake", "Toast Cleaner",
            "Quick Search", "Inventory Sort", "Hotbar Glow", "Command Queue", "Auto Sort", "Widget Fade",
            "Soft Shading", "Menu Accent", "HUD Pulse", "Glass Panels", "Soft Blur", "UI Noise"
        };

        for (int i = 0; i < names.length; i++) {
            toggles.add(new AstolfoToggle(names[i], i % 3 != 0));
        }
    }

    private void buildThemes() {
        themes.add(new AstolfoTheme("Red"));
        themes.add(new AstolfoTheme("Black"));
        themes.add(new AstolfoTheme("Blue"));
        themes.add(new AstolfoTheme("Galaxy"));
        themes.add(new AstolfoTheme("Pink"));
        themes.add(new AstolfoTheme("Neon"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        int width = context.getScaledWindowWidth();
        int height = context.getScaledWindowHeight();

        context.fill(0, 0, width, height, 0xA3000000);

        context.fill(24, 18, width - 24, 92, 0xB1121212);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.of("ASTOLFO MOD MENU"), width / 2, 42, 0xFFE7A5FF);

        int leftX = 26;
        int leftY = 110;
        int leftW = 225;
        int leftH = height - 146;
        context.fill(leftX, leftY, leftX + leftW, leftY + leftH, 0xB61A1A1A);
        context.drawTextWithShadow(this.textRenderer, Text.of("HUD Themes"), leftX + 16, leftY + 18, 0xFFFFFFFF);

        int themeY = leftY + 46;
        for (AstolfoTheme theme : themes) {
            context.fill(leftX + 14, themeY, leftX + leftW - 14, themeY + 27, 0x4D2A2A2A);
            context.drawTextWithShadow(this.textRenderer, Text.of(theme.name), leftX + 28, themeY + 7, 0xFFE9E9E9);
            themeY += 33;
        }

        int mainX = 270;
        int mainY = 110;
        int mainW = width - 310;
        int mainH = height - 146;
        context.fill(mainX, mainY, mainX + mainW, mainY + mainH, 0xB7111111);

        int toggleX = mainX + 18;
        int toggleY = mainY + 20;
        int slotW = 122;
        int slotH = 52;

        for (int i = 0; i < toggles.size(); i++) {
            AstolfoToggle toggle = toggles.get(i);
            int x = toggleX + (i % 6) * (slotW + 12);
            int y = toggleY + (i / 6) * (slotH + 12);

            if (x + slotW > mainX + mainW - 14) break;

            context.fill(x, y, x + slotW, y + slotH, toggle.enabled ? 0xCC2E7D32 : 0xCC4A4A4A);
            context.drawTextWithShadow(this.textRenderer, Text.of(toggle.name), x + 8, y + 16, 0xFFFFFFFF);
            context.drawTextWithShadow(this.textRenderer, Text.of(toggle.enabled ? "ON" : "OFF"), x + 8, y + 31, 0xFFE4E4E4);
        }

        int imgX = width - 176;
        int imgY = 30;
        context.fill(imgX, imgY, imgX + 140, imgY + 140, 0x34FFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.of("A"), imgX + 70, imgY + 62, 0xFFFFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.of("Astolfo"), imgX + 70, imgY + 100, 0xFFC7B6FF);

        context.drawTextWithShadow(this.textRenderer, Text.of("Press K to open | a huge parody menu for testing"), 30, height - 28, 0xFFCBCBCB);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
