package shadow.mods.metallurgy;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class MetallurgyItemSpade extends ItemSpade
{
    public String texturePath;
    private static Block[] blocksEffectiveAgainst = new Block[] {Block.grass, Block.dirt, Block.sand, Block.gravel, Block.snow, Block.blockSnow, Block.blockClay, Block.tilledField, Block.slowSand, Block.mycelium};

    /** Damage versus entities. */
    private int damageVsEntity;

    /** The material this tool is made from. */
    protected MetallurgyEnumToolMaterial toolMaterial;
    private int ingotID;

    public MetallurgyItemSpade(int var1, String var2, MetallurgyEnumToolMaterial var3, int var4)
    {
        this(var1, var2, var3);
        this.ingotID = var4;
    }

    public MetallurgyItemSpade(int var1, String var2, MetallurgyEnumToolMaterial var3)
    {
        super(var1, EnumToolMaterial.EMERALD);
        this.texturePath = var2;
        this.efficiencyOnProperMaterial = 4.0F;
        this.toolMaterial = var3;
        this.setMaxDamage(var3.getMaxUses());
        this.efficiencyOnProperMaterial = var3.getEfficiencyOnProperMaterial();
        this.damageVsEntity = 2 + var3.getDamageVsEntity();
    }

    /**
     * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
     * sword
     */
    public float getStrVsBlock(ItemStack var1, Block var2)
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

    /**
     * Returns the damage against a given entity.
     */
    public int getDamageVsEntity(Entity var1)
    {
        return this.damageVsEntity;
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
        return var1 == Block.snow;
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack var1, ItemStack var2)
    {
        return this.ingotID == var2.itemID;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }
}
