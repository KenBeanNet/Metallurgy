package shadow.mods.metallurgy;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod$Init;
import cpw.mods.fml.common.Mod$Instance;
import cpw.mods.fml.common.Mod$PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import ic2.api.Items;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.oredict.OreDictionary;
import railcraft.common.api.crafting.RailcraftCraftingManager;
import shadow.mods.metallurgy.MetallurgyCore$1;

@Mod(
        modid = "MetallurgyCore",
        name = "Metallurgy Core",
        version = "2.3.4"
)
@NetworkMod(
        channels = {"MetallurgyCore"},
        clientSideRequired = true,
        serverSideRequired = false,
        packetHandler = PacketHandler.class
)
public class MetallurgyCore implements ITickHandler
{
    @SidedProxy(
            clientSide = "shadow.mods.metallurgy.CoreClientProxy",
            serverSide = "shadow.mods.metallurgy.CoreCommonProxy"
    )
    public static CoreCommonProxy proxy;
    @Mod$Instance("MetallurgyCore")
    public static MetallurgyCore instance;
    public static CoreConfig config;
    public static boolean hasBase;
    public static boolean hasNether;
    public static boolean hasPrecious;
    public static boolean hasFantasy;
    public static boolean hasEnder;
    public static Block crusher;
    public static Block vanillaBricks;
    public static Block smoke;
    public static Block smokeInactive;
    public static Block smokeEater;
    public static List updateNeeded = new ArrayList();
    public static boolean sentUpdateMessage = false;

    @Mod$PreInit
    public void preInit(FMLPreInitializationEvent var1)
    {
        CoreConfig var10000 = config;
        CoreConfig.init();
        crusher = (new BC_BlockCrusher(CoreConfig.crusherID, false)).setHardness(3.5F).setBlockName("Crusher").setCreativeTab(CreativeTabs.tabDecorations);
        vanillaBricks = (new MetallurgyBlock(CoreConfig.vanillaBrickID, "/shadow/Overrides.png", 2, 5)).setHardness(2.0F).setResistance(0.1F).setBlockName("VanillaBrick");
        proxy.registerRenderInformation();
        Class var2;

        try
        {
            var2 = Class.forName("shadow.mods.metallurgy.base.MetallurgyBaseMetals");
            hasBase = true;
            System.out.println("Metallurgy Core: Base Metals detected, comapatibility added");
        }
        catch (ClassNotFoundException var7)
        {
            System.out.println("Metallurgy Core: Base Metals not detected, reason: " + var7);
        }

        try
        {
            var2 = Class.forName("shadow.mods.metallurgy.nether.MetallurgyNether");
            hasNether = true;
            System.out.println("Metallurgy Core: Nether detected, comapatibility added");
        }
        catch (ClassNotFoundException var6)
        {
            System.out.println("Metallurgy Core: Nether not detected, reason: " + var6);
        }

        try
        {
            var2 = Class.forName("shadow.mods.metallurgy.precious.MetallurgyPrecious");
            hasPrecious = true;
            System.out.println("Metallurgy Core: Precious detected, comapatibility added");
        }
        catch (ClassNotFoundException var5)
        {
            System.out.println("Metallurgy Core: Precious not detected, reason: " + var5);
        }

        try
        {
            var2 = Class.forName("shadow.mods.metallurgy.fantasy.MetallurgyFantasy");
            hasFantasy = true;
            System.out.println("Metallurgy Core: Fantasy detected, comapatibility added");
        }
        catch (ClassNotFoundException var4)
        {
            System.out.println("Metallurgy Core: Fantasy not detected, reason: " + var4);
        }

        try
        {
            var2 = Class.forName("shadow.mods.metallurgy.ender.MetallurgyEnder");
            hasEnder = true;
            System.out.println("Metallurgy Core: Ender detected, comapatibility added");
        }
        catch (ClassNotFoundException var3)
        {
            System.out.println("Metallurgy Core: Ender not detected, reason: " + var3);
        }
    }

