package shadow.mods.metallurgy.base;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockMetalLadder extends Block
{
    static int renderType = RenderingRegistry.getNextAvailableRenderId();

    protected BlockMetalLadder(int var1, int var2)
    {
        super(var1, Material.wood);
        this.blockIndexInTexture = var2;
        this.setRequiresSelfNotify();
        this.setCreativeTab(CreativeTabs.tabDecorations);
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
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return renderType;
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        return var1.isBlockSolidOnSide(var2 - 1, var3, var4, ForgeDirection.EAST) || var1.isBlockSolidOnSide(var2 + 1, var3, var4, ForgeDirection.WEST) || var1.isBlockSolidOnSide(var2, var3, var4 - 1, ForgeDirection.SOUTH) || var1.isBlockSolidOnSide(var2, var3, var4 + 1, ForgeDirection.NORTH);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        float var6 = 0.125F;

        if (var5 == 0)
        {
            this.setBlockBounds(0.0F, 0.0F, 1.0F - var6, 1.0F, 1.0F, 1.0F);
        }

        if (var5 == 1)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var6);
        }

        if (var5 == 2)
        {
            this.setBlockBounds(1.0F - var6, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

        if (var5 == 3)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, var6, 1.0F, 1.0F);
        }

        return super.getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4) % 4;
        float var6 = 0.125F;

        if (var5 == 0)
        {
            this.setBlockBounds(0.0F, 0.0F, 1.0F - var6, 1.0F, 1.0F, 1.0F);
        }

        if (var5 == 1)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var6);
        }

        if (var5 == 2)
        {
            this.setBlockBounds(1.0F - var6, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

        if (var5 == 3)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, var6, 1.0F, 1.0F);
        }

        return super.getSelectedBoundingBoxFromPool(var1, var2, var3, var4);
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        int var10 = var9 % 4;
        int var11 = var9 / 4;

        if ((var10 == 0 || var5 == 0) && var1.isBlockSolidOnSide(var2, var3, var4 + 1, ForgeDirection.NORTH))
        {
            var10 = 0;
        }
        else if ((var10 == 0 || var5 == 1) && var1.isBlockSolidOnSide(var2, var3, var4 - 1, ForgeDirection.SOUTH))
        {
            var10 = 1;
        }
        else if ((var10 == 0 || var5 == 2) && var1.isBlockSolidOnSide(var2 + 1, var3, var4, ForgeDirection.WEST))
        {
            var10 = 2;
        }
        else if ((var10 == 0 || var5 == 3) && var1.isBlockSolidOnSide(var2 - 1, var3, var4, ForgeDirection.EAST))
        {
            var10 = 3;
        }

        var1.setBlockMetadataWithNotify(var2, var3, var4, var11 * 4 + var10);
        return var11 * 4 + var10;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) / 4;
        int var7 = var1.getBlockMetadata(var2, var3, var4) % 4;
        boolean var8 = false;

        if (var7 == 0 && var1.isBlockSolidOnSide(var2, var3, var4 + 1, ForgeDirection.NORTH))
        {
            var8 = true;
        }

        if (var7 == 1 && var1.isBlockSolidOnSide(var2, var3, var4 - 1, ForgeDirection.SOUTH))
        {
            var8 = true;
        }

        if (var7 == 2 && var1.isBlockSolidOnSide(var2 + 1, var3, var4, ForgeDirection.WEST))
        {
            var8 = true;
        }

        if (var7 == 3 && var1.isBlockSolidOnSide(var2 - 1, var3, var4, ForgeDirection.EAST))
        {
            var8 = true;
        }

        if (!var8)
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var6 * 4 + var7, 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }

        super.onNeighborBlockChange(var1, var2, var3, var4, var5);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 1;
    }

    public boolean isLadder(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public String getTextureFile()
    {
        return "/shadow/deco.png";
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return var1 / 4;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int var1, int var2)
    {
        return this.blockIndexInTexture + var2 / 4;
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) / 4;
        float var7 = (float)var6 * 0.015F + 0.015F;

        if (var5.motionY >= 0.1D)
        {
            var5.setPosition(var5.posX, var5.posY + (double)var7, var5.posZ);
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        for (int var4 = 0; var4 < 4; ++var4)
        {
            var3.add(new ItemStack(this, 1, var4));
        }
    }
}
