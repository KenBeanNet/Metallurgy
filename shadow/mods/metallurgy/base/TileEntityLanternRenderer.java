package shadow.mods.metallurgy.base;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class TileEntityLanternRenderer extends TileEntitySpecialRenderer
{
    private ModelLantern lanternModel = new ModelLantern();
    int direction = 0;

    public void renderTileEntityLanternAt(TileEntityLantern var1, double var2, double var4, double var6, float var8)
    {
        short var9 = var1.color;

        if (var9 == 0)
        {
            this.bindTextureByName("/shadow/LanternRed.png");
        }
        else if (var9 == 1)
        {
            this.bindTextureByName("/shadow/LanternGreen.png");
        }
        else if (var9 == 2)
        {
            this.bindTextureByName("/shadow/LanternBlue.png");
        }
        else if (var9 == 3)
        {
            this.bindTextureByName("/shadow/LanternOrange.png");
        }
        else if (var9 == 4)
        {
            this.bindTextureByName("/shadow/LanternYellow.png");
        }
        else if (var9 == 5)
        {
            this.bindTextureByName("/shadow/LanternPurple.png");
        }
        else if (var9 == 6)
        {
            this.bindTextureByName("/shadow/LanternGrey.png");
        }
        else if (var9 == 7)
        {
            this.bindTextureByName("/shadow/LanternWhite.png");
        }

        if (var1.worldObj != null)
        {
            this.direction = var1.getBlockMetadata();
        }
        else
        {
            this.direction = 0;
        }

        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float)var2, (float)var4 + 1.0F, (float)var6 + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        byte var10 = 0;
        GL11.glRotatef((float)var10, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        this.lanternModel.renderAll(this.direction);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
    {
        this.renderTileEntityLanternAt((TileEntityLantern)var1, var2, var4, var6, var8);
    }
}
