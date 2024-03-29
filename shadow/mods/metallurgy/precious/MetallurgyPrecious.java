package shadow.mods.metallurgy.precious;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod$Init;
import cpw.mods.fml.common.Mod$Instance;
import cpw.mods.fml.common.Mod$PostInit;
import cpw.mods.fml.common.Mod$PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;
import shadow.mods.metallurgy.CreativeTabMetallurgy;
import shadow.mods.metallurgy.MetalSet;
import shadow.mods.metallurgy.MetallurgyCore;
import shadow.mods.metallurgy.MetallurgyItem;
import shadow.mods.metallurgy.MetallurgyItems;
import shadow.mods.metallurgy.RecipeHelper;
import shadow.mods.metallurgy.UpdateManager;
import shadow.mods.metallurgy.base.MetallurgyBaseMetals;
import shadow.mods.metallurgy.fantasy.MetallurgyFantasy;
import shadow.mods.metallurgy.nether.MetallurgyNether;

@Mod(
        modid = "MetallurgyPrecious",
        name = "Metallurgy Precious",
        dependencies = "after:MetallurgyCore",
        version = "2.4"
)
@NetworkMod(
        channels = {"MetallurgyPrecio"},
        clientSideRequired = true,
        serverSideRequired = false,
        packetHandler = PacketHandler.class
)
public class MetallurgyPrecious
{
    @SidedProxy(
            clientSide = "shadow.mods.metallurgy.precious.ClientProxy",
            serverSide = "shadow.mods.metallurgy.precious.CommonProxy"
    )
    public static CommonProxy proxy;
    @Mod$Instance("MetallurgyPrecious")
    public static MetallurgyPrecious instance;
    public static final String version = "2.4";
    public static MetalSet alloys;
    public static MetalSet ores;
    public static Block PreciousMetalsVein;
    public static Block PreciousMetalsBrick;
    public static Block PreciousChest;
    public static Block Mint;
    public static Block MintStorage;
    public static Item Coin;
    public static Item Stack;
    public static Item Bag;
    public static Item Bullion;
    public static Item GoldCog;
    public static CreativeTabs creativeTab;

    @Mod$PreInit
    public void preInit(FMLPreInitializationEvent var1)
    {
        ConfigPrecious.init();
        creativeTab = MetallurgyCore.getNewCreativeTab("Precious Metals", 0);
        alloys = new MetalSet(new AlloyPreciousEnum());
        ores = new MetalSet(new OrePreciousEnum());

        try
        {
            PreciousChest = (new FC_BlockChest(ConfigPrecious.PreciousChestID)).setHardness(0.5F).setResistance(0.1F).setBlockName("PreciousChest");
            Mint = (new FM_BlockMint(ConfigPrecious.PreciousMintID)).setHardness(0.5F).setResistance(0.1F).setBlockName("Mint");
            MintStorage = (new FM_BlockMintStorage(ConfigPrecious.PreciousMintLoaderID)).setHardness(0.5F).setResistance(0.1F).setBlockName("MintStorage");
            Coin = (new ItemCoins(ConfigPrecious.ItemStartID + 248)).setItemName("Coin").setCreativeTab(creativeTab).setIconIndex(8);
            Stack = (new ItemCoins(ConfigPrecious.ItemStartID + 247)).setItemName("Stack").setCreativeTab(creativeTab).setIconIndex(24);
            Bag = (new ItemCoins(ConfigPrecious.ItemStartID + 246)).setItemName("Bag").setCreativeTab(creativeTab).setIconIndex(40);
            Bullion = (new ItemCoins(ConfigPrecious.ItemStartID + 245)).setItemName("Bullion").setCreativeTab(creativeTab).setIconIndex(56);
            GoldCog = (new MetallurgyItem(ConfigPrecious.ItemStartID + 249, "/shadow/MetallurgyCoins.png")).setIconIndex(2).setItemName("GoldCog").setCreativeTab(creativeTab);
        }
        catch (IllegalArgumentException var3)
        {
            MetallurgyCore.blockError(var3);
            throw var3;
        }

        MinecraftForge.EVENT_BUS.register(new AlloyRecipes());
    }

