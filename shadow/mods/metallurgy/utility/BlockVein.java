package shadow.mods.metallurgy.utility;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockVein extends Block
{
    public String texturePath;

    public BlockVein(int var1, String var2, Material var3)
    {
        super(var1, Material.iron);
        this.texturePath = var2;
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public BlockVein setHardness(float var1)
    {
        return (BlockVein)super.setHardness(var1);
    }

    public BlockVein setResistance(float var1)
    {
        return (BlockVein)super.setResistance(var1);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return var1 == 0 ? MetallurgyUtility.phosphorus.itemID : (var1 == 1 ? MetallurgyUtility.sulfur.itemID : (var1 == 2 ? MetallurgyUtility.saltpeter.itemID : (var1 == 3 ? MetallurgyUtility.magnesium.itemID : (var1 == 4 ? MetallurgyUtility.bitumen.itemID : (var1 == 5 ? MetallurgyUtility.potash.itemID : this.blockID)))));
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, var7);

        if (this.idDropped(var5, var1.rand, var7) != this.blockID)
        {
            boolean var8 = false;
            int var9 = MathHelper.getRandomIntegerInRange(var1.rand, 0, 3);
            this.dropXpOnBlockBreak(var1, var2, var3, var4, var9);
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return 0;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 1 + var1.nextInt(4);
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int var1, int var2)
    {
        return var2;
    }

    public String getTextureFile()
    {
        return this.texturePath;
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        for (int var4 = 0; var4 < 6; ++var4)
        {
            var3.add(new ItemStack(this, 1, var4));
        }
    }

    /**
     * Sets how many hits it takes to break a block.
     */
    public Block setHardness(float var1)
    {
        return this.setHardness(var1);
    }

    /**
     * Sets the the blocks resistance to explosions. Returns the object for convenience in constructing.
     */
    public Block setResistance(float var1)
    {
        return this.setResistance(var1);
    }
}
