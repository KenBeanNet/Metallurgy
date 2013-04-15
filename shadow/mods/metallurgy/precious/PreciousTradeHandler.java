package shadow.mods.metallurgy.precious;

import cpw.mods.fml.common.registry.VillagerRegistry$IVillageTradeHandler;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import shadow.mods.metallurgy.MetallurgyCore;
import shadow.mods.metallurgy.base.MetallurgyBaseMetals;

public class PreciousTradeHandler implements VillagerRegistry$IVillageTradeHandler
{
    public void manipulateTradesForVillager(EntityVillager var1, MerchantRecipeList var2, Random var3)
    {
        if (var1.getProfession() == 0)
        {
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Coin, 9), new ItemStack(Item.appleRed, 8)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Coin, 9), new ItemStack(Item.bread, 4)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Coin, 9), new ItemStack(Item.chickenCooked, 8)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Coin, 9), new ItemStack(Item.cookie, 10)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Coin, 9), new ItemStack(Item.melon, 8)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Coin, 9), new ItemStack(Item.arrow, 5)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Coin, 27), new ItemStack(Item.flintAndSteel, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Coin, 27), new ItemStack(Item.shears, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.chickenRaw, 17), new ItemStack(MetallurgyPrecious.Coin, 9)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.wheat, 17), new ItemStack(MetallurgyPrecious.Coin, 9)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.fishRaw, 12), new ItemStack(MetallurgyPrecious.Coin, 9)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Block.cloth, 21), new ItemStack(MetallurgyPrecious.Coin, 9)));
        }
        else if (var1.getProfession() == 1)
        {
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Coin, 9), new ItemStack(Block.glass, 5)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Coin, 27), new ItemStack(Block.bookShelf, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 10), new ItemStack(Item.compass, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 10), new ItemStack(Item.pocketSundial, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.paper, 29), new ItemStack(MetallurgyPrecious.Coin, 9)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.book, 14), new ItemStack(MetallurgyPrecious.Coin, 9)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.writableBook), new ItemStack(MetallurgyPrecious.Coin, 9)));
        }
        else if (var1.getProfession() == 2)
        {
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Coin, 9), new ItemStack(Item.expBottle, 4)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Coin, 9), new ItemStack(Item.redstone, 4)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Coin, 9), new ItemStack(Block.glowStone, 3)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 10), new ItemStack(Item.eyeOfEnder, 1)));
        }

        if (var1.getProfession() == 3)
        {
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 9), new ItemStack(Item.helmetDiamond, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 19), new ItemStack(Item.plateDiamond, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 13), new ItemStack(Item.legsDiamond, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 9), new ItemStack(Item.bootsDiamond, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 13), new ItemStack(Item.swordDiamond, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 11), new ItemStack(Item.pickaxeDiamond, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 11), new ItemStack(Item.axeDiamond, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 7), new ItemStack(Item.shovelDiamond, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 7), new ItemStack(Item.hoeDiamond, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 6), new ItemStack(Item.helmetChain, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 14), new ItemStack(Item.plateChain, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 10), new ItemStack(Item.legsChain, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 6), new ItemStack(Item.bootsChain, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 5), new ItemStack(Item.helmetSteel, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 13), new ItemStack(Item.plateSteel, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 9), new ItemStack(Item.legsSteel, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 5), new ItemStack(Item.bootsSteel, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 10), new ItemStack(Item.swordSteel, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 8), new ItemStack(Item.pickaxeSteel, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 7), new ItemStack(Item.axeSteel, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 5), new ItemStack(Item.shovelSteel, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 5), new ItemStack(Item.hoeSteel, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.ingotIron, 1), new ItemStack(MetallurgyPrecious.Coin, 3, 1)));

            if (MetallurgyCore.hasBase)
            {
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyBaseMetals.ores.Bar[0], 5), new ItemStack(MetallurgyPrecious.Coin, 6)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyBaseMetals.ores.Bar[1], 5), new ItemStack(MetallurgyPrecious.Coin, 6)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyBaseMetals.alloys.Bar[0], 5), new ItemStack(MetallurgyPrecious.Coin, 12)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyBaseMetals.ores.Bar[2], 5), new ItemStack(MetallurgyPrecious.Stack, 3, 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyBaseMetals.alloys.Bar[4], 5), new ItemStack(MetallurgyPrecious.Stack, 6, 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 3), new ItemStack(MetallurgyBaseMetals.ores.Helmet[0], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 10), new ItemStack(MetallurgyBaseMetals.ores.Plate[0], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 6), new ItemStack(MetallurgyBaseMetals.ores.Legs[0], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 3), new ItemStack(MetallurgyBaseMetals.ores.Boots[0], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 7), new ItemStack(MetallurgyBaseMetals.ores.Sword[0], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 5), new ItemStack(MetallurgyBaseMetals.ores.Pickaxe[0], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 4), new ItemStack(MetallurgyBaseMetals.ores.Axe[0], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 2), new ItemStack(MetallurgyBaseMetals.ores.Shovel[0], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 2), new ItemStack(MetallurgyBaseMetals.ores.Hoe[0], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 4), new ItemStack(MetallurgyBaseMetals.alloys.Helmet[0], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 12), new ItemStack(MetallurgyBaseMetals.alloys.Plate[0], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 8), new ItemStack(MetallurgyBaseMetals.alloys.Legs[0], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 4), new ItemStack(MetallurgyBaseMetals.alloys.Boots[0], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 9), new ItemStack(MetallurgyBaseMetals.alloys.Sword[0], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 7), new ItemStack(MetallurgyBaseMetals.alloys.Pickaxe[0], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 6), new ItemStack(MetallurgyBaseMetals.alloys.Axe[0], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 4), new ItemStack(MetallurgyBaseMetals.alloys.Shovel[0], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 4), new ItemStack(MetallurgyBaseMetals.alloys.Hoe[0], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 7), new ItemStack(MetallurgyBaseMetals.alloys.Helmet[4], 1, 4)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 15), new ItemStack(MetallurgyBaseMetals.alloys.Plate[4], 1, 4)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 11), new ItemStack(MetallurgyBaseMetals.alloys.Legs[4], 1, 4)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 7), new ItemStack(MetallurgyBaseMetals.alloys.Boots[4], 1, 4)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 12), new ItemStack(MetallurgyBaseMetals.alloys.Sword[4], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 10), new ItemStack(MetallurgyBaseMetals.alloys.Pickaxe[4], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 9), new ItemStack(MetallurgyBaseMetals.alloys.Axe[4], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 7), new ItemStack(MetallurgyBaseMetals.alloys.Shovel[4], 1)));
                var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Stack, 7), new ItemStack(MetallurgyBaseMetals.alloys.Hoe[4], 1)));
            }
        }

        if (var1.getProfession() == 4)
        {
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Coin, 9), new ItemStack(Item.beefCooked, 7)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Coin, 9), new ItemStack(Item.porkCooked, 7)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Coin, 27), new ItemStack(Item.helmetLeather, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Coin, 27), new ItemStack(Item.plateLeather, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Coin, 27), new ItemStack(Item.legsLeather, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Coin, 27), new ItemStack(Item.bootsLeather, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyPrecious.Coin, 64), new ItemStack(Item.saddle, 1)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.beefRaw, 17), new ItemStack(MetallurgyPrecious.Coin, 9)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.porkRaw, 17), new ItemStack(MetallurgyPrecious.Coin, 9)));
            var2.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.coal, 23), new ItemStack(MetallurgyPrecious.Coin, 9)));
        }
    }
}
