package shadow.mods.metallurgy.fantasy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class EntitySmallAbstractorFX extends EntityFX
{
    private float flameScale;

    public EntitySmallAbstractorFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12)
    {
        super(var1, var2, var4, var6, var8, var10, var12);
        this.motionX = this.motionX * 0.01D + var8;
        this.motionY = this.motionY * 0.01D + var10;
        this.motionZ = this.motionZ * 0.01D + var12;
        double var10000 = var2 + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
        var10000 = var4 + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
        var10000 = var6 + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
        this.flameScale = this.particleScale;
        this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
        this.noClip = true;
        this.setParticleTextureIndex(1);
    }

    public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Tessellator var8 = new Tessellator();
        var8.startDrawingQuads();
        var8.setBrightness(this.getBrightnessForRender(var2));
        float var9 = ((float)this.particleAge + var2) / (float)this.particleMaxAge * 32.0F;

        if (var9 < 0.0F)
        {
            var9 = 0.0F;
        }

        if (var9 > 1.0F)
        {
            var9 = 1.0F;
        }

        float var10 = ((float)this.particleAge + var2) / (float)this.particleMaxAge;
        this.particleScale = this.flameScale * (1.0F - var10 * var10 * 0.5F);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, ModLoader.getMinecraftInstance().renderEngine.getTexture("/shadow/AbstractorParticle.png"));
        float var11 = (float)(this.getParticleTextureIndex() % 16) / 16.0F;
        float var12 = var11 + 0.0624375F;
        float var13 = (float)(this.getParticleTextureIndex() / 16) / 16.0F;
        float var14 = var13 + 0.0624375F;
        float var15 = 0.1F * this.particleScale;
        float var16 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)var2 - interpPosX);
        float var17 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)var2 - interpPosY);
        float var18 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)var2 - interpPosZ);
        float var19 = 1.0F;
        var8.addVertexWithUV((double)(var16 - var3 * var15 - var6 * var15), (double)(var17 - var4 * var15), (double)(var18 - var5 * var15 - var7 * var15), (double)var12, (double)var14);
        var8.addVertexWithUV((double)(var16 - var3 * var15 + var6 * var15), (double)(var17 + var4 * var15), (double)(var18 - var5 * var15 + var7 * var15), (double)var12, (double)var13);
        var8.addVertexWithUV((double)(var16 + var3 * var15 + var6 * var15), (double)(var17 + var4 * var15), (double)(var18 + var5 * var15 + var7 * var15), (double)var11, (double)var13);
        var8.addVertexWithUV((double)(var16 + var3 * var15 - var6 * var15), (double)(var17 - var4 * var15), (double)(var18 + var5 * var15 - var7 * var15), (double)var11, (double)var14);
        var8.draw();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, ModLoader.getMinecraftInstance().renderEngine.getTexture("/particles.png"));
    }

    public int getBrightnessForRender(float var1)
    {
        float var2 = ((float)this.particleAge + var1) / (float)this.particleMaxAge;

        if (var2 < 0.0F)
        {
            var2 = 0.0F;
        }

        if (var2 > 1.0F)
        {
            var2 = 1.0F;
        }

        int var3 = super.getBrightnessForRender(var1);
        int var4 = var3 & 255;
        int var5 = var3 >> 16 & 255;
        var4 += (int)(var2 * 15.0F * 16.0F);

        if (var4 > 240)
        {
            var4 = 240;
        }

        return var4 | var5 << 16;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float var1)
    {
        float var2 = ((float)this.particleAge + var1) / (float)this.particleMaxAge;

        if (var2 < 0.0F)
        {
            var2 = 0.0F;
        }

        if (var2 > 1.0F)
        {
            var2 = 1.0F;
        }

        float var3 = super.getBrightness(var1);
        return var3 * var2 + (1.0F - var2);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }

        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9599999785423279D;
        this.motionY *= 0.9599999785423279D;
        this.motionZ *= 0.9599999785423279D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }
}
