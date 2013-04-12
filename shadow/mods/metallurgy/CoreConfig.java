package shadow.mods.metallurgy;

import java.io.File;
import java.io.IOException;
import net.minecraftforge.common.Configuration;

public class CoreConfig
{
    public static int ItemGoldDustID;
    public static int ItemIronDustID;
    public static boolean DiamondEnabled;
    public static boolean EmeraldEnabled;
    public static boolean LapisLazuliEnabled;
    public static boolean RedstoneEnabled;
    public static boolean goldEnabled;
    public static boolean ironEnabled;
    public static int DiamondVeinCount;
    public static int DiamondOreCount;
    public static int DiamondOreHeight;
    public static int EmeraldVeinCount;
    public static int EmeraldOreCount;
    public static int EmeraldOreHeight;
    public static int LapisLazuliVeinCount;
    public static int LapisLazuliOreCount;
    public static int LapisLazuliOreHeight;
    public static int RedstoneVeinCount;
    public static int RedstoneOreCount;
    public static int RedstoneOreHeight;
    public static int IronVeinCount;
    public static int IronOreCount;
    public static int IronOreHeight;
    public static int GoldVeinCount;
    public static int GoldOreCount;
    public static int GoldOreHeight;
    public static boolean crushersEnabled;
    public static int crusherID;
    public static double stoneCrusherSpeed;
    public static double copperCrusherSpeed;
    public static double bronzeCrusherSpeed;
    public static double ironCrusherSpeed;
    public static double steelCrusherSpeed;
    public static boolean enableTextureOverrides;
    public static int vanillaBrickID;
    public static int[] customIDs;
    public static int[] customMetas;
    public static int[] customVeinCount;
    public static int[] customOreCount;
    public static int[] customMaxHeight;
    public static int[] customMinHeight;
    public static String[] customDimensions;

