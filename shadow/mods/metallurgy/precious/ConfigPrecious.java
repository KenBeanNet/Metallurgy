package shadow.mods.metallurgy.precious;

import java.io.File;
import java.io.IOException;
import net.minecraftforge.common.Configuration;

public class ConfigPrecious
{
    public static int PreciousMetalsVeinID;
    public static int PreciousMetalsBrickID;
    public static int PreciousAlloysBrickID;
    public static int oresBlockID;
    public static int alloysBlockID;
    public static int PreciousChestID;
    public static int PreciousMintID;
    public static int PreciousMintLoaderID;
    public static boolean[] alloyEnabled = new boolean[2];
    public static boolean[] metalEnabled = new boolean[3];
    public static boolean chestsEnabled;
    public static boolean tradesEnabled;
    public static boolean mintEnabled;
    public static int ItemStartID;
    public static int[] VeinCount = new int[3];
    public static int[] OreCount = new int[3];
    public static int[] OreHeight = new int[3];
    public static int[] OreMinHeight = new int[3];
    public static String dimensions;

    public static void init()
    {
        File var0 = new File(MetallurgyPrecious.proxy.getMinecraftDir() + "/config/Metallurgy");
        var0.mkdir();
        File var1 = new File(MetallurgyPrecious.proxy.getMinecraftDir() + "/config/Metallurgy/MetallurgyPrecious.cfg");

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
        int var3;

        for (var3 = 0; var3 < 2; ++var3)
        {
            alloyEnabled[var3] = var2.get(AlloyPreciousEnum.name[var3] + " Enabled", "Ores", true).getBoolean(true);
        }

        for (var3 = 0; var3 < 3; ++var3)
        {
            metalEnabled[var3] = var2.get(OrePreciousEnum.name[var3] + " Enabled", "Ores", true).getBoolean(true);
        }

        PreciousMetalsVeinID = var2.getBlock("Precious Metal Ore", 910).getInt(910);
        PreciousMetalsBrickID = var2.getBlock("Precious Metal Brick", 911).getInt(911);
        PreciousAlloysBrickID = var2.getBlock("Precious Alloy Brick", 912).getInt(912);
        oresBlockID = var2.getBlock("-_-", 935).getInt(935);
        alloysBlockID = var2.getBlock("Alloy Block", 936).getInt(936);
        PreciousChestID = var2.getBlock("Chest", 913).getInt(913);
        PreciousMintID = var2.getBlock("Mint", 914).getInt(914);
        PreciousMintLoaderID = var2.getBlock("Mint auto ", 925).getInt(925);
        chestsEnabled = var2.get("Options", "Enable Chests", true).getBoolean(true);
        tradesEnabled = var2.get("Options", "Enable Trades", true).getBoolean(true);
        mintEnabled = var2.get("Options", "Enable Mint", true).getBoolean(true);
        dimensions = var2.get("Dimensions", "Dimensions", "0 2-100000").value;
        ItemStartID = var2.get("Item Ids Uses next 250", "Item Start IDs", 26750).getInt(26750);

        for (var3 = 0; var3 < 3; ++var3)
        {
            VeinCount[var3] = var2.get("Ore Generation", OrePreciousEnum.name[var3] + " Vein Count", OrePreciousEnum.defaultVeinCount[var3]).getInt(OrePreciousEnum.defaultVeinCount[var3]);
            OreCount[var3] = var2.get("Ore Generation", OrePreciousEnum.name[var3] + " Ore Count", OrePreciousEnum.defaultOreCount[var3]).getInt(OrePreciousEnum.defaultOreCount[var3]);
            OreHeight[var3] = var2.get("Ore Generation", OrePreciousEnum.name[var3] + " Height", OrePreciousEnum.defaultOreHeight[var3]).getInt(OrePreciousEnum.defaultOreHeight[var3]);
            OreMinHeight[var3] = var2.get("Ore Generation", OrePreciousEnum.name[var3] + " Minimum Height", 0).getInt(0);
        }

        var2.save();
    }
}
