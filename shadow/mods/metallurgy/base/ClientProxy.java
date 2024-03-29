package shadow.mods.metallurgy.base;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import shadow.mods.metallurgy.CoreClientProxy;
import shadow.mods.metallurgy.mod_Gold;
import shadow.mods.metallurgy.mod_Iron;

public class ClientProxy extends CommonProxy
{
    public void addNames()
    {
        CoreClientProxy.addNamesToSet(MetallurgyBaseMetals.alloys);
        CoreClientProxy.addNamesToSet(MetallurgyBaseMetals.ores);
        ModLoader.addName(mod_Gold.GoldDust, "Gold Dust");
        ModLoader.addName(mod_Iron.IronDust, "Iron Dust");
        LanguageRegistry.instance().addStringLocalization("tile.MetalFurnace.CopperFurnace.name", "Copper Furnace");
        LanguageRegistry.instance().addStringLocalization("tile.MetalFurnace.BronzeFurnace.name", "Bronze Furnace");
        LanguageRegistry.instance().addStringLocalization("tile.MetalFurnace.IronFurnace.name", "Iron Furnace");
        LanguageRegistry.instance().addStringLocalization("tile.MetalFurnace.SteelFurnace.name", "Steel Furnace");
        LanguageRegistry.instance().addStringLocalization("tile.MetalLadder.Copper.name", "Copper Ladder");
        LanguageRegistry.instance().addStringLocalization("tile.MetalLadder.Bronze.name", "Bronze Ladder");
        LanguageRegistry.instance().addStringLocalization("tile.MetalLadder.Iron.name", "Iron Ladder");
        LanguageRegistry.instance().addStringLocalization("tile.MetalLadder.Steel.name", "Steel Ladder");
        LanguageRegistry.instance().addStringLocalization("item.glassDust.glass.name", "Glass Dust");
        LanguageRegistry.instance().addStringLocalization("item.glassDust.red.name", "Red Glass Dust");
        LanguageRegistry.instance().addStringLocalization("item.glassDust.green.name", "Green Glass Dust");
        LanguageRegistry.instance().addStringLocalization("item.glassDust.blue.name", "Blue Glass Dust");
        LanguageRegistry.instance().addStringLocalization("item.glassDust.orange.name", "Orange Glass Dust");
        LanguageRegistry.instance().addStringLocalization("item.glassDust.yellow.name", "Yellow Glass Dust");
        LanguageRegistry.instance().addStringLocalization("item.glassDust.purple.name", "Purple Glass Dust");
        LanguageRegistry.instance().addStringLocalization("item.glassDust.grey.name", "Grey Glass Dust");
        LanguageRegistry.instance().addStringLocalization("item.glassDust.white.name", "White Glass Dust");
        LanguageRegistry.instance().addStringLocalization("tile.lantern.red.name", "Red Lantern");
        LanguageRegistry.instance().addStringLocalization("tile.lantern.green.name", "Green Lantern");
        LanguageRegistry.instance().addStringLocalization("tile.lantern.blue.name", "Blue Lantern");
        LanguageRegistry.instance().addStringLocalization("tile.lantern.orange.name", "Orange Lantern");
        LanguageRegistry.instance().addStringLocalization("tile.lantern.yellow.name", "Yellow Lantern");
        LanguageRegistry.instance().addStringLocalization("tile.lantern.purple.name", "Purple Lantern");
        LanguageRegistry.instance().addStringLocalization("tile.lantern.grey.name", "Grey Lantern");
        LanguageRegistry.instance().addStringLocalization("tile.lantern.white.name", "White Lantern");
        LanguageRegistry.instance().addStringLocalization("tile.coloredGlass.red.name", "Red Glass");
        LanguageRegistry.instance().addStringLocalization("tile.coloredGlass.green.name", "Green Glass");
        LanguageRegistry.instance().addStringLocalization("tile.coloredGlass.blue.name", "Blue Glass");
        LanguageRegistry.instance().addStringLocalization("tile.coloredGlass.orange.name", "Orange Glass");
        LanguageRegistry.instance().addStringLocalization("tile.coloredGlass.yellow.name", "Yellow Glass");
        LanguageRegistry.instance().addStringLocalization("tile.coloredGlass.purple.name", "Purple Glass");
        LanguageRegistry.instance().addStringLocalization("tile.coloredGlass.grey.name", "Grey Glass");
        LanguageRegistry.instance().addStringLocalization("tile.coloredGlass.white.name", "White Glass");

        if (MetallurgyBaseMetals.spear[0] != null)
        {
            String[] var1 = new String[] {"Copper", "Bronze", "Hepatizon", "Angmallen", "Damascus Steel", "Steel"};

            for (int var2 = 0; var2 < 6; ++var2)
            {
                if (MetallurgyBaseMetals.spear[var2] != null)
                {
                    ModLoader.addName(MetallurgyBaseMetals.spear[var2], var1[var2] + " Spear");
                }

                if (MetallurgyBaseMetals.halberd[var2] != null)
                {
                    ModLoader.addName(MetallurgyBaseMetals.halberd[var2], var1[var2] + " Halberd");
                }

                if (MetallurgyBaseMetals.knife[var2] != null)
                {
                    ModLoader.addName(MetallurgyBaseMetals.knife[var2], var1[var2] + " Knife");
                }

                if (MetallurgyBaseMetals.warhammer[var2] != null)
                {
                    ModLoader.addName(MetallurgyBaseMetals.warhammer[var2], var1[var2] + " Warhammer");
                }

                if (MetallurgyBaseMetals.flail[var2] != null)
                {
                    ModLoader.addName(MetallurgyBaseMetals.flail[var2], var1[var2] + " Flail");
                }
            }
        }
    }

    public void registerRenderInformation()
    {
        MinecraftForgeClient.preloadTexture("/shadow/deco.png");
        MinecraftForgeClient.preloadTexture("/shadow/MetallurgyFurnaces.png");
        MinecraftForgeClient.preloadTexture("/shadow/MetallurgyBaseMetals.png");
        MinecraftForgeClient.preloadTexture("/shadow/MetallurgyBaseAlloys.png");
        MinecraftForgeClient.preloadTexture("/shadow/LanternBlue.png");
        MinecraftForgeClient.preloadTexture("/shadow/LanternGreen.png");
        MinecraftForgeClient.preloadTexture("/shadow/LanternGrey.png");
        MinecraftForgeClient.preloadTexture("/shadow/LanternOrange.png");
        MinecraftForgeClient.preloadTexture("/shadow/LanternPurple.png");
        MinecraftForgeClient.preloadTexture("/shadow/LanternRed.png");
        MinecraftForgeClient.preloadTexture("/shadow/LanternWhite.png");
        MinecraftForgeClient.preloadTexture("/shadow/LanternYellow.png");
        MinecraftForgeClient.preloadTexture("/shadow/LanternYellow1.png");
        RenderingRegistry.registerBlockHandler(new LanternRenderHelper());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLantern.class, new TileEntityLanternRenderer());
        RenderingRegistry.registerBlockHandler(BlockMetalLadder.renderType, new LadderRenderer());
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
