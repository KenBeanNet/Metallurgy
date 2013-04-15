package shadow.mods.metallurgy.nether;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shadow.mods.metallurgy.MetallurgyCore;

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
        int var12 = var4.readInt();
        int var13 = var4.readInt();
        World var14 = MetallurgyCore.proxy.getClientWorld();
        TileEntity var15 = var14.getBlockTileEntity(var5, var6, var7);
        NF_TileEntityNetherForge var16 = null;

        if (var15 instanceof NF_TileEntityNetherForge)
        {
            var16 = (NF_TileEntityNetherForge)var15;
            var16.setDirection(var8);
            var16.furnaceTimeBase = var9;
            var16.furnaceBurnTime = var10;
            var16.furnaceCookTime = var11;
            var16.fuel = var12;
            var16.maxFuel = var13;
        }

        var14.markBlockForUpdate(var5, var6, var7);
    }
}
