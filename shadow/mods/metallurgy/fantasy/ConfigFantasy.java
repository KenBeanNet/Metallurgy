package shadow.mods.metallurgy.fantasy;

import java.io.File;
import java.io.IOException;
import net.minecraftforge.common.Configuration;

public class ConfigFantasy
{
    public static int FantasyMetalsVeinID;
    public static int FantasyMetalsBrickID;
    public static int FantasyAlloysBrickID;
    public static int oresBlockID;
    public static int alloysBlockID;
    public static int furnaceID;
    public static boolean[] alloyEnabled = new boolean[5];
    public static boolean[] metalEnabled = new boolean[12];
    public static boolean furnaceEnabled;
    public static float[] extractorSpeeds = new float[11];
    public static int[] xpBonus = new int[11];
    public static int ItemStartID;
    public static int[] VeinCount = new int[12];
    public static int[] OreCount = new int[12];
    public static int[] OreHeight = new int[12];
    public static int[] OreMinHeight = new int[12];
    public static int dimensionID;
    public static String dimensions;

    public static void init()
    {
        File var0 = new File(MetallurgyFantasy.proxy.getMinecraftDir() + "/config/Metallurgy");
        var0.mkdir();
        File var1 = new File(MetallurgyFantasy.proxy.getMinecraftDir() + "/config/Metallurgy/MetallurgyFantasy.cfg");

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
        furnaceEnabled = var2.get("Enable Abstractors", "Machines", true).getBoolean(true);
        FantasyMetalsVeinID = var2.getBlock("Metal Ore", 919).getInt(919);
        FantasyMetalsBrickID = var2.getBlock("Metal Brick", 920).getInt(920);
        FantasyAlloysBrickID = var2.getBlock("Alloy Brick", 921).getInt(921);
        oresBlockID = var2.getBlock("Metal Block", 933).getInt(933);
        alloysBlockID = var2.getBlock("Alloy Block", 934).getInt(934);
        furnaceID = var2.getBlock("Abstractor", 922).getInt(922);
        dimensions = var2.get("Dimensions", "Dimensions", "0 2-100000").value;
        extractorSpeeds[0] = (float)var2.get("Abstractor Speeds", "Prometheum", 22000).getInt(22000) / 1000.0F;
        extractorSpeeds[1] = (float)var2.get("Abstractor Speeds", "DeepIron", 20000).getInt(20000) / 1000.0F;
        extractorSpeeds[2] = (float)var2.get("Abstractor Speeds", "BlackSteel", 18000).getInt(18000) / 1000.0F;
        extractorSpeeds[3] = (float)var2.get("Abstractor Speeds", "Oureclase", 16000).getInt(16000) / 1000.0F;
        extractorSpeeds[4] = (float)var2.get("Abstractor Speeds", "Aredrite", 14000).getInt(14000) / 1000.0F;
        extractorSpeeds[5] = (float)var2.get("Abstractor Speeds", "Mithril", 12000).getInt(12000) / 1000.0F;
        extractorSpeeds[6] = (float)var2.get("Abstractor Speeds", "Haderoth", 10000).getInt(10000) / 1000.0F;
        extractorSpeeds[7] = (float)var2.get("Abstractor Speeds", "Orichalcum", 8000).getInt(8000) / 1000.0F;
        extractorSpeeds[8] = (float)var2.get("Abstractor Speeds", "Adamantine", 6000).getInt(6000) / 1000.0F;
        extractorSpeeds[9] = (float)var2.get("Abstractor Speeds", "Atlarus", 4000).getInt(4000) / 1000.0F;
        extractorSpeeds[10] = (float)var2.get("Abstractor Speeds", "Tartarite", 2000).getInt(2000) / 1000.0F;
        xpBonus[0] = var2.get("Abstractor Bonus", "Prometheum", 10).getInt(10);
        xpBonus[1] = var2.get("Abstractor Bonus", "DeepIron", 12).getInt(12);
        xpBonus[2] = var2.get("Abstractor Bonus", "BlackSteel", 14).getInt(14);
        xpBonus[3] = var2.get("Abstractor Bonus", "Oureclase", 16).getInt(16);
        xpBonus[4] = var2.get("Abstractor Bonus", "Aredrite", 18).getInt(18);
        xpBonus[5] = var2.get("Abstractor Bonus", "Mithril", 20).getInt(20);
        xpBonus[6] = var2.get("Abstractor Bonus", "Haderoth", 24).getInt(24);
        xpBonus[7] = var2.get("Abstractor Bonus", "Orichalcum", 28).getInt(28);
        xpBonus[8] = var2.get("Abstractor Bonus", "Adamantine", 32).getInt(32);
        xpBonus[9] = var2.get("Abstractor Bonus", "Atlarus", 36).getInt(36);
        xpBonus[10] = var2.get("Abstractor Bonus", "Tartarite", 40).getInt(40);
        int var3;

        for (var3 = 0; var3 < AlloyFantasyEnum.numMetals; ++var3)
        {
            alloyEnabled[var3] = var2.get("Ores", AlloyFantasyEnum.names[var3] + " Enabled", true).getBoolean(true);
        }

        for (var3 = 0; var3 < 12; ++var3)
        {
            metalEnabled[var3] = var2.get("Ores", OreFantasyEnum.names[var3] + " Enabled", true).getBoolean(true);
        }

        ItemStartID = var2.getItem("Item Ids Uses next 850", "Item Start IDs", 27550).getInt(27550);

        for (var3 = 0; var3 < 12; ++var3)
        {
            VeinCount[var3] = var2.get("Ore Generation", OreFantasyEnum.names[var3] + " Vein Count", OreFantasyEnum.defaultVeinCount[var3]).getInt(OreFantasyEnum.defaultVeinCount[var3]);
            OreCount[var3] = var2.get("Ore Generation", OreFantasyEnum.names[var3] + " Ore Count", OreFantasyEnum.defaultOreCount[var3]).getInt(OreFantasyEnum.defaultOreCount[var3]);
            OreHeight[var3] = var2.get("Ore Generation", OreFantasyEnum.names[var3] + " Height", OreFantasyEnum.defaultOreHeight[var3]).getInt(OreFantasyEnum.defaultOreHeight[var3]);
            OreMinHeight[var3] = var2.get("Ore Generation", OreFantasyEnum.names[var3] + " Minimum Height", 0).getInt(0);
        }

        var2.save();
    }
}
