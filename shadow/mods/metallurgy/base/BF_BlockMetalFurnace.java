package shadow.mods.metallurgy.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BF_BlockMetalFurnace extends BlockContainer
{
    private Random furnaceRand = new Random();
    private final boolean isActive;
    private static boolean keepFurnaceInventory = false;

    protected BF_BlockMetalFurnace(int var1, boolean var2)
    {
        super(var1, Material.rock);
        this.isActive = var2;
        this.setRequiresSelfNotify();
        this.setCreativeTab(MetallurgyBaseMetals.baseTab);
    }

    public String getTextureFile()
    {
        return "/shadow/MetallurgyFurnaces.png";
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return ConfigBase.furnaceID;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return var1 < 8 ? var1 : var1 - 8;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int var1, int var2)
    {
        var2 = var2 < 8 ? var2 : var2 - 8;
        ++var2;
        return var1 != 1 && var1 != 0 ? (var1 != 3 ? 1 + var2 * 16 : 0 + var2 * 16) : 2 + var2 * 16;
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public int getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        TileEntity var6 = var1.getBlockTileEntity(var2, var3, var4);
        int var7 = var1.getBlockMetadata(var2, var3, var4);
        int var8 = var7 < 8 ? var7 : var7 - 8;
        int var9 = var6 instanceof BF_TileEntityMetalFurnace ? ((BF_TileEntityMetalFurnace)var6).getDirection() : 0;
        int var10 = var6 instanceof BF_TileEntityMetalFurnace ? ((BF_TileEntityMetalFurnace)var6).furnaceCookTime * 10 % 2 : 0;
        boolean var11 = var6 instanceof BF_TileEntityMetalFurnace ? ((BF_TileEntityMetalFurnace)var6).isBurning() : false;
        return var5 != 1 && var5 != 0 ? (var5 != var9 ? 1 + (var8 + 1) * 16 : (var11 ? 3 + (var8 + 1) * 16 + var10 : 0 + (var8 + 1) * 16)) : 2 + (var8 + 1) * 16;
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        var1.getBlockMetadata(var2, var3, var4);
        BF_TileEntityMetalFurnace var7 = (BF_TileEntityMetalFurnace)var1.getBlockTileEntity(var2, var3, var4);

        if (var7.isBurning())
        {
            int var8 = var7.getDirection();
            float var9 = (float)var2 + 0.5F;
            float var10 = (float)var3 + 0.0F + var5.nextFloat() * 6.0F / 16.0F;
            float var11 = (float)var4 + 0.5F;
            float var12 = 0.52F;
            float var13 = var5.nextFloat() * 0.6F - 0.3F;

            if (var8 == 4)
            {
                var1.spawnParticle("smoke", (double)(var9 - var12), (double)var10, (double)(var11 + var13), 0.0D, 0.0D, 0.0D);
                var1.spawnParticle("flame", (double)(var9 - var12), (double)var10, (double)(var11 + var13), 0.0D, 0.0D, 0.0D);
            }
            else if (var8 == 5)
            {
                var1.spawnParticle("smoke", (double)(var9 + var12), (double)var10, (double)(var11 + var13), 0.0D, 0.0D, 0.0D);
                var1.spawnParticle("flame", (double)(var9 + var12), (double)var10, (double)(var11 + var13), 0.0D, 0.0D, 0.0D);
            }
            else if (var8 == 2)
            {
                var1.spawnParticle("smoke", (double)(var9 + var13), (double)var10, (double)(var11 - var12), 0.0D, 0.0D, 0.0D);
                var1.spawnParticle("flame", (double)(var9 + var13), (double)var10, (double)(var11 - var12), 0.0D, 0.0D, 0.0D);
            }
            else if (var8 == 3)
            {
                var1.spawnParticle("smoke", (double)(var9 + var13), (double)var10, (double)(var11 + var12), 0.0D, 0.0D, 0.0D);
                var1.spawnParticle("flame", (double)(var9 + var13), (double)var10, (double)(var11 + var12), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    /**
     * Returns the block texture based on the side being looked at.  Args: side
     */
    public int getBlockTextureFromSide(int var1)
    {
        return var1 == 1 ? this.blockIndexInTexture + 17 : (var1 == 0 ? this.blockIndexInTexture + 17 : (var1 == 3 ? this.blockIndexInTexture - 1 : this.blockIndexInTexture));
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (var1.isRemote)
        {
            return true;
        }
        else if (var5.isSneaking())
        {
            return false;
        }
        else
        {
            BF_TileEntityMetalFurnace var10 = (BF_TileEntityMetalFurnace)var1.getBlockTileEntity(var2, var3, var4);

            if (var10 != null)
            {
                var5.openGui(MetallurgyBaseMetals.instance, 0, var1, var2, var3, var4);
            }

            return true;
        }
    }

    public static void updateFurnaceBlockState(boolean var0, World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);

        if (!var0 && var5 >= 8)
        {
            var1.setBlockMetadata(var2, var3, var4, var5 - 8);
        }
        else if (var0 && var5 < 8)
        {
            var1.setBlockMetadata(var2, var3, var4, var5 + 8);
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new BF_TileEntityMetalFurnace();
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5)
    {
        int var6 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var6 == 0)
        {
            ((BF_TileEntityMetalFurnace)((BF_TileEntityMetalFurnace)var1.getBlockTileEntity(var2, var3, var4))).setDirection(2);
        }

        if (var6 == 1)
        {
            ((BF_TileEntityMetalFurnace)((BF_TileEntityMetalFurnace)var1.getBlockTileEntity(var2, var3, var4))).setDirection(5);
        }

        if (var6 == 2)
        {
            ((BF_TileEntityMetalFurnace)((BF_TileEntityMetalFurnace)var1.getBlockTileEntity(var2, var3, var4))).setDirection(3);
        }

        if (var6 == 3)
        {
            ((BF_TileEntityMetalFurnace)((BF_TileEntityMetalFurnace)var1.getBlockTileEntity(var2, var3, var4))).setDirection(4);
        }

        BF_TileEntityMetalFurnace var7 = (BF_TileEntityMetalFurnace)var1.getBlockTileEntity(var2, var3, var4);
        int var8 = var1.getBlockMetadata(var2, var3, var4);

        switch (var8)
        {
            case 0:
                var7.setSpeed((int)(20.0D * ConfigBase.copperSpeed));
                break;

            case 1:
                var7.setSpeed((int)(20.0D * ConfigBase.bronzeSpeed));
                break;

            case 2:
                var7.setSpeed((int)(20.0D * ConfigBase.ironSpeed));
                break;

            case 3:
                var7.setSpeed((int)(20.0D * ConfigBase.steelSpeed));
        }
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        if (!keepFurnaceInventory)
        {
            BF_TileEntityMetalFurnace var7 = (BF_TileEntityMetalFurnace)var1.getBlockTileEntity(var2, var3, var4);

            if (var7 != null)
            {
                for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
                {
                    ItemStack var9 = var7.getStackInSlot(var8);

                    if (var9 != null)
                    {
                        float var10 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                        float var11 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                        float var12 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;

                        while (var9.stackSize > 0)
                        {
                            int var13 = this.furnaceRand.nextInt(21) + 10;

                            if (var13 > var9.stackSize)
                            {
                                var13 = var9.stackSize;
                            }

                            var9.stackSize -= var13;
                            EntityItem var14 = new EntityItem(var1, (double)((float)var2 + var10), (double)((float)var3 + var11), (double)((float)var4 + var12), new ItemStack(var9.itemID, var13, var9.getItemDamage()));

                            if (var9.hasTagCompound())
                            {
                                var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                            }

                            float var15 = 0.05F;
                            var14.motionX = (double)((float)this.furnaceRand.nextGaussian() * var15);
                            var14.motionY = (double)((float)this.furnaceRand.nextGaussian() * var15 + 0.2F);
                            var14.motionZ = (double)((float)this.furnaceRand.nextGaussian() * var15);
                            var1.spawnEntityInWorld(var14);
                        }
                    }
                }
            }
        }

        super.breakBlock(var1, var2, var3, var4, var5, var6);
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
