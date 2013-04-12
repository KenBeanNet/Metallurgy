package shadow.mods.metallurgy;

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
        int var5 = var4.readInt();
        int var6 = var4.readInt();
        int var7 = var4.readInt();
        int var8 = var4.readInt();
        int var9 = var4.readInt();
        int var10 = var4.readInt();
        int var11 = var4.readInt();
        World var12 = MetallurgyCore.proxy.getClientWorld();
        TileEntity var13 = var12.getBlockTileEntity(var5, var6, var7);
        BC_TileEntityCrusher var14 = null;

        if (var13 instanceof BC_TileEntityCrusher)
        {
            var14 = (BC_TileEntityCrusher)var13;
            var14.setDirection(var8);
            var14.furnaceTimeBase = var9;
            var14.furnaceBurnTime = var10;
            var14.furnaceCookTime = var11;
        }

        var12.markBlockForUpdate(var5, var6, var7);
    }
}
