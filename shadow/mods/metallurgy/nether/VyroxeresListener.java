package shadow.mods.metallurgy.nether;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import shadow.mods.metallurgy.CollisionListener;

public class VyroxeresListener implements CollisionListener
{
    public void collide(World var1, int var2, int var3, int var4, Entity var5, int var6)
    {
        if (var6 == 3)
        {
            if (var5 instanceof EntityLiving)
            {
                ((EntityLiving)((EntityLiving)var5)).addPotionEffect(new PotionEffect(19, 80, 0));
            }
        }
    }
}
