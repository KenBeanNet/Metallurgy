package shadow.mods.metallurgy.utility;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.src.ModLoader;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class FertilizerRecipes
{
    public static void load()
    {
        ShapelessOreRecipe var0 = new ShapelessOreRecipe(new ItemStack(MetallurgyUtility.fertilizer, 8), new Object[] {"itemPhosphorus", "itemMagnesium", "itemPotash"});
        ShapelessOreRecipe var1 = new ShapelessOreRecipe(new ItemStack(MetallurgyUtility.fertilizer, 8), new Object[] {"itemPhosphorus", "itemMagnesium", "itemSaltpeter"});
        ShapelessOreRecipe var2 = new ShapelessOreRecipe(new ItemStack(MetallurgyUtility.fertilizer, 8), new Object[] {"itemPhosphorus", "itemSaltpeter", "itemPotash"});
        ShapelessOreRecipe var3 = new ShapelessOreRecipe(new ItemStack(MetallurgyUtility.fertilizer, 8), new Object[] {"itemSaltpeter", "itemMagnesium", "itemPotash"});
        ShapelessOreRecipe var4 = new ShapelessOreRecipe(new ItemStack(Item.gunpowder, 4), new Object[] {new ItemStack(Item.coal, 1, 1), "itemSulfur", "itemSaltpeter"});
        CraftingManager.getInstance().getRecipeList().add(var0);
        CraftingManager.getInstance().getRecipeList().add(var1);
        CraftingManager.getInstance().getRecipeList().add(var2);
        CraftingManager.getInstance().getRecipeList().add(var3);
        CraftingManager.getInstance().getRecipeList().add(var4);
        ModLoader.addSmelting(MetallurgyUtility.bitumen.itemID, new ItemStack(MetallurgyUtility.tar, 1));
        GameRegistry.addRecipe(new ItemStack(Block.pistonStickyBase, 1), new Object[] {"X", "|", 'X', MetallurgyUtility.tar, '|', Block.pistonBase});
        GameRegistry.addShapelessRecipe(new ItemStack(Item.magmaCream, 1), new Object[] {MetallurgyUtility.tar, Item.blazePowder});
        ShapedOreRecipe var5 = new ShapedOreRecipe(new ItemStack(MetallurgyUtility.match, 4), new Object[] {"X", "|", 'X', "itemPhosphorus", '|', Item.stick});
        ShapedOreRecipe var6 = new ShapedOreRecipe(new ItemStack(MetallurgyUtility.igniter, 1), new Object[] {"M ", " F", 'M', "itemMagnesium", 'F', Item.flint});
        GameRegistry.addRecipe(var5);
        GameRegistry.addRecipe(var6);
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MetallurgyUtility.minersTNT, 4), new Object[] {"XMX", "MTM", "XMX", 'X', "itemPhosphorus", 'M', "itemMagnesium", 'T', Block.tnt}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MetallurgyUtility.minersTNT, 4), new Object[] {"MXM", "XTX", "MXM", 'X', "itemPhosphorus", 'M', "itemMagnesium", 'T', Block.tnt}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MetallurgyUtility.largeTNT, 4), new Object[] {"XMX", "MTM", "XMX", 'X', "itemSaltpeter", 'M', "itemSulfur", 'T', Block.tnt}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MetallurgyUtility.largeTNT, 4), new Object[] {"MXM", "XTX", "MXM", 'X', "itemSaltpeter", 'M', "itemSulfur", 'T', Block.tnt}));
    }
}