    @Mod$Init
    public void init(FMLInitializationEvent var1)
    {
        try
        {
            Class var2 = Class.forName("xcompwiz.mystcraft.api.APICallHandler");
            Class var3 = Class.forName("xcompwiz.mystcraft.api.symbol.AgeSymbol");
            Class var4 = Class.forName("shadow.mods.metallurgy.mystcraft.OreSymbol");
            Constructor var5 = var4.getConstructor(new Class[] {MetalSet.class});
            Method var6 = var2.getMethod("registerSymbol", new Class[] {var3});
            var6.invoke(var2, new Object[] {var5.newInstance(new Object[]{ores})});
        }
        catch (Exception var7)
        {
            ;
        }

        GameRegistry.registerBlock(PreciousChest, FC_ChestItemBlock.class);
        GameRegistry.registerTileEntity(FC_TileEntityChest.class, "PreciousChest");
        GameRegistry.registerBlock(Mint);
        GameRegistry.registerTileEntity(FM_TileEntityMint.class, "Mint");
        GameRegistry.registerBlock(MintStorage);
        GameRegistry.registerTileEntity(FM_TileEntityMintStorage.class, "MintStorage");

        if (ConfigPrecious.tradesEnabled)
        {
            for (int var8 = 0; var8 < 5; ++var8)
            {
                VillagerRegistry.instance().registerVillageTradeHandler(var8, new PreciousTradeHandler());
            }
        }

        NetworkRegistry.instance().registerGuiHandler(instance, proxy);
        ((CreativeTabMetallurgy)creativeTab).setTabIconItemIndex(ores.Helmet[2].itemID);
        alloys.load();
        ores.load();
        proxy.addNames();
        proxy.registerTileEntitySpecialRenderer();
        proxy.registerRenderInformation();
        this.registerWithApi();
    }

    @Mod$PostInit
    public void load(FMLPostInitializationEvent var1)
    {
        ores.registerOres();
        alloys.registerOres();
        RecipeHelper.addAlloyRecipe(new ItemStack(alloys.Dust[0]), "dustZinc", "dustCopper");
        RecipeHelper.addAlloyRecipe(new ItemStack(alloys.Dust[1]), "dustGold", "dustSilver");

        if (ConfigPrecious.mintEnabled)
        {
            ModLoader.addRecipe(new ItemStack(GoldCog, 1), new Object[] {" G ", "GIG", " G ", 'G', Item.ingotGold, 'I', Item.ingotIron});
            ModLoader.addRecipe(new ItemStack(MintStorage, 1), new Object[] {"GBG", "PCP", "GBG", 'G', GoldCog, 'C', Block.chest, 'B', Item.ingotIron, 'P', Block.pistonBase});
            ModLoader.addRecipe(new ItemStack(Mint, 1), new Object[] {"III", "SRS", "IPI", 'G', GoldCog, 'I', Item.ingotIron, 'S', Item.stick, 'P', Block.pistonBase, 'R', Item.redstone});
        }

        RecipeHelper.addPoweredRailsRecipe("ingotBrass", 1);
        RecipeHelper.addPoweredRailsRecipe(alloys.Bar[1], 9);
        RecipeHelper.addPoweredRailsRecipe("ingotSilver", 3);
        RecipeHelper.addPoweredRailsRecipe(ores.Bar[2], 24);
        RecipeHelper.addStorageRecipe(new ItemStack(Stack, 1), new ItemStack(Coin, 1));
        RecipeHelper.addStorageRecipe(new ItemStack(Bag, 1), new ItemStack(Stack, 1));
        RecipeHelper.addStorageRecipe(new ItemStack(Bullion, 1), new ItemStack(Bag, 1));

        if (ConfigPrecious.chestsEnabled)
        {
            this.addChestRecipes();
        }

        FM_MintRecipes.minting().addMinting(ores.Bar[1].itemID, 0, 3);
        FM_MintRecipes.minting().addMinting(ores.Bar[2].itemID, 0, 27);
        FM_MintRecipes.minting().addMinting(alloys.Bar[0].itemID, 0, 1);
        FM_MintRecipes.minting().addMinting(alloys.Bar[1].itemID, 0, 13);
        FM_MintRecipes.minting().addMinting(Item.ingotGold.itemID, 0, 9);

        if (MetallurgyCore.hasBase)
        {
            FM_MintRecipes.minting().addMinting(MetallurgyBaseMetals.alloys.Bar[1].itemID, 0, 10);
            FM_MintRecipes.minting().addMinting(MetallurgyBaseMetals.alloys.Bar[3].itemID, 0, 12);
        }

        if (MetallurgyCore.hasNether)
        {
            FM_MintRecipes.minting().addMinting(MetallurgyNether.alloys.Bar[2].itemID, 0, 30);
        }

        if (MetallurgyCore.hasFantasy)
        {
            FM_MintRecipes.minting().addMinting(MetallurgyFantasy.ores.Bar[5].itemID, 0, 10);
            FM_MintRecipes.minting().addMinting(MetallurgyFantasy.ores.Bar[6].itemID, 0, 15);
            FM_MintRecipes.minting().addMinting(MetallurgyFantasy.alloys.Bar[3].itemID, 0, 32);
        }

        new UpdateManager("2.4", "Precious", "http://ladadeda.info/PreciousVersion.txt");
        ArrayList var2 = new ArrayList();
        int var3;

        for (var3 = 0; var3 < ores.numMetals; ++var3)
        {
            if (ores.Sword[var3] != null)
            {
                var2.add(Integer.valueOf(ores.Sword[var3].itemID));
            }
        }

        for (var3 = 0; var3 < alloys.numMetals; ++var3)
        {
            if (alloys.Sword[var3] != null)
            {
                var2.add(Integer.valueOf(alloys.Sword[var3].itemID));
            }
        }

        int[] var6 = new int[var2.size()];

        for (int var4 = 0; var4 < var6.length; ++var4)
        {
            var6[var4] = ((Integer)var2.get(var4)).intValue();
        }

        try
        {
            Class var7 = Class.forName("me.Golui.SwordPedestal.common.SwordPedestalMain");
            var7.getDeclaredMethod("addItems", new Class[] {int[].class}).invoke(this, new Object[] {var6});
        }
        catch (Exception var5)
        {
            ;
        }
    }

