package shadow.mods.metallurgy.utility;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import shadow.mods.metallurgy.MetallurgyCore;

public class PacketHandler implements IPacketHandler
{
    public void onPacketData(INetworkManager var1, Packet250CustomPayload var2, Player var3)
    {
        ByteArrayDataInput var4 = ByteStreams.newDataInput(var2.data);
        int var5 = var4.readInt();

        if (var5 == 2)
        {
            this.handleLargeExplosion(var4, var1, var2, var3);
        }

        if (var5 == 2)
        {
            this.handleMinersExplosion(var4, var1, var2, var3);
        }
    }

    private void handleLargeExplosion(ByteArrayDataInput var1, INetworkManager var2, Packet250CustomPayload var3, Player var4)
    {
        double var5 = var1.readDouble();
        double var7 = var1.readDouble();
        double var9 = var1.readDouble();
        float var11 = var1.readFloat();
        int var12 = var1.readInt();
        ArrayList var13 = new ArrayList(var12);
        int var14 = (int)var5;
        int var15 = (int)var7;
        int var16 = (int)var9;

        for (int var17 = 0; var17 < var12; ++var17)
        {
            int var18 = var1.readByte() + var14;
            int var19 = var1.readByte() + var15;
            int var20 = var1.readByte() + var16;
            var13.add(new ChunkPosition(var18, var19, var20));
        }

        float var22 = var1.readFloat();
        float var24 = var1.readFloat();
        float var23 = var1.readFloat();
        World var25 = MetallurgyCore.proxy.getClientWorld();
        Explosion var21 = new Explosion(var25, (Entity)null, var5, var7, var9, var11);
        var21.affectedBlockPositions = var13;
        var21.doExplosionB(true);
    }

    private void handleMinersExplosion(ByteArrayDataInput var1, INetworkManager var2, Packet250CustomPayload var3, Player var4)
    {
        double var5 = var1.readDouble();
        double var7 = var1.readDouble();
        double var9 = var1.readDouble();
        float var11 = var1.readFloat();
        int var12 = var1.readInt();
        ArrayList var13 = new ArrayList(var12);
        int var14 = (int)var5;
        int var15 = (int)var7;
        int var16 = (int)var9;

        for (int var17 = 0; var17 < var12; ++var17)
        {
            int var18 = var1.readByte() + var14;
            int var19 = var1.readByte() + var15;
            int var20 = var1.readByte() + var16;
            var13.add(new ChunkPosition(var18, var19, var20));
        }

        float var22 = var1.readFloat();
        float var24 = var1.readFloat();
        float var23 = var1.readFloat();
        World var25 = MetallurgyCore.proxy.getClientWorld();
        ExplosionMiners var21 = new ExplosionMiners(var25, (Entity)null, var5, var7, var9, var11);
        var21.affectedBlockPositions = var13;
        var21.doExplosionB(true);
    }
}
