package shadow.mods.metallurgy.nether;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import shadow.mods.metallurgy.MetallurgyCore;

public class NetherForge
{
    public static Block metalFurnace;

    public static void init()
    {
        try
        {
            metalFurnace = (new NF_BlockNetherForge(ConfigNether.furnaceID, false)).setHardness(3.5F).setBlockName("NetherForge");
        }
        catch (IllegalArgumentException var1)
        {
            MetallurgyCore.blockError(var1);
            throw var1;
        }
    }

    public static void load()
    {
        if (ConfigNether.furnaceEnabled)
        {
            ModLoader.addRecipe(new ItemStack(metalFurnace, 1, 0), new Object[] {"XXX", "XFX", "XXX", 'X', MetallurgyNether.ores.Bar[0], 'F', Block.stoneOvenIdle});
            ModLoader.addRecipe(new ItemStack(metalFurnace, 1, 1), new Object[] {"XXX", "XFX", "XXX", 'X', MetallurgyNether.ores.Bar[1], 'F', new ItemStack(metalFurnace, 1, 0)});
            ModLoader.addRecipe(new ItemStack(metalFurnace, 1, 2), new Object[] {"XXX", "XFX", "XXX", 'X', new ItemStack(MetallurgyNether.alloys.Bar[0], 1), 'F', new ItemStack(metalFurnace, 1, 1)});
            ModLoader.addRecipe(new ItemStack(metalFurnace, 1, 3), new Object[] {"XXX", "XFX", "XXX", 'X', MetallurgyNether.ores.Bar[3], 'F', new ItemStack(metalFurnace, 1, 2)});
            ModLoader.addRecipe(new ItemStack(metalFurnace, 1, 4), new Object[] {"XXX", "XFX", "XXX", 'X', new ItemStack(MetallurgyNether.alloys.Bar[1], 1), 'F', new ItemStack(metalFurnace, 1, 3)});
            ModLoader.addRecipe(new ItemStack(metalFurnace, 1, 5), new Object[] {"XXX", "XFX", "XXX", 'X', MetallurgyNether.ores.Bar[5], 'F', new ItemStack(metalFurnace, 1, 4)});
            ModLoader.addRecipe(new ItemStack(metalFurnace, 1, 6), new Object[] {"XXX", "XFX", "XXX", 'X', MetallurgyNether.ores.Bar[6], 'F', new ItemStack(metalFurnace, 1, 5)});
            ModLoader.addRecipe(new ItemStack(metalFurnace, 1, 7), new Object[] {"XXX", "XFX", "XXX", 'X', MetallurgyNether.ores.Bar[7], 'F', new ItemStack(metalFurnace, 1, 6)});
        }
    }
}
