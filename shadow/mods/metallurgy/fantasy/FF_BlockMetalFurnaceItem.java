package shadow.mods.metallurgy.fantasy;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class FF_BlockMetalFurnaceItem extends ItemBlock
{
    public FF_BlockMetalFurnaceItem(int var1)
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
                var2 = "PrometheumFurnace";
                break;

            case 1:
                var2 = "DeepIronFurnace";
                break;

            case 2:
                var2 = "BlackSteelFurnace";
                break;

            case 3:
                var2 = "OureclaseFurnace";
                break;

            case 4:
                var2 = "AredriteFurnace";
                break;

            case 5:
                var2 = "MithrilFurnace";
                break;

            case 6:
                var2 = "HaderothFurnace";
                break;

            case 7:
                var2 = "OrichalcumFurnace";
                break;

            case 8:
                var2 = "AdamantineFurnace";
                break;

            case 9:
                var2 = "AtlarusFurnace";
                break;

            case 10:
                var2 = "TartariteFurnace";
                break;

            default:
                var2 = "brick";
        }

        return this.getItemName() + "." + var2;
    }
}
