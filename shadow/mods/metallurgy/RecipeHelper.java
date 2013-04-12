package shadow.mods.metallurgy;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.src.ModLoader;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeHelper
{
    public static void addBrickRecipes(int var0, int var1, Item var2, int var3)
    {
        GameRegistry.addShapelessRecipe(new ItemStack(var2, 1, var3), new Object[] {new ItemStack(var0, 1, var1)});
        GameRegistry.addRecipe(new ItemStack(var0, 4, var1), new Object[] {"##", "##", '#', new ItemStack(var2, 1, var3)});
    }

    public static void addBrickRecipes(int var0, int var1, Item var2)
    {
        addBrickRecipes(var0, var1, var2, 0);
    }

    public static void addBrickRecipes(int var0, int var1, String var2)
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(var0, 4, var1), new Object[] {"##", "##", '#', var2}));
    }

    public static void addPlateRecipe(ItemStack var0, String var1)
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(var0, new Object[] {"X X", "XXX", "XXX", 'X', var1}));
    }

    public static void addPlateRecipe(Item var0, String var1)
    {
        addPlateRecipe(new ItemStack(var0, 1), var1);
    }

    public static void addHelmetRecipe(ItemStack var0, String var1)
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(var0, new Object[] {"XXX", "X X", 'X', var1}));
    }

    public static void addHelmetRecipe(Item var0, String var1)
    {
        addHelmetRecipe(new ItemStack(var0, 1), var1);
    }

    public static void addLegsRecipe(ItemStack var0, String var1)
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(var0, new Object[] {"XXX", "X X", "X X", 'X', var1}));
    }

    public static void addLegsRecipe(Item var0, String var1)
    {
        addLegsRecipe(new ItemStack(var0, 1), var1);
    }

    public static void addBootsRecipe(ItemStack var0, String var1)
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(var0, new Object[] {"X X", "X X", 'X', var1}));
    }

    public static void addBootsRecipe(Item var0, String var1)
    {
        addBootsRecipe(new ItemStack(var0, 1), var1);
    }

    public static void addSwordRecipe(ItemStack var0, String var1)
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(var0, new Object[] {"X", "X", "S", 'X', var1, 'S', Item.stick}));
    }

    public static void addSwordRecipe(Item var0, String var1)
    {
        addSwordRecipe(new ItemStack(var0, 1), var1);
    }

    public static void addPickaxeRecipe(ItemStack var0, String var1)
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(var0, new Object[] {"XXX", " S ", " S ", 'X', var1, 'S', Item.stick}));
    }

    public static void addPickaxeRecipe(Item var0, String var1)
    {
        addPickaxeRecipe(new ItemStack(var0, 1), var1);
    }

    public static void addAxeRecipe(ItemStack var0, String var1)
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(var0, new Object[] {"XX", "SX", "S ", 'X', var1, 'S', Item.stick}));
    }

    public static void addAxeRecipe(Item var0, String var1)
    {
        addAxeRecipe(new ItemStack(var0, 1), var1);
    }

    public static void addShovelRecipe(ItemStack var0, String var1)
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(var0, new Object[] {"X", "S", "S", 'X', var1, 'S', Item.stick}));
    }

    public static void addShovelRecipe(Item var0, String var1)
    {
        addShovelRecipe(new ItemStack(var0, 1), var1);
    }

    public static void addHoeRecipe(ItemStack var0, String var1)
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(var0, new Object[] {"XX", "S ", "S ", 'X', var1, 'S', Item.stick}));
    }

    public static void addHoeRecipe(Item var0, String var1)
    {
        addHoeRecipe(new ItemStack(var0, 1), var1);
    }

    public static void addBucketRecipe(String var0)
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.bucketEmpty, 1), new Object[] {"   ", "X X", " X ", 'X', var0}));
    }

    public static void addShearsRecipe(String var0)
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.shears, 1), new Object[] {" X", "X ", 'X', var0}));
    }

    public static void addRailsRecipe(Item var0, int var1)
    {
        ModLoader.addRecipe(new ItemStack(Block.rail, var1), new Object[] {"X X", "X|X", "X X", 'X', var0, '|', Item.stick});
    }

    public static void addPoweredRailsRecipe(Item var0, int var1)
    {
        ModLoader.addRecipe(new ItemStack(Block.railPowered, var1), new Object[] {"X X", "X|X", "X X", 'X', var0, '|', Item.stick});
    }

    public static void addPoweredRailsRecipe(String var0, int var1)
    {
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Block.railPowered, var1), new Object[] {"X X", "X|X", "X X", 'X', var0, '|', Item.stick}));
    }

    public static void addStorageRecipe(ItemStack var0, ItemStack var1)
    {
        ModLoader.addShapelessRecipe(new ItemStack(var1.getItem(), 9, var1.getItemDamage()), new Object[] {var0});
        ModLoader.addRecipe(new ItemStack(var0.getItem(), 1, var0.getItemDamage()), new Object[] {"XXX", "XXX", "XXX", 'X', var1});
    }

    public static void addStorageRecipe(Item var0, Item var1)
    {
        ModLoader.addShapelessRecipe(new ItemStack(var1, 9), new Object[] {var0});
        ModLoader.addRecipe(new ItemStack(var0, 1), new Object[] {"XXX", "XXX", "XXX", 'X', var1});
    }

    public static void addAlloyRecipe(ItemStack var0, String var1, String var2)
    {
        ShapelessOreRecipe var3 = new ShapelessOreRecipe(var0, new Object[] {var1, var2});
        CraftingManager.getInstance().getRecipeList().add(var3);
    }
}
