package shadow.mods.metallurgy;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.world.IBlockAccess;

public class BC_CrusherRenderHelper implements ISimpleBlockRenderingHandler
{
    public void renderInventoryBlock(Block var1, int var2, int var3, RenderBlocks var4)
    {
        BC_TileEntityCrusher var5 = new BC_TileEntityCrusher();
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
        return MetallurgyCore.crusher.getRenderType();
    }
}
