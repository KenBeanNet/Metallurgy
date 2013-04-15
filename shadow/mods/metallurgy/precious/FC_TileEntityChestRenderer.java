package shadow.mods.metallurgy.precious;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class FC_TileEntityChestRenderer extends TileEntitySpecialRenderer
{
    private ModelChest chestModel = new ModelChest();

    public void renderTileEntityChestAt(FC_TileEntityChest var1, double var2, double var4, double var6, float var8)
    {
        String var9 = "brasschest.png";
        int var10 = var1.getDirection();
        int var11 = var1.getType();
        float var12 = 0.0F;

        switch (var11)
        {
            case 0:
                var9 = "brasschest.png";
                break;

            case 1:
                var9 = "silverchest.png";
                break;

            case 2:
                var9 = "goldchest.png";
                break;

            case 3:
                var9 = "electrumchest.png";
                break;

            case 4:
                var9 = "platinumchest.png";
        }

        int var13;

        if (!var1.func_70309_m())
        {
            var13 = 5;
            var12 = 0.1F;
        }
        else
        {
            Block var14 = var1.getBlockType();
            var13 = var1.getDirection();
        }

        ModelChest var17 = this.chestModel;
        this.bindTextureByName("/shadow/" + var9);
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float)var2, (float)var4 + 1.0F, (float)var6 + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        short var15 = 0;

        if (var13 == 2)
        {
            var15 = 180;
        }

        if (var13 == 3)
        {
            var15 = 0;
        }

        if (var13 == 4)
        {
            var15 = 90;
        }

        if (var13 == 5)
        {
            var15 = -90;
        }

        GL11.glRotatef((float)var15, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F + var12, -0.5F);
        float var16 = var1.prevLidAngle + (var1.lidAngle - var1.prevLidAngle) * var8;
        var16 = 1.0F - var16;
        var16 = 1.0F - var16 * var16 * var16;
        var17.chestLid.rotateAngleX = -(var16 * (float)Math.PI / 2.0F);
        var17.renderAll();
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
    {
        this.renderTileEntityChestAt((FC_TileEntityChest)var1, var2, var4, var6, var8);
    }
}
