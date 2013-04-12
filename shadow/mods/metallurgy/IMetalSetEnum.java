package shadow.mods.metallurgy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;

public abstract class IMetalSetEnum
{
    public abstract int numMetals();

    public abstract int startID(int var1);

    public abstract String name(int var1);

    public abstract int expValue(int var1);

    public abstract int oreHarvestLevel(int var1);

    public abstract int brickHarvestLevel(int var1);

    public abstract int pickLevel(int var1);

    public int dungeonLootChance(int var1)
    {
        return 0;
    }

    public int dungeonLootAmount(int var1)
    {
        return 0;
    }

    public abstract String image();

    public boolean isAlloy()
    {
        return false;
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

    public int oreMinHeight(int var1)
    {
        return 0;
    }

    public abstract int oreID();

    public abstract int brickID();

    public abstract String getSetName();

    public abstract MetallurgyEnumToolMaterial toolEnum(int var1);

    public abstract boolean isCatalyst(int var1);

    public int numRails(int var1)
    {
        return 0;
    }

    public abstract boolean spawnsInDimension(int var1);

    public abstract boolean metalEnabled(int var1);

    public abstract EnumArmorMaterial armorEnum(int var1);

    public abstract int level(int var1);

    public boolean hasMetalBlock()
    {
        return false;
    }

    public int blockID()
    {
        return 0;
    }

    public abstract CreativeTabs getCreativeTab();
}
