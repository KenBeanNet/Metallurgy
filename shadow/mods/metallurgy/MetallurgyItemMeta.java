package shadow.mods.metallurgy;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class MetallurgyItemMeta extends Item
{
    public String[] names;
    public String texturePath;

    public MetallurgyItemMeta(int var1, String var2, String[] var3)
    {
        super(var1);
        this.texturePath = var2;
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.names = var3;
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    public int getIconFromDamage(int var1)
    {
        int var2 = MathHelper.clamp_int(var1, 0, this.names.length);
        return this.iconIndex + var2;
    }

    public String getItemNameIS(ItemStack var1)
    {
        int var2 = MathHelper.clamp_int(var1.getItemDamage(), 0, this.names.length);
        return super.getItemName() + "." + this.names[var2];
    }

    public String getTextureFile()
    {
        return this.texturePath;
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(int var1, CreativeTabs var2, List var3)
    {
        for (int var4 = 0; var4 < this.names.length; ++var4)
        {
            var3.add(new ItemStack(var1, 1, var4));
        }
    }
}
