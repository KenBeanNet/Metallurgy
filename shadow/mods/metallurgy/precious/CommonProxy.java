package shadow.mods.metallurgy.precious;

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

        if (var7 != null && var7 instanceof FC_TileEntityChest)
        {
            FC_TileEntityChest var9 = (FC_TileEntityChest)var7;
            return new FC_ContainerChest(var2.inventory, var9);
        }
        else if (var7 != null && var7 instanceof FM_TileEntityMintStorage)
        {
            FM_TileEntityMintStorage var8 = (FM_TileEntityMintStorage)var7;
            return new FM_ContainerMintStorage(var2.inventory, var8);
        }
        else
        {
            return null;
        }
    }

    public Object getClientGuiElement(int var1, EntityPlayer var2, World var3, int var4, int var5, int var6)
    {
        TileEntity var7 = var3.getBlockTileEntity(var4, var5, var6);
        return var7 != null && var7 instanceof FC_TileEntityChest ? new FC_GuiChest(var2.inventory, (FC_TileEntityChest)var7) : (var7 != null && var7 instanceof FM_TileEntityMintStorage ? new FM_GuiMintStorage(var2.inventory, (FM_TileEntityMintStorage)var7) : null);
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
