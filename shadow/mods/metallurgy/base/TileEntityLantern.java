package shadow.mods.metallurgy.base;

import cpw.mods.fml.common.network.PacketDispatcher;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

public class TileEntityLantern extends TileEntity
{
    public short color;
    public boolean canUpdate = true;

    public TileEntityLantern() {}

    public TileEntityLantern(int var1)
    {
        this.color = (short)var1;
        this.canUpdate = true;
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        this.color = var1.getShort("color");

        if (this.worldObj != null)
        {
            int var2 = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord);
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var2, 1, this.color);
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        var1.setShort("color", this.color);
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        int var1 = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord);
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, var1, 1, this.color);
        this.canUpdate = false;
    }

    public void sendPacket()
    {
        if (this.worldObj == null || !this.worldObj.isRemote)
        {
            ByteArrayOutputStream var1 = new ByteArrayOutputStream(140);
            DataOutputStream var2 = new DataOutputStream(var1);

            try
            {
                var2.writeShort(2);
                var2.writeInt(this.xCoord);
                var2.writeInt(this.yCoord);
                var2.writeInt(this.zCoord);
                var2.writeShort(this.color);
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
                PacketDispatcher.sendPacketToAllPlayers(var3);
            }
        }
    }

    /**
     * Called when a client event is received with the event number and argument, see World.sendClientEvent
     */
    public void receiveClientEvent(int var1, int var2)
    {
        this.color = (short)var2;
    }

    public boolean canUpdate()
    {
        return this.canUpdate;
    }
}
