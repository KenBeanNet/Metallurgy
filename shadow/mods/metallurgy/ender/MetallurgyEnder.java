package shadow.mods.metallurgy.ender;

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
import shadow.mods.metallurgy.CreativeTabMetallurgy;
import shadow.mods.metallurgy.MetalSet;
import shadow.mods.metallurgy.MetallurgyBlock;
import shadow.mods.metallurgy.MetallurgyCore;
import shadow.mods.metallurgy.MetallurgyItemSword;
import shadow.mods.metallurgy.RecipeHelper;
import shadow.mods.metallurgy.UpdateManager;

@Mod(
        modid = "MetallurgyEnder",
        name = "Metallurgy Ender",
        dependencies = "after:MetallurgyCore",
        version = "2.4"
)
@NetworkMod(
        channels = {"MetallurgyNether"},
        clientSideRequired = true,
        serverSideRequired = false,
        packetHandler = PacketHandler.class
)
public class MetallurgyEnder
{
    @SidedProxy(
            clientSide = "shadow.mods.metallurgy.ender.ClientProxy",
            serverSide = "shadow.mods.metallurgy.ender.CommonProxy"
    )
    public static CommonProxy proxy;
    @Mod$Instance("MetallurgyEnder")
    public static MetallurgyEnder instance;
    public static MetalSet alloys;
    public static MetalSet ores;
    public static CreativeTabs creativeTab;

    @Mod$PreInit
    public void preInit(FMLPreInitializationEvent var1)
    {
        ConfigEnder.init();
        creativeTab = MetallurgyCore.getNewCreativeTab("Ender Metals", ConfigEnder.alloyItemIds[0]);
        alloys = new MetalSet(new AlloyEnderEnum());
        ores = new MetalSet(new OreEnderEnum());
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
            System.out.println("Metallurgy is adding symbols ");
        }
        catch (Exception var7)
        {
            System.out.println("Metallurgy not adding symbols " + var7);
        }

        NetworkRegistry.instance().registerGuiHandler(instance, proxy);
        proxy.registerRenderInformation();
        proxy.addNames();
        ((CreativeTabMetallurgy)creativeTab).setTabIconItemIndex(alloys.Helmet[0].itemID);
        alloys.load();
        ores.load();
        GameRegistry.addRecipe(new ItemStack(Item.enderPearl, 4), new Object[] {"XXX", "X X", "XXX", 'X', new ItemStack(ores.Bar[1])});
        ((MetallurgyBlock)((MetallurgyBlock)ores.ore)).addDisplayListener(new OreParticleSpawner());
        ((MetallurgyItemSword)((MetallurgyItemSword)alloys.Sword[0])).addHitListener(new EnderSwordEffectsListener());
        ((MetallurgyItemSword)((MetallurgyItemSword)alloys.Sword[0])).setSubText("knothing");
        new UpdateManager("2.4", "Ender", "http://ladadeda.info/EnderVersion.txt");
    }

    @Mod$PostInit
    public void load(FMLPostInitializationEvent var1)
    {
        ores.registerOres();
        alloys.registerOres();

        if (ConfigEnder.alloyEnabled[0])
        {
            RecipeHelper.addAlloyRecipe(new ItemStack(alloys.Dust[0], 1), "dustEximite", "dustMeutoite");
        }

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
}
