package shadow.mods.metallurgy.base;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class FurnaceUpgradeRecipes
{
    public static void load()
    {
        ShapedOreRecipe var0 = new ShapedOreRecipe(new ItemStack(MetallurgyBaseMetals.metalFurnace, 1, 0), new Object[] {"XXX", "XFX", "XXX", 'X', "ingotCopper", 'F', Block.stoneOvenIdle});
        GameRegistry.addRecipe(var0);
        ShapedOreRecipe var1 = new ShapedOreRecipe(new ItemStack(MetallurgyBaseMetals.metalFurnace, 1, 1), new Object[] {"XXX", "XFX", "XXX", 'X', "ingotBronze", 'F', new ItemStack(MetallurgyBaseMetals.metalFurnace, 1, 0)});
        GameRegistry.addRecipe(var1);
        GameRegistry.addRecipe(new ItemStack(MetallurgyBaseMetals.metalFurnace, 1, 2), new Object[] {"XXX", "XFX", "XXX", 'X', Item.ingotIron, 'F', new ItemStack(MetallurgyBaseMetals.metalFurnace, 1, 1)});
        ShapedOreRecipe var2 = new ShapedOreRecipe(new ItemStack(MetallurgyBaseMetals.metalFurnace, 1, 3), new Object[] {"XXX", "XFX", "XXX", 'X', "ingotSteel", 'F', new ItemStack(MetallurgyBaseMetals.metalFurnace, 1, 2)});
        GameRegistry.addRecipe(var2);
    }
}
