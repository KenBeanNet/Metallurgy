package shadow.mods.metallurgy.ender;

import java.util.Random;
import net.minecraft.world.World;
import shadow.mods.metallurgy.DisplayListener;

public class OreParticleSpawner implements DisplayListener
{
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        double var7 = 1.0D;
        double var9 = 1.0D;
        double var11 = 1.0D;
        String var13 = "";

        if (var6 == 0)
        {
            var13 = "enderOre1";
        }

        if (var6 == 1)
        {
            var13 = "enderOre2";
        }

        Random var14 = var1.rand;
        double var15 = 0.125D;

        for (int var17 = 0; var17 < 6; ++var17)
        {
            if (Math.random() >= 0.3D)
            {
                double var18 = (double)((float)var2 + var14.nextFloat());
                double var20 = (double)((float)var3 + var14.nextFloat());
                double var22 = (double)((float)var4 + var14.nextFloat());

                if (var17 == 0 && !var1.isBlockOpaqueCube(var2, var3 + 1, var4))
                {
                    var20 = (double)(var3 + 1) + var15;
                }

                if (var17 == 1 && !var1.isBlockOpaqueCube(var2, var3 - 1, var4))
                {
                    var20 = (double)(var3 + 0) - var15;
                }

                if (var17 == 2 && !var1.isBlockOpaqueCube(var2, var3, var4 + 1))
                {
                    var22 = (double)(var4 + 1) + var15;
                }

                if (var17 == 3 && !var1.isBlockOpaqueCube(var2, var3, var4 - 1))
                {
                    var22 = (double)(var4 + 0) - var15;
                }

                if (var17 == 4 && !var1.isBlockOpaqueCube(var2 + 1, var3, var4))
                {
                    var18 = (double)(var2 + 1) + var15;
                }

                if (var17 == 5 && !var1.isBlockOpaqueCube(var2 - 1, var3, var4))
                {
                    var18 = (double)(var2 + 0) - var15;
                }

                if (var18 < (double)var2 || var18 > (double)(var2 + 1) || var20 < 0.0D || var20 > (double)(var3 + 1) || var22 < (double)var4 || var22 > (double)(var4 + 1))
                {
                    MetallurgyEnder.proxy.spawnParticle(var13, var1, var18, var20, var22, var7, var9, var11);
                }
            }
        }
    }
}
