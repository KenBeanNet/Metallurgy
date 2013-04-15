package shadow.mods.metallurgy.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.EnumHelper;
import shadow.mods.metallurgy.IMetalSetEnum;
import shadow.mods.metallurgy.MetallurgyEnumToolMaterial;

public class AlloyBaseEnum extends IMetalSetEnum
{
    public static int numMetals = 5;
    public static String[] names = new String[] {"Bronze", "Hepatizon", "Damascus Steel", "Angmallen", "Steel"};
    public static String imageName = "/shadow/MetallurgyBaseAlloys.png";
    private int[] expValues = new int[] {2, 8, 5, 9, 6};
    private int[] harvestLevels = new int[] {1, 1, 1, 1, 2};
    private int[] pickLevels = new int[] {2, 2, 3, 2, 3};
    private int[] metalLevels = new int[] {2, 3, 5, 5, 5};
    private int[] dungeonLootChances = new int[] {80, 80, 60, 55, 45};
    private int[] dungeonLootAmounts = new int[] {3, 3, 2, 2, 2};
    private int[] numRails = new int[] {10, 14, 26, 22, 32};
    public static EnumArmorMaterial bronzeArmor = EnumHelper.addArmorMaterial("Bronze", 13, new int[] {2, 4, 3, 3}, 9);
    public static EnumArmorMaterial hepatizonArmor = EnumHelper.addArmorMaterial("Hepatizon", 14, new int[] {2, 4, 3, 3}, 22);
    public static EnumArmorMaterial damascusSteelArmor = EnumHelper.addArmorMaterial("Damascus Steel", 20, new int[] {3, 6, 5, 3}, 18);
    public static EnumArmorMaterial angmallenArmor = EnumHelper.addArmorMaterial("Angmallen", 18, new int[] {3, 6, 5, 3}, 30);
    public static EnumArmorMaterial steelArmor = EnumHelper.addArmorMaterial("Steel", 22, new int[] {3, 6, 5, 3}, 18);

    public int numMetals()
    {
        return numMetals;
    }

    public int startID(int var1)
    {
        return ConfigBase.ItemStartID + 150 + var1 * 50;
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
        return imageName;
    }

    public boolean isAlloy()
    {
        return true;
    }

    public int veinCount(int var1)
    {
        return 0;
    }

    public int oreCount(int var1)
    {
        return 0;
    }

    public int oreHeight(int var1)
    {
        return 0;
    }

    public int oreID()
    {
        return 0;
    }

    public int brickID()
    {
        return ConfigBase.baseAlloysBrickID;
    }

    public String getSetName()
    {
        return "BaseAlloy";
    }

    public MetallurgyEnumToolMaterial toolEnum(int var1)
    {
        switch (var1)
        {
            case 0:
                return MetallurgyEnumToolMaterial.Bronze;

            case 1:
                return MetallurgyEnumToolMaterial.Hepatizon;

            case 2:
                return MetallurgyEnumToolMaterial.DamascusSteel;

            case 3:
                return MetallurgyEnumToolMaterial.Angmallen;

            case 4:
                return MetallurgyEnumToolMaterial.Steel;

            default:
                return MetallurgyEnumToolMaterial.Bronze;
        }
    }

    public EnumArmorMaterial armorEnum(int var1)
    {
        switch (var1)
        {
            case 0:
                return bronzeArmor;

            case 1:
                return hepatizonArmor;

            case 2:
                return damascusSteelArmor;

            case 3:
                return angmallenArmor;

            case 4:
                return steelArmor;

            default:
                return bronzeArmor;
        }
    }

    public boolean isCatalyst(int var1)
    {
        return false;
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
        return this.numRails[var1];
    }

    public boolean spawnsInDimension(int var1)
    {
        return false;
    }

    public boolean metalEnabled(int var1)
    {
        return ConfigBase.alloyEnabled[var1];
    }

    public int oreMinHeight(int var1)
    {
        return 0;
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
        return ConfigBase.baseAlloysBlockID;
    }

    public CreativeTabs getCreativeTab()
    {
        return MetallurgyBaseMetals.baseTab;
    }
}
