package shadow.mods.metallurgy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class BC_CrusherRecipes
{
    private static final BC_CrusherRecipes smeltingBase = new BC_CrusherRecipes();
    private static Map smeltingList = new HashMap();
    private static Map metaSmeltingList = new HashMap();

    public static final BC_CrusherRecipes smelting()
    {
        return smeltingBase;
    }

    public void addCrushing(int var1, ItemStack var2)
    {
        smeltingList.put(Integer.valueOf(var1), var2);
    }

    @Deprecated
    public ItemStack getCrushingResult(int var1)
    {
        return (ItemStack)smeltingList.get(Integer.valueOf(var1));
    }

    public Map getCrushingList()
    {
        return smeltingList;
    }

    public static void addCrushing(int var0, int var1, ItemStack var2)
    {
        metaSmeltingList.put(Arrays.asList(new Integer[] {Integer.valueOf(var0), Integer.valueOf(var1)}), var2);
    }

    public ItemStack getCrushingResult(ItemStack var1)
    {
        if (var1 == null)
        {
            return null;
        }
        else
        {
            ItemStack var2 = (ItemStack)metaSmeltingList.get(Arrays.asList(new Integer[] {Integer.valueOf(var1.itemID), Integer.valueOf(var1.getItemDamage())}));

            if (var2 != null)
            {
                return var2;
            }
            else
            {
                var2 = (ItemStack)smeltingList.get(Integer.valueOf(var1.itemID));

                if (var2 != null)
                {
                    return var2;
                }
                else
                {
                    String[] var3 = OreDictionary.getOreNames();
                    int var4 = var3.length;

                    for (int var5 = 0; var5 < var4; ++var5)
                    {
                        String var6 = var3[var5];
                        Iterator var7 = OreDictionary.getOres(var6).iterator();

                        while (var7.hasNext())
                        {
                            ItemStack var8 = (ItemStack)var7.next();

                            if (var8.itemID == var1.itemID && var8.getItemDamage() == var1.getItemDamage())
                            {
                                String var9 = "";
                                var9 = var6.contains("ore") ? "ore" : var9;
                                var9 = var6.contains("ingot") ? "ingot" : var9;
                                var9 = var6.contains("item") ? "item" : var9;

                                if (var6.contains("dust"))
                                {
                                    return null;
                                }

                                var6 = var6.replace(var9, "dust");
                                var2 = MetallurgyItems.getItem(var6);

                                if (var2 != null)
                                {
                                    if (var9.equals("ore"))
                                    {
                                        var2.stackSize = 2;
                                    }

                                    return var2;
                                }

                                ArrayList var10 = OreDictionary.getOres(var6);

                                if (var10.size() > 0)
                                {
                                    var2 = ((ItemStack)var10.get(0)).copy();

                                    if (var9.equals("ore"))
                                    {
                                        var2.stackSize = 2;
                                    }

                                    return var2;
                                }
                            }
                        }
                    }

                    return null;
                }
            }
        }
    }
}
