package shadow.mods.metallurgy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class MetallurgyItemPickaxe extends ItemPickaxe
{
    public String texturePath;
    private static Block[] blocksEffectiveAgainst = new Block[] {Block.cobblestone, Block.stoneDoubleSlab, Block.stone, Block.sandStone, Block.cobblestoneMossy, Block.oreIron, Block.blockSteel, Block.oreCoal, Block.blockGold, Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice, Block.netherrack, Block.oreLapis, Block.blockLapis, Block.oreRedstone, Block.oreRedstoneGlowing, Block.rail, Block.railDetector, Block.railPowered};

    /** Damage versus entities. */
    private int damageVsEntity;

    /** The material this tool is made from. */
    protected MetallurgyEnumToolMaterial toolMaterial;
    private int ingotID;

    public MetallurgyItemPickaxe(int var1, String var2, MetallurgyEnumToolMaterial var3, int var4)
    {
        this(var1, var2, var3);
        this.ingotID = var4;
    }

    public MetallurgyItemPickaxe(int var1, String var2, MetallurgyEnumToolMaterial var3)
    {
        super(var1, EnumToolMaterial.EMERALD);
        this.texturePath = var2;
        this.toolMaterial = var3;
        this.setMaxDamage(var3.getMaxUses());
        this.efficiencyOnProperMaterial = var3.getEfficiencyOnProperMaterial();
        this.damageVsEntity = 2 + var3.getDamageVsEntity();
    }

    public String getTextureFile()
    {
        return this.texturePath;
    }

    /**
     * Returns if the item (tool) can harvest results from the block type.
     */
    public boolean canHarvestBlock(Block var1)
    {
        return var1 == Block.obsidian ? this.toolMaterial.getHarvestLevel() == 3 : (var1 != Block.blockDiamond && var1 != Block.oreDiamond ? (var1 != Block.blockGold && var1 != Block.oreGold ? (var1 != Block.blockSteel && var1 != Block.oreIron ? (var1 != Block.blockLapis && var1 != Block.oreLapis ? (var1 != Block.oreRedstone && var1 != Block.oreRedstoneGlowing ? (var1.blockMaterial == Material.rock ? true : var1.blockMaterial == Material.iron) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2);
    }

    /**
     * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
     * sword
     */
    public float getStrVsBlock(ItemStack var1, Block var2)
    {
        if (var2 != null && (var2.blockMaterial == Material.iron || var2.blockMaterial == Material.rock))
        {
            return this.efficiencyOnProperMaterial;
        }
        else
        {
            for (int var3 = 0; var3 < blocksEffectiveAgainst.length; ++var3)
            {
                if (blocksEffectiveAgainst[var3] == var2)
                {
                    return this.efficiencyOnProperMaterial;
                }
            }

            return 1.0F;
        }
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }

    /**
     * Returns the damage against a given entity.
     */
    public int getDamageVsEntity(Entity var1)
    {
        return this.damageVsEntity;
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack var1, ItemStack var2)
    {
        return this.ingotID == var2.itemID;
    }
}
