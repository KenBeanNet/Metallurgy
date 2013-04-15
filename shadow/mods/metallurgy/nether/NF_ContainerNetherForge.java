package shadow.mods.metallurgy.nether;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class NF_ContainerNetherForge extends Container
{
    private NF_TileEntityNetherForge furnace;
    private int lastCookTime = 0;
    private int lastBurnTime = 0;
    private int lastItemBurnTime = 0;

    public NF_ContainerNetherForge(InventoryPlayer var1, NF_TileEntityNetherForge var2)
    {
        this.furnace = var2;
        this.addSlotToContainer(new Slot(var2, 0, 36, 34));
        this.addSlotToContainer(new NF_SlotNetherForge(var1.player, var2, 1, 96, 34));
        int var3;

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(var1, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(var1, var3, 8 + var3 * 18, 142));
        }
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int var1 = 0; var1 < this.crafters.size(); ++var1)
        {
            ICrafting var2 = (ICrafting)this.crafters.get(var1);

            if (this.lastCookTime != this.furnace.furnaceCookTime)
            {
                var2.sendProgressBarUpdate(this, 0, this.furnace.furnaceCookTime);
            }

            if (this.lastItemBurnTime != this.furnace.currentItemBurnTime)
            {
                var2.sendProgressBarUpdate(this, 1, this.furnace.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.furnace.furnaceCookTime;
        this.lastBurnTime = this.furnace.furnaceBurnTime;
        this.lastItemBurnTime = this.furnace.currentItemBurnTime;
    }

    public void updateProgressBar(int var1, int var2)
    {
        if (var1 == 0)
        {
            this.furnace.furnaceCookTime = var2;
        }

        if (var1 == 1)
        {
            this.furnace.currentItemBurnTime = var2;
        }
    }

    public boolean canInteractWith(EntityPlayer var1)
    {
        return this.furnace.isUseableByPlayer(var1);
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

            if (var2 == 1)
            {
                if (!this.mergeItemStack(var5, 2, 38, true))
                {
                    return null;
                }

                var4.onSlotChange(var5, var3);
            }
            else if (var2 != 1 && var2 != 0)
            {
                if (FurnaceRecipes.smelting().getSmeltingResult(var5) != null)
                {
                    if (!this.mergeItemStack(var5, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityFurnace.isItemFuel(var5))
                {
                    if (!this.mergeItemStack(var5, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (var2 >= 2 && var2 < 29)
                {
                    if (!this.mergeItemStack(var5, 29, 38, false))
                    {
                        return null;
                    }
                }
                else if (var2 >= 29 && var2 < 38 && !this.mergeItemStack(var5, 2, 29, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var5, 2, 38, false))
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

            if (var5.stackSize == var3.stackSize)
            {
                return null;
            }

            var4.onPickupFromSlot(var1, var5);
        }

        return var3;
    }
}
