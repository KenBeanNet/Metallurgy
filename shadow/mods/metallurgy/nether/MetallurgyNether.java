package shadow.mods.metallurgy.nether;

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
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.MinecraftForge;
import shadow.mods.metallurgy.CreativeTabMetallurgy;
import shadow.mods.metallurgy.MetalSet;
import shadow.mods.metallurgy.MetallurgyBlock;
import shadow.mods.metallurgy.MetallurgyCore;
import shadow.mods.metallurgy.MetallurgyItemSword;
import shadow.mods.metallurgy.MetallurgyItems;
import shadow.mods.metallurgy.UpdateManager;
import shadow.mods.metallurgy.mod_Gold;
import shadow.mods.metallurgy.mod_Iron;
import shadow.mods.metallurgy.base.AlloyBaseEnum;
import shadow.mods.metallurgy.base.MetallurgyBaseMetals;
import shadow.mods.metallurgy.ender.MetallurgyEnder;
import shadow.mods.metallurgy.fantasy.MetallurgyFantasy;
import shadow.mods.metallurgy.precious.MetallurgyPrecious;

@Mod(
        modid = "MetallurgyNether",
        name = "Metallurgy Nether",
        dependencies = "after:MetallurgyCore",
        version = "2.4"
)
@NetworkMod(
        channels = {"MetallurgyNether"},
        clientSideRequired = true,
        serverSideRequired = false,
        packetHandler = PacketHandler.class
)
public class MetallurgyNether
{
    @SidedProxy(
            clientSide = "shadow.mods.metallurgy.nether.ClientProxy",
            serverSide = "shadow.mods.metallurgy.nether.CommonProxy"
    )
    public static CommonProxy proxy;
    @Mod$Instance("MetallurgyNether")
    public static MetallurgyNether instance;
    public static MetalSet alloys;
    public static MetalSet ores;
    public static CreativeTabs creativeTab;