    @Mod$Init
    public void load(FMLInitializationEvent var1)
    {
        mod_Iron.load();
        mod_Gold.load();
        GameRegistry.registerBlock(vanillaBricks, MetallurgyItemBlock.class);
        GameRegistry.registerWorldGenerator(new CoreWorldGen());
        OreDictionary.registerOre("dustGold", new ItemStack(mod_Gold.GoldDust, 1));
        OreDictionary.registerOre("dustIron", new ItemStack(mod_Iron.IronDust, 1));
        NetworkRegistry.instance().registerGuiHandler(instance, proxy);
        proxy.addNames();
        proxy.registerRenderInformation();
        proxy.registerTileEntitySpecialRenderer();

        if (CoreConfig.enableTextureOverrides)
        {
            proxy.addTextureOverrides();
        }

        ModLoader.registerBlock(crusher, BC_BlockCrusherItem.class);
        ModLoader.registerTileEntity(BC_TileEntityCrusher.class, "crusher");
        RecipeHelper.addBrickRecipes(vanillaBricks.blockID, 0, Item.ingotIron);
        RecipeHelper.addBrickRecipes(vanillaBricks.blockID, 1, Item.ingotGold);

        if (CoreConfig.crushersEnabled)
        {
            CrusherUpgradeRecipes.load();
        }

        BC_CrusherRecipes.smelting().addCrushing(Block.cobblestone.blockID, new ItemStack(Block.sand));
        BC_CrusherRecipes.smelting().addCrushing(Block.gravel.blockID, new ItemStack(Item.flint));
        Class var2;

        try
        {
            var2 = Class.forName("ic2.api.Ic2Recipes");
            ItemStack var3 = Items.getItem("electronicCircuit");
            ItemStack var4 = Items.getItem("macerator");
            GameRegistry.addRecipe(var4, new Object[] {" I ", "IFI", "ICI", 'I', Item.ingotIron, 'C', var3, 'F', new ItemStack(crusher, 1, 1)});
            GameRegistry.addRecipe(var4, new Object[] {" I ", "IFI", " C ", 'I', Item.ingotIron, 'C', var3, 'F', new ItemStack(crusher, 1, 2)});
            GameRegistry.addRecipe(var4, new Object[] {"F", "C", 'C', var3, 'F', new ItemStack(crusher, 1, 3)});
            GameRegistry.addRecipe(var4, new Object[] {"F", "C", 'C', var3, 'F', new ItemStack(crusher, 1, 4)});
            ItemStack var5 = Items.getItem("coalDust");

            if (var5 != null)
            {
                BC_CrusherRecipes.smelting();
                BC_CrusherRecipes.addCrushing(Item.coal.itemID, 0, var5);
            }
        }
        catch (Exception var7)
        {
            ;
        }

        try
        {
            var2 = Class.forName("railcraft.common.api.crafting.RailcraftCraftingManager");
            HashMap var8 = new HashMap();
            var8.put(new ItemStack(mod_Iron.IronDust, 2), Float.valueOf(1.0F));
            RailcraftCraftingManager.rockCrusher.addRecipe(new ItemStack(Block.oreIron, 1), var8);
            var8 = new HashMap();
            var8.put(new ItemStack(mod_Gold.GoldDust, 2), Float.valueOf(1.0F));
            RailcraftCraftingManager.rockCrusher.addRecipe(new ItemStack(Block.oreGold, 1), var8);
        }
        catch (Exception var6)
        {
            ;
        }

        new UpdateManager("2.3.4", "Core", "http://ladadeda.info/CoreVersion.txt");
        TickRegistry.registerTickHandler(this, Side.CLIENT);
    }

    public static CreativeTabs getNewCreativeTab(String var0, int var1)
    {
        int var2 = CreativeTabs.getNextID();
        CreativeTabMetallurgy var3 = new CreativeTabMetallurgy(var2, var0, var1);
        proxy.addCreativeTabName(var0);
        return var3;
    }

    public static void needsUpdate(String var0, String var1) {}

    public void tickStart(EnumSet var1, Object ... var2) {}

    public void tickEnd(EnumSet var1, Object ... var2)
    {
        if (var1.equals(EnumSet.of(TickType.PLAYER)) && !sentUpdateMessage && updateNeeded.size() > 0)
        {
            String var3 = "";
            String var5;

            for (Iterator var4 = updateNeeded.iterator(); var4.hasNext(); var3 = var3 + var5 + ", ")
            {
                var5 = (String)var4.next();
            }

            var3 = var3.substring(0, var3.length() - 2);
            System.out.println(var3);
            EntityPlayer var6 = (EntityPlayer)var2[0];
            var6.sendChatToPlayer("Metallurgy: Updates avaliable for " + var3);
            sentUpdateMessage = true;
        }
    }

    public EnumSet ticks()
    {
        return EnumSet.of(TickType.PLAYER);
    }

    public String getLabel()
    {
        return null;
    }

    public static void blockError(IllegalArgumentException var0)
    {
        String var1 = var0.getMessage();
        Thread var2 = new Thread(new MetallurgyCore$1(var1));
        var2.start();
    }
}
