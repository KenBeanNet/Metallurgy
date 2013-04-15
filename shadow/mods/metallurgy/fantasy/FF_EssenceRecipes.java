package shadow.mods.metallurgy.fantasy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.ItemStack;

public class FF_EssenceRecipes
{
    private static final FF_EssenceRecipes smeltingBase = new FF_EssenceRecipes();
    private static Map smeltingList = new HashMap();
    private static Map metaSmeltingList = new HashMap();
    private static Map fuelList = new HashMap();

    public static final FF_EssenceRecipes essence()
    {
        return smeltingBase;
    }

    public void addEssenceAmount(int var1, int var2)
    {
        smeltingList.put(Integer.valueOf(var1), Integer.valueOf(var2));
    }

    @Deprecated
    public int getEssenceResuly(int var1)
    {
        return ((Integer)smeltingList.get(Integer.valueOf(var1))).intValue();
    }

    public Map getEssenceList()
    {
        return smeltingList;
    }

    public static void addEssence(int var0, int var1, int var2)
    {
        metaSmeltingList.put(Arrays.asList(new Integer[] {Integer.valueOf(var0), Integer.valueOf(var1)}), Integer.valueOf(var2));
    }

    public int getEssenceResult(ItemStack var1)
    {
        if (var1 == null)
        {
            return 0;
        }
        else
        {
            Integer var2 = (Integer)metaSmeltingList.get(Arrays.asList(new Integer[] {Integer.valueOf(var1.itemID), Integer.valueOf(var1.getItemDamage())}));

            if (var2 != null)
            {
                return var2.intValue();
            }
            else
            {
                var2 = (Integer)smeltingList.get(Integer.valueOf(var1.itemID));
                return var2 != null ? var2.intValue() : 0;
            }
        }
    }

    public static void addFuel(int var0, int var1, int var2)
    {
        fuelList.put(Arrays.asList(new Integer[] {Integer.valueOf(var0), Integer.valueOf(var1)}), Integer.valueOf(var2));
    }

    public static int getFuelAmount(ItemStack var0)
    {
        return fuelList.containsKey(Arrays.asList(new Integer[] {Integer.valueOf(var0.itemID), Integer.valueOf(var0.getItemDamage())})) ? ((Integer)fuelList.get(Arrays.asList(new Integer[] {Integer.valueOf(var0.itemID), Integer.valueOf(var0.getItemDamage())}))).intValue(): 0;
    }
}
