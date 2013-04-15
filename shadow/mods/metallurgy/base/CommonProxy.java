package shadow.mods.metallurgy.base;

import cpw.mods.fml.common.network.IGuiHandler;
import java.io.File;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shadow.mods.metallurgy.storage.ContainerStorage;
import shadow.mods.metallurgy.storage.GuiStorage;
import shadow.mods.metallurgy.storage.TileEntityStorage;

public class CommonProxy implements IGuiHandler
{
    public void addNames() {}

    public void registerRenderInformation() {}

    public void registerTileEntitySpecialRenderer() {}

    public Object getServerGuiElement(int var1, EntityPlayer var2, World var3, int var4, int var5, int var6)
    {
        TileEntity var7 = var3.getBlockTileEntity(var4, var5, var6);
        return var7 != null && var7 instanceof BF_TileEntityMetalFurnace ? new BF_ContainerMetalFurnace(var2.inventory, (BF_TileEntityMetalFurnace)var7) : (var7 != null && var7 instanceof TileEntityStorage ? new ContainerStorage(var2.inventory, (TileEntityStorage)var7) : null);
    }

    public Object getClientGuiElement(int var1, EntityPlayer var2, World var3, int var4, int var5, int var6)
    {
        TileEntity var7 = var3.getBlockTileEntity(var4, var5, var6);
        return var7 != null && var7 instanceof BF_TileEntityMetalFurnace ? new BF_GuiMetalFurnace(var2.inventory, (BF_TileEntityMetalFurnace)var7) : (var7 != null && var7 instanceof TileEntityStorage ? new GuiStorage(var2.inventory, (TileEntityStorage)var7) : null);
    }

    public World getClientWorld()
    {
        return null;
    }

    public File getMinecraftDir()
    {
        return new File(".");
    }
}
