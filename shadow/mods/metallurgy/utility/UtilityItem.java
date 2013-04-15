package shadow.mods.metallurgy.utility;

import net.minecraft.item.Item;

public class UtilityItem extends Item
{
    public String texturePath;

    protected UtilityItem(int var1, String var2)
    {
        super(var1);
        this.texturePath = var2;
    }

    public String getTextureFile()
    {
        return this.texturePath;
    }

    public String toString()
    {
        return this.getItemName();
    }
}
