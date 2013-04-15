package shadow.mods.metallurgy.base;

import cpw.mods.fml.common.FMLLog;
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
import ic2.api.Items;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingSpecialSpawnEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import shadow.mods.metallurgy.BC_CrusherRecipes;
import shadow.mods.metallurgy.BlockDoorMetal;
import shadow.mods.metallurgy.CreativeTabMetallurgy;
import shadow.mods.metallurgy.ItemDoorMetal;
import shadow.mods.metallurgy.MetalSet;
import shadow.mods.metallurgy.MetallurgyCore;
import shadow.mods.metallurgy.MetallurgyItems;
import shadow.mods.metallurgy.RecipeHelper;
import shadow.mods.metallurgy.UpdateManager;
import shadow.mods.metallurgy.api.MetallurgyAPI;

@Mod(
        modid = "MetallurgyBase",
        name = "Metallurgy Base",
        dependencies = "after:MetallurgyCore",
        version = "2.4"
)
@NetworkMod(
        channels = {"MetallurgyBase"},
        clientSideRequired = true,
        serverSideRequired = false,
        packetHandler = PacketHandler.class
)
public class MetallurgyBaseMetals
{
    @SidedProxy(
            clientSide = "shadow.mods.metallurgy.base.ClientProxy",
            serverSide = "shadow.mods.metallurgy.base.CommonProxy"
    )
    public static CommonProxy proxy;
    @Mod$Instance("MetallurgyBase")
    public static MetallurgyBaseMetals instance;
    public static MetalSet alloys;
    public static MetalSet ores;
    public static Block metalFurnace;
    public static Block lantern;
    public static Block ladder;
    public static Block coloredGlass;
    public static Item[] spear = new Item[7];
    public static Item[] halberd = new Item[7];
    public static Item[] knife = new Item[7];
    public static Item[] battleaxe = new Item[7];
    public static Item[] warhammer = new Item[7];
    public static Item[] flail = new Item[7];
    public static Item glassDust;
    public static Item copperItemDoor;
    public static Block copperDoor;
    public static CreativeTabs baseTab;
    public static Block storage;
    public static Block accessor;

