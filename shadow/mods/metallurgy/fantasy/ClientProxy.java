package shadow.mods.metallurgy.fantasy;

import cpw.mods.fml.client.FMLClientHandler;
import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import shadow.mods.metallurgy.CoreClientProxy;

public class ClientProxy extends CommonProxy
{
    public void addNames()
    {
        CoreClientProxy.addNamesToSet(MetallurgyFantasy.alloys);
        CoreClientProxy.addNamesToSet(MetallurgyFantasy.ores);
        ModLoader.addLocalization("tile.MetalFantasyFurnace.PrometheumFurnace.name", "Prometheum Abstractor");
        ModLoader.addLocalization("tile.MetalFantasyFurnace.DeepIronFurnace.name", "Deep Iron Abstractor");
        ModLoader.addLocalization("tile.MetalFantasyFurnace.BlackSteelFurnace.name", "Black Steel Abstractor");
        ModLoader.addLocalization("tile.MetalFantasyFurnace.OureclaseFurnace.name", "Oureclase Abstractor");
        ModLoader.addLocalization("tile.MetalFantasyFurnace.AredriteFurnace.name", "Aredrite Abstractor");
        ModLoader.addLocalization("tile.MetalFantasyFurnace.MithrilFurnace.name", "Mithril Abstractor");
        ModLoader.addLocalization("tile.MetalFantasyFurnace.HaderothFurnace.name", "Haderoth Abstractor");
        ModLoader.addLocalization("tile.MetalFantasyFurnace.OrichalcumFurnace.name", "Orichalcum Abstractor");
        ModLoader.addLocalization("tile.MetalFantasyFurnace.AdamantineFurnace.name", "Adamantine Abstractor");
        ModLoader.addLocalization("tile.MetalFantasyFurnace.AtlarusFurnace.name", "Atlarus Abstractor");
        ModLoader.addLocalization("tile.MetalFantasyFurnace.TartariteFurnace.name", "Tartarite Abstractor");
    }

    public void registerRenderInformation()
    {
        MinecraftForgeClient.preloadTexture("/shadow/AbstractorParticle.png");
        MinecraftForgeClient.preloadTexture("/shadow/MetallurgyFantasyFurnace.png");
        MinecraftForgeClient.preloadTexture("/shadow/MetallurgyFantasyMetals.png");
        MinecraftForgeClient.preloadTexture("/shadow/MetallurgyFantasyAlloys.png");
    }

    public void spawnParticle(String var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13)
    {
        if (var1.equals("abstractorLarge"))
        {
            Minecraft.getMinecraft().effectRenderer.addEffect(new EntityAbstractorFX(var2, var3, var5, var7, var9, var11, var13));
        }

        if (var1.equals("abstractorSmall"))
        {
            Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySmallAbstractorFX(var2, var3, var5, var7, var9, var11, var13));
        }

        if (var1.equals("fantasyOre"))
        {
            EntityOreFX var15 = new EntityOreFX(var2, var3, var5, var7, (float)var9, (float)var11, (float)var13);
            Minecraft.getMinecraft().effectRenderer.addEffect(var15);
            var15.setRBGColorF((float)var9, (float)var11, (float)var13);
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
