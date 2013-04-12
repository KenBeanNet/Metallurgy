package shadow.mods.metallurgy;

import java.util.Random;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import shadow.mods.metallurgy.base.MetallurgyBaseMetals;

public class BlockDoorMetal extends BlockDoor
{
    public int itemID;

    public BlockDoorMetal(int var1, Material var2, int var3)
    {
        super(var1, var2);
        this.itemID = var3;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return (var1 & 8) != 0 ? 0 : (this.blockMaterial == Material.iron ? MetallurgyBaseMetals.copperItemDoor.itemID : MetallurgyBaseMetals.copperItemDoor.itemID);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        System.out.println("check");

        if (var1.isBlockIndirectlyGettingPowered(var2, var3, var4))
        {
            System.out.println("powerered");
        }

        super.onNeighborBlockChange(var1, var2, var3, var4, var5);
    }

    /**
     * A function to open a door.
     */
    public void onPoweredBlockChange(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.getFullMetadata(var1, var2, var3, var4);
        boolean var7 = (var6 & 4) != 0;
        System.out.println("changing block state" + var6 + " " + var7 + " " + var5);

        if (var7 != var5)
        {
            System.out.println("needs to be changed");
            int var8 = var6 & 7;
            var8 ^= 4;

            if ((var6 & 8) == 0)
            {
                var1.setBlockMetadataWithNotify(var2, var3, var4, var8);
                var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
            }
            else
            {
                var1.setBlockMetadataWithNotify(var2, var3 - 1, var4, var8);
                var1.markBlockRangeForRenderUpdate(var2, var3 - 1, var4, var2, var3, var4);
            }

            var1.playAuxSFXAtEntity((EntityPlayer)null, 1003, var2, var3, var4, 0);
        }
    }
}
