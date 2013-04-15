package shadow.mods.metallurgy.nether;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import shadow.mods.metallurgy.CoreClientProxy;

public class ClientProxy extends CommonProxy
{
    public void addNames()
    {
        CoreClientProxy.addNamesToSet(MetallurgyNether.alloys);
        CoreClientProxy.addNamesToSet(MetallurgyNether.ores);
        String[] var1 = NF_GuiNetherForge.names;
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3)
        {
            String var4 = var1[var3];
            LanguageRegistry.instance().addStringLocalization("tile.NetherForge." + var4 + "Forge.name", var4 + " Smelter");
        }

        LanguageRegistry.instance().addStringLocalization("container.netherforge", "Nether Smelter");
    }

    public void registerRenderInformation()
    {
        MinecraftForgeClient.preloadTexture("/shadow/MetallurgyNetherForges.png");
        MinecraftForgeClient.preloadTexture("/shadow/MetallurgyNetherMetals.png");
        MinecraftForgeClient.preloadTexture("/shadow/MetallurgyNetherAlloys.png");
    }

    public void spawnParticle(String var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13)
    {
        EntityOreFX var15;

        if (var1.equals("nether2"))
        {
            var15 = new EntityOreFX(var2, var3, var5, var7, 1.0F, 0.8F, 0.3F);
            Minecraft.getMinecraft().effectRenderer.addEffect(var15);
            var15.setRBGColorF(1.0F, 0.8F, 0.25F);
        }

        if (var1.equals("nether4"))
        {
            var15 = new EntityOreFX(var2, var3, var5, var7, 1.0F, 0.8F, 0.3F);
            Minecraft.getMinecraft().effectRenderer.addEffect(var15);
            var15.setRBGColorF(0.35F, 0.6F, 0.9F);
        }

        if (var1.equals("nether5"))
        {
            var15 = new EntityOreFX(var2, var3, var5, var7, 1.0F, 0.8F, 0.3F);
            Minecraft.getMinecraft().effectRenderer.addEffect(var15);
            var15.setRBGColorF(0.8F, 0.4F, 0.8F);
        }

        if (var1.equals("nether7"))
        {
            var15 = new EntityOreFX(var2, var3, var5, var7, 1.0F, 0.8F, 0.3F);
            Minecraft.getMinecraft().effectRenderer.addEffect(var15);
            var15.setRBGColorF(0.85F, 0.0F, 0.0F);
        }
    }

    public World getClientWorld()
    {
        return FMLClientHandler.instance().getClient().theWorld;
    }

    public File getMinecraftDir()
    {
        return Minecraft.getMinecraftDir();
    }
}
