package shadow.mods.metallurgy.base;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

public class LadderRenderer implements ISimpleBlockRenderingHandler
{
    public void renderInventoryBlock(Block var1, int var2, int var3, RenderBlocks var4)
    {
        System.out.println("rendering inventory");
    }

    public boolean renderWorldBlock(IBlockAccess var1, int var2, int var3, int var4, Block var5, int var6, RenderBlocks var7)
    {
        Tessellator var8 = Tessellator.instance;
        int var9 = var1.getBlockMetadata(var2, var3, var4);
        int var10 = var5.getBlockTextureFromSideAndMetadata(0, var9);
        var9 %= 4;
        var8.setBrightness(var5.getMixedBrightnessForBlock(var1, var2, var3, var4));
        float var11 = 1.0F;
        var8.setColorOpaque_F(var11, var11, var11);
        int var12 = (var10 & 15) << 4;
        int var13 = var10 & 240;
        double var14 = (double)((float)var12 / 256.0F);
        double var16 = (double)(((float)var12 + 15.99F) / 256.0F);
        double var18 = (double)((float)var13 / 256.0F);
        double var20 = (double)(((float)var13 + 15.99F) / 256.0F);
        double var22 = 0.0D;
        double var24 = 0.05000000074505806D;

        if (var9 == 3)
        {
            var8.addVertexWithUV((double)var2 + var24, (double)(var3 + 1) + var22, (double)(var4 + 1) + var22, var14, var18);
            var8.addVertexWithUV((double)var2 + var24, (double)(var3 + 0) - var22, (double)(var4 + 1) + var22, var14, var20);
            var8.addVertexWithUV((double)var2 + var24, (double)(var3 + 0) - var22, (double)(var4 + 0) - var22, var16, var20);
            var8.addVertexWithUV((double)var2 + var24, (double)(var3 + 1) + var22, (double)(var4 + 0) - var22, var16, var18);
        }

        if (var9 == 2)
        {
            var8.addVertexWithUV((double)(var2 + 1) - var24, (double)(var3 + 0) - var22, (double)(var4 + 1) + var22, var16, var20);
            var8.addVertexWithUV((double)(var2 + 1) - var24, (double)(var3 + 1) + var22, (double)(var4 + 1) + var22, var16, var18);
            var8.addVertexWithUV((double)(var2 + 1) - var24, (double)(var3 + 1) + var22, (double)(var4 + 0) - var22, var14, var18);
            var8.addVertexWithUV((double)(var2 + 1) - var24, (double)(var3 + 0) - var22, (double)(var4 + 0) - var22, var14, var20);
        }

        if (var9 == 1)
        {
            var8.addVertexWithUV((double)(var2 + 1) + var22, (double)(var3 + 0) - var22, (double)var4 + var24, var16, var20);
            var8.addVertexWithUV((double)(var2 + 1) + var22, (double)(var3 + 1) + var22, (double)var4 + var24, var16, var18);
            var8.addVertexWithUV((double)(var2 + 0) - var22, (double)(var3 + 1) + var22, (double)var4 + var24, var14, var18);
            var8.addVertexWithUV((double)(var2 + 0) - var22, (double)(var3 + 0) - var22, (double)var4 + var24, var14, var20);
        }

        if (var9 == 0)
        {
            var8.addVertexWithUV((double)(var2 + 1) + var22, (double)(var3 + 1) + var22, (double)(var4 + 1) - var24, var14, var18);
            var8.addVertexWithUV((double)(var2 + 1) + var22, (double)(var3 + 0) - var22, (double)(var4 + 1) - var24, var14, var20);
            var8.addVertexWithUV((double)(var2 + 0) - var22, (double)(var3 + 0) - var22, (double)(var4 + 1) - var24, var16, var20);
            var8.addVertexWithUV((double)(var2 + 0) - var22, (double)(var3 + 1) + var22, (double)(var4 + 1) - var24, var16, var18);
        }

        return true;
    }

    public boolean shouldRender3DInInventory()
    {
        return false;
    }

    public int getRenderId()
    {
        return BlockMetalLadder.renderType;
    }
}
