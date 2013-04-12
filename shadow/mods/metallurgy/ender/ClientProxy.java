package shadow.mods.metallurgy.ender;

import cpw.mods.fml.client.FMLClientHandler;
import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import shadow.mods.metallurgy.CoreClientProxy;

public class ClientProxy extends CommonProxy
{
    public void addNames()
    {
        CoreClientProxy.addNamesToSet(MetallurgyEnder.alloys);
        CoreClientProxy.addNamesToSet(MetallurgyEnder.ores);
    }

    public void registerRenderInformation()
    {
        MinecraftForgeClient.preloadTexture("/shadow/MetallurgyEnderMetals.png");
        MinecraftForgeClient.preloadTexture("/shadow/MetallurgyEnderAlloys.png");
    }

    public World getClientWorld()
    {
        return FMLClientHandler.instance().getClient().theWorld;
    }

    public File getMinecraftDir()
    {
        return Minecraft.getMinecraftDir();
    }

    public void spawnParticle(String var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13)
    {
        EntityOreFX var15;

        if (var1.equals("enderOre1"))
        {
            System.out.println("spawning particle");
            var15 = new EntityOreFX(var2, var3, var5, var7, (float)var9, (float)var11, (float)var13, 0);
            Minecraft.getMinecraft().effectRenderer.addEffect(var15);
            var15.setRBGColorF((float)var9, (float)var11, (float)var13);
        }

        if (var1.equals("enderOre2"))
        {
            var15 = new EntityOreFX(var2, var3, var5, var7, (float)var9, (float)var11, (float)var13, 1);
            Minecraft.getMinecraft().effectRenderer.addEffect(var15);
            var15.setRBGColorF((float)var9, (float)var11, (float)var13);
        }
    }
}
