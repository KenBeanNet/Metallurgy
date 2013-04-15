package shadow.mods.metallurgy.nether;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.EnumHelper;
import shadow.mods.metallurgy.IMetalSetEnum;
import shadow.mods.metallurgy.MetallurgyEnumToolMaterial;

public class AlloyNetherEnum extends IMetalSetEnum
{
    public static int numMetals = 3;
    public static String[] names = new String[] {"Shadow Steel", "Inolashite", "Amordrine"};
    public static String imageName = "/shadow/MetallurgyNetherAlloys.png";
    private int[] expValues = new int[] {4, 6, 24};
    private int[] harvestLevels = new int[] {2, 4, 4};
    private int[] pickLevels = new int[] {2, 4, 4};
    private int[] metalLevels = new int[] {3, 7, 9};
    public static EnumArmorMaterial shadowSteelArmor = EnumHelper.addArmorMaterial("Shadow Steel", 20, new int[] {3, 6, 5, 4}, 5);
    public static EnumArmorMaterial inolashiteArmor = EnumHelper.addArmorMaterial("Inolashite", 30, new int[] {4, 7, 5, 4}, 25);
    public static EnumArmorMaterial amordrineArmor = EnumHelper.addArmorMaterial("Amordrine", 32, new int[] {4, 7, 5, 4}, 50);

    public int numMetals()
    {
        return numMetals;
    }

    public int startID(int var1)
    {
        return ConfigNether.alloyItemIds[var1];
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
        return ConfigNether.NetherAlloysBrickID;
    }

    public String getSetName()
    {
        return "NetherAlloy";
    }

    public MetallurgyEnumToolMaterial toolEnum(int var1)
    {
        switch (var1)
        {
            case 0:
                return MetallurgyEnumToolMaterial.ShadowSteel;

            case 1:
                return MetallurgyEnumToolMaterial.Inolashite;

            case 2:
                return MetallurgyEnumToolMaterial.Amordrine;

            default:
                return MetallurgyEnumToolMaterial.Brass;
        }
    }

    public EnumArmorMaterial armorEnum(int var1)
    {
        switch (var1)
        {
            case 0:
                return shadowSteelArmor;

            case 1:
                return inolashiteArmor;

            case 2:
                return amordrineArmor;

            default:
                return shadowSteelArmor;
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
        return ConfigNether.alloyEnabled[var1];
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
        return ConfigNether.alloysBlockID;
    }

    public CreativeTabs getCreativeTab()
    {
        return MetallurgyNether.creativeTab;
    }
}
