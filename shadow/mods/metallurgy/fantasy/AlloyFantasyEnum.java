package shadow.mods.metallurgy.fantasy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.EnumHelper;
import shadow.mods.metallurgy.IMetalSetEnum;
import shadow.mods.metallurgy.MetallurgyEnumToolMaterial;

public class AlloyFantasyEnum extends IMetalSetEnum
{
    public static int numMetals = 5;
    public static String[] names = new String[] {"Black Steel", "Quicksilver", "Haderoth", "Celenegil", "Tartarite"};
    public static String imageName = "/shadow/MetallurgyFantasyAlloys.png";
    private int[] expValues = new int[] {4, 9, 6, 25, 13};
    private int[] harvestLevels = new int[] {2, 4, 4, 5, 6};
    private int[] pickLevels = new int[] {2, 4, 4, 5, 6};
    private int[] metalLevels = new int[] {4, 9, 9, 11, 12};
    public static EnumArmorMaterial blackSteelArmor = EnumHelper.addArmorMaterial("Black Steel", 22, new int[] {3, 6, 5, 3}, 17);
    public static EnumArmorMaterial quicksilverArmor = EnumHelper.addArmorMaterial("Quicksilver", 30, new int[] {4, 7, 5, 4}, 20);
    public static EnumArmorMaterial haderothArmor = EnumHelper.addArmorMaterial("Haderoth", 32, new int[] {4, 7, 5, 4}, 19);
    public static EnumArmorMaterial celenegilArmor = EnumHelper.addArmorMaterial("Celenegil", 36, new int[] {5, 7, 6, 4}, 50);
    public static EnumArmorMaterial tartariteArmor = EnumHelper.addArmorMaterial("Tartarite", 42, new int[] {5, 7, 6, 5}, 25);

    public int numMetals()
    {
        return numMetals;
    }

    public int startID(int var1)
    {
        return ConfigFantasy.ItemStartID + 600 + var1 * 50;
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
        return ConfigFantasy.FantasyAlloysBrickID;
    }

    public String getSetName()
    {
        return "FantasyAlloy";
    }

    public MetallurgyEnumToolMaterial toolEnum(int var1)
    {
        switch (var1)
        {
            case 0:
                return MetallurgyEnumToolMaterial.BlackSteel;

            case 1:
                return MetallurgyEnumToolMaterial.Quicksilver;

            case 2:
                return MetallurgyEnumToolMaterial.Haderoth;

            case 3:
                return MetallurgyEnumToolMaterial.Celenegil;

            case 4:
                return MetallurgyEnumToolMaterial.Tartarite;

            default:
                return MetallurgyEnumToolMaterial.BlackSteel;
        }
    }

    public EnumArmorMaterial armorEnum(int var1)
    {
        switch (var1)
        {
            case 0:
                return blackSteelArmor;

            case 1:
                return quicksilverArmor;

            case 2:
                return haderothArmor;

            case 3:
                return celenegilArmor;

            case 4:
                return tartariteArmor;

            default:
                return blackSteelArmor;
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
        return ConfigFantasy.alloyEnabled[var1];
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
        return ConfigFantasy.alloysBlockID;
    }

    public CreativeTabs getCreativeTab()
    {
        return MetallurgyFantasy.creativeTab;
    }
}
