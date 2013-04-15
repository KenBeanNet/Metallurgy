package shadow.mods.metallurgy.nether;

import java.io.File;
import java.io.IOException;
import net.minecraftforge.common.Configuration;

public class ConfigNether
{
    public static int NetherMetalsVeinID;
    public static int NetherMetalsBrickID;
    public static int NetherCatalystVeinID;
    public static int NetherCatalystBrickID;
    public static int oresBlockID;
    public static int alloysBlockID;
    public static int NetherAlloysBrickID;
    public static int furnaceID;
    public static boolean[] alloyEnabled = new boolean[3];
    public static boolean[] metalEnabled = new boolean[10];
    public static boolean furnaceEnabled;
    public static boolean midasiumEnabled;
    public static float[] speeds = new float[8];
    public static int[] buckets = new int[8];
    public static int[] VeinCount = new int[10];
    public static int[] OreCount = new int[10];
    public static int[] OreHeight = new int[10];
    public static int[] OreMinHeight = new int[10];
    public static int ObsidianVeinCount;
    public static int ObsidianOreCount;
    public static int ObsidianOreHeight;
    public static int ItemStartID;
    public static int ItemCatalystStartID;
    public static int[] alloyItemIds = new int[3];
    public static int[] metalItemIds = new int[10];
    public static String dimensions;
    public static boolean smelterDropsLava;

