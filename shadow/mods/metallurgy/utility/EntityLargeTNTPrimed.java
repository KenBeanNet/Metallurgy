package shadow.mods.metallurgy.utility;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.network.packet.Packet60Explosion;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityLargeTNTPrimed extends Entity
{
    public int fuse;

    public EntityLargeTNTPrimed(World var1)
    {
        super(var1);
        this.fuse = 0;
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.yOffset = this.height / 2.0F;
    }

    public EntityLargeTNTPrimed(World var1, double var2, double var4, double var6)
    {
        this(var1);
        this.setPosition(var2, var4, var6);
        float var8 = (float)(Math.random() * Math.PI * 2.0D);
        this.motionX = (double)(-((float)Math.sin((double)var8)) * 0.02F);
        this.motionY = 0.20000000298023224D;
        this.motionZ = (double)(-((float)Math.cos((double)var8)) * 0.02F);
        this.fuse = 80;
        this.prevPosX = var2;
        this.prevPosY = var4;
        this.prevPosZ = var6;
    }

    protected void entityInit() {}

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.03999999910593033D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
            this.motionY *= -0.5D;
        }

        if (this.fuse-- <= 0)
        {
            if (!this.worldObj.isRemote)
            {
                this.setDead();
                this.explode();
            }
        }
        else
        {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    private void explode()
    {
        float var1 = 20.0F;
        Explosion var2 = new Explosion(this.worldObj, (Entity)null, this.posX, this.posY, this.posZ, var1);
        var2.isFlaming = false;
        var2.isSmoking = true;
        var2.doExplosionA();
        var2.doExplosionB(true);

        if (!this.worldObj.isRemote)
        {
            Iterator var3 = this.worldObj.playerEntities.iterator();

            while (var3.hasNext())
            {
                EntityPlayer var4 = (EntityPlayer)var3.next();

                if (var4.getDistanceSq(this.posX, this.posY, this.posZ) < 4096.0D)
                {
                    ((EntityPlayerMP)var4).playerNetServerHandler.sendPacketToPlayer(new Packet60Explosion(this.posX, this.posY, this.posZ, var1, var2.affectedBlockPositions, (Vec3)var2.func_77277_b().get(var4)));
                    this.sendPacket(this.posX, this.posY, this.posZ, var1, var2.affectedBlockPositions, (Vec3)var2.func_77277_b().get(var4));
                }
            }
        }
    }

    public void sendPacket(double var1, double var3, double var5, float var7, List var8, Vec3 var9)
    {
        if (!this.worldObj.isRemote)
        {
            ByteArrayOutputStream var10 = new ByteArrayOutputStream(140);
            DataOutputStream var11 = new DataOutputStream(var10);

            try
            {
                var11.writeInt(1);
                var11.writeDouble(var1);
                var11.writeDouble(var3);
                var11.writeDouble(var5);
                var11.writeFloat(var7);
                var11.write(var8.size());
                int var12 = (int)var1;
                int var13 = (int)var3;
                int var14 = (int)var5;
                Iterator var15 = var8.iterator();

                while (var15.hasNext())
                {
                    ChunkPosition var16 = (ChunkPosition)var15.next();
                    int var17 = var16.x - var12;
                    int var18 = var16.y - var13;
                    int var19 = var16.z - var14;
                    var11.writeByte(var17);
                    var11.writeByte(var18);
                    var11.writeByte(var19);
                }

                if (var9 != null)
                {
                    var11.writeFloat((float)var9.xCoord);
                    var11.writeFloat((float)var9.yCoord);
                    var11.writeFloat((float)var9.xCoord);
                }
                else
                {
                    var11.writeFloat(0.0F);
                    var11.writeFloat(0.0F);
                    var11.writeFloat(0.0F);
                }
            }
            catch (IOException var20)
            {
                ;
            }

            Packet250CustomPayload var21 = new Packet250CustomPayload();
            var21.channel = "MetallurgyUtility";
            var21.data = var10.toByteArray();
            var21.length = var10.size();
            var21.isChunkDataPacket = true;

            if (var21 != null)
            {
                PacketDispatcher.sendPacketToAllAround(var1, var3, var5, 16.0D, this.worldObj.provider.dimensionId, var21);
            }
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound var1)
    {
        var1.setByte("Fuse", (byte)this.fuse);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound var1)
    {
        this.fuse = var1.getByte("Fuse");
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }
}
