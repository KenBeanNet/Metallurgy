package shadow.mods.metallurgy.ender;

import java.io.File;
import java.io.IOException;
import net.minecraftforge.common.Configuration;

public class ConfigEnder
{
    public static int EnderMetalsVeinID;
    public static int EnderMetalsBrickID;
    public static int EnderAlloysBrickID;
    public static int oresBlockID;
    public static int alloysBlockID;
    public static boolean[] alloyEnabled = new boolean[1];
    public static boolean[] metalEnabled = new boolean[2];
    public static int[] VeinCount = new int[2];
    public static int[] OreCount = new int[2];
    public static int[] OreHeight = new int[2];
    public static int[] OreMinHeight = new int[2];
    public static int[] alloyItemIds = new int[1];
    public static int[] metalItemIds = new int[2];
    public static String dimensions;

    public static void init()
    {
        File var0 = new File(MetallurgyEnder.proxy.getMinecraftDir() + "/config/Metallurgy");
        var0.mkdir();
        File var1 = new File(MetallurgyEnder.proxy.getMinecraftDir() + "/config/Metallurgy/MetallurgyEnder.cfg");

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
        EnderMetalsVeinID = var2.getBlock("Metal Ore", 924).getInt(924);
        EnderMetalsBrickID = var2.getBlock("Metal Brick", 925).getInt(925);
        EnderAlloysBrickID = var2.getBlock("Alloy Brick", 926).getInt(926);
        oresBlockID = var2.getBlock("Metal Block", 937).getInt(937);
        alloysBlockID = var2.getBlock("Alloy Block", 938).getInt(938);
        dimensions = var2.get("Dimensions", "Dimensions", "1").value;
        int var3;

        for (var3 = 0; var3 < 1; ++var3)
        {
            alloyEnabled[var3] = var2.get("Ores", AlloyEnderEnum.names[var3] + " Enabled", true).getBoolean(true);
        }

        for (var3 = 0; var3 < 2; ++var3)
        {
            metalEnabled[var3] = var2.get("Ores", OreEnderEnum.names[var3] + " Enabled", true).getBoolean(true);
        }

        for (var3 = 0; var3 < 2; ++var3)
        {
            metalItemIds[var3] = var2.get("Item Ids uses next 50", OreEnderEnum.names[var3] + " item ids", 28500 + var3 * 50).getInt(28500 + var3 * 50);
        }

        for (var3 = 0; var3 < 1; ++var3)
        {
            alloyItemIds[var3] = var2.get("Item Ids uses next 50", AlloyEnderEnum.names[var3] + " item ids", 28600 + var3 * 50).getInt(28600 + var3 * 50);
        }

        for (var3 = 0; var3 < 2; ++var3)
        {
            VeinCount[var3] = var2.get("Ore Generation", OreEnderEnum.names[var3] + " Vein Count", OreEnderEnum.defaultVeinCount[var3]).getInt(OreEnderEnum.defaultVeinCount[var3]);
            OreCount[var3] = var2.get("Ore Generation", OreEnderEnum.names[var3] + " Ore Count", OreEnderEnum.defaultOreCount[var3]).getInt(OreEnderEnum.defaultOreCount[var3]);
            OreHeight[var3] = var2.get("Ore Generation", OreEnderEnum.names[var3] + " Height", OreEnderEnum.defaultOreHeight[var3]).getInt(OreEnderEnum.defaultOreHeight[var3]);
            OreMinHeight[var3] = var2.get("Ore Generation", OreEnderEnum.names[var3] + " Minimum Height", 0).getInt(0);
        }

        var2.save();
    }
}