    public static void init()
    {
        File var0 = new File(MetallurgyNether.proxy.getMinecraftDir() + "/config/Metallurgy");
        var0.mkdir();
        File var1 = new File(MetallurgyNether.proxy.getMinecraftDir() + "/config/Metallurgy/MetallurgyNether.cfg");

        try
        {
            var1.createNewFile();
            System.out.println("Successfully created/read configuration file");
        }
        catch (IOException var4)
        {
            System.out.println("Could not create configuration file for mod_MetallugyBase. Reason:");
            System.out.println(var4);
        }

        Configuration var2 = new Configuration(var1);
        var2.load();
        NetherMetalsVeinID = var2.getBlock("Metal Ore", 915).getInt(915);
        NetherMetalsBrickID = var2.getBlock("Metal Brick", 916).getInt(916);
        NetherCatalystVeinID = var2.getBlock("Catalyst Ore", 924).getInt(924);
        NetherCatalystBrickID = var2.getBlock("Catalyst Brick", 925).getInt(925);
        oresBlockID = var2.getBlock("Metal Block", 931).getInt(931);
        alloysBlockID = var2.getBlock("Alloy Block", 932).getInt(931);
        NetherAlloysBrickID = var2.getBlock("Alloy Brick", 917).getInt(917);
        furnaceID = var2.getBlock("Nether Smelter", 918).getInt(918);
        dimensions = var2.get("Dimensions", "Dimensions", "-1").value;
        int var3;

        for (var3 = 0; var3 < 3; ++var3)
        {
            alloyEnabled[var3] = var2.get("Ores", AlloyNetherEnum.names[var3] + " Enabled", true).getBoolean(true);
        }

        for (var3 = 0; var3 < 10; ++var3)
        {
            metalEnabled[var3] = var2.get("Ores", OreNetherEnum.names[var3] + " Enabled", true).getBoolean(true);
        }

        furnaceEnabled = var2.get("Options", "Enable Smelters", true).getBoolean(true);
        midasiumEnabled = var2.get("Options", "Enable Midasium Recipes", true).getBoolean(true);
        speeds[0] = (float)var2.get("Furnace Speed", "Ignatius", 6000).getInt(6000) / 1000.0F;
        speeds[1] = (float)var2.get("Furnace Speed", "ShadowIron", 5500).getInt(5500) / 1000.0F;
        speeds[2] = (float)var2.get("Furnace Speed", "ShadowSteel", 5000).getInt(5000) / 1000.0F;
        speeds[3] = (float)var2.get("Furnace Speed", "Vyroxeres", 4500).getInt(4500) / 1000.0F;
        speeds[4] = (float)var2.get("Furnace Speed", "Inolashite", 4000).getInt(4000) / 1000.0F;
        speeds[5] = (float)var2.get("Furnace Speed", "Kalendrite", 3500).getInt(3500) / 1000.0F;
        speeds[6] = (float)var2.get("Furnace Speed", "Vulcanite", 3000).getInt(3000) / 1000.0F;
        speeds[7] = (float)var2.get("Furnace Speed", "Sanguinite", 2000).getInt(2000) / 1000.0F;
        buckets[0] = var2.get("Furnace Buckets", "Ignatius", 10).getInt(10);
        buckets[1] = var2.get("Furnace Buckets", "ShadowIron", 20).getInt(20);
        buckets[2] = var2.get("Furnace Buckets", "ShadowSteel", 30).getInt(30);
        buckets[3] = var2.get("Furnace Buckets", "Vyroxeres", 40).getInt(40);
        buckets[4] = var2.get("Furnace Buckets", "Inolashite", 50).getInt(50);
        buckets[5] = var2.get("Furnace Buckets", "Kalendrite", 60).getInt(60);
        buckets[6] = var2.get("Furnace Buckets", "Vulcanite", 80).getInt(80);
        buckets[7] = var2.get("Furnace Buckets", "Sanguinite", 100).getInt(100);
        ItemStartID = var2.get("Item Ids Uses next 550", "Item Start IDs", 27000).getInt(27000);
        smelterDropsLava = var2.get("Options", "Smelters leave lava", false).getBoolean(false);

        for (var3 = 0; var3 < 8; ++var3)
        {
            metalItemIds[var3] = var2.get("Item Ids uses next 50", OreNetherEnum.names[var3] + " item ids", 27000 + var3 * 50).getInt(27000 + var3 * 50);
        }

        for (var3 = 0; var3 < 3; ++var3)
        {
            alloyItemIds[var3] = var2.get("Item Ids uses next 50", AlloyNetherEnum.names[var3] + " item ids", 27400 + var3 * 50).getInt(27400 + var3 * 50);
        }

        for (var3 = 0; var3 < 2; ++var3)
        {
            metalItemIds[var3 + 8] = var2.get("Item Ids uses next 2", OreNetherEnum.names[var3 + 8] + " item ids", 27098 + var3 * 200).getInt(27098 + var3 * 200);
        }

        ItemCatalystStartID = var2.get("Item Ids Uses next 100", "Item Catalyst Start IDs", 28500).getInt(28500);

        for (var3 = 0; var3 < 10; ++var3)
        {
            VeinCount[var3] = var2.get("Ore Generation", OreNetherEnum.names[var3] + " Vein Count", OreNetherEnum.defaultVeinCount[var3]).getInt(OreNetherEnum.defaultVeinCount[var3]);
            OreCount[var3] = var2.get("Ore Generation", OreNetherEnum.names[var3] + " Ore Count", OreNetherEnum.defaultOreCount[var3]).getInt(OreNetherEnum.defaultOreCount[var3]);
            OreHeight[var3] = var2.get("Ore Generation", OreNetherEnum.names[var3] + " Height", OreNetherEnum.defaultOreHeight[var3]).getInt(OreNetherEnum.defaultOreHeight[var3]);
            OreMinHeight[var3] = var2.get("Ore Generation", OreNetherEnum.names[var3] + " Minimum Height", 0).getInt(0);
        }

        ObsidianVeinCount = var2.get("Ore Generation", "Obsidian Vein Count", 1).getInt(1);
        ObsidianOreCount = var2.get("Ore Generation", "Obsidian Ore Count", 72).getInt(72);
        ObsidianOreHeight = var2.get("Ore Generation", "Obsidian Height", 128).getInt(128);
        var2.save();
    }
}
