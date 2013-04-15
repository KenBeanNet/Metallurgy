package shadow.mods.metallurgy.nether;

import java.util.Random;
import net.minecraft.world.World;
import shadow.mods.metallurgy.DisplayListener;

public class VyroxeresDisplay implements DisplayListener
{
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        String var7 = "";
        double var8 = 0.0D;
        double var10 = 0.3D;

        if (var6 == 3)
        {
            var7 = "mobSpell";
            var8 = 0.9D;
            var10 = 0.7D;
        }
        else if (var6 == 6)
        {
            var10 = 0.3D;
            var7 = "flame";
        }
        else if (var6 == 2)
        {
            var7 = "nether2";
        }
        else if (var6 == 4)
        {
            var7 = "nether4";
        }
        else if (var6 == 5)
        {
            var7 = "nether5";
        }
        else if (var6 == 7)
        {
            var7 = "nether7";
        }

        Random var12 = var1.rand;
        double var13 = 0.0625D;

        for (int var15 = 0; var15 < 6; ++var15)
        {
            if (Math.random() <= var10)
            {
                double var16 = (double)((float)var2 + var12.nextFloat());
                double var18 = (double)((float)var3 + var12.nextFloat());
                double var20 = (double)((float)var4 + var12.nextFloat());

                if (var15 == 0 && !var1.isBlockOpaqueCube(var2, var3 + 1, var4))
                {
                    var18 = (double)(var3 + 1) + var13;
                }

                if (var15 == 1 && !var1.isBlockOpaqueCube(var2, var3 - 1, var4))
                {
                    var18 = (double)(var3 + 0) - var13;
                }

                if (var15 == 2 && !var1.isBlockOpaqueCube(var2, var3, var4 + 1))
                {
                    var20 = (double)(var4 + 1) + var13;
                }

                if (var15 == 3 && !var1.isBlockOpaqueCube(var2, var3, var4 - 1))
                {
                    var20 = (double)(var4 + 0) - var13;
                }

                if (var15 == 4 && !var1.isBlockOpaqueCube(var2 + 1, var3, var4))
                {
                    var16 = (double)(var2 + 1) + var13;
                }

                if (var15 == 5 && !var1.isBlockOpaqueCube(var2 - 1, var3, var4))
                {
                    var16 = (double)(var2 + 0) - var13;
                }

                if (var16 < (double)var2 || var16 > (double)(var2 + 1) || var18 < 0.0D || var18 > (double)(var3 + 1) || var20 < (double)var4 || var20 > (double)(var4 + 1))
                {
                    if (var7.contains("nether"))
                    {
                        MetallurgyNether.proxy.spawnParticle(var7, var1, var16, var18, var20, 0.0D, 0.0D, 0.0D);
                    }
                    else
                    {
                        var1.spawnParticle(var7, var16, var18, var20, 0.0D, var8, 0.0D);
                    }
                }
            }
        }
    }
}
