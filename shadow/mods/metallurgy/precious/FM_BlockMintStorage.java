package shadow.mods.metallurgy.precious;

import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class FM_BlockMintStorage extends BlockContainer
{
    private Random random = new Random();

    public FM_BlockMintStorage(int var1)
    {
        super(var1, Material.wood);
        this.blockIndexInTexture = 1;
        this.setCreativeTab(MetallurgyPrecious.creativeTab);
    }

    public String getTextureFile()
    {
        return "/shadow/MetallurgyCoins.png";
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

        var1.getBlockMetadata(var2, var3, var4);
        TileEntity var9 = var1.getBlockTileEntity(var2, var3, var4);

        if (var9 instanceof FM_TileEntityMintStorage)
        {
            ((FM_TileEntityMintStorage)var9).setDirection(var6);
        }
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public int getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        var1.getBlockMetadata(var2, var3, var4);
        return var5 == 1 ? this.blockIndexInTexture : (var5 == 0 ? this.blockIndexInTexture + 32 : this.blockIndexInTexture + 16);
    }

    /**
     * Returns the block texture based on the side being looked at.  Args: side
     */
    public int getBlockTextureFromSide(int var1)
    {
        return var1 == 1 ? this.blockIndexInTexture : (var1 == 0 ? this.blockIndexInTexture + 16 : (var1 == 3 ? this.blockIndexInTexture + 16 : this.blockIndexInTexture + 16));
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        FM_TileEntityMintStorage var7 = (FM_TileEntityMintStorage)var1.getBlockTileEntity(var2, var3, var4);

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
        FM_TileEntityMintStorage var10 = (FM_TileEntityMintStorage)var1.getBlockTileEntity(var2, var3, var4);

        if (var10 == null)
        {
            return true;
        }
        else if (var1.isRemote)
        {
            return true;
        }
        else
        {
            var5.openGui(MetallurgyPrecious.instance, 2, var1, var2, var3, var4);
            return true;
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FM_TileEntityMintStorage();
    }
}
