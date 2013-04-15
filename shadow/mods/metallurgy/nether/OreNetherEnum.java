package shadow.mods.metallurgy.nether;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.EnumHelper;
import shadow.mods.metallurgy.IMetalSetEnum;
import shadow.mods.metallurgy.MetallurgyEnumToolMaterial;

public class OreNetherEnum extends IMetalSetEnum
{
    public static final int numMetals = 10;
    public static String imageName = "/shadow/MetallurgyNetherMetals.png";
    public static String[] names = new String[] {"Ignatius", "Shadow Iron", "Midasium", "Vyroxeres", "Ceruclase", "Kalendrite", "Vulcanite", "Sanguinite", "Lemurite", "Adluorite"};
    public static int[] expValues = new int[] {1, 2, 3, 3, 3, 4, 5, 6, 3, 4};
    private final int[] harvestLevels = new int[] {1, 1, 2, 2, 2, 3, 4, 5, 0, 0};
    private final int[] pickLevels = new int[] {1, 1, 3, 3, 3, 4, 5, 6, 0, 0};
    private int[] metalLevels = new int[] {1, 2, 4, 5, 6, 8, 10, 11, 2, 6};
    public static int[] defaultVeinCount = new int[] {9, 7, 5, 4, 4, 3, 2, 2, 6, 3};
    public static int[] defaultOreCount = new int[] {6, 6, 6, 7, 4, 4, 3, 3, 6, 4};
    public static int[] defaultOreHeight = new int[] {128, 128, 128, 128, 128, 128, 128, 128, 128, 128};
    public static EnumArmorMaterial ignatiusArmor = EnumHelper.addArmorMaterial("Ignatius", 15, new int[] {2, 6, 5, 2}, 15);
    public static EnumArmorMaterial shadowIronArmor = EnumHelper.addArmorMaterial("Shadow Iron", 16, new int[] {3, 6, 5, 2}, 3);
    public static EnumArmorMaterial midasiumArmor = EnumHelper.addArmorMaterial("Midasium", 8, new int[] {2, 5, 3, 3}, 35);
    public static EnumArmorMaterial vyroxeresArmor = EnumHelper.addArmorMaterial("Vyroxeres", 23, new int[] {3, 6, 5, 4}, 16);
    public static EnumArmorMaterial ceruclaseArmor = EnumHelper.addArmorMaterial("Ceruclase", 25, new int[] {3, 6, 5, 4}, 18);
    public static EnumArmorMaterial kalendriteArmor = EnumHelper.addArmorMaterial("Kalendrite", 28, new int[] {4, 6, 5, 4}, 20);
    public static EnumArmorMaterial vulcaniteArmor = EnumHelper.addArmorMaterial("Vulcanite", 34, new int[] {4, 7, 5, 4}, 20);
    public static EnumArmorMaterial sanguiniteArmor = EnumHelper.addArmorMaterial("Sanguinite", 36, new int[] {5, 7, 6, 4}, 25);

    public int numMetals()
    {
        return 10;
    }

    public int startID(int var1)
    {
        return ConfigNether.metalItemIds[var1];
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
        return ConfigNether.VeinCount[var1];
    }

    public int oreCount(int var1)
    {
        return ConfigNether.OreCount[var1];
    }

    public int oreHeight(int var1)
    {
        return ConfigNether.OreHeight[var1];
    }

    public int oreID()
    {
        return ConfigNether.NetherMetalsVeinID;
    }

    public int brickID()
    {
        return ConfigNether.NetherMetalsBrickID;
    }

    public String getSetName()
    {
        return "NetherMetal";
    }

    public MetallurgyEnumToolMaterial toolEnum(int var1)
    {
        switch (var1)
        {
            case 0:
                return MetallurgyEnumToolMaterial.Ignatius;

            case 1:
                return MetallurgyEnumToolMaterial.ShadowIron;

            case 2:
                return MetallurgyEnumToolMaterial.Midasium;

            case 3:
                return MetallurgyEnumToolMaterial.Vyroxeres;

            case 4:
                return MetallurgyEnumToolMaterial.Ceruclase;

            case 5:
                return MetallurgyEnumToolMaterial.Kalendrite;

            case 6:
                return MetallurgyEnumToolMaterial.Vulcanite;

            case 7:
                return MetallurgyEnumToolMaterial.Sanguinite;

            default:
                return MetallurgyEnumToolMaterial.Ignatius;
        }
    }

    public EnumArmorMaterial armorEnum(int var1)
    {
        switch (var1)
        {
            case 0:
                return ignatiusArmor;

            case 1:
                return shadowIronArmor;

            case 2:
                return midasiumArmor;

            case 3:
                return vyroxeresArmor;

            case 4:
                return ceruclaseArmor;

            case 5:
                return kalendriteArmor;

            case 6:
                return vulcaniteArmor;

            case 7:
                return sanguiniteArmor;

            default:
                return ignatiusArmor;
        }
    }

    public boolean isCatalyst(int var1)
    {
        return var1 >= 8;
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
        String[] var2 = ConfigNether.dimensions.split(" ");
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
        return ConfigNether.metalEnabled[var1];
    }

    public int oreMinHeight(int var1)
    {
        return ConfigNether.OreMinHeight[var1];
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
        return ConfigNether.oresBlockID;
    }

    public CreativeTabs getCreativeTab()
    {
        return MetallurgyNether.creativeTab;
    }
}
