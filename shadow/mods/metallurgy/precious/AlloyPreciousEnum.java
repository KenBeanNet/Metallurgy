package shadow.mods.metallurgy.precious;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.EnumHelper;
import shadow.mods.metallurgy.IMetalSetEnum;
import shadow.mods.metallurgy.MetallurgyEnumToolMaterial;

public class AlloyPreciousEnum extends IMetalSetEnum
{
    public static String[] name = new String[] {"Brass", "Electrum"};
    private int[] expValues = new int[] {4, 11};
    private int[] harvestLevels = new int[] {1, 1};
    private int[] pickLevels = new int[] {0, 1};
    private int[] dungeonLootChances = new int[] {25, 10};
    private int[] dungeonLootAmounts = new int[] {3, 2};
    private int[] metalLevels = new int[] {2, 4};
    public static EnumArmorMaterial brassArmor = EnumHelper.addArmorMaterial("Brass", 3, new int[] {2, 5, 3, 3}, 18);
    public static EnumArmorMaterial electrumArmor = EnumHelper.addArmorMaterial("Electrum", 9, new int[] {2, 6, 5, 2}, 30);

    public int numMetals()
    {
        return 2;
    }

    public int startID(int var1)
    {
        return ConfigPrecious.ItemStartID + 150 + var1 * 50;
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
        return "/shadow/MetallurgyPreciousAlloys.png";
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
        return ConfigPrecious.PreciousAlloysBrickID;
    }

    public String getSetName()
    {
        return "PreciousAlloy";
    }

    public MetallurgyEnumToolMaterial toolEnum(int var1)
    {
        switch (var1)
        {
            case 0:
                return MetallurgyEnumToolMaterial.Brass;

            case 1:
                return MetallurgyEnumToolMaterial.Electrum;

            default:
                return MetallurgyEnumToolMaterial.Brass;
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
        return 0;
    }

    public boolean spawnsInDimension(int var1)
    {
        return false;
    }

    public boolean metalEnabled(int var1)
    {
        return ConfigPrecious.alloyEnabled[var1];
    }

    public EnumArmorMaterial armorEnum(int var1)
    {
        return var1 == 0 ? brassArmor : electrumArmor;
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
        return ConfigPrecious.alloysBlockID;
    }

    public CreativeTabs getCreativeTab()
    {
        return MetallurgyPrecious.creativeTab;
    }
}
