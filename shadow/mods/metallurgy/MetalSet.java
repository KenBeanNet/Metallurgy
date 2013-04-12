package shadow.mods.metallurgy;

import com.xcompwiz.mystcraft.api.MystAPI;
import com.xcompwiz.mystcraft.api.symbol.IAgeSymbol;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.Ic2Recipes;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreDictionary$OreRegisterEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import railcraft.common.api.crafting.RailcraftCraftingManager;
import shadow.mods.metallurgy.fantasy.FF_EssenceRecipes;
import thermalexpansion.api.core.ItemRegistry;
import thermalexpansion.api.crafting.CraftingManagers;

public class MetalSet implements IWorldGenerator
{
    public int numMetals;
    public String setName;
    public IMetalSetEnum info;
    public Block ore;
    public Block brick;
    public Block block;
    public Item[] Dust;
    public Item[] Bar;
    public Item[] Pickaxe;
    public Item[] Shovel;
    public Item[] Axe;
    public Item[] Hoe;
    public Item[] Sword;
    public Item[] Helmet;
    public Item[] Plate;
    public Item[] Legs;
    public Item[] Boots;
    public static int oreSpawnCount;

    public MetalSet(IMetalSetEnum var1)
    {
        this.info = var1;
        this.setName = var1.getSetName();
        this.numMetals = var1.numMetals();
        this.Dust = new Item[this.numMetals];
        this.Bar = new Item[this.numMetals];
        this.Pickaxe = new Item[this.numMetals];
        this.Shovel = new Item[this.numMetals];
        this.Axe = new Item[this.numMetals];
        this.Hoe = new Item[this.numMetals];
        this.Sword = new Item[this.numMetals];
        this.Helmet = new Item[this.numMetals];
        this.Plate = new Item[this.numMetals];
        this.Legs = new Item[this.numMetals];
        this.Boots = new Item[this.numMetals];
        int var2;

        for (var2 = 0; var2 < this.numMetals; ++var2)
        {
            int var3 = var1.startID(var2);
            this.Dust[var2] = (new MetallurgyItem(var3, var1.image())).setIconCoord(var2, 3).setItemName(var1.name(var2) + "Dust").setCreativeTab(var1.getCreativeTab());
            this.Bar[var2] = (new MetallurgyItem(var3 + 1, var1.image())).setIconCoord(var2, 4).setItemName(var1.name(var2) + "Bar").setCreativeTab(var1.getCreativeTab());

            if (!var1.isCatalyst(var2))
            {
                this.Pickaxe[var2] = (new MetallurgyItemPickaxe(var3 + 2, var1.image(), var1.toolEnum(var2), this.Bar[var2].itemID)).setIconCoord(var2, 7).setItemName(var1.name(var2) + "Pickaxe").setCreativeTab(var1.getCreativeTab());
                this.Shovel[var2] = (new MetallurgyItemSpade(var3 + 3, var1.image(), var1.toolEnum(var2), this.Bar[var2].itemID)).setIconCoord(var2, 8).setItemName(var1.name(var2) + "Shovel").setCreativeTab(var1.getCreativeTab());
                this.Axe[var2] = (new MetallurgyItemAxe(var3 + 4, var1.image(), var1.toolEnum(var2), this.Bar[var2].itemID)).setIconCoord(var2, 5).setItemName(var1.name(var2) + "Axe").setCreativeTab(var1.getCreativeTab());
                this.Hoe[var2] = (new MetallurgyItemHoe(var3 + 5, var1.image(), var1.toolEnum(var2), this.Bar[var2].itemID)).setIconCoord(var2, 6).setItemName(var1.name(var2) + "Hoe").setCreativeTab(var1.getCreativeTab());
                this.Sword[var2] = (new MetallurgyItemSword(var3 + 6, var1.image(), var1.toolEnum(var2), this.Bar[var2].itemID)).setIconCoord(var2, 9).setItemName(var1.name(var2) + "Sword").setCreativeTab(var1.getCreativeTab());
                this.Helmet[var2] = (new MetallurgyArmor(var3 + 7, var1.image(), var1.name(var2).toLowerCase().replaceAll("\\W", "") + "_1", var1.armorEnum(var2), 0, 0, this.Bar[var2].itemID)).setIconCoord(var2, 12).setItemName(var1.name(var2) + "Helmet").setCreativeTab(var1.getCreativeTab());
                this.Plate[var2] = (new MetallurgyArmor(var3 + 8, var1.image(), var1.name(var2).toLowerCase().replaceAll("\\W", "") + "_1", var1.armorEnum(var2), 0, 1, this.Bar[var2].itemID)).setIconCoord(var2, 13).setItemName(var1.name(var2) + "Plate").setCreativeTab(var1.getCreativeTab());
                this.Legs[var2] = (new MetallurgyArmor(var3 + 9, var1.image(), var1.name(var2).toLowerCase().replaceAll("\\W", "") + "_2", var1.armorEnum(var2), 0, 2, this.Bar[var2].itemID)).setIconCoord(var2, 14).setItemName(var1.name(var2) + "Legs").setCreativeTab(var1.getCreativeTab());
                this.Boots[var2] = (new MetallurgyArmor(var3 + 10, var1.image(), var1.name(var2).toLowerCase().replaceAll("\\W", "") + "_1", var1.armorEnum(var2), 0, 3, this.Bar[var2].itemID)).setIconCoord(var2, 15).setItemName(var1.name(var2) + "Boots").setCreativeTab(var1.getCreativeTab());

                if (var1.numRails(var2) > 0)
                {
                    RecipeHelper.addRailsRecipe(this.Bar[var2], var1.numRails(var2));
                }
            }
        }

        try
        {
            if (!var1.isAlloy())
            {
                this.ore = (new MetallurgyBlock(var1.oreID(), var1.image(), this.numMetals, 0)).setHardness(2.0F).setResistance(0.1F).setBlockName(this.setName + "Ore").setCreativeTab(var1.getCreativeTab());
            }

            this.brick = (new MetallurgyBlock(var1.brickID(), var1.image(), this.numMetals, 1)).setHardness(5.0F).setResistance(10.0F).setBlockName(this.setName + "Brick").setCreativeTab(var1.getCreativeTab());

            if (var1.hasMetalBlock())
            {
                this.block = (new MetallurgyBlock(var1.blockID(), var1.image(), this.numMetals, 0)).setHardness(5.0F).setResistance(0.1F).setBlockName(this.setName + "Block").setCreativeTab(var1.getCreativeTab());
                ((MetallurgyBlock)this.block).isMetalBlock = true;
            }
        }
        catch (IllegalArgumentException var4)
        {
            MetallurgyCore.blockError(var4);
            throw var4;
        }

        for (var2 = 0; var2 < this.numMetals; ++var2)
        {
            if (!var1.isAlloy())
            {
                MinecraftForge.setBlockHarvestLevel(this.ore, var2, "pickaxe", var1.oreHarvestLevel(var2));
            }

            if (var1.hasMetalBlock())
            {
                MinecraftForge.setBlockHarvestLevel(this.block, var2, "pickaxe", var1.oreHarvestLevel(var2));
            }

            MinecraftForge.setBlockHarvestLevel(this.brick, var2, "pickaxe", var1.oreHarvestLevel(var2));

            if (!var1.isCatalyst(var2))
            {
                MinecraftForge.setToolClass(this.Pickaxe[var2], "pickaxe", var1.pickLevel(var2));
            }
        }

        this.registerWithApi();
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void load()
    {
        if (this.ore != null)
        {
            GameRegistry.registerWorldGenerator(this);
            GameRegistry.registerBlock(this.ore, MetallurgyItemBlock.class);
        }

        int var1;

        if (this.block != null)
        {
            GameRegistry.registerBlock(this.block, MetallurgyItemBlock.class);

            for (var1 = 0; var1 < this.info.numMetals(); ++var1)
            {
                ShapedOreRecipe var2 = new ShapedOreRecipe(new ItemStack(this.block, 1, var1), new Object[] {"XXX", "XXX", "XXX", 'X', "ingot" + this.info.name(var1)});
                GameRegistry.addRecipe(var2);
                GameRegistry.addRecipe(new ItemStack(this.Bar[var1], 9), new Object[] {"X", 'X', new ItemStack(this.block, 1, var1)});
            }
        }

        GameRegistry.registerBlock(this.brick, MetallurgyItemBlock.class);
        Class var9;

        for (var1 = 0; var1 < this.info.numMetals(); ++var1)
        {
            GameRegistry.addSmelting(this.Dust[var1].itemID, new ItemStack(this.Bar[var1], 1), 1.0F);

            if (this.ore != null)
            {
                FurnaceRecipes.smelting().addSmelting(this.ore.blockID, var1, new ItemStack(this.Bar[var1], 1), 0.4F);

                try
                {
                    var9 = Class.forName("railcraft.common.api.crafting.RailcraftCraftingManager");
                    HashMap var3 = new HashMap();
                    var3.put(new ItemStack(this.Dust[var1], 2), Float.valueOf(1.0F));
                    RailcraftCraftingManager.rockCrusher.addRecipe(new ItemStack(this.ore, 1, var1), var3);
                }
                catch (Exception var7)
                {
                    ;
                }

                try
                {
                    var9 = Class.forName("ic2.api.Ic2Recipes");
                    Ic2Recipes.addMaceratorRecipe(new ItemStack(this.ore, 1, var1), new ItemStack(this.Dust[var1], 2, 0));
                }
                catch (Exception var6)
                {
                    ;
                }

                try
                {
                    var9 = Class.forName("thermalexpansion.api.crafting.CraftingManagers");
                    CraftingManagers.pulverizerManager.addRecipe(400, new ItemStack(this.ore, 1, var1), new ItemStack(this.Dust[var1], 2, 0));
                    CraftingManagers.smelterManager.addRecipe(320, new ItemStack(this.ore, 1, var1), new ItemStack(Block.sand), new ItemStack(this.Bar[var1], 2), ItemRegistry.getItem("slag", 1));
                    CraftingManagers.smelterManager.addRecipe(80, new ItemStack(this.Dust[var1], 2), new ItemStack(Block.sand), new ItemStack(this.Bar[var1], 2), ItemRegistry.getItem("slag", 1));
                }
                catch (Exception var5)
                {
                    ;
                }

                DungeonHooks.addDungeonLoot(new ItemStack(this.Bar[var1], 1), this.info.dungeonLootChance(var1), 1, this.info.dungeonLootAmount(var1));
            }

            try
            {
                var9 = Class.forName("ic2.api.Ic2Recipes");
                Ic2Recipes.addMaceratorRecipe(new ItemStack(this.Bar[var1], 1, 0), new ItemStack(this.Dust[var1], 1, 0));
            }
            catch (Exception var8)
            {
                ;
            }
        }

        for (var1 = 0; var1 < this.info.numMetals(); ++var1)
        {
            if (!this.info.isCatalyst(var1))
            {
                RecipeHelper.addAxeRecipe(this.Axe[var1], "ingot" + this.info.name(var1));
                RecipeHelper.addPickaxeRecipe(this.Pickaxe[var1], "ingot" + this.info.name(var1));
                RecipeHelper.addShovelRecipe(this.Shovel[var1], "ingot" + this.info.name(var1));
                RecipeHelper.addHoeRecipe(this.Hoe[var1], "ingot" + this.info.name(var1));
                RecipeHelper.addSwordRecipe(this.Sword[var1], "ingot" + this.info.name(var1));
                RecipeHelper.addHelmetRecipe(this.Helmet[var1], "ingot" + this.info.name(var1));
                RecipeHelper.addPlateRecipe(this.Plate[var1], "ingot" + this.info.name(var1));
                RecipeHelper.addLegsRecipe(this.Legs[var1], "ingot" + this.info.name(var1));
                RecipeHelper.addBootsRecipe(this.Boots[var1], "ingot" + this.info.name(var1));
                RecipeHelper.addBucketRecipe("ingot" + this.info.name(var1));
                RecipeHelper.addShearsRecipe("ingot" + this.info.name(var1));
            }
        }

        if (this.info.getSetName().contains("Metal"))
        {
            System.out.println("Metallurgy: Attempting to add Mystcraft symbols for set " + this.info.getSetName());

            try
            {
                Class var10 = Class.forName("com.xcompwiz.mystcraft.api.MystAPI");
                var9 = Class.forName("shadow.mods.metallurgy.mystcraft.OreSymbol");
                Constructor var11 = var9.getConstructor(new Class[] {MetalSet.class});
                MystAPI.symbol.registerSymbol((IAgeSymbol)var11.newInstance(new Object[] {this}));
                System.out.println("Sucess");
            }
            catch (Exception var4)
            {
                System.out.println("Failed " + var4);
            }
        }
    }

    public void registerWithApi()
    {
        for (int var1 = 0; var1 < this.numMetals; ++var1)
        {
            if (this.ore != null)
            {
                MetallurgyItems.registerItem("ore" + this.info.name(var1), new ItemStack(this.ore, 1, var1));
            }

            MetallurgyItems.registerItem("brick" + this.info.name(var1), new ItemStack(this.brick, 1, var1));

            if (this.block != null)
            {
                MetallurgyItems.registerItem("block" + this.info.name(var1), new ItemStack(this.block, 1, var1));
            }

            MetallurgyItems.registerItem("dust" + this.info.name(var1), new ItemStack(this.Dust[var1]));
            MetallurgyItems.registerItem("ingot" + this.info.name(var1), new ItemStack(this.Bar[var1]));

            if (!this.info.isCatalyst(var1))
            {
                MetallurgyItems.registerItem("sword" + this.info.name(var1), new ItemStack(this.Sword[var1]));
                MetallurgyItems.registerItem("pickaxe" + this.info.name(var1), new ItemStack(this.Pickaxe[var1]));
                MetallurgyItems.registerItem("axe" + this.info.name(var1), new ItemStack(this.Axe[var1]));
                MetallurgyItems.registerItem("shovel" + this.info.name(var1), new ItemStack(this.Shovel[var1]));
                MetallurgyItems.registerItem("hoe" + this.info.name(var1), new ItemStack(this.Hoe[var1]));
                MetallurgyItems.registerItem("helmet" + this.info.name(var1), new ItemStack(this.Helmet[var1]));
                MetallurgyItems.registerItem("plate" + this.info.name(var1), new ItemStack(this.Plate[var1]));
                MetallurgyItems.registerItem("legs" + this.info.name(var1), new ItemStack(this.Legs[var1]));
                MetallurgyItems.registerItem("boots" + this.info.name(var1), new ItemStack(this.Boots[var1]));
            }
        }
    }

    @ForgeSubscribe
    public void oreRegistered(OreDictionary$OreRegisterEvent var1)
    {
        for (int var2 = 0; var2 < this.info.numMetals(); ++var2)
        {
            if (var1.Name.equals("ore" + this.info.name(var2)))
            {
                ;
            }

            if (var1.Name.equals("ingot" + this.info.name(var2)))
            {
                if (MetallurgyCore.hasFantasy)
                {
                    FF_EssenceRecipes.essence().addEssenceAmount(var1.Ore.itemID, this.info.expValue(var2));
                }

                BC_CrusherRecipes.smelting().addCrushing(var1.Ore.itemID, new ItemStack(this.Dust[var2], 1));
                RecipeHelper.addBrickRecipes(this.brick.blockID, var2, var1.Ore.getItem(), var1.Ore.getItemDamage());
            }
        }
    }

    public void registerOres()
    {
        for (int var1 = 0; var1 < this.info.numMetals(); ++var1)
        {
            if (this.ore != null)
            {
                OreDictionary.registerOre("ore" + this.info.name(var1), new ItemStack(this.ore, 1, var1));
            }

            if (this.block != null)
            {
                OreDictionary.registerOre("block" + this.info.name(var1), new ItemStack(this.block, 1, var1));
            }

            OreDictionary.registerOre("dust" + this.info.name(var1), new ItemStack(this.Dust[var1], 1));
            OreDictionary.registerOre("ingot" + this.info.name(var1), new ItemStack(this.Bar[var1], 1));
        }
    }

    public void generate(Random var1, int var2, int var3, World var4, IChunkProvider var5, IChunkProvider var6)
    {
        if (this.info.spawnsInDimension(var4.provider.dimensionId))
        {
            this.generateAllOres(var1, var2, var3, var4);
        }
    }

    public void generateAllOres(Random var1, int var2, int var3, World var4)
    {
        for (int var5 = 0; var5 < this.numMetals; ++var5)
        {
            if (this.info.metalEnabled(var5))
            {
                this.generateOre(var4, var1, var2 * 16, var3 * 16, var5);
            }
        }
    }

    public void generateOre(World var1, Random var2, int var3, int var4, int var5)
    {
        oreSpawnCount = 0;

        for (int var6 = 0; var6 < this.info.veinCount(var5); ++var6)
        {
            int var7 = var3 + var2.nextInt(16);
            int var8 = var2.nextInt(this.info.oreHeight(var5) - this.info.oreMinHeight(var5)) + this.info.oreMinHeight(var5);
            int var9 = var4 + var2.nextInt(16);

            if (var1.provider.isHellWorld)
            {
                (new MetallurgyWorldGenNetherMinable(this.ore.blockID, var5, this.info.oreCount(var5))).generate(var1, var2, var7, var8, var9);
            }
            else if (var1.provider.dimensionId == 1)
            {
                (new MetallurgyWorldGenEnderMinable(this.ore.blockID, var5, this.info.oreCount(var5))).generate(var1, var2, var7, var8, var9);
            }
            else
            {
                (new WorldGenMinable(this.ore.blockID, var5, this.info.oreCount(var5))).generate(var1, var2, var7, var8, var9);
            }
        }
    }
}
