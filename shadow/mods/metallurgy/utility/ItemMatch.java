package shadow.mods.metallurgy.utility;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMatch extends Item
{
    public ItemMatch(int var1, String var2)
    {
        super(var1);
        this.setCreativeTab(MetallurgyUtility.creativeTab);
        this.setTextureFile(var2);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (var3.getBlockId(var4, var5, var6) == Block.tnt.blockID)
        {
            Block.tnt.onBlockDestroyedByPlayer(var3, var4, var5, var6, 1);
            var3.setBlock(var4, var5, var6, 0);
            return true;
        }
        else
        {
            if (var7 == 0)
            {
                --var5;
            }

            if (var7 == 1)
            {
                ++var5;
            }

            if (var7 == 2)
            {
                --var6;
            }

            if (var7 == 3)
            {
                ++var6;
            }

            if (var7 == 4)
            {
                --var4;
            }

            if (var7 == 5)
            {
                ++var4;
            }

            if (!var2.canPlayerEdit(var4, var5, var6, var7, var1))
            {
                return false;
            }
            else
            {
                int var11 = var3.getBlockId(var4, var5, var6);

                if (var11 == 0)
                {
                    var3.playSoundEffect((double)var4 + 0.5D, (double)var5 + 0.5D, (double)var6 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                    var3.setBlockWithNotify(var4, var5, var6, Block.fire.blockID);
                }

                --var1.stackSize;
                return true;
            }
        }
    }
}
