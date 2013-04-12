package shadow.mods.metallurgy.api;

import java.lang.reflect.Method;
import net.minecraft.item.ItemStack;

public class MetallurgyAPI
{
    private static Class metallurgyItems;
    private static Method getItem;
    private static Class crusherRecipes;
    private static Method addCrushing;
    private static Class abstractorRecipes;
    private static Method addAbstraction;
    private static Method addAbstractorFuel;
    private static Class mintRecipes;
    private static Method addMinting;

    public static ItemStack getItem(String var0)
    {
        try
        {
            if (metallurgyItems == null)
            {
                metallurgyItems = Class.forName("shadow.mods.metallurgy.MetallurgyItems");
            }

            if (getItem == null)
            {
                getItem = metallurgyItems.getDeclaredMethod("getItem", new Class[] {String.class});
            }

            Object var1 = getItem.invoke(metallurgyItems, new Object[] {var0});
            return var1 instanceof ItemStack ? (ItemStack)var1 : null;
        }
        catch (Exception var2)
        {
            System.out.println("Metallurgy API: Failed to get item " + var0);
            return null;
        }
    }

    public static void addCrusherRecipe(int var0, int var1, ItemStack var2)
    {
        try
        {
            if (crusherRecipes == null)
            {
                crusherRecipes = Class.forName("shadow.mods.metallurgy.BC_CrusherRecipes");
            }

            if (addCrushing == null)
            {
                addCrushing = crusherRecipes.getDeclaredMethod("addCrushing", new Class[] {Integer.TYPE, Integer.TYPE, ItemStack.class});
            }

            addCrushing.invoke(crusherRecipes, new Object[] {Integer.valueOf(var0), Integer.valueOf(var1), var2});
        }
        catch (Exception var4)
        {
            System.out.println("Metallurgy API: Failed to add crusher recipe" + var4);
        }
    }

    public static void addAbstractorRecipe(int var0, int var1, int var2)
    {
        try
        {
            if (abstractorRecipes == null)
            {
                abstractorRecipes = Class.forName("shadow.mods.metallurgy.fantasy.FF_EssenceRecipes");
            }

            if (addAbstraction == null)
            {
                addAbstraction = abstractorRecipes.getDeclaredMethod("addEssence", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE});
            }

            addAbstraction.invoke(abstractorRecipes, new Object[] {Integer.valueOf(var0), Integer.valueOf(var1), Integer.valueOf(var2)});
        }
        catch (Exception var4)
        {
            System.out.println("Metallurgy API: Failed to add abstractor recipe" + var4);
        }
    }

    public static void addAbstractorFuel(int var0, int var1, int var2)
    {
        try
        {
            if (abstractorRecipes == null)
            {
                abstractorRecipes = Class.forName("shadow.mods.metallurgy.fantasy.FF_EssenceRecipes");
            }

            if (addAbstractorFuel == null)
            {
                addAbstractorFuel = abstractorRecipes.getDeclaredMethod("addFuel", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE});
            }

            addAbstractorFuel.invoke(abstractorRecipes, new Object[] {Integer.valueOf(var0), Integer.valueOf(var1), Integer.valueOf(var2)});
        }
        catch (Exception var4)
        {
            System.out.println("Metallurgy API: Failed to add abstractor fuel" + var4);
        }
    }

    public static void addMintingIngot(int var0, int var1, String var2)
    {
        try
        {
            if (mintRecipes == null)
            {
                mintRecipes = Class.forName("shadow.mods.metallurgy.precious.FM_MintRecipes");
            }

            if (addMinting == null)
            {
                addMinting = mintRecipes.getDeclaredMethod("addMinting", new Class[] {Integer.TYPE, Integer.TYPE, String.class});
            }

            addMinting.invoke(mintRecipes, new Object[] {Integer.valueOf(var0), Integer.valueOf(var1), var2});
        }
        catch (Exception var4)
        {
            System.out.println("Metallurgy API: Failed to add minting recipe" + var4);
        }
    }
}
