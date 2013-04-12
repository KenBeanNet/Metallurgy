package shadow.mods.metallurgy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class MetallurgyItemAxe extends ItemAxe
{
    public String texturePath;
    private static Block[] blocksEffectiveAgainst = new Block[] {Block.planks, Block.bookShelf, Block.wood, Block.chest, Block.woodDoubleSlab, Block.woodSingleSlab, Block.pumpkin, Block.pumpkinLantern};

    /** The material this tool is made from. */
    protected MetallurgyEnumToolMaterial toolMaterial;
    private int ingotID;

    public MetallurgyItemAxe(int var1, String var2, MetallurgyEnumToolMaterial var3, int var4)
    {
        this(var1, var2, var3);
        this.ingotID = var4;
    }

    public MetallurgyItemAxe(int var1, String var2, MetallurgyEnumToolMaterial var3)
    {
        super(var1, EnumToolMaterial.EMERALD);
        this.texturePath = var2;
        this.toolMaterial = var3;
        this.setMaxDamage(var3.getMaxUses());
        this.efficiencyOnProperMaterial = var3.getEfficiencyOnProperMaterial();
        this.ingotID = 0;
    }

    public String getTextureFile()
    {
        return this.texturePath;
    }

    /**
     * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
     * sword
     */
    public float getStrVsBlock(ItemStack var1, Block var2)
    {
        if (var2 != null && var2.blockMaterial == Material.wood)
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
     * Returns the damage against a given entity.
     */
    public int getDamageVsEntity(Entity var1)
    {
        return 2 + this.toolMaterial.getDamageVsEntity();
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack var1, ItemStack var2)
    {
        return this.ingotID == var2.itemID;
    }
}
