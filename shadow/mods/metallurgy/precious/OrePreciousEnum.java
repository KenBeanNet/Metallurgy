package shadow.mods.metallurgy.precious;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.EnumHelper;
import shadow.mods.metallurgy.IMetalSetEnum;
import shadow.mods.metallurgy.MetallurgyEnumToolMaterial;

public class OrePreciousEnum extends IMetalSetEnum
{
    public static final int numMetals = 3;
    public static final String[] name = new String[] {"Zinc", "Silver", "Platinum"};
    private final int[] expValues = new int[] {3, 5, 20};
    private final int[] harvestLevels = new int[] {0, 1, 2};
    private final int[] pickLevels = new int[] {0, 0, 2};
    private int[] metalLevels = new int[] {1, 2, 5};
    private final int[] dungeonLootChances = new int[] {40, 18, 4};
    private final int[] dungeonLootAmounts = new int[] {4, 3, 1};
    public static int[] defaultVeinCount = new int[] {6, 5, 3};
    public static int[] defaultOreCount = new int[] {5, 5, 3};
    public static int[] defaultOreHeight = new int[] {128, 128, 128};
    public static EnumArmorMaterial silverArmor = EnumHelper.addArmorMaterial("Silver", 5, new int[] {2, 4, 3, 2}, 20);
    public static EnumArmorMaterial platinumArmor = EnumHelper.addArmorMaterial("Platinum", 11, new int[] {3, 6, 5, 3}, 50);

    public int numMetals()
    {
        return 3;
    }

    public int startID(int var1)
    {
        return ConfigPrecious.ItemStartID + var1 * 50;
    }

    public String name(int var1)
    {
        return name[var1];
    }

    public int expValue(int var1)
    {
        return this.expValues[var1];
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
        return "/shadow/MetallurgyPreciousMetals.png";
    }

    public boolean isAlloy()
    {
        return false;
    }

    public int veinCount(int var1)
    {
        return ConfigPrecious.VeinCount[var1];
    }

    public int oreCount(int var1)
    {
        return ConfigPrecious.OreCount[var1];
    }

    public int oreHeight(int var1)
    {
        return ConfigPrecious.OreHeight[var1];
    }

    public int oreMinHeight(int var1)
    {
        return ConfigPrecious.OreMinHeight[var1];
    }

    public int oreID()
    {
        return ConfigPrecious.PreciousMetalsVeinID;
    }

    public int brickID()
    {
        return ConfigPrecious.PreciousMetalsBrickID;
    }

    public String getSetName()
    {
        return "PreciousMetal";
    }

    public MetallurgyEnumToolMaterial toolEnum(int var1)
    {
        switch (var1)
        {
            case 0:
                return null;

            case 1:
                return MetallurgyEnumToolMaterial.Silver;

            case 2:
                return MetallurgyEnumToolMaterial.Platinum;

            default:
                return MetallurgyEnumToolMaterial.Brass;
        }
    }

    public EnumArmorMaterial armorEnum(int var1)
    {
        switch (var1)
        {
            case 0:
                return null;

            case 1:
                return silverArmor;

            case 2:
                return platinumArmor;

            default:
                return silverArmor;
        }
    }

    public boolean isCatalyst(int var1)
    {
        return var1 == 0;
    }

    public int dungeonLootChance(int var1)
    {
        return this.dungeonLootChances[var1];
    }

    public int dungeonLootAmount(int var1)
    {
        return this.dungeonLootAmounts[var1];
    }

    public int numRails(int var1)
    {
        return 0;
    }

    public boolean spawnsInDimension(int var1)
    {
        String[] var2 = ConfigPrecious.dimensions.split(" ");
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
        return ConfigPrecious.metalEnabled[var1];
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
        return ConfigPrecious.oresBlockID;
    }

    public CreativeTabs getCreativeTab()
    {
        return MetallurgyPrecious.creativeTab;
    }
}
