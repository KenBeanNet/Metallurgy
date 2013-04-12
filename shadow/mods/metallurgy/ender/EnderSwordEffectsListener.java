package shadow.mods.metallurgy.ender;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import shadow.mods.metallurgy.HitEntityListener;

public class EnderSwordEffectsListener implements HitEntityListener
{
    public boolean hitEntity(ItemStack var1, EntityLiving var2, EntityLiving var3)
    {
        if (Math.random() < 0.5D)
        {
            return false;
        }
        else if (var2.worldObj.isRemote)
        {
            return false;
        }
        else
        {
            if (var1.getItem().itemID == MetallurgyEnder.alloys.Sword[0].itemID)
            {
                double var4 = var2.posX - Math.sin((double)var3.rotationYawHead / 180.0D * Math.PI) * 6.0D;
                double var6 = var2.posZ + Math.cos((double)var3.rotationYawHead / 180.0D * Math.PI) * 6.0D;
                double var8 = var2.posY + Math.random() * 1.0D + 1.0D;
                var2.setPosition(var4, var8, var6);
                var2.worldObj.updateEntity(var2);
            }

            return true;
        }
    }
}
