package shadow.mods.metallurgy;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import shadow.mods.metallurgy.fantasy.FF_EssenceRecipes;

public class mod_Iron
{
    public static final int meta = 2;
    public static Item IronDust = (new MetallurgyItem(CoreConfig.ItemIronDustID, "/shadow/Overrides.png")).setIconIndex(69).setItemName("IronDust").setCreativeTab(CreativeTabs.tabMaterials);

    public static void load()
    {
        ModLoader.addSmelting(IronDust.itemID, new ItemStack(Item.ingotIron, 1));
        BC_CrusherRecipes.smelting().addCrushing(Block.oreIron.blockID, new ItemStack(IronDust, 2));
        BC_CrusherRecipes.smelting().addCrushing(Item.ingotIron.itemID, new ItemStack(IronDust, 1));

        if (MetallurgyCore.hasFantasy)
        {
            FF_EssenceRecipes.essence().addEssenceAmount(Item.ingotIron.itemID, 3);
        }
    }
}
