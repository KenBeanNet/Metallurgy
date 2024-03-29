package shadow.mods.metallurgy.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraftforge.common.Configuration;

public class ConfigUtility
{
    public static int veinID;
    public static boolean phosphoriteEnabled;
    public static boolean sulfurEnabled;
    public static boolean saltpeterEnabled;
    public static boolean magnesiumEnabled;
    public static boolean bitumenEnabled;
    public static boolean potashEnabled;
    public static int itemPhosphoriteID;
    public static int itemPhosphorousID;
    public static int itemSulfurID;
    public static int itemSaltpeterID;
    public static int itemMagnesiumID;
    public static int itemBitumenID;
    public static int itemTarID;
    public static int itemPotashID;
    public static int itemFertilizerID;
    public static int itemIgniterID;
    public static int itemMatchID;
    public static int BitumenVeinCount;
    public static int BitumenOreCount;
    public static int BitumenOreHeight;
    public static int MagnesiumVeinCount;
    public static int MagnesiumOreCount;
    public static int MagnesiumOreHeight;
    public static int PhosphoriteVeinCount;
    public static int PhosphoriteOreCount;
    public static int PhosphoriteOreHeight;
    public static int PotashVeinCount;
    public static int PotashOreCount;
    public static int PotashOreHeight;
    public static int SaltpeterVeinCount;
    public static int SaltpeterOreCount;
    public static int SaltpeterOreHeight;
    public static int SulfurVeinCount;
    public static int SulfurOreCount;
    public static int SulfurOreHeight;
    public static String dimensions;
    public static List minersTNTDestoryable;
    public static int minersTntId;
    public static int largeTntId;

    public static void init()
    {
        File var0 = new File(MetallurgyUtility.proxy.getMinecraftDir() + "/config/Metallurgy");
        var0.mkdir();
        File var1 = new File(MetallurgyUtility.proxy.getMinecraftDir() + "/config/Metallurgy/MetallurgyUtility.cfg");

        try
        {
            var1.createNewFile();
            System.out.println("Successfully created/read configuration file");
        }
        catch (IOException var9)
        {
            System.out.println("Could not create configuration file for mod_MetallugyBase. Reason:");
            System.out.println(var9);
        }

        Configuration var2 = new Configuration(var1);
        var2.load();
        veinID = var2.getBlock("Utility Ore", 923).getInt(923);
        dimensions = var2.get("Dimensions", "Dimensions", "0 2-100000").value;
        bitumenEnabled = var2.get("Ores", "Enable Bitumen", true).getBoolean(true);
        magnesiumEnabled = var2.get("Ores", "Enable Magnesium", true).getBoolean(true);
        phosphoriteEnabled = var2.get("Ores", "Enable Phosphorite", true).getBoolean(true);
        potashEnabled = var2.get("Ores", "Enable Potash", true).getBoolean(true);
        saltpeterEnabled = var2.get("Ores", "Enable SaltPeter", true).getBoolean(true);
        sulfurEnabled = var2.get("Ores", "Enable Sulfur", true).getBoolean(true);
        itemPhosphoriteID = var2.get("Item IDs", "Phosphorite Id", 28400).getInt(28400);
        itemPhosphorousID = var2.get("Item IDs", "Phosphorous Id", 28401).getInt(28401);
        itemSulfurID = var2.get("Item IDs", "Sulfur Id", 28402).getInt(28402);
        itemSaltpeterID = var2.get("Item IDs", "Saltpeter Id", 28403).getInt(28403);
        itemMagnesiumID = var2.get("Item IDs", "Magnesium Id", 28404).getInt(28404);
        itemBitumenID = var2.get("Item IDs", "Bitumen Id", 28406).getInt(28406);
        itemTarID = var2.get("Item IDs", "Tar Id", 28405).getInt(28405);
        itemPotashID = var2.get("Item IDs", "Potash Id", 28407).getInt(28407);
        itemFertilizerID = var2.get("Item IDs", "Fertilizer Id", 28408).getInt(28408);
        itemIgniterID = var2.get("Item IDs", "Igniter Id", 28409).getInt(28409);
        itemMatchID = var2.get("Item IDs", "Match Id", 28410).getInt(28410);
        BitumenVeinCount = var2.get("Ore Generation", "Bitumen Vein Count", 4).getInt(4);
        BitumenOreCount = var2.get("Ore Generation", "Bitumen Ore Count", 4).getInt(4);
        BitumenOreHeight = var2.get("Ore Generation", "Bitumen Ore Height", 128).getInt(128);
        MagnesiumVeinCount = var2.get("Ore Generation", "Magnesium Vein Count", 4).getInt(4);
        MagnesiumOreCount = var2.get("Ore Generation", "Magnesium Ore Count", 4).getInt(4);
        MagnesiumOreHeight = var2.get("Ore Generation", "Magnesium Ore Height", 128).getInt(128);
        PhosphoriteVeinCount = var2.get("Ore Generation", "Phosphorite Vein Count", 4).getInt(4);
        PhosphoriteOreCount = var2.get("Ore Generation", "Phosphorite Ore Count", 4).getInt(4);
        PhosphoriteOreHeight = var2.get("Ore Generation", "Phosphorite Ore Height", 128).getInt(128);
        PotashVeinCount = var2.get("Ore Generation", "Potash Vein Count", 4).getInt(4);
        PotashOreCount = var2.get("Ore Generation", "Potash Ore Count", 4).getInt(4);
        PotashOreHeight = var2.get("Ore Generation", "Potash Ore Height", 128).getInt(128);
        SaltpeterVeinCount = var2.get("Ore Generation", "Saltpeter Vein Count", 4).getInt(4);
        SaltpeterOreCount = var2.get("Ore Generation", "Saltpeter Ore Count", 4).getInt(4);
        SaltpeterOreHeight = var2.get("Ore Generation", "Saltpeter Ore Height", 128).getInt(128);
        SulfurVeinCount = var2.get("Ore Generation", "Sulfur Vein Count", 4).getInt(4);
        SulfurOreCount = var2.get("Ore Generation", "Sulfur Ore Count", 4).getInt(4);
        SulfurOreHeight = var2.get("Ore Generation", "Sulfur Ore Height", 128).getInt(128);
        minersTntId = var2.getBlock("LE TNT ID", 3000).getInt(3000);
        largeTntId = var2.getBlock("HE TNT ID", 3001).getInt(3001);
        String var3 = var2.get("Miner TNT Blocks", "Blocks", "1, 2, 3, 4, 5, 12, 13, 17, 18, 19, 20, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 37, 38, 39, 40, 43, 44, 45, 47, 48, 50, 53, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 72, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 117, 121, 123, 124, 125, 126, 127, 128, 131, 132, 134, 135, 136, 139, 140, 141, 142, 143").value;
        String[] var4 = var3.split(", ");
        minersTNTDestoryable = new ArrayList();
        String[] var5 = var4;
        int var6 = var4.length;

        for (int var7 = 0; var7 < var6; ++var7)
        {
            String var8 = var5[var7];
            minersTNTDestoryable.add(Integer.valueOf(var8));
        }

        var2.save();
    }
}
