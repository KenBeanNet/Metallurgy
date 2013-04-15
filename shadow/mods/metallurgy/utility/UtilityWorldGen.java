package shadow.mods.metallurgy.utility;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import shadow.mods.metallurgy.MetallurgyWorldGenMinable;

public class UtilityWorldGen implements IWorldGenerator
{
    public void generate(Random var1, int var2, int var3, World var4, IChunkProvider var5, IChunkProvider var6)
    {
        if (this.spawnsInDim(var4.provider.dimensionId))
        {
            generateAllOres(var1, var2, var3, var4);
        }
    }

    public static void generateAllOres(Random var0, int var1, int var2, World var3)
    {
        if (ConfigUtility.bitumenEnabled)
        {
            generateBitumen(var3, var0, var1 * 16, var2 * 16);
        }

        if (ConfigUtility.magnesiumEnabled)
        {
            generateMagnesium(var3, var0, var1 * 16, var2 * 16);
        }

        if (ConfigUtility.phosphoriteEnabled)
        {
            generatePhosphorite(var3, var0, var1 * 16, var2 * 16);
        }

        if (ConfigUtility.potashEnabled)
        {
            generatePostash(var3, var0, var1 * 16, var2 * 16);
        }

        if (ConfigUtility.saltpeterEnabled)
        {
            generateSaltpeter(var3, var0, var1 * 16, var2 * 16);
        }

        if (ConfigUtility.sulfurEnabled)
        {
            generateSulfur(var3, var0, var1 * 16, var2 * 16);
        }
    }

    public boolean spawnsInDim(int var1)
    {
        String[] var2 = ConfigUtility.dimensions.split(" ");
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4)
        {
            String var5 = var2[var4];

            if (var5.matches("[0-9]+-[0-9]+"))
            {
                int var6 = Integer.parseInt(var5.split("-")[0]);
                int var7 = Integer.parseInt(var5.split("-")[1]);

                if (var1 >= var6 && var1 <= var7)
                {
                    return true;
                }
            }
            else if (var1 == Integer.parseInt(var5))
            {
                return true;
            }
        }

        return false;
    }

    public static void generateBitumen(World var0, Random var1, int var2, int var3)
    {
        for (int var4 = 0; var4 < ConfigUtility.BitumenVeinCount; ++var4)
        {
            int var5 = var2 + var1.nextInt(16);
            int var6 = var1.nextInt(ConfigUtility.BitumenOreHeight);
            int var7 = var3 + var1.nextInt(16);
            (new MetallurgyWorldGenMinable(MetallurgyUtility.vein.blockID, 4, ConfigUtility.BitumenOreCount)).generate(var0, var1, var5, var6, var7);
        }
    }

    public static void generateMagnesium(World var0, Random var1, int var2, int var3)
    {
        for (int var4 = 0; var4 < ConfigUtility.MagnesiumVeinCount; ++var4)
        {
            int var5 = var2 + var1.nextInt(16);
            int var6 = var1.nextInt(ConfigUtility.MagnesiumOreHeight);
            int var7 = var3 + var1.nextInt(16);
            (new MetallurgyWorldGenMinable(MetallurgyUtility.vein.blockID, 3, ConfigUtility.MagnesiumOreCount)).generate(var0, var1, var5, var6, var7);
        }
    }

    public static void generatePhosphorite(World var0, Random var1, int var2, int var3)
    {
        for (int var4 = 0; var4 < ConfigUtility.PhosphoriteVeinCount; ++var4)
        {
            int var5 = var2 + var1.nextInt(16);
            int var6 = var1.nextInt(ConfigUtility.PhosphoriteOreHeight);
            int var7 = var3 + var1.nextInt(16);
            (new MetallurgyWorldGenMinable(MetallurgyUtility.vein.blockID, 0, ConfigUtility.PhosphoriteOreCount)).generate(var0, var1, var5, var6, var7);
        }
    }

    public static void generatePostash(World var0, Random var1, int var2, int var3)
    {
        for (int var4 = 0; var4 < ConfigUtility.PotashVeinCount; ++var4)
        {
            int var5 = var2 + var1.nextInt(16);
            int var6 = var1.nextInt(ConfigUtility.PotashOreHeight);
            int var7 = var3 + var1.nextInt(16);
            (new MetallurgyWorldGenMinable(MetallurgyUtility.vein.blockID, 5, ConfigUtility.PotashOreCount)).generate(var0, var1, var5, var6, var7);
        }
    }

    public static void generateSaltpeter(World var0, Random var1, int var2, int var3)
    {
        for (int var4 = 0; var4 < ConfigUtility.SaltpeterVeinCount; ++var4)
        {
            int var5 = var2 + var1.nextInt(16);
            int var6 = var1.nextInt(ConfigUtility.SaltpeterOreHeight);
            int var7 = var3 + var1.nextInt(16);
            (new MetallurgyWorldGenMinable(MetallurgyUtility.vein.blockID, 2, ConfigUtility.SaltpeterOreCount)).generate(var0, var1, var5, var6, var7);
        }
    }

    public static void generateSulfur(World var0, Random var1, int var2, int var3)
    {
        for (int var4 = 0; var4 < ConfigUtility.SulfurVeinCount; ++var4)
        {
            int var5 = var2 + var1.nextInt(16);
            int var6 = var1.nextInt(ConfigUtility.SulfurOreHeight);
            int var7 = var3 + var1.nextInt(16);
            (new MetallurgyWorldGenMinable(MetallurgyUtility.vein.blockID, 1, ConfigUtility.SulfurOreCount)).generate(var0, var1, var5, var6, var7);
        }
    }
}
