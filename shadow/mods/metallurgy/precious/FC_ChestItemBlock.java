package shadow.mods.metallurgy.precious;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class FC_ChestItemBlock extends ItemBlock
{
    public FC_ChestItemBlock(int var1)
    {
        super(var1);
        this.setHasSubtypes(true);
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        return var1;
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4)
    {
        switch (var1.getItemDamage())
        {
            case 0:
                var3.add("6x9");
                break;

            case 1:
                var3.add("8x9");
                break;

            case 2:
                var3.add("9x9");
                break;

            case 3:
                var3.add("9x10");
                break;

            case 4:
                var3.add("9x12");
        }
    }

    public String getItemNameIS(ItemStack var1)
    {
        String var2 = "";

        switch (var1.getItemDamage())
        {
            case 0:
                var2 = "BrassChest";
                break;

            case 1:
                var2 = "SilverChest";
                break;

            case 2:
                var2 = "GoldChest";
                break;

            case 3:
                var2 = "ElectrumChest";
                break;

            case 4:
                var2 = "PlatinumChest";
                break;

            default:
                var2 = "chest";
        }

        return this.getItemName() + "." + var2;
    }
}