    public void addChestRecipes()
    {
        CraftingManager var1 = CraftingManager.getInstance();
        GameRegistry.addRecipe(new ItemStack(PreciousChest, 1, 2), new Object[] {"XXX", "XFX", "XXX", 'X', Item.ingotGold, 'F', new ItemStack(PreciousChest, 1, 1)});
        ShapedOreRecipe var2 = new ShapedOreRecipe(new ItemStack(PreciousChest, 1, 0), new Object[] {"XXX", "XFX", "XXX", 'X', "ingotBrass", 'F', Block.chest});
        ShapedOreRecipe var3 = new ShapedOreRecipe(new ItemStack(PreciousChest, 1, 1), new Object[] {"XXX", "XFX", "XXX", 'X', "ingotSilver", 'F', new ItemStack(PreciousChest, 1, 0)});
        ShapedOreRecipe var4 = new ShapedOreRecipe(new ItemStack(PreciousChest, 1, 3), new Object[] {"XXX", "XFX", "XXX", 'X', "ingotElectrum", 'F', new ItemStack(PreciousChest, 1, 2)});
        ShapedOreRecipe var5 = new ShapedOreRecipe(new ItemStack(PreciousChest, 1, 4), new Object[] {"XXX", "XFX", "XXX", 'X', "ingotPlatinum", 'F', new ItemStack(PreciousChest, 1, 3)});
        var1.getRecipeList().add(var2);
        var1.getRecipeList().add(var3);
        var1.getRecipeList().add(var4);
        var1.getRecipeList().add(var5);
    }

    private void registerWithApi()
    {
        MetallurgyItems.registerItem("brassChest", new ItemStack(PreciousChest, 1, 0));
        MetallurgyItems.registerItem("silverChest", new ItemStack(PreciousChest, 1, 1));
        MetallurgyItems.registerItem("goldChest", new ItemStack(PreciousChest, 1, 2));
        MetallurgyItems.registerItem("electrumChest", new ItemStack(PreciousChest, 1, 3));
        MetallurgyItems.registerItem("platinumChest", new ItemStack(PreciousChest, 1, 4));
        MetallurgyItems.registerItem("mint", new ItemStack(Mint, 1, 0));
        MetallurgyItems.registerItem("mintStorage", new ItemStack(MintStorage, 1, 0));
        MetallurgyItems.registerItem("goldCog", new ItemStack(GoldCog, 1, 0));
        MetallurgyItems.registerItem("coin", new ItemStack(Coin, 1));
        MetallurgyItems.registerItem("stack", new ItemStack(Stack, 1));
        MetallurgyItems.registerItem("bag", new ItemStack(Bag, 1));
        MetallurgyItems.registerItem("bullion", new ItemStack(Bullion, 1));
    }
}
