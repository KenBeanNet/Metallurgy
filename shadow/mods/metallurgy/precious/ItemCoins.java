package shadow.mods.metallurgy.precious;

import net.minecraft.item.Item;

public class ItemCoins extends Item
{
    private String texturePath = "/shadow/Overrides.png";

    public ItemCoins(int var1)
    {
        super(var1);
    }

    public String getTextureFile()
    {
        return this.texturePath;
    }
}
