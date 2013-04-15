package shadow.mods.metallurgy.base;

import buildcraft.api.inventory.ISpecialInventory;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.GameRegistry;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;

public class BF_TileEntityMetalFurnace extends TileEntity implements ISidedInventory, ISpecialInventory
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
        this.ticksSinceSync = 80;
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
        if (++this.ticksSinceSync > 80)
        {
            int var1 = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord);
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var1, 1, this.direction);
            this.sendPacket();
            this.ticksSinceSync = 0;
        }

        boolean var3 = this.furnaceBurnTime > 0;
        boolean var2 = false;

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
                            this.furnaceItemStacks[1] = this.furnaceItemStacks[1].getItem().getContainerItemStack(this.furnaceItemStacks[1]);
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

            if (var3 != this.furnaceBurnTime > 0)
            {
                var2 = true;
            }
        }

        if (var2)
        {
            this.onInventoryChanged();
            this.sendPacket();
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
            ItemStack var1 = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);

            if (var1 == null)
            {
                return false;
            }
            else if (this.furnaceItemStacks[2] == null)
            {
                return true;
            }
            else if (!this.furnaceItemStacks[2].isItemEqual(var1))
            {
                return false;
            }
            else
            {
                int var2 = this.furnaceItemStacks[2].stackSize + var1.stackSize;
                return var2 <= this.getInventoryStackLimit() && var2 <= var1.getMaxStackSize();
            }
        }
    }

    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack var1 = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);

            if (this.furnaceItemStacks[2] == null)
            {
                this.furnaceItemStacks[2] = var1.copy();
            }
            else if (this.furnaceItemStacks[2].isItemEqual(var1))
            {
                ++this.furnaceItemStacks[2].stackSize;
            }

            --this.furnaceItemStacks[0].stackSize;

            if (this.furnaceItemStacks[0].stackSize <= 0)
            {
                this.furnaceItemStacks[0] = null;
            }
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

            if (var0.getItem() instanceof ItemBlock && Block.blocksList[var1] != null)
            {
                Block var3 = Block.blocksList[var1];

                if (var3 == Block.woodSingleSlab)
                {
                    return 150;
                }

                if (var3.blockMaterial == Material.wood)
                {
                    return 300;
                }
            }

            return var2 instanceof ItemTool && ((ItemTool)var2).getToolMaterialName().equals("WOOD") ? 200 : (var2 instanceof ItemSword && ((ItemSword)var2).getToolMaterialName().equals("WOOD") ? 200 : (var2 instanceof ItemHoe && ((ItemHoe)var2).func_77842_f().equals("WOOD") ? 200 : (var1 == Item.stick.itemID ? 100 : (var1 == Item.coal.itemID ? 1600 : (var1 == Item.bucketLava.itemID ? 20000 : (var1 == Block.sapling.blockID ? 100 : (var1 == Item.blazeRod.itemID ? 2400 : GameRegistry.getFuelValue(var0))))))));
        }
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

    /**
     * Called when a client event is received with the event number and argument, see World.sendClientEvent
     */
    public void receiveClientEvent(int var1, int var2)
    {
        if (var1 == 11)
        {
            this.direction = var2;
        }

        if (var1 == 4)
        {
            this.furnaceCookTime = var2;
        }
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
        int var1 = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
        return var1 < 8 ? var1 : var1 - 8;
    }

    public void sendPacket()
    {
        if (!this.worldObj.isRemote)
        {
            ByteArrayOutputStream var1 = new ByteArrayOutputStream(140);
            DataOutputStream var2 = new DataOutputStream(var1);

            try
            {
                var2.writeShort(1);
                var2.writeInt(this.xCoord);
                var2.writeInt(this.yCoord);
                var2.writeInt(this.zCoord);
                var2.writeInt(this.direction);
                var2.writeInt(this.furnaceTimeBase);
                var2.writeInt(this.furnaceBurnTime);
                var2.writeInt(this.furnaceCookTime);
            }
            catch (IOException var4)
            {
                ;
            }

            Packet250CustomPayload var3 = new Packet250CustomPayload();
            var3.channel = "MetallurgyBase";
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
        if (this.furnaceItemStacks[2] != null)
        {
            int var4 = this.furnaceItemStacks[2].stackSize < var3 ? this.furnaceItemStacks[2].stackSize : var3;
            ItemStack[] var5 = new ItemStack[] {new ItemStack(this.furnaceItemStacks[2].itemID, var4, this.furnaceItemStacks[2].getItemDamage())};

            if (var1)
            {
                this.decrStackSize(2, var4);
            }

            return var5;
        }
        else
        {
            return null;
        }
    }
}
