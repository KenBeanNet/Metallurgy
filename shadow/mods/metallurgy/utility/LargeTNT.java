package shadow.mods.metallurgy.utility;

import net.minecraft.block.BlockTNT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class LargeTNT extends BlockTNT
{
    public LargeTNT(int var1, int var2)
    {
        super(var1, var2);
        this.setCreativeTab(MetallurgyUtility.creativeTab);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (var5.getCurrentEquippedItem() != null && this.isActivator(var5.getCurrentEquippedItem().itemID))
        {
            this.onBlockDestroyedByPlayer(var1, var2, var3, var4, 1);
            var1.setBlockWithNotify(var2, var3, var4, 0);
            return true;
        }
        else
        {
            return super.onBlockActivated(var1, var2, var3, var4, var5, var6, var7, var8, var9);
        }
    }

    public boolean isActivator(int var1)
    {
        return var1 == Item.flintAndSteel.itemID || var1 == MetallurgyUtility.match.itemID || var1 == MetallurgyUtility.igniter.itemID;
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4)
    {
        if (!var1.isRemote)
        {
            EntityLargeTNTPrimed var5 = new EntityLargeTNTPrimed(var1, (double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F));
            var5.fuse = var1.rand.nextInt(var5.fuse / 4) + var5.fuse / 8;
            var1.spawnEntityInWorld(var5);
        }
    }

    /**
     * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
     */
    public void onBlockDestroyedByPlayer(World var1, int var2, int var3, int var4, int var5)
    {
        if (!var1.isRemote && (var5 & 1) == 1)
        {
            EntityLargeTNTPrimed var6 = new EntityLargeTNTPrimed(var1, (double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F));
            var1.spawnEntityInWorld(var6);
            var1.playSoundAtEntity(var6, "random.fuse", 1.0F, 1.0F);
        }
    }
}
