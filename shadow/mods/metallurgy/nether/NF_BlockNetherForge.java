package shadow.mods.metallurgy.nether;

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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class NF_BlockNetherForge extends BlockContainer
{
    private Random furnaceRand = new Random();
    private final boolean isActive;
    private static boolean keepFurnaceInventory = false;

    protected NF_BlockNetherForge(int var1, boolean var2)
    {
        super(var1, Material.rock);
        this.isActive = var2;
        this.setRequiresSelfNotify();
        this.setCreativeTab(MetallurgyNether.creativeTab);
    }

    public String getTextureFile()
    {
        return "/shadow/MetallurgyNetherForges.png";
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return ConfigNether.furnaceID;
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
        return var1 != 1 && var1 != 0 ? (var1 != 3 ? 9 + var2 * 16 : 0 + var2 * 16) : 14 + var2 * 16;
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public int getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        TileEntity var6 = var1.getBlockTileEntity(var2, var3, var4);
        int var7 = var1.getBlockMetadata(var2, var3, var4);
        int var8 = var7 < 8 ? var7 : var7 - 8;
        int var9 = var6 instanceof NF_TileEntityNetherForge ? ((NF_TileEntityNetherForge)var6).getDirection() : 0;

        if (var6 instanceof NF_TileEntityNetherForge)
        {
            int var10000 = ((NF_TileEntityNetherForge)var6).furnaceCookTime * 10 % 2;
        }
        else
        {
            boolean var13 = false;
        }

        int var11 = var6 instanceof NF_TileEntityNetherForge ? ((NF_TileEntityNetherForge)var6).getScaledFuel(4) : 0;
        boolean var12 = var6 instanceof NF_TileEntityNetherForge ? ((NF_TileEntityNetherForge)var6).isBurning() : false;
        return var5 == 1 ? (var7 >= 8 ? 15 + var8 * 16 : 14 + var8 * 16) : (var5 == 0 ? 240 + var8 : (var5 != var9 ? 9 + var11 + var8 * 16 : (var12 ? 4 + var11 + var8 * 16 : 0 + var11 + var8 * 16)));
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        var1.getBlockMetadata(var2, var3, var4);
        NF_TileEntityNetherForge var7 = (NF_TileEntityNetherForge)var1.getBlockTileEntity(var2, var3, var4);

        if (var7.isBurning())
        {
            int var8 = ((NF_TileEntityNetherForge)((NF_TileEntityNetherForge)var1.getBlockTileEntity(var2, var3, var4))).getDirection();
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
            ;
        }

        if (var5.isSneaking())
        {
            return false;
        }
        else
        {
            ItemStack var10 = var5.inventory.getCurrentItem();
            NF_TileEntityNetherForge var11 = (NF_TileEntityNetherForge)var1.getBlockTileEntity(var2, var3, var4);
            var5.sendChatToPlayer("Fuel: " + var11.fuel);
            var5.sendChatToPlayer("MaxFuel: " + var11.maxFuel);

            if (var10 != null)
            {
                if (var10.itemID == Item.bucketLava.itemID)
                {
                    if (var11.fuel == var11.maxFuel)
                    {
                        return false;
                    }

                    var11.addFuelBucket();
                    var5.inventory.setInventorySlotContents(var5.inventory.currentItem, new ItemStack(Item.bucketEmpty, 1));
                    return true;
                }

                if (var10.itemID == Item.bucketEmpty.itemID)
                {
                    if (var11.fuel < 1000)
                    {
                        return false;
                    }

                    var11.addTakeBucket();
                    --var10.stackSize;

                    if (!var5.inventory.addItemStackToInventory(new ItemStack(Item.bucketLava)))
                    {
                        var5.dropPlayerItem(new ItemStack(Item.bucketLava.itemID, 1, 0));
                    }

                    return true;
                }
            }

            if (var11 != null)
            {
                var5.openGui(MetallurgyNether.instance, 0, var1, var2, var3, var4);
            }

            return true;
        }
    }

    public static void updateFurnaceBlockState(boolean var0, World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        keepFurnaceInventory = true;

        if (var0 && var5 < 8)
        {
            var1.setBlockMetadata(var2, var3, var4, var5 + 8);
        }
        else if (!var0 && var5 >= 8)
        {
            var1.setBlockMetadata(var2, var3, var4, var5 - 8);
        }

        keepFurnaceInventory = false;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new NF_TileEntityNetherForge();
    }

    /**
     * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
     */
    public void onBlockClicked(World var1, int var2, int var3, int var4, EntityPlayer var5) {}

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5)
    {
        int var6 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var6 == 0)
        {
            ((NF_TileEntityNetherForge)((NF_TileEntityNetherForge)var1.getBlockTileEntity(var2, var3, var4))).setDirection(2);
        }

        if (var6 == 1)
        {
            ((NF_TileEntityNetherForge)((NF_TileEntityNetherForge)var1.getBlockTileEntity(var2, var3, var4))).setDirection(5);
        }

        if (var6 == 2)
        {
            ((NF_TileEntityNetherForge)((NF_TileEntityNetherForge)var1.getBlockTileEntity(var2, var3, var4))).setDirection(3);
        }

        if (var6 == 3)
        {
            ((NF_TileEntityNetherForge)((NF_TileEntityNetherForge)var1.getBlockTileEntity(var2, var3, var4))).setDirection(4);
        }

        NF_TileEntityNetherForge var7 = (NF_TileEntityNetherForge)var1.getBlockTileEntity(var2, var3, var4);
        int var8 = var1.getBlockMetadata(var2, var3, var4);
        var7.setSpeed((int)(20.0F * ConfigNether.speeds[var8]));
        var7.setMaxBuckets(ConfigNether.buckets[var8]);
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        boolean var7 = false;

        if (((NF_TileEntityNetherForge)var1.getBlockTileEntity(var2, var3, var4)).getFuelScaled(2) > 0)
        {
            var7 = true;
        }

        if (!keepFurnaceInventory)
        {
            NF_TileEntityNetherForge var8 = (NF_TileEntityNetherForge)var1.getBlockTileEntity(var2, var3, var4);

            if (var8 != null)
            {
                for (int var9 = 0; var9 < var8.getSizeInventory(); ++var9)
                {
                    ItemStack var10 = var8.getStackInSlot(var9);

                    if (var10 != null)
                    {
                        float var11 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                        float var12 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                        float var13 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;

                        while (var10.stackSize > 0)
                        {
                            int var14 = this.furnaceRand.nextInt(21) + 10;

                            if (var14 > var10.stackSize)
                            {
                                var14 = var10.stackSize;
                            }

                            var10.stackSize -= var14;
                            EntityItem var15 = new EntityItem(var1, (double)((float)var2 + var11), (double)((float)var3 + var12), (double)((float)var4 + var13), new ItemStack(var10.itemID, var14, var10.getItemDamage()));

                            if (var10.hasTagCompound())
                            {
                                var15.getEntityItem().setTagCompound((NBTTagCompound)var10.getTagCompound().copy());
                            }

                            float var16 = 0.05F;
                            var15.motionX = (double)((float)this.furnaceRand.nextGaussian() * var16);
                            var15.motionY = (double)((float)this.furnaceRand.nextGaussian() * var16 + 0.2F);
                            var15.motionZ = (double)((float)this.furnaceRand.nextGaussian() * var16);
                            var1.spawnEntityInWorld(var15);
                        }
                    }
                }
            }
        }

        super.breakBlock(var1, var2, var3, var4, var5, var6);

        if (ConfigNether.smelterDropsLava && var7)
        {
            var1.setBlockWithNotify(var2, var3, var4, Block.lavaMoving.blockID);
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
