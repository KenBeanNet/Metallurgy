package shadow.mods.metallurgy.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockColoredGlass extends ItemBlock
{
    public ItemBlockColoredGlass(int var1)
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

        switch (var1.getItemDamage() + 1)
        {
            case 0:
                var2 = "glass";
                break;

            case 1:
                var2 = "red";
                break;

            case 2:
                var2 = "green";
                break;

            case 3:
                var2 = "blue";
                break;

            case 4:
                var2 = "orange";
                break;

            case 5:
                var2 = "yellow";
                break;

            case 6:
                var2 = "purple";
                break;

            case 7:
                var2 = "grey";
                break;

            case 8:
                var2 = "white";
                break;

            default:
                var2 = "error";
        }

        return this.getItemName() + "." + var2;
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(int var1, CreativeTabs var2, List var3)
    {
        for (int var4 = 0; var4 < 8; ++var4)
        {
            var3.add(new ItemStack(var1, 1, var4));
        }
    }
}
