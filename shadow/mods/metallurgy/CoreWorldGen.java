package shadow.mods.metallurgy;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class CoreWorldGen implements IWorldGenerator
{
    public void generate(Random var1, int var2, int var3, World var4, IChunkProvider var5, IChunkProvider var6)
    {
        this.replaceOre(var4, var1, var2 * 16, var3 * 16);

        if (CoreConfig.DiamondEnabled)
        {
            this.generateDiamond(var4, var1, var2 * 16, var3 * 16);
        }

        if (CoreConfig.EmeraldEnabled)
        {
            this.generateEmerald(var4, var1, var2 * 16, var3 * 16);
        }

        if (CoreConfig.LapisLazuliEnabled)
        {
            this.generateLapisLazuli(var4, var1, var2 * 16, var3 * 16);
        }

        if (CoreConfig.RedstoneEnabled)
        {
            this.generateRedstone(var4, var1, var2 * 16, var3 * 16);
        }

        if (CoreConfig.goldEnabled)
        {
            this.generateGold(var4, var1, var2 * 16, var3 * 16);
        }

        if (CoreConfig.ironEnabled)
        {
            this.generateIron(var4, var1, var2 * 16, var3 * 16);
        }

        for (int var7 = 0; var7 < CoreConfig.customIDs.length; ++var7)
        {
            if (Block.blocksList[CoreConfig.customIDs[var7]] != null && this.spawnsInDimension(CoreConfig.customDimensions[var7], var4.provider.dimensionId))
            {
                for (int var8 = 0; var8 < CoreConfig.customVeinCount[var7]; ++var8)
                {
                    int var9 = var2 * 16 + var1.nextInt(16);
                    int var10 = var1.nextInt(CoreConfig.customMaxHeight[var7] - CoreConfig.customMinHeight[var7]) + CoreConfig.customMinHeight[var7];
                    int var11 = var3 * 16 + var1.nextInt(16);

                    if (var4.provider.dimensionId == -1)
                    {
                        (new MetallurgyWorldGenNetherMinable(CoreConfig.customIDs[var7], CoreConfig.customMetas[var7], CoreConfig.customOreCount[var7])).generate(var4, var1, var9, var10, var11);
                    }
                    else if (var4.provider.dimensionId == 1)
                    {
                        (new MetallurgyWorldGenEnderMinable(CoreConfig.customIDs[var7], CoreConfig.customMetas[var7], CoreConfig.customOreCount[var7])).generate(var4, var1, var9, var10, var11);
                    }
                    else
                    {
                        (new MetallurgyWorldGenMinable(CoreConfig.customIDs[var7], CoreConfig.customMetas[var7], CoreConfig.customOreCount[var7])).generate(var4, var1, var9, var10, var11);
                    }
                }
            }
        }
    }

    public boolean spawnsInDimension(String var1, int var2)
    {
        String[] var3 = var1.split(" ");
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5)
        {
            String var6 = var3[var5];

            if (var6.matches("[0-9]+-[0-9]+"))
            {
                int var7 = Integer.parseInt(var6.split("-")[0]);
                int var8 = Integer.parseInt(var6.split("-")[1]);

                if (var2 >= var7 && var2 <= var8)
                {
                    return true;
                }
            }
            else if (var2 == Integer.parseInt(var6))
            {
                return true;
            }
        }

        return false;
    }

    private boolean shouldReplace(int var1)
    {
        return var1 == Block.oreDiamond.blockID && CoreConfig.DiamondEnabled ? true : (var1 == Block.oreEmerald.blockID && CoreConfig.EmeraldEnabled ? true : (var1 == Block.oreLapis.blockID && CoreConfig.LapisLazuliEnabled ? true : (var1 == Block.oreRedstone.blockID && CoreConfig.RedstoneEnabled ? true : (var1 == Block.oreGold.blockID && CoreConfig.goldEnabled ? true : var1 == Block.oreIron.blockID && CoreConfig.ironEnabled))));
    }

    public void replaceOre(World var1, Random var2, int var3, int var4)
    {
        for (int var5 = 64; var5 >= 1; --var5)
        {
            for (int var6 = 0; var6 <= 15; ++var6)
            {
                for (int var7 = 0; var7 <= 15; ++var7)
                {
                    if (this.shouldReplace(var1.getBlockId(var3 + var6, var5, var4 + var7)))
                    {
                        var1.setBlockAndMetadata(var3 + var6, var5, var4 + var7, 1, 0);
                    }
                }
            }
        }
    }

    public void generateDiamond(World var1, Random var2, int var3, int var4)
    {
        for (int var5 = 0; var5 < CoreConfig.DiamondVeinCount; ++var5)
        {
            int var6 = var3 + var2.nextInt(16);
            int var7 = var2.nextInt(CoreConfig.DiamondOreHeight);
            int var8 = var4 + var2.nextInt(16);
            (new WorldGenMinable(Block.oreDiamond.blockID, CoreConfig.DiamondOreCount)).generate(var1, var2, var6, var7, var8);
        }
    }

    public void generateEmerald(World var1, Random var2, int var3, int var4)
    {
        for (int var5 = 0; var5 < CoreConfig.EmeraldVeinCount; ++var5)
        {
            int var6 = var3 + var2.nextInt(16);
            int var7 = var2.nextInt(CoreConfig.EmeraldOreHeight);
            int var8 = var4 + var2.nextInt(16);
            (new WorldGenMinable(Block.oreEmerald.blockID, CoreConfig.EmeraldOreCount)).generate(var1, var2, var6, var7, var8);
        }
    }

    public void generateLapisLazuli(World var1, Random var2, int var3, int var4)
    {
        for (int var5 = 0; var5 < CoreConfig.LapisLazuliVeinCount; ++var5)
        {
            int var6 = var3 + var2.nextInt(16);
            int var7 = var2.nextInt(CoreConfig.LapisLazuliOreHeight);
            int var8 = var4 + var2.nextInt(16);
            (new WorldGenMinable(Block.oreLapis.blockID, CoreConfig.LapisLazuliOreCount)).generate(var1, var2, var6, var7, var8);
        }
    }

    public void generateRedstone(World var1, Random var2, int var3, int var4)
    {
        for (int var5 = 0; var5 < CoreConfig.RedstoneVeinCount; ++var5)
        {
            int var6 = var3 + var2.nextInt(16);
            int var7 = var2.nextInt(CoreConfig.RedstoneOreHeight);
            int var8 = var4 + var2.nextInt(16);
            (new WorldGenMinable(Block.oreRedstone.blockID, CoreConfig.RedstoneOreCount)).generate(var1, var2, var6, var7, var8);
        }
    }

    public void generateIron(World var1, Random var2, int var3, int var4)
    {
        for (int var5 = 0; var5 < CoreConfig.IronVeinCount; ++var5)
        {
            int var6 = var3 + var2.nextInt(16);
            int var7 = var2.nextInt(CoreConfig.IronOreHeight);
            int var8 = var4 + var2.nextInt(16);
            (new MetallurgyWorldGenMinable(Block.oreIron.blockID, 0, CoreConfig.IronOreCount)).generate(var1, var2, var6, var7, var8);
        }
    }

    public void generateGold(World var1, Random var2, int var3, int var4)
    {
        for (int var5 = 0; var5 < CoreConfig.GoldVeinCount; ++var5)
        {
            int var6 = var3 + var2.nextInt(16);
            int var7 = var2.nextInt(CoreConfig.GoldOreHeight);
            int var8 = var4 + var2.nextInt(16);
            (new MetallurgyWorldGenMinable(Block.oreGold.blockID, 0, CoreConfig.GoldOreCount)).generate(var1, var2, var6, var7, var8);
        }
    }
}
