package shadow.mods.metallurgy.base;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockLantern extends BlockContainer
{
    public int renderId = RenderingRegistry.getNextAvailableRenderId();
    private int color;

    protected BlockLantern(int var1)
    {
        super(var1, Material.wood);
        this.setBlockBounds(0.1875F, 0.0F, 0.1875F, 0.8125F, 0.8125F, 0.8125F);
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return this.renderId;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass()
    {
        return 1;
    }

    public TileEntity createTileEntity(World var1, int var2)
    {
        TileEntityLantern var3 = new TileEntityLantern();
        return var3;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return null;
    }

    private boolean canPlaceTorchOn(World var1, int var2, int var3, int var4)
    {
        if (var1.doesBlockHaveSolidTopSurface(var2, var3, var4))
        {
            return true;
        }
        else
        {
            int var5 = var1.getBlockId(var2, var3, var4);
            return Block.blocksList[var5] != null && Block.blocksList[var5].canPlaceTorchOnTop(var1, var2, var3, var4);
        }
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        return var1.isBlockSolidOnSide(var2 - 1, var3, var4, ForgeDirection.EAST, true) || var1.isBlockSolidOnSide(var2 + 1, var3, var4, ForgeDirection.WEST, true) || var1.isBlockSolidOnSide(var2, var3, var4 - 1, ForgeDirection.SOUTH, true) || var1.isBlockSolidOnSide(var2, var3, var4 + 1, ForgeDirection.NORTH, true) || var1.isBlockSolidOnSide(var2, var3 + 1, var4, ForgeDirection.DOWN, true) || var1.getBlockId(var2, var3 + 1, var4) == Block.fence.blockID || this.canPlaceTorchOn(var1, var2, var3 - 1, var4);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        boolean var7 = false;

        if (!var1.isBlockNormalCube(var2, var3 - 1, var4) && var6 == 0)
        {
            var7 = true;
        }

        if (!var1.isBlockNormalCube(var2, var3 + 1, var4) && var1.getBlockId(var2, var3 + 1, var4) != Block.fence.blockID && var6 == 5)
        {
            var7 = true;
        }
        else if (!var1.isBlockNormalCube(var2 + 1, var3, var4) && var6 == 1)
        {
            var7 = true;
        }
        else if (!var1.isBlockNormalCube(var2 - 1, var3, var4) && var6 == 3)
        {
            var7 = true;
        }
        else if (!var1.isBlockNormalCube(var2, var3, var4 + 1) && var6 == 2)
        {
            var7 = true;
        }
        else if (!var1.isBlockNormalCube(var2, var3, var4 - 1) && var6 == 4)
        {
            var7 = true;
        }

        if (var7)
        {
            var1.setBlockAndMetadata(var2, var3, var4, 0, 0);
        }
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5)
    {
        byte var6 = 0;

        if (!var1.isBlockNormalCube(var2, var3 + 1, var4) && var1.getBlockId(var2, var3 + 1, var4) != Block.fence.blockID)
        {
            if (var1.isBlockNormalCube(var2 + 1, var3, var4))
            {
                var6 = 1;
            }
            else if (var1.isBlockNormalCube(var2 - 1, var3, var4))
            {
                var6 = 3;
            }
            else if (var1.isBlockNormalCube(var2, var3, var4 + 1))
            {
                var6 = 2;
            }
            else if (var1.isBlockNormalCube(var2, var3, var4 - 1))
            {
                var6 = 4;
            }
        }
        else
        {
            var6 = 5;
        }

        var1.setBlockAndMetadata(var2, var3, var4, this.blockID, var6);
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World var1, int var2, int var3, int var4)
    {
        TileEntityLantern var5 = (TileEntityLantern)var1.getBlockTileEntity(var2, var3, var4);
        return var5.color;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 0;
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        this.spawnItem(var1, var2, var3, var4);
        super.breakBlock(var1, var2, var3, var4, var5, var6);
    }

    public void spawnItem(World var1, int var2, int var3, int var4)
    {
        TileEntity var5 = var1.getBlockTileEntity(var2, var3, var4);
        int var6 = var1.getBlockId(var2, var3, var4);

        if (var5 != null && var6 == 0)
        {
            short var7 = ((TileEntityLantern)var5).color;
            ItemStack var8 = new ItemStack(this, 1, var7);
            float var9 = 0.7F;
            double var10 = (double)(var1.rand.nextFloat() * var9) + (double)(1.0F - var9) * 0.5D;
            double var12 = (double)(var1.rand.nextFloat() * var9) + (double)(1.0F - var9) * 0.5D;
            double var14 = (double)(var1.rand.nextFloat() * var9) + (double)(1.0F - var9) * 0.5D;
            EntityItem var16 = new EntityItem(var1, (double)var2 + var10, (double)var3 + var12, (double)var4 + var14, var8);
            var16.delayBeforeCanPickup = 10;

            if (!var1.isRemote)
            {
                var1.spawnEntityInWorld(var16);
            }
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        for (int var4 = 0; var4 < 8; ++var4)
        {
            var3.add(new ItemStack(this, 1, var4));
        }
    }
}
