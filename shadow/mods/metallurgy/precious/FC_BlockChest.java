package shadow.mods.metallurgy.precious;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class FC_BlockChest extends BlockContainer
{
    private Random random = new Random();
    private int renderId = RenderingRegistry.getNextAvailableRenderId();

    public FC_BlockChest(int var1)
    {
        super(var1, Material.wood);
        this.blockIndexInTexture = 2;
        this.setCreativeTab(MetallurgyPrecious.creativeTab);
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
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return var1;
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
        return this.renderId;
    }

    public String getTextureFile()
    {
        return "/shadow/MetallurgyFantasyMetals.png";
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5)
    {
        byte var6 = 0;
        int var7 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var7 == 0)
        {
            var6 = 2;
        }

        if (var7 == 1)
        {
            var6 = 5;
        }

        if (var7 == 2)
        {
            var6 = 3;
        }

        if (var7 == 3)
        {
            var6 = 4;
        }

        int var8 = var1.getBlockMetadata(var2, var3, var4);
        TileEntity var9 = var1.getBlockTileEntity(var2, var3, var4);

        if (var9 instanceof FC_TileEntityChest)
        {
            ((FC_TileEntityChest)var9).setDirection(var6);
            ((FC_TileEntityChest)var9).setType(var8);
        }
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public int getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        var1.getBlockMetadata(var2, var3, var4);
        return var5 == 1 ? this.blockIndexInTexture - 1 : (var5 == 0 ? this.blockIndexInTexture - 1 : 3);
    }

    /**
     * Returns the block texture based on the side being looked at.  Args: side
     */
    public int getBlockTextureFromSide(int var1)
    {
        return var1 == 1 ? this.blockIndexInTexture - 1 : (var1 == 0 ? this.blockIndexInTexture - 1 : (var1 == 3 ? this.blockIndexInTexture + 1 : this.blockIndexInTexture));
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        FC_TileEntityChest var7 = (FC_TileEntityChest)var1.getBlockTileEntity(var2, var3, var4);

        if (var7 != null)
        {
            for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
            {
                ItemStack var9 = var7.getStackInSlot(var8);

                if (var9 != null)
                {
                    float var10 = this.random.nextFloat() * 0.8F + 0.1F;
                    float var11 = this.random.nextFloat() * 0.8F + 0.1F;
                    EntityItem var12;

                    for (float var13 = this.random.nextFloat() * 0.8F + 0.1F; var9.stackSize > 0; var1.spawnEntityInWorld(var12))
                    {
                        int var14 = this.random.nextInt(21) + 10;

                        if (var14 > var9.stackSize)
                        {
                            var14 = var9.stackSize;
                        }

                        var9.stackSize -= var14;
                        var12 = new EntityItem(var1, (double)((float)var2 + var10), (double)((float)var3 + var11), (double)((float)var4 + var13), new ItemStack(var9.itemID, var14, var9.getItemDamage()));
                        float var15 = 0.05F;
                        var12.motionX = (double)((float)this.random.nextGaussian() * var15);
                        var12.motionY = (double)((float)this.random.nextGaussian() * var15 + 0.2F);
                        var12.motionZ = (double)((float)this.random.nextGaussian() * var15);

                        if (var9.hasTagCompound())
                        {
                            var12.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                        }
                    }
                }
            }
        }

        super.breakBlock(var1, var2, var3, var4, var5, var6);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        FC_TileEntityChest var10 = (FC_TileEntityChest)var1.getBlockTileEntity(var2, var3, var4);

        if (var10 == null)
        {
            return true;
        }
        else if (var1.isBlockSolidOnSide(var2, var3 + 1, var4, ForgeDirection.DOWN))
        {
            return true;
        }
        else if (isOcelotBlockingChest(var1, var2, var3, var4))
        {
            return true;
        }
        else if (var1.isRemote)
        {
            return true;
        }
        else
        {
            var5.openGui(MetallurgyPrecious.instance, -1, var1, var2, var3, var4);
            return true;
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FC_TileEntityChest();
    }

    private static boolean isOcelotBlockingChest(World var0, int var1, int var2, int var3)
    {
        Iterator var4 = var0.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double)var1, (double)(var2 + 1), (double)var3, (double)(var1 + 1), (double)(var2 + 2), (double)(var3 + 1))).iterator();

        while (var4.hasNext())
        {
            Entity var6 = (Entity)var4.next();
            EntityOcelot var5 = (EntityOcelot)var6;

            if (var5.isSitting())
            {
                return true;
            }
        }

        return false;
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        for (int var4 = 0; var4 < 5; ++var4)
        {
            var3.add(new ItemStack(this, 1, var4));
        }
    }
}
