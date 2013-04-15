package shadow.mods.metallurgy.fantasy;

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

        if (var7 != null && var7 instanceof FF_TileEntityMetalFurnace)
        {
            FF_TileEntityMetalFurnace var8 = (FF_TileEntityMetalFurnace)var7;
            return new FF_ContainerMetalFurnace(var2.inventory, var8);
        }
        else
        {
            return null;
        }
    }

    public Object getClientGuiElement(int var1, EntityPlayer var2, World var3, int var4, int var5, int var6)
    {
        TileEntity var7 = var3.getBlockTileEntity(var4, var5, var6);
        return var7 != null && var7 instanceof FF_TileEntityMetalFurnace ? new FF_GuiMetalFurnace(var2.inventory, (FF_TileEntityMetalFurnace)var7) : null;
    }

    public void spawnParticle(String var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13) {}

    public World getClientWorld()
    {
        return null;
    }

    public File getMinecraftDir()
    {
        return new File(".");
    }
}