    public static void init()
    {
        File var0 = new File(MetallurgyCore.proxy.getMinecraftDir() + "/config/Metallurgy");
        var0.mkdir();
        File var1 = new File(MetallurgyCore.proxy.getMinecraftDir() + "/config/Metallurgy/MetallurgyCore.cfg");

        try
        {
            var1.createNewFile();
            System.out.println("Successfully created/read configuration file");
        }
        catch (IOException var7)
        {
            System.out.println("Could not create configuration file for mod_MetallugyBase. Reason:");
            System.out.println(var7);
        }

        Configuration var2 = new Configuration(var1);
        var2.load();
        enableTextureOverrides = var2.get("Enable", "Texture Override", true).getBoolean(true);
        ItemGoldDustID = var2.get("Item Ids", "Gold Dust ID", 26200).getInt(26200);
        ItemIronDustID = var2.get("Item Ids", "Iron Dust ID", 26201).getInt(26201);
        DiamondEnabled = var2.get("Ores", "Enable Diamond", true).getBoolean(true);
        EmeraldEnabled = var2.get("Ores", "Enable Emerald", true).getBoolean(true);
        LapisLazuliEnabled = var2.get("Ores", "Enable Lapis Lazuli", true).getBoolean(true);
        RedstoneEnabled = var2.get("Ores", "Enable Redstone", true).getBoolean(true);
        goldEnabled = var2.get("Ores", "Enable Gold", true).getBoolean(true);
        ironEnabled = var2.get("Ores", "Enable Iron", true).getBoolean(true);
        DiamondVeinCount = var2.get("Ore Generation", "Diamond Vein Count", 4).getInt(4);
        DiamondOreCount = var2.get("Ore Generation", "Diamond Ore Count", 3).getInt(3);
        DiamondOreHeight = var2.get("Ore Generation", "Diamond Ore Height", 128).getInt(128);
        EmeraldVeinCount = var2.get("Ore Generation", "Emerald Vein Count", 2).getInt(2);
        EmeraldOreCount = var2.get("Ore Generation", "Emerald Ore Count", 3).getInt(3);
        EmeraldOreHeight = var2.get("Ore Generation", "Emerald Ore Height", 128).getInt(128);
        LapisLazuliVeinCount = var2.get("Ore Generation", "LapisLazuili Vein Count", 5).getInt(5);
        LapisLazuliOreCount = var2.get("Ore Generation", "LapisLazuili Ore Count", 4).getInt(4);
        LapisLazuliOreHeight = var2.get("Ore Generation", "LapisLazuili Ore Height", 128).getInt(128);
        RedstoneVeinCount = var2.get("Ore Generation", "Redstone Vein Count", 8).getInt(8);
        RedstoneOreCount = var2.get("Ore Generation", "Redstone Ore Count", 6).getInt(6);
        RedstoneOreHeight = var2.get("Ore Generation", "Redstone Ore Height", 128).getInt(128);
        GoldVeinCount = var2.get("Ore Generation", "Gold Vein Count", 4).getInt(4);
        GoldOreCount = var2.get("Ore Generation", "Gold Ore Count", 5).getInt(5);
        GoldOreHeight = var2.get("Ore Generation", "Gold Ore Height", 128).getInt(128);
        IronVeinCount = var2.get("Ore Generation", "Iron Vein Count", 10).getInt(10);
        IronOreCount = var2.get("Ore Generation", "Iron Ore Count", 7).getInt(7);
        IronOreHeight = var2.get("Ore Generation", "Iron Ore Height", 128).getInt(128);
        crusherID = var2.getBlock("Crusher", 900).getInt(900);
        vanillaBrickID = var2.getBlock("Bricks", 905).getInt(905);
        crushersEnabled = var2.get("Machines", "Enable Crushers", true).getBoolean(true);
        stoneCrusherSpeed = (double)((float)var2.get("Crushers", "Stone Speed", 60000).getInt(60000) / 1000.0F);
        copperCrusherSpeed = (double)((float)var2.get("Crushers", "Copper Speed", 30000).getInt(30000) / 1000.0F);
        bronzeCrusherSpeed = (double)((float)var2.get("Crushers", "Bronze Speed", 20000).getInt(20000) / 1000.0F);
        ironCrusherSpeed = (double)((float)var2.get("Crushers", "Iron Speed", 15000).getInt(15000) / 1000.0F);
        steelCrusherSpeed = (double)((float)var2.get("Crushers", "Steel Speed", 10000).getInt(10000) / 1000.0F);
        var2.addCustomCategoryComment("Custom Ores", "Add a block ids and metadata, seperate entries with a comma and a space, i.e. \"17, 35, 35:10\"\nafter adding an id, start and quit minecraft to let the information for the ore generate in \nthe Ore Generation category and fill the numbers as you like");
        String[] var3 = var2.get("Custom Ores", "ID list", "").value.split(", ");

        if (!var3[0].equals(""))
        {
            customIDs = new int[var3.length];
            customMetas = new int[var3.length];
            customVeinCount = new int[var3.length];
            customOreCount = new int[var3.length];
            customMaxHeight = new int[var3.length];
            customMinHeight = new int[var3.length];
            customDimensions = new String[var3.length];

            for (int var4 = 0; var4 < var3.length; ++var4)
            {
                String[] var5 = var3[var4].replace("\"", "").split(":");
                customIDs[var4] = Integer.parseInt(var5[0]);

                if (var5.length > 1)
                {
                    customMetas[var4] = Integer.parseInt(var5[1]);
                }
                else
                {
                    customMetas[var4] = 0;
                }

                String var6 = "" + customIDs[var4];

                if (customMetas[var4] != 0)
                {
                    var6 = var6 + ":" + customMetas[var4];
                }

                customVeinCount[var4] = var2.get("Ore Generation", var6 + " Vein Count", 0).getInt(0);
                customOreCount[var4] = var2.get("Ore Generation", var6 + " Ore Count", 0).getInt(0);
                customMaxHeight[var4] = var2.get("Ore Generation", var6 + " Ore Height", 128).getInt(128);
                customMinHeight[var4] = var2.get("Ore Generation", var6 + " Ore Min Height", 0).getInt(0);
                customDimensions[var4] = var2.get("Ore Generation", var6 + " Ore Dimensions", "0 2-100000").value;
            }
        }
        else
        {
            customIDs = new int[0];
            customVeinCount = new int[0];
            customOreCount = new int[0];
            customMaxHeight = new int[0];
            customMinHeight = new int[0];
        }

        var2.save();
    }
}
