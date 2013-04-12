package shadow.mods.metallurgy;

import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MetallurgyItem extends Item
{
    public String texturePath;

    public MetallurgyItem(int var1, String var2)
    {
        super(var1);
        this.texturePath = var2;
        this.setMaxDamage(0);
    }

    public void addInformation(ItemStack var1, List var2) {}

    public String getTextureFile()
    {
        return this.texturePath;
    }

    public String toString()
    {
        return super.toString() + " " + this.getItemName();
    }
}
