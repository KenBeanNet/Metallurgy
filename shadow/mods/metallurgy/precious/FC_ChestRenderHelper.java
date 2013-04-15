package shadow.mods.metallurgy.precious;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.world.IBlockAccess;

public class FC_ChestRenderHelper implements ISimpleBlockRenderingHandler
{
    public void renderInventoryBlock(Block var1, int var2, int var3, RenderBlocks var4)
    {
        FC_TileEntityChest var5 = new FC_TileEntityChest();
        var5.setType(var2);
        TileEntityRenderer.instance.renderTileEntityAt(var5, 0.0D, 0.0D, 0.0D, 0.0F);
    }

    public boolean renderWorldBlock(IBlockAccess var1, int var2, int var3, int var4, Block var5, int var6, RenderBlocks var7)
    {
        return false;
    }

    public boolean shouldRender3DInInventory()
    {
        return true;
    }

    public int getRenderId()
    {
        return MetallurgyPrecious.PreciousChest.getRenderType();
    }
}
