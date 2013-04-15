package shadow.mods.metallurgy.nether;

import buildcraft.api.inventory.ISpecialInventory;
import cpw.mods.fml.common.network.PacketDispatcher;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class NF_TileEntityNetherForge extends TileEntity implements ISidedInventory, ITankContainer, ISpecialInventory
{
    private ItemStack[] furnaceItemStacks = new ItemStack[2];
    public int furnaceBurnTime = 0;
    public int currentItemBurnTime = 0;
    public int furnaceCookTime = 0;
    public float fuelMultiplier = 0.5F;
    public int furnaceTimeBase = 200;
    public int fuel = 0;
    public int maxFuel = 10000;
    public int fuelPerItem = 100;
    public boolean isBurning;
    private LiquidTank tempTank;
    public int direction;
    private int ticksSinceSync;

    public NF_TileEntityNetherForge()
    {
        this.tempTank = new LiquidTank(Block.lavaStill.blockID, this.fuel, this.maxFuel);
        this.direction = 0;
    }

    public void setSpeed(int var1)
    {
        this.furnaceTimeBase = var1;
    }

    public void setMaxBuckets(int var1)
    {
        this.maxFuel = var1 * 1000;
    }

    public void addFuelBucket()
    {
        this.fuel += 1000;
        this.fuel = this.fuel < this.maxFuel ? this.fuel : this.maxFuel;

        if (!this.worldObj.isRemote)
        {
            this.sendPacket();
        }
    }

    public void addTakeBucket()
    {
        if (this.fuel >= 1000)
        {
            this.fuel -= 1000;
        }

        if (!this.worldObj.isRemote)
        {
            this.sendPacket();
        }
    }

    public int getFuelScaled(int var1)
    {
        int var2 = this.fuel * var1 / this.maxFuel;
        return var2 > var1 ? var1 : var2;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.furnaceItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int var1)
    {
        return this.furnaceItemStacks[var1];
    }

    public void setDirection(int var1)
    {
        this.direction = var1;
    }

    public int getDirection()
    {
        return this.direction;
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int var1, int var2)
    {
        if (this.furnaceItemStacks[var1] != null)
        {
            ItemStack var3;

            if (this.furnaceItemStacks[var1].stackSize <= var2)
            {
                var3 = this.furnaceItemStacks[var1];
                this.furnaceItemStacks[var1] = null;
                return var3;
            }
            else
            {
                var3 = this.furnaceItemStacks[var1].splitStack(var2);

                if (this.furnaceItemStacks[var1].stackSize == 0)
                {
                    this.furnaceItemStacks[var1] = null;
                }

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
        if (this.furnaceItemStacks[var1] != null)
        {
            ItemStack var2 = this.furnaceItemStacks[var1];
            this.furnaceItemStacks[var1] = null;
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
        this.furnaceItemStacks[var1] = var2;

        if (var2 != null && var2.stackSize > this.getInventoryStackLimit())
        {
            var2.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "container.furnace";
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        NBTTagList var2 = var1.getTagList("Items");
        this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.furnaceItemStacks.length)
            {
                this.furnaceItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        this.fuel = var1.getInteger("Fuel");
        this.furnaceCookTime = var1.getShort("CookTime");
        this.direction = var1.getShort("Direction");
        this.furnaceTimeBase = var1.getShort("TimeBase");
        this.maxFuel = var1.getInteger("MaxFuel");
        this.sync();
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        var1.setInteger("Fuel", this.fuel);
        var1.setShort("CookTime", (short)this.furnaceCookTime);
        var1.setShort("Direction", (short)this.direction);
        var1.setShort("TimeBase", (short)this.furnaceTimeBase);
        var1.setInteger("MaxFuel", this.maxFuel);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.furnaceItemStacks.length; ++var3)
        {
            if (this.furnaceItemStacks[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.furnaceItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        var1.setTag("Items", var2);
        this.sync();
    }

    public void sync()
    {
        if (this.worldObj != null)
        {
            int var1 = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord);
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var1, 1, this.direction);
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var1, 2, this.furnaceTimeBase);
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var1, 3, this.fuel);
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var1, 4, this.maxFuel);
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var1, 5, this.furnaceCookTime);
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    public int getCookProgressScaled(int var1)
    {
        return this.furnaceCookTime * var1 / this.furnaceTimeBase;
    }

    public boolean isBurning()
    {
        return this.fuel > 0 && this.canSmelt();
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        if (this.tempTank != null)
        {
            LiquidStack var1 = this.tempTank.drain(this.maxFuel, true);

            if (var1 != null)
            {
                this.fuel += var1.amount;

                if (this.fuel > this.maxFuel)
                {
                    this.fuel = this.maxFuel;
                }
            }
        }

        if (++this.ticksSinceSync % 80 == 0)
        {
            this.sendPacket();
        }

        boolean var4 = this.furnaceBurnTime > 0;
        boolean var2 = false;
        boolean var3 = this.isBurning;

        if (this.canSmelt() && this.fuel > 0)
        {
            ++this.furnaceCookTime;
            this.isBurning = true;

            if (this.furnaceCookTime == this.furnaceTimeBase)
            {
                this.furnaceCookTime = 0;
                this.fuel -= this.fuelPerItem;
                this.smeltItem();
                var2 = true;
            }
        }
        else
        {
            this.furnaceCookTime = 0;
            this.isBurning = false;
        }

        if (var3 != this.isBurning)
        {
            var2 = true;
        }

        if (var2)
        {
            this.onInventoryChanged();
            this.sendPacket();
        }
    }

    private boolean canSmelt()
    {
        ItemStack var1 = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);

        if (var1 == null)
        {
            return false;
        }
        else if (this.furnaceItemStacks[1] == null)
        {
            return true;
        }
        else if (!this.furnaceItemStacks[1].isItemEqual(var1))
        {
            return false;
        }
        else
        {
            int var2 = this.furnaceItemStacks[1].stackSize + var1.stackSize;
            return var2 <= this.getInventoryStackLimit() && var2 <= var1.getMaxStackSize();
        }
    }

    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack var1 = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);

            if (this.furnaceItemStacks[1] == null)
            {
                this.furnaceItemStacks[1] = var1.copy();
            }
            else if (this.furnaceItemStacks[1].isItemEqual(var1))
            {
                ++this.furnaceItemStacks[1].stackSize;
            }

            --this.furnaceItemStacks[0].stackSize;

            if (this.furnaceItemStacks[0].stackSize <= 0)
            {
                this.furnaceItemStacks[0] = null;
            }
        }
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}

    /**
     * Called when a client event is received with the event number and argument, see World.sendClientEvent
     */
    public void receiveClientEvent(int var1, int var2) {}

    public int getStartInventorySide(ForgeDirection var1)
    {
        return var1 == ForgeDirection.DOWN ? 1 : (var1 == ForgeDirection.UP ? 0 : 0);
    }

    public int getSizeInventorySide(ForgeDirection var1)
    {
        return 1;
    }

    public int getType()
    {
        int var1 = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
        return var1 < 8 ? var1 : var1 - 8;
    }

    public int getScaledFuel(int var1)
    {
        int var2 = MathHelper.ceiling_float_int((float)var1 * ((float)this.fuel / (float)this.maxFuel));
        return var2 >= var1 ? var1 : var2;
    }

    public void sendPacket()
    {
        if (!this.worldObj.isRemote)
        {
            ByteArrayOutputStream var1 = new ByteArrayOutputStream();
            DataOutputStream var2 = new DataOutputStream(var1);

            try
            {
                var2.writeInt(this.xCoord);
                var2.writeInt(this.yCoord);
                var2.writeInt(this.zCoord);
                var2.writeInt(this.direction);
                var2.writeInt(this.furnaceTimeBase);
                var2.writeInt(this.furnaceBurnTime);
                var2.writeInt(this.furnaceCookTime);
                var2.writeInt(this.fuel);
                var2.writeInt(this.maxFuel);
            }
            catch (IOException var4)
            {
                ;
            }

            Packet250CustomPayload var3 = new Packet250CustomPayload();
            var3.channel = "MetallurgyNether";
            var3.data = var1.toByteArray();
            var3.length = var1.size();
            var3.isChunkDataPacket = true;

            if (var3 != null)
            {
                PacketDispatcher.sendPacketToAllAround((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, 16.0D, this.worldObj.provider.dimensionId, var3);
            }
        }
    }

    public int fill(ForgeDirection var1, LiquidStack var2, boolean var3)
    {
        if (var2.itemID != Block.lavaStill.blockID)
        {
            return 0;
        }
        else if (this.fuel < this.maxFuel)
        {
            boolean var4 = false;
            int var5;

            if (this.fuel + var2.amount <= this.maxFuel)
            {
                var5 = var2.amount;
                this.fuel += var2.amount;
            }
            else
            {
                var5 = this.maxFuel - this.fuel;
                this.fuel = this.maxFuel;
            }

            this.sync();
            return var5;
        }
        else
        {
            return 0;
        }
    }

    public int fill(int var1, LiquidStack var2, boolean var3)
    {
        return 0;
    }

    public LiquidStack drain(ForgeDirection var1, int var2, boolean var3)
    {
        return null;
    }

    public LiquidStack drain(int var1, int var2, boolean var3)
    {
        return null;
    }

    public int addItem(ItemStack var1, boolean var2, ForgeDirection var3)
    {
        byte var4 = 0;

        if (this.furnaceItemStacks[var4] == null)
        {
            if (var2)
            {
                this.furnaceItemStacks[var4] = var1;
            }

            return var1.stackSize;
        }
        else if (this.furnaceItemStacks[var4].itemID == var1.itemID && this.furnaceItemStacks[var4].getItemDamage() == var1.getItemDamage())
        {
            if (this.furnaceItemStacks[var4].stackSize + var1.stackSize > var1.getMaxStackSize())
            {
                int var5 = var1.getMaxStackSize() - this.furnaceItemStacks[1].stackSize;

                if (var2)
                {
                    this.furnaceItemStacks[var4].stackSize = this.furnaceItemStacks[1].getMaxStackSize();
                }

                return var5;
            }
            else
            {
                if (var2)
                {
                    this.furnaceItemStacks[var4].stackSize += var1.stackSize;
                }

                return var1.stackSize;
            }
        }
        else
        {
            return 0;
        }
    }

    public ItemStack[] extractItem(boolean var1, ForgeDirection var2, int var3)
    {
        if (this.furnaceItemStacks[1] != null)
        {
            int var4 = this.furnaceItemStacks[1].stackSize < var3 ? this.furnaceItemStacks[1].stackSize : var3;
            ItemStack[] var5 = new ItemStack[] {new ItemStack(this.furnaceItemStacks[1].itemID, var4, this.furnaceItemStacks[1].getItemDamage())};

            if (var1)
            {
                this.decrStackSize(1, var4);
            }

            return var5;
        }
        else
        {
            return null;
        }
    }

    public ILiquidTank[] getTanks(ForgeDirection var1)
    {
        return new LiquidTank[] {new LiquidTank(Block.lavaStill.blockID, this.fuel, this.maxFuel)};
    }

    public ILiquidTank getTank(ForgeDirection var1, LiquidStack var2)
    {
        this.tempTank.setCapacity(this.maxFuel - this.fuel);
        return this.tempTank;
    }
}
