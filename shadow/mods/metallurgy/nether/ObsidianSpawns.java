package shadow.mods.metallurgy.nether;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import shadow.mods.metallurgy.MetallurgyWorldGenNetherMinable;

public class ObsidianSpawns implements IWorldGenerator
{
    public void generate(Random var1, int var2, int var3, World var4, IChunkProvider var5, IChunkProvider var6)
    {
        for (int var7 = 0; var7 < ConfigNether.ObsidianVeinCount; ++var7)
        {
            int var8 = var2 * 16 + var1.nextInt(16);
            int var9 = var1.nextInt(ConfigNether.ObsidianOreHeight);
            int var10 = var3 * 16 + var1.nextInt(16);
            (new MetallurgyWorldGenNetherMinable(Block.obsidian.blockID, 0, ConfigNether.ObsidianOreCount)).generate(var4, var1, var8, var9, var10);
        }
    }
}
