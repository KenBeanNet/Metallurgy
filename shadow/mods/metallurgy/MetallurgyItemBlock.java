package shadow.mods.metallurgy;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class MetallurgyItemBlock extends ItemBlock
{
    public MetallurgyItemBlock(int var1)
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

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4) {}

    public String getItemNameIS(ItemStack var1)
    {
        int var2 = var1.getItemDamage();
        return this.getItemName() + "." + var2;
    }
}
