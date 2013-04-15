package shadow.mods.metallurgy.fantasy;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;

public class FantasyFurnace
{
    public static String texturePath = "/shadow/MetallurgyFantasyFurnace.png";
    public static int furnaceID = -1;
    public static Block metalFurnace;

    public static void load()
    {
        furnaceID = ConfigFantasy.furnaceID;
        GameRegistry.registerBlock(metalFurnace, FF_BlockMetalFurnaceItem.class);
        GameRegistry.registerTileEntity(FF_TileEntityMetalFurnace.class, "metalFantasyFurnace");

        if (ConfigFantasy.furnaceEnabled)
        {
            ModLoader.addRecipe(new ItemStack(metalFurnace, 1, 0), new Object[] {"XXX", "XFX", "XXX", 'X', MetallurgyFantasy.ores.Bar[0], 'F', Block.stoneOvenIdle});
            ModLoader.addRecipe(new ItemStack(metalFurnace, 1, 1), new Object[] {"XXX", "XFX", "XXX", 'X', MetallurgyFantasy.ores.Bar[1], 'F', new ItemStack(metalFurnace, 1, 0)});
            ModLoader.addRecipe(new ItemStack(metalFurnace, 1, 2), new Object[] {"XXX", "XFX", "XXX", 'X', MetallurgyFantasy.alloys.Bar[0], 'F', new ItemStack(metalFurnace, 1, 1)});
            ModLoader.addRecipe(new ItemStack(metalFurnace, 1, 3), new Object[] {"XXX", "XFX", "XXX", 'X', MetallurgyFantasy.ores.Bar[3], 'F', new ItemStack(metalFurnace, 1, 2)});
            ModLoader.addRecipe(new ItemStack(metalFurnace, 1, 4), new Object[] {"XXX", "XFX", "XXX", 'X', MetallurgyFantasy.ores.Bar[4], 'F', new ItemStack(metalFurnace, 1, 3)});
            ModLoader.addRecipe(new ItemStack(metalFurnace, 1, 5), new Object[] {"XXX", "XFX", "XXX", 'X', MetallurgyFantasy.ores.Bar[7], 'F', new ItemStack(metalFurnace, 1, 4)});
            ModLoader.addRecipe(new ItemStack(metalFurnace, 1, 6), new Object[] {"XXX", "XFX", "XXX", 'X', MetallurgyFantasy.alloys.Bar[2], 'F', new ItemStack(metalFurnace, 1, 5)});
            ModLoader.addRecipe(new ItemStack(metalFurnace, 1, 7), new Object[] {"XXX", "XFX", "XXX", 'X', MetallurgyFantasy.ores.Bar[9], 'F', new ItemStack(metalFurnace, 1, 6)});
            ModLoader.addRecipe(new ItemStack(metalFurnace, 1, 8), new Object[] {"XXX", "XFX", "XXX", 'X', MetallurgyFantasy.ores.Bar[10], 'F', new ItemStack(metalFurnace, 1, 7)});
            ModLoader.addRecipe(new ItemStack(metalFurnace, 1, 9), new Object[] {"XXX", "XFX", "XXX", 'X', MetallurgyFantasy.ores.Bar[11], 'F', new ItemStack(metalFurnace, 1, 8)});
            ModLoader.addRecipe(new ItemStack(metalFurnace, 1, 10), new Object[] {"XXX", "XFX", "XXX", 'X', MetallurgyFantasy.alloys.Bar[4], 'F', new ItemStack(metalFurnace, 1, 9)});
        }
    }
}
