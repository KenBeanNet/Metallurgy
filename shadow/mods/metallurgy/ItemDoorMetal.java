package shadow.mods.metallurgy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemDoorMetal extends ItemDoor
{
    public int doorID;

    public ItemDoorMetal(int var1, Material var2, int var3)
    {
        super(var1, var2);
        System.out.println("creating door item " + var1);
        this.doorID = var3;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (var7 != 1)
        {
            return false;
        }
        else
        {
            ++var5;
            Block var11 = Block.blocksList[this.doorID];

            if (var2.canPlayerEdit(var4, var5, var6, var7, var1) && var2.canPlayerEdit(var4, var5 + 1, var6, var7, var1))
            {
                if (!var11.canPlaceBlockAt(var3, var4, var5, var6))
                {
                    return false;
                }
                else
                {
                    int var12 = MathHelper.floor_double((double)((var2.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
                    placeDoorBlock(var3, var4, var5, var6, var12, var11);
                    --var1.stackSize;
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }
}
