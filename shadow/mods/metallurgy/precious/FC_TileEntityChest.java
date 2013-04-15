package shadow.mods.metallurgy.precious;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class FC_TileEntityChest extends TileEntity implements IInventory
{
    private ItemStack[] chestContents = new ItemStack[120];
    public float lidAngle;
    public float prevLidAngle;
    public int numUsingPlayers;
    private int ticksSinceSync;
    private int direction;
    private int type;

    public void setDirection(int var1)
    {
        this.direction = var1;
    }

    public int getDirection()
    {
        return this.direction;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.getNumRows() * this.getNumCols();
    }

    public void setType(int var1)
    {
        this.type = var1;
    }

    public int getNumRows()
    {
        switch (this.type)
        {
            case 0:
                return 6;

            case 1:
                return 8;

            case 2:
                return 9;

            case 3:
                return 9;

            case 4:
                return 9;

            default:
                return 3;
        }
    }

    public int getNumCols()
    {
        switch (this.type)
        {
            case 0:
                return 9;

            case 1:
                return 9;

            case 2:
                return 9;

            case 3:
                return 10;

            case 4:
                return 12;

            default:
                return 9;
        }
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int var1)
    {
        return this.chestContents[var1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int var1, int var2)
    {
        if (this.chestContents[var1] != null)
        {
            ItemStack var3;

            if (this.chestContents[var1].stackSize <= var2)
            {
                var3 = this.chestContents[var1];
                this.chestContents[var1] = null;
                this.onInventoryChanged();
                return var3;
            }
            else
            {
                var3 = this.chestContents[var1].splitStack(var2);

                if (this.chestContents[var1].stackSize == 0)
                {
                    this.chestContents[var1] = null;
                }

                this.onInventoryChanged();
                return var3;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int var1)
    {
        if (this.chestContents[var1] != null)
        {
            ItemStack var2 = this.chestContents[var1];
            this.chestContents[var1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int var1, ItemStack var2)
    {
        this.chestContents[var1] = var2;

        if (var2 != null && var2.stackSize > this.getInventoryStackLimit())
        {
            var2.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "container.chest";
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        this.direction = var1.getInteger("Direction");
        this.type = var1.getInteger("Type");
        NBTTagList var2 = var1.getTagList("Items");
        this.chestContents = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            int var5 = var4.getByte("Slot") & 255;

            if (var5 >= 0 && var5 < this.chestContents.length)
            {
                this.chestContents[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        NBTTagList var2 = new NBTTagList();
        var1.setInteger("Direction", this.direction);
        var1.setInteger("Type", this.type);

        for (int var3 = 0; var3 < this.chestContents.length; ++var3)
        {
            if (this.chestContents[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.chestContents[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        var1.setTag("Items", var2);
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        super.updateEntity();

        if (++this.ticksSinceSync % 20 * 4 == 0)
        {
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, MetallurgyPrecious.PreciousChest.blockID, 2, this.direction);
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, MetallurgyPrecious.PreciousChest.blockID, 3, this.type);
        }

        this.prevLidAngle = this.lidAngle;
        float var1 = 0.1F;
        double var2;

        if (this.numUsingPlayers > 0 && this.lidAngle == 0.0F)
        {
            double var4 = (double)this.xCoord + 0.5D;
            var2 = (double)this.zCoord + 0.5D;
            this.worldObj.playSoundEffect(var4, (double)this.yCoord + 0.5D, var2, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (this.numUsingPlayers == 0 && this.lidAngle > 0.0F || this.numUsingPlayers > 0 && this.lidAngle < 1.0F)
        {
            float var8 = this.lidAngle;

            if (this.numUsingPlayers > 0)
            {
                this.lidAngle += var1;
            }
            else
            {
                this.lidAngle -= var1;
            }

            if (this.lidAngle > 1.0F)
            {
                this.lidAngle = 1.0F;
            }

            float var5 = 0.5F;

            if (this.lidAngle < var5 && var8 >= var5)
            {
                var2 = (double)this.xCoord + 0.5D;
                double var6 = (double)this.zCoord + 0.5D;
                this.worldObj.playSoundEffect(var2, (double)this.yCoord + 0.5D, var6, "random.chestclosed", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (this.lidAngle < 0.0F)
            {
                this.lidAngle = 0.0F;
            }
        }
    }

    /**
     * Called when a client event is received with the event number and argument, see World.sendClientEvent
     */
    public void receiveClientEvent(int var1, int var2)
    {
        if (var1 == 1)
        {
            this.numUsingPlayers = var2;
        }
        else if (var1 == 2)
        {
            this.direction = var2;
        }
        else if (var1 == 3)
        {
            this.type = var2;
        }
    }

    public void openChest()
    {
        ++this.numUsingPlayers;
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, MetallurgyPrecious.PreciousChest.blockID, 1, this.numUsingPlayers);
    }

    public void closeChest()
    {
        --this.numUsingPlayers;
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, MetallurgyPrecious.PreciousChest.blockID, 1, this.numUsingPlayers);
    }

    /**
     * invalidates a tile entity
     */
    public void invalidate()
    {
        this.updateContainingBlockInfo();
        super.invalidate();
    }

    public int getType()
    {
        return this.type;
    }
}
