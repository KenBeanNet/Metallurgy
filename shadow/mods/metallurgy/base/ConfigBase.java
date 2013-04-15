package shadow.mods.metallurgy.base;

import java.io.File;
import java.io.IOException;
import net.minecraftforge.common.Configuration;

public class ConfigBase
{
    public static int baseMetalsVeinID;
    public static int baseMetalsBrickID;
    public static int baseAlloysBrickID;
    public static int baseMetalsBlockID;
    public static int baseAlloysBlockID;
    public static int itemDoorID;
    public static int doorID;
    public static int furnaceID;
    public static boolean[] alloyEnabled = new boolean[5];
    public static boolean[] metalEnabled = new boolean[3];
    public static boolean furnacesEnabled;
    public static boolean railsEnabled;
    public static double copperSpeed;
    public static double bronzeSpeed;
    public static double ironSpeed;
    public static double steelSpeed;
    public static int ItemStartID;
    public static int[] VeinCount = new int[8];
    public static int[] OreCount = new int[8];
    public static int[] OreHeight = new int[8];
    public static int[] OreMinHeight = new int[8];
    public static String dimensions;
    public static int balkonsIDs;
    public static int lanternId;
    public static int ladderId;
    public static int glassDustId;
    public static int coloredGlassId;

    public static void init()
    {
        File var0 = new File(MetallurgyBaseMetals.proxy.getMinecraftDir() + "/config/Metallurgy");
        var0.mkdir();
        File var1 = new File(MetallurgyBaseMetals.proxy.getMinecraftDir() + "/config/Metallurgy/MetallurgyBase.cfg");

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
        baseMetalsVeinID = var2.getBlock("Metal Ore", 901).getInt(900);
        baseMetalsBrickID = var2.getBlock("Metal Brick", 902).getInt(901);
        baseAlloysBrickID = var2.getBlock("Alloy Brick", 903).getInt(902);
        baseMetalsBlockID = var2.getBlock("Metal Block", 929).getInt(929);
        baseAlloysBlockID = var2.getBlock("Alloy Block", 930).getInt(930);
        doorID = var2.getBlock("Copper Door", 935).getInt(935);
        furnaceID = var2.getBlock("Furnace", 904).getInt(904);
        itemDoorID = var2.getItem("Copper Door", 926).getInt(9350);
        dimensions = var2.get("Dimensions", "Dimensions", "0 2-10000").value;
        furnacesEnabled = var2.get("Machines", "Enable Furnaces", true).getBoolean(true);
        railsEnabled = var2.get("Machines", "Enable Rail Recipes", true).getBoolean(true);
        copperSpeed = (double)((float)var2.get("Furnaces", "Copper Speed", 9500).getInt(9500) / 1000.0F);
        bronzeSpeed = (double)((float)var2.get("Furnaces", "Bronze Speed", 9000).getInt(9000) / 1000.0F);
        ironSpeed = (double)((float)var2.get("Furnaces", "Iron Speed", 8000).getInt(8000) / 1000.0F);
        steelSpeed = (double)((float)var2.get("Furnaces", "Steel Speed", 7000).getInt(7000) / 1000.0F);
        int var3;

        for (var3 = 0; var3 < 5; ++var3)
        {
            alloyEnabled[var3] = var2.get("Ores", AlloyBaseEnum.names[var3] + " Enabled", true).getBoolean(true);
        }

        for (var3 = 0; var3 < 3; ++var3)
        {
            metalEnabled[var3] = var2.get("Ores", OreBaseEnum.names[var3] + " Enabled", true).getBoolean(true);
        }

        ItemStartID = var2.get("Item Ids Uses next 250", "Item Start IDs", 26250).getInt(26250);
        balkonsIDs = var2.get("Balkons Ids Uses next 36", "Balkons Start IDs", 29000).getInt(29000);
        lanternId = var2.getBlock("Lantern", 2016).getInt(2016);
        ladderId = var2.getBlock("Ladder", 2011).getInt(2011);
        glassDustId = var2.getItem("Glass Dust", 2012).getInt(2012);
        coloredGlassId = var2.getBlock("Colored Glass", 2013).getInt(2013);

        for (var3 = 0; var3 < 3; ++var3)
        {
            VeinCount[var3] = var2.get("Ore Generation", OreBaseEnum.names[var3] + " Vein Count", OreBaseEnum.defaultVeinCount[var3]).getInt(OreBaseEnum.defaultVeinCount[var3]);
            OreCount[var3] = var2.get("Ore Generation", OreBaseEnum.names[var3] + " Ore Count", OreBaseEnum.defaultOreCount[var3]).getInt(OreBaseEnum.defaultOreCount[var3]);
            OreHeight[var3] = var2.get("Ore Generation", OreBaseEnum.names[var3] + " Height", OreBaseEnum.defaultOreHeight[var3]).getInt(OreBaseEnum.defaultOreHeight[var3]);
            OreMinHeight[var3] = var2.get("Ore Generation", OreBaseEnum.names[var3] + " Minimum Height", 0).getInt(0);
        }

        var2.save();
    }
}
