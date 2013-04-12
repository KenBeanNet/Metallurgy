package shadow.mods.metallurgy;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import shadow.mods.metallurgy.fantasy.FF_EssenceRecipes;

public class mod_Gold
{
    public static final int meta = 4;
    public static Item GoldDust = (new MetallurgyItem(CoreConfig.ItemGoldDustID, "/shadow/Overrides.png")).setIconIndex(70).setItemName("GoldDust").setCreativeTab(CreativeTabs.tabMaterials);

    public static void load()
    {
        ModLoader.addSmelting(GoldDust.itemID, new ItemStack(Item.ingotGold, 1));
        BC_CrusherRecipes.smelting();
        BC_CrusherRecipes.addCrushing(Block.oreGold.blockID, 0, new ItemStack(GoldDust, 2));
        BC_CrusherRecipes.smelting().addCrushing(Item.ingotGold.itemID, new ItemStack(GoldDust, 1));

        if (MetallurgyCore.hasFantasy)
        {
            FF_EssenceRecipes.essence().addEssenceAmount(Item.ingotGold.itemID, 6);
        }
    }
}
