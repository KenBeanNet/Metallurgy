package shadow.mods.metallurgy.nether;

import cpw.mods.fml.common.network.IGuiHandler;
import java.io.File;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler
{
    public void addNames() {}

    public void registerRenderInformation() {}

    public void registerTileEntitySpecialRenderer() {}

    public Object getServerGuiElement(int var1, EntityPlayer var2, World var3, int var4, int var5, int var6)
    {
        TileEntity var7 = var3.getBlockTileEntity(var4, var5, var6);

        if (var7 != null && var7 instanceof NF_TileEntityNetherForge)
        {
            NF_TileEntityNetherForge var8 = (NF_TileEntityNetherForge)var7;
            return new NF_ContainerNetherForge(var2.inventory, var8);
        }
        else
        {
            return null;
        }
    }

    public Object getClientGuiElement(int var1, EntityPlayer var2, World var3, int var4, int var5, int var6)
    {
        TileEntity var7 = var3.getBlockTileEntity(var4, var5, var6);
        return var7 != null && var7 instanceof NF_TileEntityNetherForge ? new NF_GuiNetherForge(var2.inventory, (NF_TileEntityNetherForge)var7) : null;
    }

    public World getClientWorld()
    {
        return null;
    }

    public File getMinecraftDir()
    {
        return new File(".");
    }

    public void spawnParticle(String var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13) {}
}
