package shadow.mods.metallurgy.base;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BF_BlockMetalFurnaceItem extends ItemBlock
{
    public BF_BlockMetalFurnaceItem(int var1)
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
                var2 = "CopperFurnace";
                break;

            case 1:
                var2 = "BronzeFurnace";
                break;

            case 2:
                var2 = "IronFurnace";
                break;

            case 3:
                var2 = "SteelFurnace";
                break;

            default:
                var2 = "brick";
        }

        return this.getItemName() + "." + var2;
    }
}
