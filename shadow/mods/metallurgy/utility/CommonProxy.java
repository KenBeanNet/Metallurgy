package shadow.mods.metallurgy.utility;

import java.io.File;
import net.minecraft.world.World;

public class CommonProxy
{
    public void addNames() {}

    public void registerRenderInformation() {}

    public void registerTileEntitySpecialRenderer() {}

    public World getClientWorld()
    {
        return null;
    }

    public File getMinecraftDir()
    {
        return new File(".");
    }
}
