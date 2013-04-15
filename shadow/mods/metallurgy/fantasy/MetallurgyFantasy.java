package shadow.mods.metallurgy.fantasy;

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
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.MinecraftForge;
import shadow.mods.metallurgy.CreativeTabMetallurgy;
import shadow.mods.metallurgy.MetalSet;
import shadow.mods.metallurgy.MetallurgyBlock;
import shadow.mods.metallurgy.MetallurgyCore;
import shadow.mods.metallurgy.MetallurgyItemSword;
import shadow.mods.metallurgy.MetallurgyItems;
import shadow.mods.metallurgy.RecipeHelper;
import shadow.mods.metallurgy.UpdateManager;
import shadow.mods.metallurgy.mod_Iron;
import shadow.mods.metallurgy.precious.MetallurgyPrecious;

@Mod(
        modid = "MetallurgyFantasy",
        name = "Metallurgy Fantasy",
        dependencies = "after:MetallurgyCore",
        version = "2.4"
)
@NetworkMod(
        channels = {"MetallurgyFantas"},
        clientSideRequired = true,
        serverSideRequired = false,
        packetHandler = PacketHandler.class
)
public class MetallurgyFantasy
{
    @SidedProxy(
            clientSide = "shadow.mods.metallurgy.fantasy.ClientProxy",
            serverSide = "shadow.mods.metallurgy.fantasy.CommonProxy"
    )
    public static CommonProxy proxy;
    @Mod$Instance("MetallurgyFantasy")
    public static MetallurgyFantasy instance;
    public static MetalSet alloys;
    public static MetalSet ores;
    public static CreativeTabs creativeTab;

    @Mod$PreInit
    public void preInit(FMLPreInitializationEvent var1)
    {
        ConfigFantasy.init();
        creativeTab = MetallurgyCore.getNewCreativeTab("Fantasy Metals", ConfigFantasy.ItemStartID);
        alloys = new MetalSet(new AlloyFantasyEnum());
        ores = new MetalSet(new OreFantasyEnum());

        try
        {
            FantasyFurnace.metalFurnace = (new FF_BlockMetalFurnace(ConfigFantasy.furnaceID, false)).setHardness(3.5F).setBlockName("MetalFantasyFurnace");
        }
        catch (IllegalArgumentException var3)
        {
            MetallurgyCore.blockError(var3);
            throw var3;
        }

        proxy.registerRenderInformation();
        this.registerWithApi();
    }

    @Mod$Init
    public void load(FMLInitializationEvent var1)
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

