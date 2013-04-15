package shadow.mods.metallurgy.fantasy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import shadow.mods.metallurgy.HitEntityListener;

public class FantasySwordEffectsListener implements HitEntityListener
{
    public boolean hitEntity(ItemStack var1, EntityLiving var2, EntityLiving var3)
    {
        if (Math.random() < 0.7D)
        {
            return false;
        }
        else
        {
            if (var1.getItem().itemID == MetallurgyFantasy.ores.Sword[1].itemID)
            {
                var2.addPotionEffect(new PotionEffect(15, 80, 0));
            }

            if (var1.getItem().itemID == MetallurgyFantasy.ores.Sword[3].itemID)
            {
                var3.addPotionEffect(new PotionEffect(11, 80, 0));
            }

            if (var1.getItem().itemID == MetallurgyFantasy.ores.Sword[4].itemID)
            {
                var3.addPotionEffect(new PotionEffect(5, 80, 0));
            }

            if (var1.getItem().itemID == MetallurgyFantasy.ores.Sword[5].itemID)
            {
                ;
            }

            if (var1.getItem().itemID == MetallurgyFantasy.ores.Sword[6].itemID)
            {
                ;
            }

            if (var1.getItem().itemID == MetallurgyFantasy.ores.Sword[7].itemID)
            {
                var3.addPotionEffect(new PotionEffect(3, 80, 0));
            }

            if (var1.getItem().itemID == MetallurgyFantasy.ores.Sword[9].itemID)
            {
                var3.addPotionEffect(new PotionEffect(11, 80, 1));
            }

            if (var1.getItem().itemID == MetallurgyFantasy.ores.Sword[10].itemID)
            {
                var3.addPotionEffect(new PotionEffect(12, 80, 0));
                var2.setFire(4);
            }

            if (var1.getItem().itemID == MetallurgyFantasy.ores.Sword[11].itemID)
            {
                var3.addPotionEffect(new PotionEffect(5, 80, 1));
            }

            if (var1.getItem().itemID == MetallurgyFantasy.alloys.Sword[0].itemID)
            {
                var2.addPotionEffect(new PotionEffect(15, 80, 1));
            }

            if (var1.getItem().itemID == MetallurgyFantasy.alloys.Sword[1].itemID)
            {
                var3.addPotionEffect(new PotionEffect(1, 80, 0));
            }

            if (var1.getItem().itemID == MetallurgyFantasy.alloys.Sword[2].itemID)
            {
                var3.addPotionEffect(new PotionEffect(3, 80, 0));
                var2.setFire(4);
            }

            if (var1.getItem().itemID == MetallurgyFantasy.alloys.Sword[3].itemID)
            {
                var3.addPotionEffect(new PotionEffect(11, 80, 2));
            }

            if (var1.getItem().itemID == MetallurgyFantasy.alloys.Sword[4].itemID)
            {
                var2.addPotionEffect(new PotionEffect(20, 80, 1));
                var2.setFire(4);
            }

            return false;
        }
    }

    @ForgeSubscribe
    public void onDeathEvent(LivingDeathEvent var1)
    {
        if (var1.source.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer var2 = (EntityPlayer)var1.source.getEntity();

            if (var2.getCurrentEquippedItem() == null)
            {
                return;
            }

            if (var2.getCurrentEquippedItem().itemID == MetallurgyFantasy.ores.Sword[5].itemID || var2.getCurrentEquippedItem().itemID == MetallurgyFantasy.ores.Sword[6].itemID)
            {
                try
                {
                    Method var3 = EntityLiving.class.getDeclaredMethod("dropFewItems", new Class[] {Boolean.TYPE, Integer.TYPE});
                    var3.setAccessible(true);
                    var3.invoke(var1.entityLiving, new Object[] {Boolean.valueOf(true), Integer.valueOf(0)});

                    if (var2.getCurrentEquippedItem().itemID == MetallurgyFantasy.ores.Sword[6].itemID && Math.random() > 5.0D)
                    {
                        var3.invoke(var1.entityLiving, new Object[] {Boolean.valueOf(true), Integer.valueOf(0)});
                    }
                }
                catch (SecurityException var4)
                {
                    var4.printStackTrace();
                }
                catch (NoSuchMethodException var5)
                {
                    var5.printStackTrace();
                }
                catch (IllegalArgumentException var6)
                {
                    var6.printStackTrace();
                }
                catch (IllegalAccessException var7)
                {
                    var7.printStackTrace();
                }
                catch (InvocationTargetException var8)
                {
                    var8.printStackTrace();
                }
            }
        }
    }
}