    @Mod$PreInit
    public void preInit(FMLPreInitializationEvent var1)
    {
        ConfigNether.init();
        creativeTab = MetallurgyCore.getNewCreativeTab("Nether Metals", 0);
        alloys = new MetalSet(new AlloyNetherEnum());
        ores = new MetalSet(new OreNetherEnum());
        NetherForge.init();
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

        GameRegistry.registerBlock(NetherForge.metalFurnace, NF_BlockNetherForgeItem.class);
        GameRegistry.registerTileEntity(NF_TileEntityNetherForge.class, "netherFurnace");
        NetworkRegistry.instance().registerGuiHandler(instance, proxy);
        proxy.registerRenderInformation();
        proxy.addNames();
        ((CreativeTabMetallurgy)creativeTab).setTabIconItemIndex(ores.Helmet[7].itemID);
        alloys.load();
        ores.load();
        ((MetallurgyBlock)((MetallurgyBlock)ores.ore)).addCollisionListener(new VyroxeresListener());
        ((MetallurgyBlock)((MetallurgyBlock)ores.ore)).addDisplayListener(new VyroxeresDisplay());
        NetherSwordEffectsListener var8 = new NetherSwordEffectsListener();
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[0])).addHitListener(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[1])).addHitListener(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[3])).addHitListener(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[4])).addHitListener(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[5])).addHitListener(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[6])).addHitListener(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[7])).addHitListener(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)alloys.Sword[0])).addHitListener(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)alloys.Sword[1])).addHitListener(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)alloys.Sword[2])).addHitListener(var8);
        MinecraftForge.EVENT_BUS.register(var8);
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[0])).setSubText("cIgnite I");
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[1])).setSubText("cWeakness I");
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[2])).setSubText("7Looting I");
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[3])).setSubText("cPoison I");
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[4])).setSubText("cSlowing I");
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[5])).setSubText("7Regen");
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[6])).setSubText("cIgnite II");
        ((MetallurgyItemSword)((MetallurgyItemSword)ores.Sword[7])).setSubText("cWither I");
        ((MetallurgyItemSword)((MetallurgyItemSword)alloys.Sword[0])).setSubText("7Weakness II");
        ((MetallurgyItemSword)((MetallurgyItemSword)alloys.Sword[1])).setSubText("7Poison, Slowing");
        ((MetallurgyItemSword)((MetallurgyItemSword)alloys.Sword[2])).setSubText("cHealing");
        NetherForge.load();
        GameRegistry.registerFuelHandler(new NetherFuelDust());

        if (ConfigNether.midasiumEnabled)
        {
            addMidasiumRecipes();
        }

        ModLoader.addRecipe(new ItemStack(Item.blazeRod, 1), new Object[] {"X", "X", 'X', ores.Bar[6]});
        GameRegistry.registerWorldGenerator(new ObsidianSpawns());
        ModLoader.addShapelessRecipe(new ItemStack(mod_Iron.IronDust, 1), new Object[] {ores.Dust[0], ores.Dust[1]});
        ModLoader.addShapelessRecipe(new ItemStack(alloys.Dust[0], 1), new Object[] {ores.Dust[8], ores.Dust[1]});
        ModLoader.addShapelessRecipe(new ItemStack(alloys.Dust[1], 1), new Object[] {ores.Dust[9], ores.Dust[4]});

        if (MetallurgyCore.hasPrecious)
        {
            ModLoader.addShapelessRecipe(new ItemStack(alloys.Dust[2], 1), new Object[] {new ItemStack(MetallurgyPrecious.ores.Dust[2], 1), ores.Dust[5]});
        }

        new UpdateManager("2.4", "Nether", "http://ladadeda.info/NetherVersion.txt");
    }

    public static void addMidasiumRecipes()
    {
        ModLoader.addShapelessRecipe(new ItemStack(mod_Gold.GoldDust, 1), new Object[] {new ItemStack(mod_Iron.IronDust, 1), ores.Dust[2]});
        ModLoader.addShapelessRecipe(new ItemStack(mod_Gold.GoldDust, 1), new Object[] {new ItemStack(mod_Gold.GoldDust, 1), ores.Dust[2]});
        int var0;

        if (MetallurgyCore.hasBase)
        {
            for (var0 = 0; var0 < AlloyBaseEnum.numMetals; ++var0)
            {
                ModLoader.addShapelessRecipe(new ItemStack(mod_Gold.GoldDust, 1), new Object[] {new ItemStack(MetallurgyBaseMetals.alloys.Dust[var0], 1), ores.Dust[2]});
            }

            for (var0 = 0; var0 < 3; ++var0)
            {
                ModLoader.addShapelessRecipe(new ItemStack(mod_Gold.GoldDust, 1), new Object[] {new ItemStack(MetallurgyBaseMetals.ores.Dust[var0], 1), ores.Dust[2]});
            }
        }

        if (MetallurgyCore.hasPrecious)
        {
            for (var0 = 0; var0 < MetallurgyPrecious.alloys.numMetals; ++var0)
            {
                ModLoader.addShapelessRecipe(new ItemStack(mod_Gold.GoldDust, 1), new Object[] {new ItemStack(MetallurgyPrecious.alloys.Dust[var0], 1), ores.Dust[2]});
            }

            for (var0 = 0; var0 < MetallurgyPrecious.ores.numMetals; ++var0)
            {
                ModLoader.addShapelessRecipe(new ItemStack(mod_Gold.GoldDust, 1), new Object[] {new ItemStack(MetallurgyPrecious.ores.Dust[var0], 1), ores.Dust[2]});
            }
        }

        if (MetallurgyCore.hasFantasy)
        {
            for (var0 = 0; var0 < MetallurgyFantasy.alloys.numMetals; ++var0)
            {
                ModLoader.addShapelessRecipe(new ItemStack(mod_Gold.GoldDust, 1), new Object[] {new ItemStack(MetallurgyFantasy.alloys.Dust[var0], 1), ores.Dust[2]});
            }

            for (var0 = 0; var0 < MetallurgyFantasy.ores.numMetals; ++var0)
            {
                ModLoader.addShapelessRecipe(new ItemStack(mod_Gold.GoldDust, 1), new Object[] {new ItemStack(MetallurgyFantasy.ores.Dust[var0], 1), ores.Dust[2]});
            }
        }

        if (MetallurgyCore.hasEnder)
        {
            for (var0 = 0; var0 < MetallurgyEnder.alloys.numMetals; ++var0)
            {
                ModLoader.addShapelessRecipe(new ItemStack(mod_Gold.GoldDust, 1), new Object[] {new ItemStack(MetallurgyEnder.alloys.Dust[var0], 1), ores.Dust[2]});
            }

            for (var0 = 0; var0 < MetallurgyEnder.ores.numMetals; ++var0)
            {
                ModLoader.addShapelessRecipe(new ItemStack(mod_Gold.GoldDust, 1), new Object[] {new ItemStack(MetallurgyEnder.ores.Dust[var0], 1), ores.Dust[2]});
            }
        }

        for (var0 = 0; var0 < alloys.numMetals; ++var0)
        {
            ModLoader.addShapelessRecipe(new ItemStack(mod_Gold.GoldDust, 1), new Object[] {new ItemStack(alloys.Dust[var0], 1), ores.Dust[2]});
        }

        for (var0 = 0; var0 < ores.numMetals; ++var0)
        {
            ModLoader.addShapelessRecipe(new ItemStack(mod_Gold.GoldDust, 1), new Object[] {ores.Dust[var0], ores.Dust[2]});
        }
    }

    @Mod$PostInit
    public void load(FMLPostInitializationEvent var1)
    {
        ores.registerOres();
        alloys.registerOres();
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
        MetallurgyItems.registerItem("ignatiusSmelter", new ItemStack(NetherForge.metalFurnace, 1, 0));
        MetallurgyItems.registerItem("shadowIronSmelter", new ItemStack(NetherForge.metalFurnace, 1, 1));
        MetallurgyItems.registerItem("shadowSteelSmelter", new ItemStack(NetherForge.metalFurnace, 1, 2));
        MetallurgyItems.registerItem("vyroxeresSmelter", new ItemStack(NetherForge.metalFurnace, 1, 3));
        MetallurgyItems.registerItem("inolashiteSmelter", new ItemStack(NetherForge.metalFurnace, 1, 4));
        MetallurgyItems.registerItem("kalendriteSmelter", new ItemStack(NetherForge.metalFurnace, 1, 5));
        MetallurgyItems.registerItem("vulcaniteSmelter", new ItemStack(NetherForge.metalFurnace, 1, 6));
        MetallurgyItems.registerItem("sanguiniteSmelter", new ItemStack(NetherForge.metalFurnace, 1, 7));
    }
}
