package shadow.mods.metallurgy;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BC_BlockCrusherItem extends ItemBlock
{
    public BC_BlockCrusherItem(int var1)
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
                var2 = "StoneCrusher";
                break;

            case 1:
                var2 = "CopperCrusher";
                break;

            case 2:
                var2 = "BronzeCrusher";
                break;

            case 3:
                var2 = "IronCrusher";
                break;

            case 4:
                var2 = "SteelCrusher";
                break;

            default:
                var2 = "brick";
        }

        return this.getItemName() + "." + var2;
    }
}
