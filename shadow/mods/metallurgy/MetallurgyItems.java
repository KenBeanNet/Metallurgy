package shadow.mods.metallurgy;

import java.util.HashMap;
import net.minecraft.item.ItemStack;

public class MetallurgyItems
{
    private static HashMap items = new HashMap();

    public static void registerItem(String var0, ItemStack var1)
    {
        if (!items.containsKey(var0))
        {
            items.put(var0, var1);
        }
    }

    public static ItemStack getItem(String var0)
    {
        return (ItemStack)items.get(var0);
    }
}
