package shadow.mods.metallurgy;

import cpw.mods.fml.common.network.IGuiHandler;
import java.io.File;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CoreCommonProxy implements IGuiHandler
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

    public void addCreativeTabName(String var1) {}

    public Object getServerGuiElement(int var1, EntityPlayer var2, World var3, int var4, int var5, int var6)
    {
        TileEntity var7 = var3.getBlockTileEntity(var4, var5, var6);

        if (var7 != null && var7 instanceof BC_TileEntityCrusher)
        {
            BC_TileEntityCrusher var8 = (BC_TileEntityCrusher)var7;
            ((BC_TileEntityCrusher)var7).sendPacket();
            return new BC_ContainerCrusher(var2.inventory, var8);
        }
        else
        {
            return null;
        }
    }

    public Object getClientGuiElement(int var1, EntityPlayer var2, World var3, int var4, int var5, int var6)
    {
        TileEntity var7 = var3.getBlockTileEntity(var4, var5, var6);
        return var7 != null && var7 instanceof BC_TileEntityCrusher ? new BC_GuiCrusher(var2.inventory, (BC_TileEntityCrusher)var7, "Crusher") : null;
    }

    public void addTextureOverrides() {}
}
