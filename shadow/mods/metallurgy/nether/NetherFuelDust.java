package shadow.mods.metallurgy.nether;

import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.ItemStack;

public class NetherFuelDust implements IFuelHandler
{
    public int getBurnTime(ItemStack var1)
    {
        return var1.itemID == MetallurgyNether.ores.Dust[0].itemID ? 3200 : (var1.itemID == MetallurgyNether.ores.Dust[6].itemID ? 25600 : 0);
    }
}
