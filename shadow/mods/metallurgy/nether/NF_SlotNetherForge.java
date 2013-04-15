package shadow.mods.metallurgy.nether;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class NF_SlotNetherForge extends Slot
{
    public NF_SlotNetherForge(EntityPlayer var1, IInventory var2, int var3, int var4, int var5)
    {
        super(var2, var3, var4, var5);
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid(ItemStack var1)
    {
        return false;
    }
}
