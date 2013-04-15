package shadow.mods.metallurgy.nether;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class NF_BlockNetherForgeItem extends ItemBlock
{
    public NF_BlockNetherForgeItem(int var1)
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
                var2 = "IgnatiusForge";
                break;

            case 1:
                var2 = "Shadow IronForge";
                break;

            case 2:
                var2 = "Shadow SteelForge";
                break;

            case 3:
                var2 = "VyroxeresForge";
                break;

            case 4:
                var2 = "InolashiteForge";
                break;

            case 5:
                var2 = "KalendriteForge";
                break;

            case 6:
                var2 = "VulcaniteForge";
                break;

            case 7:
                var2 = "SanguiniteForge";
                break;

            default:
                var2 = "brick";
        }

        return this.getItemName() + "." + var2;
    }
}
