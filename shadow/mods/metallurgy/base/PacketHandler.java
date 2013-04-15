package shadow.mods.metallurgy.base;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PacketHandler implements IPacketHandler
{
    public void onPacketData(INetworkManager var1, Packet250CustomPayload var2, Player var3)
    {
        ByteArrayDataInput var4 = ByteStreams.newDataInput(var2.data);
        short var5 = var4.readShort();

        if (var5 == 1)
        {
            this.readCrusher(var4);
        }
        else if (var5 == 2)
        {
            this.readLantern(var4);
        }
    }

    private void readLantern(ByteArrayDataInput var1)
    {
        int var2 = var1.readInt();
        int var3 = var1.readInt();
        int var4 = var1.readInt();
        short var5 = var1.readShort();
        World var6 = MetallurgyBaseMetals.proxy.getClientWorld();
        TileEntity var7 = var6.getBlockTileEntity(var2, var3, var4);
        Object var8 = null;

        if (var7 instanceof TileEntityLantern)
        {
            ((TileEntityLantern)var7).color = var5;
        }

        var6.markBlockForUpdate(var2, var3, var4);
    }

    private void readCrusher(ByteArrayDataInput var1)
    {
        int var2 = var1.readInt();
        int var3 = var1.readInt();
        int var4 = var1.readInt();
        int var5 = var1.readInt();
        int var6 = var1.readInt();
        int var7 = var1.readInt();
        int var8 = var1.readInt();
        World var9 = MetallurgyBaseMetals.proxy.getClientWorld();
        TileEntity var10 = var9.getBlockTileEntity(var2, var3, var4);
        BF_TileEntityMetalFurnace var11 = null;

        if (var10 instanceof BF_TileEntityMetalFurnace)
        {
            var11 = (BF_TileEntityMetalFurnace)var10;
            var11.setDirection(var5);
            var11.furnaceTimeBase = var6;
            var11.furnaceBurnTime = var7;
            var11.furnaceCookTime = var8;
        }

        var9.markBlockForUpdate(var2, var3, var4);
    }
}
