package shadow.mods.metallurgy.precious;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class FM_TileEntityMintRenderer extends TileEntitySpecialRenderer
{
    private FM_ModelMint mintModel = new FM_ModelMint();

    public void renderTileEntityMintAt(FM_TileEntityMint var1, double var2, double var4, double var6, float var8)
    {
        String var9 = "Mint.png";
        String var10 = new String("");
        int var11 = var1.getDirection();
        boolean var12 = false;
        float var13 = 0.0F;
        float var14 = 0.0F;
        int var20;

        if (!var1.func_70309_m())
        {
            boolean var15 = true;
            var13 = 0.1F;
        }
        else
        {
            var20 = var1.getDirection();
        }

        var20 = var1.getDirection();
        float var16 = (float)var1.resetTime;

        if (var16 >= 5.0F)
        {
            var14 = 0.29F * ((10.0F - var16) / 5.0F);
        }

        if (var16 < 5.0F && var16 > 0.0F)
        {
            var14 = 0.29F * (var16 / 5.0F);
        }

        if (var1.hasIngot())
        {
            var10 = var1.getIngotImage();
            var12 = true;
        }

        FM_ModelMint var17 = this.mintModel;
        FM_ModelMintHead var18 = new FM_ModelMintHead();
        this.bindTextureByName("/shadow/Mint.png");
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float)var2, (float)var4 + 1.0F, (float)var6 + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        short var19 = 0;

        if (var20 == 2)
        {
            var19 = 180;
        }

        if (var20 == 3)
        {
            var19 = 0;
        }

        if (var20 == 4)
        {
            var19 = 90;
        }

        if (var20 == 5)
        {
            var19 = -90;
        }

        GL11.glRotatef((float)var19, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F + var13, -0.5F);
        var17.renderAll();

        if (var12)
        {
            this.bindTextureByName(var10);
            (new FM_ModelIngot()).renderAll();
        }

        GL11.glTranslatef(0.0F, var14 + var13, 0.0F);
        this.bindTextureByName("/shadow/MintHead.png");
        var18.renderAll();
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
    {
        this.renderTileEntityMintAt((FM_TileEntityMint)var1, var2, var4, var6, var8);
    }
}
