package shadow.mods.metallurgy.base;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockLantern extends ItemBlock
{
    public ItemBlockLantern(int var1)
    {
        super(var1);
        this.setHasSubtypes(true);
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        return var1;
    }

    public boolean placeBlockAt(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10, int var11)
    {
        if (!var3.setBlockAndMetadataWithNotify(var4, var5, var6, MetallurgyBaseMetals.lantern.blockID, this.getMetadata(var1.getItemDamage())))
        {
            return false;
        }
        else
        {
            if (var3.getBlockId(var4, var5, var6) == MetallurgyBaseMetals.lantern.blockID)
            {
                Block.blocksList[MetallurgyBaseMetals.lantern.blockID].onBlockPlaced(var3, var4, var5, var6, var7, var8, var9, var10, var11);
                Block.blocksList[MetallurgyBaseMetals.lantern.blockID].onBlockPlacedBy(var3, var4, var5, var6, var2);
                TileEntityLantern var12 = new TileEntityLantern(var1.getItemDamage());
                var3.setBlockTileEntity(var4, var5, var6, var12);
            }

            return true;
        }
    }

    public String getItemNameIS(ItemStack var1)
    {
        String var2 = "";

        switch (var1.getItemDamage())
        {
            case 0:
                var2 = "red";
                break;

            case 1:
                var2 = "green";
                break;

            case 2:
                var2 = "blue";
                break;

            case 3:
                var2 = "orange";
                break;

            case 4:
                var2 = "yellow";
                break;

            case 5:
                var2 = "purple";
                break;

            case 6:
                var2 = "grey";
                break;

            case 7:
                var2 = "white";
                break;

            default:
                var2 = "red";
        }

        return this.getItemName() + "." + var2;
    }
}