        NetworkRegistry.instance().registerGuiHandler(instance, proxy);
        proxy.registerRenderInformation();
        proxy.addNames();
        ((CreativeTabMetallurgy)creativeTab).setTabIconItemIndex(alloys.Helmet[4].itemID);
        alloys.load();
        ores.load();
        FantasySwordEffectsListener var8 = new FantasySwordEffectsListener();
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[1])).addHitListener(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[3])).addHitListener(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[4])).addHitListener(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[7])).addHitListener(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[9])).addHitListener(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[10])).addHitListener(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[11])).addHitListener(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)alloys.Sword[0])).addHitListener(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)alloys.Sword[1])).addHitListener(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)alloys.Sword[2])).addHitListener(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)alloys.Sword[3])).addHitListener(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)alloys.Sword[4])).addHitListener(var8);
        MinecraftForge.EVENT_BUS.register(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[1])).setSubText("cBlindness I");
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[3])).setSubText("cResistance I");
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[4])).setSubText("7Strength I");
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[5])).setSubText("7Looting I");
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[6])).setSubText("7Looting II");
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[7])).setSubText("7Haste I");
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[9])).setSubText("7Resistance II");
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[10])).setSubText("7Fire Resistance-cIgnite II");
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[11])).setSubText("7Strength II");
        ((MetallurgyItemSword)((MetallurgyItemSword)alloys.Sword[0])).setSubText("cBlindness II");
        ((MetallurgyItemSword)((MetallurgyItemSword)alloys.Sword[1])).setSubText("7Speed I");
        ((MetallurgyItemSword)((MetallurgyItemSword)alloys.Sword[2])).setSubText("7Haste II-cIgnite II");
        ((MetallurgyItemSword)((MetallurgyItemSword)alloys.Sword[3])).setSubText("7Resistance III");
        ((MetallurgyItemSword)((MetallurgyItemSword)alloys.Sword[4])).setSubText("7Haste II-cIgnite II");
        FantasyFurnace.load();
        ((MetallurgyBlock)((MetallurgyBlock)ores.ore)).addDisplayListener(new OreParticleSpawner());
        ModLoader.addShapelessRecipe(new ItemStack(mod_Iron.IronDust, 1), new Object[] {ores.Dust[0], ores.Dust[1]});
        ModLoader.addShapelessRecipe(new ItemStack(alloys.Dust[0], 1), new Object[] {ores.Dust[1], ores.Dust[2]});

        if (MetallurgyCore.hasPrecious)
        {
            ModLoader.addShapelessRecipe(new ItemStack(alloys.Dust[1], 1), new Object[] {ores.Dust[7], new ItemStack(MetallurgyPrecious.ores.Dust[1], 1)});
        }

        ModLoader.addShapelessRecipe(new ItemStack(alloys.Dust[2], 1), new Object[] {ores.Dust[7], ores.Dust[8]});

        if (MetallurgyCore.hasPrecious)
        {
            ModLoader.addShapelessRecipe(new ItemStack(alloys.Dust[3], 1), new Object[] {ores.Dust[9], new ItemStack(MetallurgyPrecious.ores.Dust[2], 1)});
        }

        ModLoader.addShapelessRecipe(new ItemStack(alloys.Dust[4], 1), new Object[] {ores.Dust[10], ores.Dust[11]});
        new UpdateManager("2.4", "Fantasy", "http://ladadeda.info/FantasyVersion.txt");
    }

    @Mod$PostInit
    public void load(FMLPostInitializationEvent var1)
    {
        ores.registerOres();
        alloys.registerOres();
        RecipeHelper.addAlloyRecipe(new ItemStack(alloys.Dust[0], 1), "dustDeep Iron", "dustInfuscolium");
        RecipeHelper.addAlloyRecipe(new ItemStack(alloys.Dust[1], 1), "dustMithril", "dustSilver");
        RecipeHelper.addAlloyRecipe(new ItemStack(alloys.Dust[2], 1), "dustMithril", "dustRubracium");
        RecipeHelper.addAlloyRecipe(new ItemStack(alloys.Dust[3], 1), "dustOrichalcum", "dustPlatinum");
        RecipeHelper.addAlloyRecipe(new ItemStack(alloys.Dust[4], 1), "dustAdamantine", "dustAtlarus");
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

    public void registerWithApi()
    {
        MetallurgyItems.registerItem("prometheumAbstractor", new ItemStack(FantasyFurnace.metalFurnace, 1, 0));
        MetallurgyItems.registerItem("deepIronAbstractor", new ItemStack(FantasyFurnace.metalFurnace, 1, 1));
        MetallurgyItems.registerItem("blackSteelAbstractor", new ItemStack(FantasyFurnace.metalFurnace, 1, 2));
        MetallurgyItems.registerItem("oureclaseAbstractor", new ItemStack(FantasyFurnace.metalFurnace, 1, 3));
        MetallurgyItems.registerItem("aredriteAbstractor", new ItemStack(FantasyFurnace.metalFurnace, 1, 4));
        MetallurgyItems.registerItem("mithrilAbstractor", new ItemStack(FantasyFurnace.metalFurnace, 1, 5));
        MetallurgyItems.registerItem("haderothAbstractor", new ItemStack(FantasyFurnace.metalFurnace, 1, 6));
        MetallurgyItems.registerItem("orichalcumAbstractor", new ItemStack(FantasyFurnace.metalFurnace, 1, 7));
        MetallurgyItems.registerItem("adamantineAbstractor", new ItemStack(FantasyFurnace.metalFurnace, 1, 8));
        MetallurgyItems.registerItem("atlarusAbstractor", new ItemStack(FantasyFurnace.metalFurnace, 1, 9));
        MetallurgyItems.registerItem("tartariteAbstractor", new ItemStack(FantasyFurnace.metalFurnace, 1, 10));
    }
}
