package shadow.mods.metallurgy.base;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLantern extends ModelBase
{
    ModelRenderer Base;
    ModelRenderer Top;
    ModelRenderer Body;
    ModelRenderer AtTop;
    ModelRenderer AtSide11;
    ModelRenderer AtSide12;
    ModelRenderer AtSide13;
    ModelRenderer AtSide14;
    ModelRenderer AtSide21;
    ModelRenderer AtSide22;
    ModelRenderer AtSide23;
    ModelRenderer AtSide24;
    ModelRenderer AtSide31;
    ModelRenderer AtSide32;
    ModelRenderer AtSide33;
    ModelRenderer AtSide34;
    ModelRenderer AtSide41;
    ModelRenderer AtSide42;
    ModelRenderer AtSide43;
    ModelRenderer AtSide44;

    public ModelLantern()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Base = new ModelRenderer(this, 0, 0);
        this.Base.addBox(3.0F, 14.0F, 3.0F, 10, 2, 10);
        this.Base.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Base.setTextureSize(64, 32);
        this.Base.mirror = true;
        this.setRotation(this.Base, 0.0F, 0.0F, 0.0F);
        this.Top = new ModelRenderer(this, 0, 12);
        this.Top.addBox(4.0F, 5.0F, 4.0F, 8, 9, 8);
        this.Top.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Top.setTextureSize(64, 32);
        this.Top.mirror = true;
        this.setRotation(this.Top, 0.0F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 0);
        this.Body.addBox(3.0F, 3.0F, 3.0F, 10, 2, 10);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.setTextureSize(64, 32);
        this.Body.mirror = true;
        this.setRotation(this.Body, 0.0F, 0.0F, 0.0F);
        this.AtTop = new ModelRenderer(this, 40, 9);
        this.AtTop.addBox(7.0F, 0.0F, 7.0F, 2, 3, 2);
        this.AtTop.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.AtTop.setTextureSize(64, 32);
        this.AtTop.mirror = true;
        this.setRotation(this.AtTop, 0.0F, 0.0F, 0.0F);
        this.AtSide11 = new ModelRenderer(this, 40, 5);
        this.AtSide11.addBox(13.0F, 3.0F, 4.0F, 3, 2, 2);
        this.AtSide11.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.AtSide11.setTextureSize(64, 32);
        this.AtSide11.mirror = true;
        this.setRotation(this.AtSide11, 0.0F, 0.0F, 0.0F);
        this.AtSide12 = new ModelRenderer(this, 40, 5);
        this.AtSide12.addBox(13.0F, 3.0F, 10.0F, 3, 2, 2);
        this.AtSide12.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.AtSide12.setTextureSize(64, 32);
        this.AtSide12.mirror = true;
        this.setRotation(this.AtSide12, 0.0F, 0.0F, 0.0F);
        this.AtSide13 = new ModelRenderer(this, 40, 5);
        this.AtSide13.addBox(13.0F, 14.0F, 4.0F, 3, 2, 2);
        this.AtSide13.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.AtSide13.setTextureSize(64, 32);
        this.AtSide13.mirror = true;
        this.setRotation(this.AtSide13, 0.0F, 0.0F, 0.0F);
        this.AtSide14 = new ModelRenderer(this, 40, 5);
        this.AtSide14.addBox(13.0F, 14.0F, 10.0F, 3, 2, 2);
        this.AtSide14.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.AtSide14.setTextureSize(64, 32);
        this.AtSide14.mirror = true;
        this.setRotation(this.AtSide14, 0.0F, 0.0F, 0.0F);
        this.AtSide21 = new ModelRenderer(this, 40, 0);
        this.AtSide21.addBox(4.0F, 3.0F, 0.0F, 2, 2, 3);
        this.AtSide21.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.AtSide21.setTextureSize(64, 32);
        this.AtSide21.mirror = true;
        this.setRotation(this.AtSide21, 0.0F, 0.0F, 0.0F);
        this.AtSide22 = new ModelRenderer(this, 40, 0);
        this.AtSide22.addBox(10.0F, 3.0F, 0.0F, 2, 2, 3);
        this.AtSide22.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.AtSide22.setTextureSize(64, 32);
        this.AtSide22.mirror = true;
        this.setRotation(this.AtSide22, 0.0F, 0.0F, 0.0F);
        this.AtSide23 = new ModelRenderer(this, 40, 0);
        this.AtSide23.addBox(4.0F, 14.0F, 0.0F, 2, 2, 3);
        this.AtSide23.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.AtSide23.setTextureSize(64, 32);
        this.AtSide23.mirror = true;
        this.setRotation(this.AtSide23, 0.0F, 0.0F, 0.0F);
        this.AtSide24 = new ModelRenderer(this, 40, 0);
        this.AtSide24.addBox(10.0F, 14.0F, 0.0F, 2, 2, 3);
        this.AtSide24.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.AtSide24.setTextureSize(64, 32);
        this.AtSide24.mirror = true;
        this.setRotation(this.AtSide24, 0.0F, 0.0F, 0.0F);
        this.AtSide31 = new ModelRenderer(this, 40, 5);
        this.AtSide31.addBox(0.0F, 3.0F, 4.0F, 3, 2, 2);
        this.AtSide31.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.AtSide31.setTextureSize(64, 32);
        this.AtSide31.mirror = true;
        this.setRotation(this.AtSide31, 0.0F, 0.0F, 0.0F);
        this.AtSide32 = new ModelRenderer(this, 40, 5);
        this.AtSide32.addBox(0.0F, 3.0F, 10.0F, 3, 2, 2);
        this.AtSide32.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.AtSide32.setTextureSize(64, 32);
        this.AtSide32.mirror = true;
        this.setRotation(this.AtSide32, 0.0F, 0.0F, 0.0F);
        this.AtSide33 = new ModelRenderer(this, 40, 5);
        this.AtSide33.addBox(0.0F, 14.0F, 4.0F, 3, 2, 2);
        this.AtSide33.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.AtSide33.setTextureSize(64, 32);
        this.AtSide33.mirror = true;
        this.setRotation(this.AtSide33, 0.0F, 0.0F, 0.0F);
        this.AtSide34 = new ModelRenderer(this, 40, 5);
        this.AtSide34.addBox(0.0F, 14.0F, 10.0F, 3, 2, 2);
        this.AtSide34.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.AtSide34.setTextureSize(64, 32);
        this.AtSide34.mirror = true;
        this.setRotation(this.AtSide34, 0.0F, 0.0F, 0.0F);
        this.AtSide41 = new ModelRenderer(this, 40, 0);
        this.AtSide41.addBox(4.0F, 3.0F, 13.0F, 2, 2, 3);
        this.AtSide41.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.AtSide41.setTextureSize(64, 32);
        this.AtSide41.mirror = true;
        this.setRotation(this.AtSide41, 0.0F, 0.0F, 0.0F);
        this.AtSide42 = new ModelRenderer(this, 40, 0);
        this.AtSide42.addBox(10.0F, 3.0F, 13.0F, 2, 2, 3);
        this.AtSide42.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.AtSide42.setTextureSize(64, 32);
        this.AtSide42.mirror = true;
        this.setRotation(this.AtSide42, 0.0F, 0.0F, 0.0F);
        this.AtSide43 = new ModelRenderer(this, 40, 0);
        this.AtSide43.addBox(10.0F, 14.0F, 13.0F, 2, 2, 3);
        this.AtSide43.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.AtSide43.setTextureSize(64, 32);
        this.AtSide43.mirror = true;
        this.setRotation(this.AtSide43, 0.0F, 0.0F, 0.0F);
        this.AtSide44 = new ModelRenderer(this, 40, 0);
        this.AtSide44.addBox(4.0F, 14.0F, 13.0F, 2, 2, 3);
        this.AtSide44.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.AtSide44.setTextureSize(64, 32);
        this.AtSide44.mirror = true;
        this.setRotation(this.AtSide44, 0.0F, 0.0F, 0.0F);
    }

    public void renderAll(int var1)
    {
        this.render(var1, 0.0625F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        this.render(-1, 0.0625F);
    }

    public void render(int var1, float var2)
    {
        this.Base.render(var2);
        this.Top.render(var2);
        this.Body.render(var2);

        if (var1 == 5)
        {
            this.AtTop.render(var2);
        }
        else if (var1 == 1)
        {
            this.AtSide11.render(var2);
            this.AtSide12.render(var2);
            this.AtSide13.render(var2);
            this.AtSide14.render(var2);
        }
        else if (var1 == 2)
        {
            this.AtSide21.render(var2);
            this.AtSide22.render(var2);
            this.AtSide23.render(var2);
            this.AtSide24.render(var2);
        }
        else if (var1 == 3)
        {
            this.AtSide31.render(var2);
            this.AtSide32.render(var2);
            this.AtSide33.render(var2);
            this.AtSide34.render(var2);
        }
        else if (var1 == 4)
        {
            this.AtSide41.render(var2);
            this.AtSide42.render(var2);
            this.AtSide43.render(var2);
            this.AtSide44.render(var2);
        }
    }

    private void setRotation(ModelRenderer var1, float var2, float var3, float var4)
    {
        var1.rotateAngleX = var2;
        var1.rotateAngleY = var3;
        var1.rotateAngleZ = var4;
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7)
    {
        super.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
    }
}