    @Mod$PreInit
    public void preInit(FMLPreInitializationEvent var1)
    {
        ConfigBase.init();
        baseTab = MetallurgyCore.getNewCreativeTab("Base Metals", ConfigBase.ItemStartID);
        alloys = new MetalSet(new AlloyBaseEnum());
        ores = new MetalSet(new OreBaseEnum());

        try
        {
            copperItemDoor = new ItemDoorMetal(ConfigBase.itemDoorID, Material.wood, ConfigBase.doorID);
            copperDoor = new BlockDoorMetal(ConfigBase.doorID, Material.wood, ConfigBase.itemDoorID);
            copperDoor.setTextureFile("/shadow/deco.png");
            copperDoor.blockIndexInTexture = 32;
            copperItemDoor.setIconIndex(43);
            metalFurnace = (new BF_BlockMetalFurnace(ConfigBase.furnaceID, false)).setHardness(3.5F).setBlockName("MetalFurnace");
            lantern = (new BlockLantern(ConfigBase.lanternId)).setHardness(0.1F).setLightValue(1.0F).setBlockName("lantern");
            ladder = (new BlockMetalLadder(ConfigBase.ladderId, 48)).setBlockName("MetalLadder");
            glassDust = (new ItemGlassDust(ConfigBase.glassDustId, "/shadow/MetallurgyGlassLanterns.png")).setItemName("glassDust").setIconIndex(68);
            coloredGlass = (new BlockColoredGlass(ConfigBase.coloredGlassId, "/shadow/MetallurgyGlassLanterns.png")).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setBlockName("coloredGlass");
        }
        catch (IllegalArgumentException var3)
        {
            MetallurgyCore.blockError(var3);
            throw var3;
        }

        proxy.registerRenderInformation();
        MinecraftForge.EVENT_BUS.register(new FurnaceUpgradeRecipes());
        this.registerWithApi();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @ForgeSubscribe
    public void equip(LivingSpecialSpawnEvent var1)
    {
        if (Math.random() <= 0.01D)
        {
            if (var1.entity instanceof EntityZombie)
            {
                ((EntityZombie)((EntityZombie)var1.entity)).setCurrentItemOrArmor(0, new ItemStack(alloys.Sword[alloys.numMetals - 1]));
                ((EntityZombie)((EntityZombie)var1.entity)).setCurrentItemOrArmor(1, new ItemStack(alloys.Helmet[alloys.numMetals - 1]));
                ((EntityZombie)((EntityZombie)var1.entity)).setCurrentItemOrArmor(2, new ItemStack(alloys.Plate[alloys.numMetals - 1]));
                ((EntityZombie)((EntityZombie)var1.entity)).setCurrentItemOrArmor(3, new ItemStack(alloys.Legs[alloys.numMetals - 1]));
                ((EntityZombie)((EntityZombie)var1.entity)).setCurrentItemOrArmor(4, new ItemStack(alloys.Boots[alloys.numMetals - 1]));
            }
        }
    }

    @Mod$Init
    public void load(FMLInitializationEvent var1)
    {
        Class var2;

        try
        {
            var2 = Class.forName("xcompwiz.mystcraft.api.APICallHandler");
            Class var3 = Class.forName("xcompwiz.mystcraft.api.symbol.AgeSymbol");
            Class var4 = Class.forName("shadow.mods.metallurgy.mystcraft.OreSymbol");
            Constructor var5 = var4.getConstructor(new Class[] {MetalSet.class});
            Method var6 = var2.getMethod("registerSymbol", new Class[] {var3});
            var6.invoke(var2, new Object[] {var5.newInstance(new Object[]{ores})});
        }
        catch (Exception var9)
        {
            ;
        }

        GameRegistry.registerBlock(metalFurnace, BF_BlockMetalFurnaceItem.class);
        GameRegistry.registerBlock(lantern, ItemBlockLantern.class);
        GameRegistry.registerBlock(copperDoor);
        GameRegistry.registerBlock(ladder, ItemBlockMetalLadder.class);
        GameRegistry.registerBlock(coloredGlass, ItemBlockColoredGlass.class);
        GameRegistry.registerTileEntity(TileEntityLantern.class, "Lantern");
        GameRegistry.registerTileEntity(BF_TileEntityMetalFurnace.class, "metalFurnace");
        ((CreativeTabMetallurgy)baseTab).setTabIconItemIndex(alloys.Helmet[4].itemID);
        alloys.load();
        ores.load();
        lantern.setCreativeTab(baseTab);
        ladder.setCreativeTab(baseTab);
        glassDust.setCreativeTab(baseTab);
        coloredGlass.setCreativeTab(baseTab);
        NetworkRegistry.instance().registerGuiHandler(instance, proxy);

        if (ConfigBase.furnacesEnabled)
        {
            FurnaceUpgradeRecipes.load();
        }

        try
        {
            var2 = Class.forName("ic2.api.Ic2Recipes");
            ItemStack var11 = Items.getItem("reBattery");
            ItemStack var13 = Items.getItem("refinedIronIngot");
            ItemStack var15 = Items.getItem("generator");
            GameRegistry.addRecipe(var15, new Object[] {" B ", "III", " F ", 'B', var11, 'I', var13, 'F', new ItemStack(metalFurnace, 1, 2)});
            GameRegistry.addRecipe(var15, new Object[] {" B ", "III", " F ", 'B', var11, 'I', var13, 'F', new ItemStack(metalFurnace, 1, 3)});
            var11 = Items.getItem("chargedReBattery");
            GameRegistry.addRecipe(var15, new Object[] {" B ", "III", " F ", 'B', var11, 'I', var13, 'F', new ItemStack(metalFurnace, 1, 2)});
            GameRegistry.addRecipe(var15, new Object[] {" B ", "III", " F ", 'B', var11, 'I', var13, 'F', new ItemStack(metalFurnace, 1, 3)});
        }
        catch (Exception var8)
        {
            ;
        }

        try
        {
            var2 = Class.forName("weaponmod.BalkonsWeaponMod");
            this.addBalkonsWeapons();
        }
        catch (Exception var7)
        {
            ;
        }

        ShapedOreRecipe var10 = new ShapedOreRecipe(new ItemStack(Block.pistonBase, 1), new Object[] {"PPP", "SIS", "SRS", 'I', "ingotBronze", 'P', new ItemStack(Block.planks, 1, -1), 'S', Block.cobblestone, 'R', Item.redstone});
        CraftingManager.getInstance().getRecipeList().add(var10);
        var10 = new ShapedOreRecipe(new ItemStack(Block.pistonBase, 1), new Object[] {"PPP", "SIS", "SRS", 'I', "ingotHepatizon", 'P', new ItemStack(Block.planks, 1, -1), 'S', Block.cobblestone, 'R', Item.redstone});
        CraftingManager.getInstance().getRecipeList().add(var10);
        var10 = new ShapedOreRecipe(new ItemStack(Block.pistonBase, 1), new Object[] {"PPP", "SIS", "SRS", 'I', "ingotDamascus Steel", 'P', new ItemStack(Block.planks, 1, -1), 'S', Block.cobblestone, 'R', Item.redstone});
        CraftingManager.getInstance().getRecipeList().add(var10);
        var10 = new ShapedOreRecipe(new ItemStack(Block.pistonBase, 2), new Object[] {"PPP", "SIS", "SRS", 'I', "ingotAngmallen", 'P', new ItemStack(Block.planks, 1, -1), 'S', Block.cobblestone, 'R', Item.redstone});
        CraftingManager.getInstance().getRecipeList().add(var10);
        var10 = new ShapedOreRecipe(new ItemStack(Block.pistonBase, 2), new Object[] {"PPP", "SIS", "SRS", 'I', "ingotSteel", 'P', new ItemStack(Block.planks, 1, -1), 'S', Block.cobblestone, 'R', Item.redstone});
        CraftingManager.getInstance().getRecipeList().add(var10);
        ShapelessOreRecipe var14 = new ShapelessOreRecipe(new ItemStack(glassDust, 1, 1), new Object[] {new ItemStack(glassDust, 1, 0), new ItemStack(glassDust, 1, 0)});
        CraftingManager.getInstance().getRecipeList().add(var14);
        proxy.addNames();
        BC_CrusherRecipes.smelting().addCrushing(Block.glass.blockID, new ItemStack(glassDust, 1));
        MetallurgyAPI.addCrusherRecipe(Item.appleRed.itemID, 0, new ItemStack(Block.dirt));
        MetallurgyAPI.addAbstractorRecipe(Item.appleRed.itemID, 0, 50);
        MetallurgyAPI.addAbstractorFuel(Item.appleRed.itemID, 0, 40);
        MetallurgyAPI.addMintingIngot(Block.cobblestone.blockID, 3, "/shadow/MintGold.png");
        int var12;

        for (var12 = 0; var12 < 8; ++var12)
        {
            FurnaceRecipes.smelting().addSmelting(glassDust.itemID, var12 + 1, new ItemStack(coloredGlass, 1, var12), 0.0F);
        }

        for (var12 = 0; var12 < 8; ++var12)
        {
            ModLoader.addRecipe(new ItemStack(lantern, 1, var12), new Object[] {"SSS", "GTG", "SSS", 'S', Block.cobblestone, 'T', Block.torchWood, 'G', new ItemStack(coloredGlass, 1, var12)});
        }

        var14 = new ShapelessOreRecipe(new ItemStack(glassDust, 1, 1), new Object[] {new ItemStack(glassDust, 1, 0), "dustIron"});
        GameRegistry.addRecipe(var14);
        var14 = new ShapelessOreRecipe(new ItemStack(glassDust, 1, 2), new Object[] {new ItemStack(glassDust, 1, 0), "dustBronze"});
        GameRegistry.addRecipe(var14);
        var14 = new ShapelessOreRecipe(new ItemStack(glassDust, 1, 3), new Object[] {new ItemStack(glassDust, 1, 0), "dustCopper"});
        GameRegistry.addRecipe(var14);
        var14 = new ShapelessOreRecipe(new ItemStack(glassDust, 1, 4), new Object[] {new ItemStack(glassDust, 1, 0), "dustAngmallen"});
        GameRegistry.addRecipe(var14);
        var14 = new ShapelessOreRecipe(new ItemStack(glassDust, 1, 5), new Object[] {new ItemStack(glassDust, 1, 0), "dustGold"});
        GameRegistry.addRecipe(var14);
        var14 = new ShapelessOreRecipe(new ItemStack(glassDust, 1, 6), new Object[] {new ItemStack(glassDust, 1, 0), "dustManganese"});
        GameRegistry.addRecipe(var14);
        var14 = new ShapelessOreRecipe(new ItemStack(glassDust, 1, 7), new Object[] {new ItemStack(glassDust, 1, 0), "dustHepatizon"});
        GameRegistry.addRecipe(var14);
        var14 = new ShapelessOreRecipe(new ItemStack(glassDust, 1, 8), new Object[] {new ItemStack(glassDust, 1, 0), "dustSteel"});
        GameRegistry.addRecipe(var14);
        var10 = new ShapedOreRecipe(new ItemStack(ladder, 4, 0), new Object[] {"I I", "III", "I I", 'I', "ingotCopper"});
        GameRegistry.addRecipe(var10);
        var10 = new ShapedOreRecipe(new ItemStack(ladder, 4, 1), new Object[] {"I I", "III", "I I", 'I', "ingotBronze"});
        GameRegistry.addRecipe(var10);
        var10 = new ShapedOreRecipe(new ItemStack(ladder, 4, 2), new Object[] {"I I", "III", "I I", 'I', Item.ingotIron});
        GameRegistry.addRecipe(var10);
        var10 = new ShapedOreRecipe(new ItemStack(ladder, 4, 3), new Object[] {"I I", "III", "I I", 'I', "ingotSteel"});
        GameRegistry.addRecipe(var10);
        new UpdateManager("2.4", "Base", "http://ladadeda.info/BaseVersion.txt");
    }

    @Mod$PostInit
    public void postInit(FMLPostInitializationEvent var1)
    {
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

    private void addBalkonsWeapons() {}

    public void registerWithApi()
    {
        MetallurgyItems.registerItem("copperFurnace", new ItemStack(metalFurnace, 1, 0));
        MetallurgyItems.registerItem("bronzeFurnace", new ItemStack(metalFurnace, 1, 1));
        MetallurgyItems.registerItem("ironFurnace", new ItemStack(metalFurnace, 1, 2));
        MetallurgyItems.registerItem("steelFurnace", new ItemStack(metalFurnace, 1, 3));
    }

    @Mod$PostInit
    public void load(FMLPostInitializationEvent var1)
    {
        FMLLog.fine("Registering Base ores", new Object[] {"MetallurgyPrecious"});
        ores.registerOres();
        alloys.registerOres();

        if (ConfigBase.alloyEnabled[0])
        {
            RecipeHelper.addAlloyRecipe(new ItemStack(alloys.Dust[0], 1), "dustCopper", "dustTin");
        }

        if (ConfigBase.alloyEnabled[1])
        {
            RecipeHelper.addAlloyRecipe(new ItemStack(alloys.Dust[1], 1), "dustBronze", "dustGold");
        }

        if (ConfigBase.alloyEnabled[2])
        {
            RecipeHelper.addAlloyRecipe(new ItemStack(alloys.Dust[2], 1), "dustBronze", "dustIron");
        }

        if (ConfigBase.alloyEnabled[3])
        {
            RecipeHelper.addAlloyRecipe(new ItemStack(alloys.Dust[3], 1), "dustGold", "dustIron");
        }

        if (ConfigBase.alloyEnabled[4])
        {
            RecipeHelper.addAlloyRecipe(new ItemStack(alloys.Dust[4], 1), "dustIron", "dustManganese");
        }
    }
}
