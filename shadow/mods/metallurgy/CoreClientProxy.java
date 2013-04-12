package shadow.mods.metallurgy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class CoreClientProxy extends CoreCommonProxy
{
    public void addNames()
    {
        LanguageRegistry.instance().addStringLocalization("container.crusher", "Crusher");
        LanguageRegistry.instance().addStringLocalization("tile.Crusher.StoneCrusher.name", "Stone Crusher");
        LanguageRegistry.instance().addStringLocalization("tile.Crusher.CopperCrusher.name", "Copper Crusher");
        LanguageRegistry.instance().addStringLocalization("tile.Crusher.BronzeCrusher.name", "Bronze Crusher");
        LanguageRegistry.instance().addStringLocalization("tile.Crusher.IronCrusher.name", "Iron Crusher");
        LanguageRegistry.instance().addStringLocalization("tile.Crusher.SteelCrusher.name", "Steel Crusher");
        LanguageRegistry.instance().addStringLocalization("tile.VanillaBrick.0.name", "Iron Bricks");
        LanguageRegistry.instance().addStringLocalization("tile.VanillaBrick.1.name", "Gold Bricks");
        LanguageRegistry.instance().addStringLocalization("itemGroup.Metallurgy 2", "Metallurgy 2");
    }

    public void addCreativeTabName(String var1)
    {
        LanguageRegistry.instance().addStringLocalization("itemGroup." + var1, "M2: " + var1);
    }

    public static void addNamesToSet(MetalSet var0)
    {
        String[] var1 = new String[] {" ", "\u00a71", "\u00a79", "\u00a73", "\u00a7b", "\u00a72", "\u00a7a", "\u00a75", "\u00a7d", "\u00a7e", "\u00a76", "\u00a74", "\u00a7c"};

        for (int var2 = 0; var2 < var0.numMetals; ++var2)
        {
            if (!var0.info.isAlloy())
            {
                LanguageRegistry.instance().addStringLocalization("tile." + var0.setName + "Ore." + var2 + ".name", var1[var0.info.level(var2)] + var0.info.name(var2) + " Ore");
            }

            if (var0.info.hasMetalBlock())
            {
                LanguageRegistry.instance().addStringLocalization("tile." + var0.setName + "Block." + var2 + ".name", var1[var0.info.level(var2)] + var0.info.name(var2) + " Block");
            }

            LanguageRegistry.instance().addStringLocalization("tile." + var0.setName + "Brick." + var2 + ".name", var1[var0.info.level(var2)] + var0.info.name(var2) + " Bricks");
            ModLoader.addName(var0.Dust[var2], var1[var0.info.level(var2)] + var0.info.name(var2) + " Dust");
            ModLoader.addName(var0.Bar[var2], var1[var0.info.level(var2)] + var0.info.name(var2) + " Bar");

            if (!var0.info.isCatalyst(var2))
            {
                ModLoader.addName(var0.Pickaxe[var2], var0.info.name(var2) + " Pickaxe");
                ModLoader.addName(var0.Shovel[var2], var0.info.name(var2) + " Shovel");
                ModLoader.addName(var0.Axe[var2], var0.info.name(var2) + " Axe");
                ModLoader.addName(var0.Hoe[var2], var0.info.name(var2) + " Hoe");
                ModLoader.addName(var0.Sword[var2], var0.info.name(var2) + " Sword");
                ModLoader.addName(var0.Helmet[var2], var0.info.name(var2) + " Helmet");
                ModLoader.addName(var0.Plate[var2], var0.info.name(var2) + " Plate");
                ModLoader.addName(var0.Legs[var2], var0.info.name(var2) + " Legs");
                ModLoader.addName(var0.Boots[var2], var0.info.name(var2) + " Boots");
            }
        }
    }

    public void addTextureOverrides()
    {
        Item.swordWood.setIconIndex(0).setTextureFile("/shadow/Overrides.png");
        Item.pickaxeWood.setIconIndex(16).setTextureFile("/shadow/Overrides.png");
        Item.axeWood.setIconIndex(32).setTextureFile("/shadow/Overrides.png");
        Item.hoeWood.setIconIndex(48).setTextureFile("/shadow/Overrides.png");
        Item.shovelWood.setIconIndex(64).setTextureFile("/shadow/Overrides.png");
        Item.swordStone.setIconIndex(1).setTextureFile("/shadow/Overrides.png");
        Item.pickaxeStone.setIconIndex(17).setTextureFile("/shadow/Overrides.png");
        Item.axeStone.setIconIndex(33).setTextureFile("/shadow/Overrides.png");
        Item.hoeStone.setIconIndex(49).setTextureFile("/shadow/Overrides.png");
        Item.shovelStone.setIconIndex(65).setTextureFile("/shadow/Overrides.png");
        Item.swordSteel.setIconIndex(2).setTextureFile("/shadow/Overrides.png");
        Item.pickaxeSteel.setIconIndex(18).setTextureFile("/shadow/Overrides.png");
        Item.axeSteel.setIconIndex(34).setTextureFile("/shadow/Overrides.png");
        Item.hoeSteel.setIconIndex(50).setTextureFile("/shadow/Overrides.png");
        Item.shovelSteel.setIconIndex(66).setTextureFile("/shadow/Overrides.png");
        Item.swordGold.setIconIndex(3).setTextureFile("/shadow/Overrides.png");
        Item.pickaxeGold.setIconIndex(19).setTextureFile("/shadow/Overrides.png");
        Item.axeGold.setIconIndex(35).setTextureFile("/shadow/Overrides.png");
        Item.hoeGold.setIconIndex(51).setTextureFile("/shadow/Overrides.png");
        Item.shovelGold.setIconIndex(67).setTextureFile("/shadow/Overrides.png");
        Item.swordDiamond.setIconIndex(4).setTextureFile("/shadow/Overrides.png");
        Item.pickaxeDiamond.setIconIndex(20).setTextureFile("/shadow/Overrides.png");
        Item.axeDiamond.setIconIndex(36).setTextureFile("/shadow/Overrides.png");
        Item.hoeDiamond.setIconIndex(52).setTextureFile("/shadow/Overrides.png");
        Item.shovelDiamond.setIconIndex(68).setTextureFile("/shadow/Overrides.png");
        Item.helmetSteel.setIconIndex(5).setTextureFile("/shadow/Overrides.png");
        Item.plateSteel.setIconIndex(21).setTextureFile("/shadow/Overrides.png");
        Item.legsSteel.setIconIndex(37).setTextureFile("/shadow/Overrides.png");
        Item.bootsSteel.setIconIndex(53).setTextureFile("/shadow/Overrides.png");
        Item.helmetGold.setIconIndex(6).setTextureFile("/shadow/Overrides.png");
        Item.plateGold.setIconIndex(22).setTextureFile("/shadow/Overrides.png");
        Item.legsGold.setIconIndex(38).setTextureFile("/shadow/Overrides.png");
        Item.bootsGold.setIconIndex(54).setTextureFile("/shadow/Overrides.png");
        Item.helmetDiamond.setIconIndex(7).setTextureFile("/shadow/Overrides.png");
        Item.plateDiamond.setIconIndex(23).setTextureFile("/shadow/Overrides.png");
        Item.legsDiamond.setIconIndex(39).setTextureFile("/shadow/Overrides.png");
        Item.bootsDiamond.setIconIndex(55).setTextureFile("/shadow/Overrides.png");
    }

    public void registerTileEntitySpecialRenderer()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(BC_TileEntityCrusher.class, new BC_TileEntityCrusherRenderer());
    }

    public void registerRenderInformation()
    {
        RenderingRegistry.registerBlockHandler(new BC_CrusherRenderHelper());
        MinecraftForgeClient.preloadTexture("/shadow/Overrides.png");
        MinecraftForgeClient.preloadTexture("/shadow/MetallurgyTerrain.png");
        MinecraftForgeClient.preloadTexture("/shadow/MetallurgyFurnaces.png");
        MinecraftForgeClient.preloadTexture("/shadow/MetallurgyBaseMetals.png");
        MinecraftForgeClient.preloadTexture("/shadow/MetallurgyBaseAlloys.png");
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
