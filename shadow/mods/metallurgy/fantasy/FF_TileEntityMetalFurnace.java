package shadow.mods.metallurgy.fantasy;

import buildcraft.api.inventory.ISpecialInventory;
import cpw.mods.fml.common.network.PacketDispatcher;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;

public class FF_TileEntityMetalFurnace extends TileEntity implements ISpecialInventory, ISidedInventory
{
    private ItemStack[] furnaceItemStacks = new ItemStack[3];
    public int furnaceBurnTime = 0;
    public int currentItemBurnTime = 0;
    public int furnaceCookTime = 0;
    public int furnaceTimeBase = 200;
    public int direction = 0;
    private int ticksSinceSync;

    public void setSpeed(int var1)
    {
        this.furnaceTimeBase = var1;
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

        this.furnaceBurnTime = var1.getShort("BurnTime");
        this.furnaceCookTime = var1.getShort("CookTime");
        this.direction = var1.getShort("Direction");
        this.furnaceTimeBase = var1.getShort("TimeBase");
        this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
        this.ticksSinceSync = 40;
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        var1.setShort("BurnTime", (short)this.furnaceBurnTime);
        var1.setShort("CookTime", (short)this.furnaceCookTime);
        var1.setShort("Direction", (short)this.direction);
        var1.setShort("TimeBase", (short)this.furnaceTimeBase);
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

    public int getBurnTimeRemainingScaled(int var1)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = this.furnaceTimeBase;
        }

