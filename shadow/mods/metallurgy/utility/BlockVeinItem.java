package shadow.mods.metallurgy.utility;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockVeinItem extends ItemBlock
{
    public BlockVeinItem(int var1)
    {
        super(var1);
        this.setHasSubtypes(true);
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        return var1;
    }

    public String getItemNameIS(ItemStack var1)
    {
        String var2 = "";

        switch (var1.getItemDamage())
        {
            case 0:
                var2 = "PhosphoriteOre";
                break;

            case 1:
                var2 = "SulfurOre";
                break;

            case 2:
                var2 = "SaltpeterOre";
                break;

            case 3:
                var2 = "MagnesiumOre";
                break;

            case 4:
                var2 = "BitumenOre";
                break;

            case 5:
                var2 = "PotashOre";
                break;

            default:
                var2 = "ore";
        }

        return this.getItemName() + "." + var2;
    }
}
