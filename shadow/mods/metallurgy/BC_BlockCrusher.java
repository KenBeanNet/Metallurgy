package shadow.mods.metallurgy;

import cpw.mods.fml.client.registry.RenderingRegistry;
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

public class BC_BlockCrusher extends BlockContainer
{
    private Random furnaceRand = new Random();
    private final boolean isActive;
    private int renderId = RenderingRegistry.getNextAvailableRenderId();
    private static boolean keepFurnaceInventory = false;

    protected BC_BlockCrusher(int var1, boolean var2)
    {
        super(var1, Material.rock);
        this.isActive = var2;
        this.setRequiresSelfNotify();
    }

    public String getTextureFile()
    {
        return "/shadow/MetallurgyFurnaces.png";
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

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return MetallurgyCore.crusher.blockID;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return var1 < 8 ? var1 : var1 - 8;
    }

    public int getLightValue(IBlockAccess var1, int var2, int var3, int var4)
    {
        BC_TileEntityCrusher var5 = (BC_TileEntityCrusher)((BC_TileEntityCrusher)var1.getBlockTileEntity(var2, var3, var4));
        return var5.isBurning() ? 12 : 0;
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        BC_TileEntityCrusher var6 = (BC_TileEntityCrusher)((BC_TileEntityCrusher)var1.getBlockTileEntity(var2, var3, var4));
        var1.getBlockMetadata(var2, var3, var4);

        if (var6.isBurning())
        {
            int var8 = var6.getDirection();
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
            BC_TileEntityCrusher var10 = (BC_TileEntityCrusher)var1.getBlockTileEntity(var2, var3, var4);
            int var11 = var1.getBlockMetadata(var2, var3, var4);

            if (var10 != null)
            {
                var5.openGui(MetallurgyCore.instance, var11, var1, var2, var3, var4);
            }

            return true;
        }
    }

    public static void updateFurnaceBlockState(boolean var0, World var1, int var2, int var3, int var4) {}

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return null;
    }

    public TileEntity createTileEntity(World var1, int var2)
    {
        BC_TileEntityCrusher var3 = new BC_TileEntityCrusher();
        var2 = var2 > 8 ? var2 : var2 - 8;

        switch (var2)
        {
            case 0:
                var3.setSpeed((int)(20.0D * CoreConfig.stoneCrusherSpeed));
                break;

            case 1:
                var3.setSpeed((int)(20.0D * CoreConfig.copperCrusherSpeed));
                break;

            case 2:
                var3.setSpeed((int)(20.0D * CoreConfig.bronzeCrusherSpeed));
                break;

            case 3:
                var3.setSpeed((int)(20.0D * CoreConfig.ironCrusherSpeed));
                break;

            case 4:
                var3.setSpeed((int)(20.0D * CoreConfig.steelCrusherSpeed));
        }

        return var3;
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5)
    {
        int var6 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var6 == 0)
        {
            ((BC_TileEntityCrusher)((BC_TileEntityCrusher)var1.getBlockTileEntity(var2, var3, var4))).setDirection(2);
        }

        if (var6 == 1)
        {
            ((BC_TileEntityCrusher)((BC_TileEntityCrusher)var1.getBlockTileEntity(var2, var3, var4))).setDirection(5);
        }

        if (var6 == 2)
        {
            ((BC_TileEntityCrusher)((BC_TileEntityCrusher)var1.getBlockTileEntity(var2, var3, var4))).setDirection(3);
        }

        if (var6 == 3)
        {
            ((BC_TileEntityCrusher)((BC_TileEntityCrusher)var1.getBlockTileEntity(var2, var3, var4))).setDirection(4);
        }

        BC_TileEntityCrusher var7 = (BC_TileEntityCrusher)var1.getBlockTileEntity(var2, var3, var4);
        int var8 = var1.getBlockMetadata(var2, var3, var4);

        switch (var8)
        {
            case 0:
                var7.setSpeed((int)(20.0D * CoreConfig.stoneCrusherSpeed));
                break;

            case 1:
                var7.setSpeed((int)(20.0D * CoreConfig.copperCrusherSpeed));
                break;

            case 2:
                var7.setSpeed((int)(20.0D * CoreConfig.bronzeCrusherSpeed));
                break;

            case 3:
                var7.setSpeed((int)(20.0D * CoreConfig.ironCrusherSpeed));
                break;

            case 4:
                var7.setSpeed((int)(20.0D * CoreConfig.steelCrusherSpeed));
        }
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        if (!var1.isRemote)
        {
            if (!keepFurnaceInventory)
            {
                BC_TileEntityCrusher var7 = (BC_TileEntityCrusher)var1.getBlockTileEntity(var2, var3, var4);

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
