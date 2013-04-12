package shadow.mods.metallurgy.ender;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.EnumHelper;
import shadow.mods.metallurgy.IMetalSetEnum;
import shadow.mods.metallurgy.MetallurgyEnumToolMaterial;

public class AlloyEnderEnum extends IMetalSetEnum
{
    public static int numMetals = 1;
    public static String[] names = new String[] {"Desichalkos"};
    public static String imageName = "/shadow/MetallurgyEnderAlloys.png";
    private int[] expValues = new int[] {10};
    private int[] harvestLevels = new int[] {4};
    private int[] pickLevels = new int[] {4};
    private int[] metalLevels = new int[] {7};
    public static EnumArmorMaterial desichalkosArmor = EnumHelper.addArmorMaterial("Desichalkos", 30, new int[] {4, 7, 5, 4}, 30);

    public int numMetals()
    {
        return numMetals;
    }

    public int startID(int var1)
    {
        return ConfigEnder.alloyItemIds[var1];
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
        return ConfigEnder.EnderAlloysBrickID;
    }

    public String getSetName()
    {
        return "EnderAlloy";
    }

    public MetallurgyEnumToolMaterial toolEnum(int var1)
    {
        switch (var1)
        {
            case 0:
                return MetallurgyEnumToolMaterial.Steel;

            default:
                return MetallurgyEnumToolMaterial.Steel;
        }
    }

    public boolean isCatalyst(int var1)
    {
        return false;
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
        return false;
    }

    public boolean metalEnabled(int var1)
    {
        return ConfigEnder.alloyEnabled[var1];
    }

    public EnumArmorMaterial armorEnum(int var1)
    {
        return desichalkosArmor;
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
        return ConfigEnder.alloysBlockID;
    }

    public CreativeTabs getCreativeTab()
    {
        return MetallurgyEnder.creativeTab;
    }
}
