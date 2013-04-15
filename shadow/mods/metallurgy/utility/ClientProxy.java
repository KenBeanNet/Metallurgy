package shadow.mods.metallurgy.utility;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
    public void addNames()
    {
        ModLoader.addLocalization("tile.UtilityVein.PhosphoriteOre.name", "Phosporite Ore");
        ModLoader.addLocalization("tile.UtilityVein.SulfurOre.name", "Sulfur Ore");
        ModLoader.addLocalization("tile.UtilityVein.SaltpeterOre.name", "Saltpeter Ore");
        ModLoader.addLocalization("tile.UtilityVein.MagnesiumOre.name", "Magnesium Ore");
        ModLoader.addLocalization("tile.UtilityVein.BitumenOre.name", "Bitumen Ore");
        ModLoader.addLocalization("tile.UtilityVein.PotashOre.name", "Potash Ore");
        ModLoader.addName(MetallurgyUtility.bitumen, "Bitumen");
        ModLoader.addName(MetallurgyUtility.tar, "Tar");
        ModLoader.addName(MetallurgyUtility.saltpeter, "Saltpeter");
        ModLoader.addName(MetallurgyUtility.potash, "Potash");
        ModLoader.addName(MetallurgyUtility.fertilizer, "Fertilizer");
        ModLoader.addName(MetallurgyUtility.magnesium, "Magnesium");
        ModLoader.addName(MetallurgyUtility.phosphorus, "Phosphorous");
        ModLoader.addName(MetallurgyUtility.sulfur, "Sulfur");
        ModLoader.addName(MetallurgyUtility.largeTNT, "HE TNT");
        ModLoader.addName(MetallurgyUtility.minersTNT, "LE TNT");
        ModLoader.addName(MetallurgyUtility.igniter, "Igniter");
        ModLoader.addName(MetallurgyUtility.match, "Match");
    }

    public void registerRenderInformation()
    {
        MinecraftForgeClient.preloadTexture("/shadow/MetallurgyUtilityOres.png");
        RenderingRegistry.registerEntityRenderingHandler(EntityMinersTNTPrimed.class, new RenderMinersTNTPrimed());
        RenderingRegistry.registerEntityRenderingHandler(EntityLargeTNTPrimed.class, new RenderLargeTNTPrimed());
    }

    public void registerTileEntitySpecialRenderer() {}

    public World getClientWorld()
    {
        return FMLClientHandler.instance().getClient().theWorld;
    }

    public File getMinecraftDir()
    {
        return Minecraft.getMinecraftDir();
    }
}
