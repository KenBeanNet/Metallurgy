package shadow.mods.metallurgy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;

public class CreativeTabMetallurgy extends CreativeTabs
{
    private int iconItemID = 1;

    CreativeTabMetallurgy(int var1, String var2, int var3)
    {
        super(var1, var2);
        this.iconItemID = var3;
    }

    @SideOnly(Side.CLIENT)

    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex()
    {
        return this.iconItemID;
    }

    public void setTabIconItemIndex(int var1)
    {
        this.iconItemID = var1;
    }
}