        return this.furnaceBurnTime * var1 / this.currentItemBurnTime;
    }

    public boolean isBurning()
    {
        return this.furnaceBurnTime > 0;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        boolean var1 = this.furnaceBurnTime > 0;
        boolean var2 = false;
        int var3;

        if (++this.ticksSinceSync > 40)
        {
            var3 = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord);
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var3, 1, this.direction);
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var3, 2, this.furnaceTimeBase);
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var3, 3, this.furnaceBurnTime);
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var3, 4, this.furnaceCookTime);
            this.ticksSinceSync = 0;
        }

        if (this.furnaceBurnTime > 0)
        {
            --this.furnaceBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.furnaceBurnTime == 0 && this.canSmelt())
            {
                this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);

                if (this.furnaceBurnTime > 0)
                {
                    var2 = true;

                    if (this.furnaceItemStacks[1] != null)
                    {
                        --this.furnaceItemStacks[1].stackSize;

                        if (this.furnaceItemStacks[1].stackSize == 0)
                        {
                            this.furnaceItemStacks[1] = null;
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt())
            {
                ++this.furnaceCookTime;

                if (this.furnaceCookTime == this.furnaceTimeBase)
                {
                    this.furnaceCookTime = 0;
                    this.smeltItem();
                    var2 = true;
                }
            }
            else
            {
                this.furnaceCookTime = 0;
            }

            if (var1 != this.furnaceBurnTime > 0)
            {
                var2 = true;
            }
        }

        if (var2)
        {
            this.onInventoryChanged();
            this.sendPacket();
            var3 = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord);
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var3, 1, this.direction);
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var3, 2, this.furnaceTimeBase);
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var3, 3, this.furnaceBurnTime);
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }
    }

    private boolean canSmelt()
    {
        if (this.furnaceItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            int var1 = FF_EssenceRecipes.essence().getEssenceResult(this.furnaceItemStacks[0]);
            return var1 != 0;
        }
    }

    public void smeltItem()
    {
        if (this.canSmelt())
        {
            int var1 = FF_EssenceRecipes.essence().getEssenceResult(this.furnaceItemStacks[0]);
            int var2 = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
            int var3 = (int)((float)(var1 * ConfigFantasy.xpBonus[var2]) / 10.0F);
            --this.furnaceItemStacks[0].stackSize;

            if (this.furnaceItemStacks[0].stackSize <= 0)
            {
                this.furnaceItemStacks[0] = null;
            }

            byte var4 = 1;
            int var5 = var3;

            if (var3 > 20)
            {
                var4 = 2;
                var5 = var3 / 2 + 1;
            }

            if (var3 > 40)
            {
                var4 = 4;
                var5 = var3 / 4 + 1;
            }

            if (var3 > 80)
            {
                var4 = 8;
                var5 = var3 / 8 + 1;
            }

            for (int var7 = 0; var7 < var5; ++var7)
            {
                double var8 = 0.5D;
                double var10 = 0.5D;
                double var12 = 0.0D;
                double var14 = 0.0D;

                if (this.direction == 2)
                {
                    var10 = 0.0D;
                    var10 = -0.1D;
                }
                else if (this.direction == 3)
                {
                    var10 = 1.0D;
                    var14 = 0.1D;
                }
                else if (this.direction == 4)
                {
                    var8 = 0.0D;
                    var12 = -0.1D;
                }
                else if (this.direction == 5)
                {
                    var8 = 1.0D;
                    var12 = 0.1D;
                }

                Random var16 = new Random();
                var8 += (double)(var16.nextInt(21) - 10) / 100.0D;
                var10 += (double)(var16.nextInt(21) - 10) / 100.0D;
                var12 += (double)(var16.nextInt(11) - 5) / 100.0D;
                var14 += (double)(var16.nextInt(11) - 5) / 100.0D;
                double var17 = (double)(var16.nextInt(11) - 5) / 200.0D;
                MetallurgyFantasy.proxy.spawnParticle("abstractorSmall", this.worldObj, (double)this.xCoord + var8, (double)this.yCoord + 0.75D, (double)this.zCoord + var10, var12 * 0.699999988079071D, var17, var14 * 0.699999988079071D);
                EntityXPOrb var6 = new EntityXPOrb(this.worldObj, (double)this.xCoord + var8, (double)((float)this.yCoord + 0.5F), (double)this.zCoord + var10, var4);
                var6.motionX = var12;
                var6.motionZ = var14;

                if (!this.worldObj.isRemote)
                {
                    this.worldObj.spawnEntityInWorld(var6);
                    this.worldObj.updateEntity(var6);
                }
            }

            this.sendPacket();
        }
    }

    public static int getItemBurnTime(ItemStack var0)
    {
        if (var0 == null)
        {
            return 0;
        }
        else
        {
            int var1 = var0.getItem().itemID;
            Item var2 = var0.getItem();
            return var1 == MetallurgyFantasy.ores.Dust[0].itemID ? 1760 : (var1 == MetallurgyFantasy.ores.Dust[5].itemID ? 7040 : (var1 == MetallurgyFantasy.ores.Dust[6].itemID ? 14080 : FF_EssenceRecipes.getFuelAmount(var0)));
        }
    }

    /**
     * Called when a client event is received with the event number and argument, see World.sendClientEvent
     */
    public void receiveClientEvent(int var1, int var2)
    {
        if (var1 == 1)
        {
            this.direction = var2;
        }
        else if (var1 == 2)
        {
            this.furnaceTimeBase = var2;
        }
        else if (var1 == 3)
        {
            this.furnaceBurnTime = var2;
        }
        else if (var1 == 4)
        {
            this.furnaceCookTime = var2;
        }

        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

    public static boolean isItemFuel(ItemStack var0)
    {
        return getItemBurnTime(var0) > 0;
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

    public int getStartInventorySide(ForgeDirection var1)
    {
        return var1 == ForgeDirection.DOWN ? 1 : (var1 == ForgeDirection.UP ? 0 : 2);
    }

    public int getSizeInventorySide(ForgeDirection var1)
    {
        return 1;
    }

    public int getType()
    {
        return this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
    }

    public void sendPacket()
    {
        if (!this.worldObj.isRemote)
        {
            ByteArrayOutputStream var1 = new ByteArrayOutputStream(140);
            DataOutputStream var2 = new DataOutputStream(var1);

            try
            {
                var2.writeInt(1);
                var2.writeInt(this.xCoord);
                var2.writeInt(this.yCoord);
                var2.writeInt(this.zCoord);
                var2.writeInt(this.direction);
                var2.writeInt(this.furnaceTimeBase);
                var2.writeInt(this.furnaceBurnTime);
            }
            catch (IOException var4)
            {
                ;
            }

            Packet250CustomPayload var3 = new Packet250CustomPayload();
            var3.channel = "MetallurgyFantas";
            var3.data = var1.toByteArray();
            var3.length = var1.size();
            var3.isChunkDataPacket = true;

            if (var3 != null)
            {
                PacketDispatcher.sendPacketToAllAround((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, 16.0D, this.worldObj.provider.dimensionId, var3);
            }
        }
    }

    public int addItem(ItemStack var1, boolean var2, ForgeDirection var3)
    {
        byte var4 = 0;

        if (getItemBurnTime(var1) > 0)
        {
            var4 = 1;
        }

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
                int var5 = var1.getMaxStackSize() - this.furnaceItemStacks[var4].stackSize;

                if (var2)
                {
                    this.furnaceItemStacks[var4].stackSize = this.furnaceItemStacks[var4].getMaxStackSize();
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
        return null;
    }
}
