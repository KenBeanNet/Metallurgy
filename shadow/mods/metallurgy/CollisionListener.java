package shadow.mods.metallurgy;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public interface CollisionListener
{
    void collide(World var1, int var2, int var3, int var4, Entity var5, int var6);
}
