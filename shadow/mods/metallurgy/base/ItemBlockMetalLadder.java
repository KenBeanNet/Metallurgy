package shadow.mods.metallurgy.base;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMetalLadder extends ItemBlock
{
    public ItemBlockMetalLadder(int var1)
    {
        super(var1);
        this.setHasSubtypes(true);
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        return var1 * 4;
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    public int getIconFromDamage(int var1)
    {
        return this.iconIndex + var1;
    }

    public String getItemNameIS(ItemStack var1)
    {
        String var2 = "";

        switch (var1.getItemDamage())
        {
            case 0:
                var2 = "Copper";
                break;

            case 1:
                var2 = "Bronze";
                break;

            case 2:
                var2 = "Iron";
                break;

            case 3:
                var2 = "Steel";
                break;

            default:
                var2 = "Copper";
        }

        return this.getItemName() + "." + var2;
    }
}
