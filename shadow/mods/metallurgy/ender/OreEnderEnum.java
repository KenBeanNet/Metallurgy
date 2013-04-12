package shadow.mods.metallurgy.ender;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.EnumHelper;
import shadow.mods.metallurgy.IMetalSetEnum;
import shadow.mods.metallurgy.MetallurgyEnumToolMaterial;

public class OreEnderEnum extends IMetalSetEnum
{
    public static final int numMetals = 2;
    public static String imageName = "/shadow/MetallurgyEnderMetals.png";
    public static String[] names = new String[] {"Eximite", "Meutoite"};
    public static int[] expValues = new int[] {3, 3};
    private final int[] harvestLevels = new int[] {3, 3};
    private final int[] pickLevels = new int[] {3, 0};
    private int[] metalLevels = new int[] {5, 6};
    public static int[] defaultVeinCount = new int[] {6, 3};
    public static int[] defaultOreCount = new int[] {4, 3};
    public static int[] defaultOreHeight = new int[] {128, 128};
    public static EnumArmorMaterial eximiteArmor = EnumHelper.addArmorMaterial("Eximite", 24, new int[] {4, 6, 5, 4}, 25);
    public static List dimList = new ArrayList();

    public int numMetals()
    {
        return 2;
    }

    public int startID(int var1)
    {
        return ConfigEnder.metalItemIds[var1];
    }

    public String name(int var1)
    {
        return names[var1];
    }

    public int expValue(int var1)
    {
        return expValues[var1];
    }

    public int oreHarvestLevel(int var1)
    {
        return this.harvestLevels[var1];
    }

    public int brickHarvestLevel(int var1)
    {
        return this.harvestLevels[var1];
    }

    public int pickLevel(int var1)
    {
        return this.pickLevels[var1];
    }

    public String image()
    {
        return imageName;
    }

    public boolean isAlloy()
    {
        return false;
    }

    public int veinCount(int var1)
    {
        return ConfigEnder.VeinCount[var1];
    }

    public int oreCount(int var1)
    {
        return ConfigEnder.OreCount[var1];
    }

    public int oreHeight(int var1)
    {
        return ConfigEnder.OreHeight[var1];
    }

    public int oreID()
    {
        return ConfigEnder.EnderMetalsVeinID;
    }

    public int brickID()
    {
        return ConfigEnder.EnderMetalsBrickID;
    }

    public String getSetName()
    {
        return "EnderMetal";
    }

    public MetallurgyEnumToolMaterial toolEnum(int var1)
    {
        switch (var1)
        {
            case 0:
                return MetallurgyEnumToolMaterial.Copper;

            default:
                return MetallurgyEnumToolMaterial.Copper;
        }
    }

    public boolean isCatalyst(int var1)
    {
        return var1 == 1;
    }

    public int dungeonLootChance(int var1)
    {
        return 0;
    }

    public int dungeonLootAmount(int var1)
    {
        return 0;
    }

    public int numRails(int var1)
    {
        return 0;
    }

    public boolean spawnsInDimension(int var1)
    {
        String[] var2 = ConfigEnder.dimensions.split(" ");
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4)
        {
            String var5 = var2[var4];

            if (var5.matches("[0-9]+-[0-9]+"))
            {
                int var6 = Integer.parseInt(var5.split("-")[0]);
                int var7 = Integer.parseInt(var5.split("-")[1]);

                if (var1 >= var6 && var1 <= var7)
                {
                    return true;
                }
            }
            else if (var1 == Integer.parseInt(var5))
            {
                return true;
            }
        }

        return false;
    }

    public boolean metalEnabled(int var1)
    {
        return ConfigEnder.metalEnabled[var1];
    }

    public EnumArmorMaterial armorEnum(int var1)
    {
        return eximiteArmor;
    }

    public int oreMinHeight(int var1)
    {
        return ConfigEnder.OreMinHeight[var1];
    }

    public int level(int var1)
    {
        return this.metalLevels[var1];
    }

    public boolean hasMetalBlock()
    {
        return true;
    }

    public int blockID()
    {
        return ConfigEnder.oresBlockID;
    }

    public CreativeTabs getCreativeTab()
    {
        return MetallurgyEnder.creativeTab;
    }

    static
    {
        String[] var0 = ConfigEnder.dimensions.split(" ");
        int var1 = var0.length;

        for (int var2 = 0; var2 < var1; ++var2)
        {
            String var3 = var0[var2];
            dimList.add(Integer.valueOf(Integer.parseInt(var3)));
        }
    }
}
