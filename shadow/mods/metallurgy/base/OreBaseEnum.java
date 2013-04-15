package shadow.mods.metallurgy.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.EnumHelper;
import shadow.mods.metallurgy.IMetalSetEnum;
import shadow.mods.metallurgy.MetallurgyEnumToolMaterial;

public class OreBaseEnum extends IMetalSetEnum
{
    public static final int numMetals = 3;
    public static final String[] names = new String[] {"Copper", "Tin", "Manganese"};
    private final int[] expValues = new int[] {1, 1, 3};
    private final int[] harvestLevels = new int[] {1, 1, 2};
    private final int[] pickLevels = new int[] {1, 0, 0};
    private int[] metalLevels = new int[] {1, 2, 4};
    private final int[] dungeonLootChances = new int[] {120, 100, 45};
    private final int[] dungeonLootAmounts = new int[] {6, 4, 2};
    public static int[] defaultVeinCount = new int[] {12, 10, 4};
    public static int[] defaultOreCount = new int[] {6, 7, 4};
    public static int[] defaultOreHeight = new int[] {128, 128, 128};
    public static int[] numRails = new int[] {4, 0, 0};
    public static EnumArmorMaterial copperArmor = EnumHelper.addArmorMaterial("Copper", 10, new int[] {2, 3, 2, 1}, 5);

    public int numMetals()
    {
        return 3;
    }

    public int startID(int var1)
    {
        return ConfigBase.ItemStartID + var1 * 50;
    }

    public String name(int var1)
    {
        return names[var1];
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
        return "/shadow/MetallurgyBaseMetals.png";
    }

    public boolean isAlloy()
    {
        return false;
    }

    public int veinCount(int var1)
    {
        return ConfigBase.VeinCount[var1];
    }

    public int oreCount(int var1)
    {
        return ConfigBase.OreCount[var1];
    }

    public int oreHeight(int var1)
    {
        return ConfigBase.OreHeight[var1];
    }

    public int oreID()
    {
        return ConfigBase.baseMetalsVeinID;
    }

    public int brickID()
    {
        return ConfigBase.baseMetalsBrickID;
    }

    public String getSetName()
    {
        return "BaseMetal";
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
        return var1 == 1 || var1 == 2;
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
        return numRails[var1];
    }

    public boolean spawnsInDimension(int var1)
    {
        String[] var2 = ConfigBase.dimensions.split(" ");
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
        return ConfigBase.metalEnabled[var1];
    }

    public EnumArmorMaterial armorEnum(int var1)
    {
        return copperArmor;
    }

    public int oreMinHeight(int var1)
    {
        return ConfigBase.OreMinHeight[var1];
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
        return ConfigBase.baseMetalsBlockID;
    }

    public CreativeTabs getCreativeTab()
    {
        return MetallurgyBaseMetals.baseTab;
    }
}
