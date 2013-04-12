package shadow.mods.metallurgy;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class MetallurgyArmor extends ItemArmor implements IArmorTextureProvider
{
    public String texturePath;
    public String texturePrefix;
    private int ingotID;

    public MetallurgyArmor(int var1, String var2, String var3, EnumArmorMaterial var4, int var5, int var6, int var7)
    {
        this(var1, var2, var3, var4, var5, var6);
        this.ingotID = var7;
    }

    public MetallurgyArmor(int var1, String var2, String var3, EnumArmorMaterial var4, int var5, int var6)
    {
        super(var1, var4, var5, var6);
        this.texturePath = var2;
        this.texturePrefix = var3;
    }

    public String getArmorTextureFile(ItemStack var1)
    {
        return "/armor/" + this.texturePrefix + ".png";
    }

    public void setTexturePrefix(String var1)
    {
        this.texturePrefix = var1;
    }

    public String getTextureFile()
    {
        return this.texturePath;
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack var1, ItemStack var2)
    {
        return this.ingotID == var2.itemID;
    }
}
