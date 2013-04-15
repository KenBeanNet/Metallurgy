package shadow.mods.metallurgy.fantasy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.EnumHelper;
import shadow.mods.metallurgy.IMetalSetEnum;
import shadow.mods.metallurgy.MetallurgyEnumToolMaterial;

public class OreFantasyEnum extends IMetalSetEnum
{
    public static final int numMetals = 12;
    public static String imageName = "/shadow/MetallurgyFantasyMetals.png";
    public static String[] names = new String[] {"Prometheum", "Deep Iron", "Infuscolium", "Oureclase", "Aredrite", "Astral Silver", "Carmot", "Mithril", "Rubracium", "Orichalcum", "Adamantine", "Atlarus"};
    public static int[] expValues = new int[] {1, 2, 4, 3, 3, 11, 15, 4, 2, 5, 6, 7};
    private final int[] harvestLevels = new int[] {1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 5, 5};
    private final int[] pickLevels = new int[] {1, 2, 0, 3, 3, 4, 4, 4, 0, 5, 6, 6};
    private int[] metalLevels = new int[] {1, 2, 3, 5, 5, 6, 7, 8, 9, 10, 10, 11};
    public static int[] defaultVeinCount = new int[] {5, 5, 5, 4, 4, 3, 3, 3, 2, 2, 1, 1};
    public static int[] defaultOreCount = new int[] {6, 4, 3, 3, 3, 3, 3, 3, 3, 4, 4, 3};
    public static int[] defaultOreHeight = new int[] {128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128};
    public static EnumArmorMaterial prometheumArmor = EnumHelper.addArmorMaterial("Prometheum", 9, new int[] {2, 3, 2, 1}, 16);
    public static EnumArmorMaterial deepIronArmor = EnumHelper.addArmorMaterial("Deep Iron", 14, new int[] {2, 6, 5, 2}, 14);
    public static EnumArmorMaterial oureclaseArmor = EnumHelper.addArmorMaterial("Oureclase", 20, new int[] {3, 6, 5, 4}, 18);
    public static EnumArmorMaterial aredriteArmor = EnumHelper.addArmorMaterial("Aredrite", 24, new int[] {3, 6, 5, 4}, 18);
    public static EnumArmorMaterial astralSilverArmor = EnumHelper.addArmorMaterial("Astral Silver", 10, new int[] {2, 6, 5, 2}, 30);
    public static EnumArmorMaterial carmotArmor = EnumHelper.addArmorMaterial("Carmot", 12, new int[] {3, 6, 5, 3}, 40);
    public static EnumArmorMaterial mithrilArmor = EnumHelper.addArmorMaterial("Mithril", 28, new int[] {4, 6, 5, 4}, 18);
    public static EnumArmorMaterial orichalcumArmor = EnumHelper.addArmorMaterial("Orichalcum", 34, new int[] {4, 7, 6, 4}, 20);
    public static EnumArmorMaterial adamantineArmor = EnumHelper.addArmorMaterial("Adamantine", 38, new int[] {5, 7, 6, 4}, 22);
    public static EnumArmorMaterial atlarusArmor = EnumHelper.addArmorMaterial("Atlarus", 40, new int[] {5, 7, 6, 4}, 22);

    public int numMetals()
    {
        return 12;
    }

    public int startID(int var1)
    {
        return ConfigFantasy.ItemStartID + var1 * 50;
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
        return ConfigFantasy.VeinCount[var1];
    }

    public int oreCount(int var1)
    {
        return ConfigFantasy.OreCount[var1];
    }

    public int oreHeight(int var1)
    {
        return ConfigFantasy.OreHeight[var1];
    }

    public int oreID()
    {
        return ConfigFantasy.FantasyMetalsVeinID;
    }

    public int brickID()
    {
        return ConfigFantasy.FantasyMetalsBrickID;
    }

    public String getSetName()
    {
        return "FantasyMetal";
    }

    public MetallurgyEnumToolMaterial toolEnum(int var1)
    {
        switch (var1)
        {
            case 0:
                return MetallurgyEnumToolMaterial.Prometheum;

            case 1:
                return MetallurgyEnumToolMaterial.DeepIron;

            case 2:
                return null;

            case 3:
                return MetallurgyEnumToolMaterial.Oureclase;

            case 4:
                return MetallurgyEnumToolMaterial.Aredrite;

            case 5:
                return MetallurgyEnumToolMaterial.AstralSilver;

            case 6:
                return MetallurgyEnumToolMaterial.Carmot;

            case 7:
                return MetallurgyEnumToolMaterial.Mithril;

            case 8:
                return null;

            case 9:
                return MetallurgyEnumToolMaterial.Orichalcum;

            case 10:
                return MetallurgyEnumToolMaterial.Adamantine;

            case 11:
                return MetallurgyEnumToolMaterial.Atlarus;

            default:
                return MetallurgyEnumToolMaterial.Prometheum;
        }
    }

    public EnumArmorMaterial armorEnum(int var1)
    {
        switch (var1)
        {
            case 0:
                return prometheumArmor;

            case 1:
                return deepIronArmor;

            case 2:
                return null;

            case 3:
                return oureclaseArmor;

            case 4:
                return aredriteArmor;

            case 5:
                return astralSilverArmor;

            case 6:
                return carmotArmor;

            case 7:
                return mithrilArmor;

            case 8:
                return null;

            case 9:
                return orichalcumArmor;

            case 10:
                return adamantineArmor;

            case 11:
                return atlarusArmor;

            default:
                return prometheumArmor;
        }
    }

    public boolean isCatalyst(int var1)
    {
        return var1 == 2 || var1 == 8;
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
        String[] var2 = ConfigFantasy.dimensions.split(" ");
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
        return ConfigFantasy.metalEnabled[var1];
    }

    public int oreMinHeight(int var1)
    {
        return ConfigFantasy.OreMinHeight[var1];
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
        return ConfigFantasy.oresBlockID;
    }

    public CreativeTabs getCreativeTab()
    {
        return MetallurgyFantasy.creativeTab;
    }
}
