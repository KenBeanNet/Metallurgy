package shadow.mods.metallurgy;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class BC_TileEntityCrusherRenderer extends TileEntitySpecialRenderer
{
    private BC_ModelCrusher crusherModel = new BC_ModelCrusher();

    public void renderTileEntityCrusherAt(BC_TileEntityCrusher var1, double var2, double var4, double var6, float var8)
    {
        float var10 = 0.0F;
        int var9;

        if (var1.worldObj == null)
        {
            var9 = 5;
            var10 = 0.1F;
        }
        else
        {
            var9 = var1.direction;
        }

        BC_ModelCrusher var11 = this.crusherModel;
        String var12 = "";

        if (var1.getType() == 1)
        {
            var12 = "Copper";
        }
        else if (var1.getType() == 2)
        {
            var12 = "Bronze";
        }
        else if (var1.getType() == 3)
        {
            var12 = "Iron";
        }
        else if (var1.getType() == 4)
        {
            var12 = "Steel";
        }

        if (var1.isBurning())
        {
            this.bindTextureByName("/shadow/ModelCrusher" + var12 + "Burning.png");
        }
        else
        {
            this.bindTextureByName("/shadow/ModelCrusher" + var12 + ".png");
        }

        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float)var2, (float)var4 + 1.0F, (float)var6 + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        short var13 = 0;

        if (var9 == 2)
        {
            var13 = 180;
        }

        if (var9 == 3)
        {
            var13 = 0;
        }

        if (var9 == 4)
        {
            var13 = 90;
        }

        if (var9 == 5)
        {
            var13 = -90;
        }

        GL11.glRotatef((float)var13, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F + var10, -0.5F);
        var11.renderAll();
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
    {
        this.renderTileEntityCrusherAt((BC_TileEntityCrusher)var1, var2, var4, var6, var8);
    }
}
