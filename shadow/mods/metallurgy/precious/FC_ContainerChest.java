package shadow.mods.metallurgy.precious;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class FC_ContainerChest extends Container
{
    private IInventory lowerChestInventory;
    private int numRows;
    private int numCols;

    public FC_ContainerChest(IInventory var1, IInventory var2)
    {
        this.lowerChestInventory = var2;
        this.numRows = ((FC_TileEntityChest)var2).getNumRows();
        this.numCols = ((FC_TileEntityChest)var2).getNumCols();
        var2.openChest();
        int var3 = (this.numRows - 4) * 18;
        byte var6 = 0;

        if (this.numCols == 12)
        {
            var6 = 27;
        }

        if (this.numCols == 10)
        {
            var6 = 9;
        }

        boolean var7 = false;
        int var4;
        int var5;

        for (var4 = 0; var4 < this.numRows; ++var4)
        {
            for (var5 = 0; var5 < this.numCols; ++var5)
            {
                this.addSlotToContainer(new Slot(var2, var5 + var4 * this.numCols, 11 + var5 * 18, 18 + var4 * 18));
            }
        }

        for (var4 = 0; var4 < 3; ++var4)
        {
            for (var5 = 0; var5 < 9; ++var5)
            {
                this.addSlotToContainer(new Slot(var1, var5 + var4 * 9 + 9, 11 + var5 * 18 + var6, 94 + var4 * 18 + var3));
            }
        }

        for (var4 = 0; var4 < 9; ++var4)
        {
            this.addSlotToContainer(new Slot(var1, var4, 11 + var4 * 18 + var6, 152 + var3));
        }
    }

    public boolean canInteractWith(EntityPlayer var1)
    {
        return this.lowerChestInventory.isUseableByPlayer(var1);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer var1, int var2)
    {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(var2);

        if (var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (var2 < this.numRows * this.numCols)
            {
                if (!this.mergeItemStack(var5, this.numRows * this.numCols, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var5, 0, this.numRows * this.numCols, false))
            {
                return null;
            }

            if (var5.stackSize == 0)
            {
                var4.putStack((ItemStack)null);
            }
            else
            {
                var4.onSlotChanged();
            }
        }

        return var3;
    }

    /**
     * Callback for when the crafting gui is closed.
     */
    public void onCraftGuiClosed(EntityPlayer var1)
    {
        super.onCraftGuiClosed(var1);
        this.lowerChestInventory.closeChest();
    }
}
