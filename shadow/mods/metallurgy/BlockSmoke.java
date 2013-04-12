package shadow.mods.metallurgy;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockSmoke extends Block
{
    public boolean active;

    public BlockSmoke(int var1, Boolean var2)
    {
        super(var1, Material.wood);
        this.active = var2.booleanValue();
        this.setLightOpacity(3);
    }

    /**
     * Returns whether this block is collideable based on the arguments passed in Args: blockMetaData, unknownFlag
     */
    public boolean canCollideCheck(int var1, boolean var2)
    {
        return false;
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return null;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate());
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate()
    {
        return 20;
    }

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass()
    {
        return 1;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        super.updateTick(var1, var2, var3, var4, var5);

        if (!var1.isRemote)
        {
            if (this.active && var5.nextFloat() < 1.0F)
            {
                int var6 = var1.getBlockMetadata(var2, var3, var4);
                byte var7 = 15;

                if (var6 < var7)
                {
                    int[] var8 = new int[6];
                    int var9 = 0;

                    if (this.canPlaceBlockAt(var1, var2 + 1, var3, var4) && var6 < var7)
                    {
                        ++var6;
                        ++var9;
                        var8[0] = 1;
                    }

                    if (this.canPlaceBlockAt(var1, var2 - 1, var3, var4) && var6 < var7)
                    {
                        ++var6;
                        ++var9;
                        var8[1] = 1;
                    }

                    if (this.canPlaceBlockAt(var1, var2, var3 + 1, var4) && var6 < var7)
                    {
                        ++var6;
                        ++var9;
                        var8[2] = 1;
                    }

                    if (this.canPlaceBlockAt(var1, var2, var3 - 1, var4) && var6 < var7)
                    {
                        ++var6;
                        ++var9;
                        var8[3] = 1;
                    }

                    if (this.canPlaceBlockAt(var1, var2, var3, var4 + 1) && var6 < var7)
                    {
                        ++var6;
                        ++var9;
                        var8[4] = 1;
                    }

                    if (this.canPlaceBlockAt(var1, var2, var3, var4 - 1) && var6 < var7)
                    {
                        ++var6;
                        ++var9;
                        var8[5] = 1;
                    }

                    var6 = (int)((double)var7 - Math.ceil((double)(var7 - var6) / (double)var9));

                    if (var6 > 15)
                    {
                        var6 = 15;
                    }

                    if (var8[0] == 1)
                    {
                        var1.setBlockAndMetadata(var2 + 1, var3, var4, this.blockID, var6);
                    }

                    if (var8[1] == 1)
                    {
                        var1.setBlockAndMetadata(var2 - 1, var3, var4, this.blockID, var6);
                    }

                    if (var8[2] == 1)
                    {
                        var1.setBlockAndMetadata(var2, var3 + 1, var4, this.blockID, var6);
                    }

                    if (var8[3] == 1)
                    {
                        var1.setBlockAndMetadata(var2, var3 - 1, var4, this.blockID, var6);
                    }

                    if (var8[4] == 1)
                    {
                        var1.setBlockAndMetadata(var2, var3, var4 + 1, this.blockID, var6);
                    }

                    if (var8[5] == 1)
                    {
                        var1.setBlockAndMetadata(var2, var3, var4 - 1, this.blockID, var6);
                    }

                    if (var9 > 0)
                    {
                        var1.setBlock(var2, var3, var4, MetallurgyCore.smokeInactive.blockID);
                    }

                    var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate());
                }
            }
        }
    }
}
