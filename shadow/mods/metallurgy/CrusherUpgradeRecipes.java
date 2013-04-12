package shadow.mods.metallurgy;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.oredict.OreDictionary$OreRegisterEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CrusherUpgradeRecipes
{
    public static void load()
    {
        GameRegistry.addRecipe(new ItemStack(MetallurgyCore.crusher, 1, 0), new Object[] {"XSX", "SFS", "XSX", 'X', Block.cobblestone, 'S', Item.stick, 'F', Block.stoneOvenIdle});
        ShapedOreRecipe var0 = new ShapedOreRecipe(new ItemStack(MetallurgyCore.crusher, 1, 1), new Object[] {"XXX", "XFX", "XXX", 'X', "ingotCopper", 'F', new ItemStack(MetallurgyCore.crusher, 1, 0)});
        GameRegistry.addRecipe(var0);
        ShapedOreRecipe var1 = new ShapedOreRecipe(new ItemStack(MetallurgyCore.crusher, 1, 2), new Object[] {"XXX", "XFX", "XXX", 'X', "ingotBronze", 'F', new ItemStack(MetallurgyCore.crusher, 1, 1)});
        GameRegistry.addRecipe(var1);
        GameRegistry.addRecipe(new ItemStack(MetallurgyCore.crusher, 1, 3), new Object[] {"XXX", "XFX", "XXX", 'X', Item.ingotIron, 'F', new ItemStack(MetallurgyCore.crusher, 1, 2)});
        ShapedOreRecipe var2 = new ShapedOreRecipe(new ItemStack(MetallurgyCore.crusher, 1, 4), new Object[] {"XXX", "XFX", "XXX", 'X', "ingotSteel", 'F', new ItemStack(MetallurgyCore.crusher, 1, 3)});
        GameRegistry.addRecipe(var2);
    }

    @ForgeSubscribe
    public void oreRegistered(OreDictionary$OreRegisterEvent var1)
    {
        if (var1.Name.equals("ingotCopper"))
        {
            ModLoader.addRecipe(new ItemStack(MetallurgyCore.crusher, 1, 1), new Object[] {"XXX", "XFX", "XXX", 'X', var1.Ore, 'F', new ItemStack(MetallurgyCore.crusher, 1, 0)});
        }

        if (var1.Name.equals("ingotBronze"))
        {
            ModLoader.addRecipe(new ItemStack(MetallurgyCore.crusher, 1, 2), new Object[] {"XXX", "XFX", "XXX", 'X', var1.Ore, 'F', new ItemStack(MetallurgyCore.crusher, 1, 1)});
        }

        if (var1.Name.equals("ingotSteel"))
        {
            ModLoader.addRecipe(new ItemStack(MetallurgyCore.crusher, 1, 4), new Object[] {"XXX", "XFX", "XXX", 'X', var1.Ore, 'F', new ItemStack(MetallurgyCore.crusher, 1, 3)});
        }
    }
}
