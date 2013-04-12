package shadow.mods.metallurgy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class MetallurgyBlock extends Block
{
    public String texturePath;
    public int row;
    public int numTypes;
    public boolean collisionEffect;
    public List clList = new ArrayList();
    public boolean displayEffect;
    public List dlList = new ArrayList();
    public boolean isMetalBlock = false;

    public MetallurgyBlock(int var1, String var2, int var3, int var4)
    {
        super(var1, Material.iron);
        this.texturePath = var2;
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.numTypes = var3;
        this.row = var4;
    }

    public void addCollisionListener(CollisionListener var1)
    {
        this.collisionEffect = true;
        this.clList.add(var1);
    }

    public void addDisplayListener(DisplayListener var1)
    {
        this.setTickRandomly(true);
        this.displayEffect = true;
        this.dlList.add(var1);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return this.blockID;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int var1, int var2)
    {
        return this.isMetalBlock ? 16 * var2 + 15 : var2 + this.row * 16;
    }

    public String getTextureFile()
    {
        return this.texturePath;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return var1;
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        if (this.collisionEffect)
        {
            float var5 = 0.025F;
            return AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double)var2, (double)var3, (double)var4, (double)(var2 + 1), (double)((float)(var3 + 1) - var5), (double)(var4 + 1));
        }
        else
        {
            return super.getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
        }
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        Iterator var7 = this.clList.iterator();

        while (var7.hasNext())
        {
            CollisionListener var8 = (CollisionListener)var7.next();
            var8.collide(var1, var2, var3, var4, var5, var6);
        }
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        Iterator var6 = this.dlList.iterator();

        while (var6.hasNext())
        {
            DisplayListener var7 = (DisplayListener)var6.next();
            var7.randomDisplayTick(var1, var2, var3, var4, var5);
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        for (int var4 = 0; var4 < this.numTypes; ++var4)
        {
            var3.add(new ItemStack(this, 1, var4));
        }
    }
}
