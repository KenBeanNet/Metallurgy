package shadow.mods.metallurgy;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;

public interface HitEntityListener
{
    boolean hitEntity(ItemStack var1, EntityLiving var2, EntityLiving var3);
}
