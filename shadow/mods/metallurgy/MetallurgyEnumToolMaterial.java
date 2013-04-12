package shadow.mods.metallurgy;

public enum MetallurgyEnumToolMaterial
{
    Copper("Copper", 0, 1, 180, 5.0F, 1, 5),
    Bronze("Bronze", 0, 2, 250, 6.0F, 1, 9),
    Hepatizon("Hepatizon", 0, 2, 300, 8.0F, 1, 22),
    DamascusSteel("DamascusSteel", 0, 3, 500, 6.0F, 2, 18),
    Angmallen("Angmallen", 0, 2, 300, 8.0F, 2, 30),
    Steel("Steel", 0, 3, 750, 8.0F, 3, 14),
    Ignatius("Ignatius", 0, 1, 200, 4.0F, 2, 15),
    ShadowIron("ShadowIron", 0, 1, 300, 5.0F, 2, 2),
    ShadowSteel("ShadowSteel", 0, 2, 400, 6.0F, 2, 5),
    Midasium("Midasium", 0, 3, 100, 10.0F, 2, 35),
    Vyroxeres("Vyroxeres", 0, 3, 300, 7.0F, 2, 16),
    Ceruclase("Ceruclase", 0, 3, 500, 7.0F, 2, 18),
    Inolashite("Inolashite", 0, 4, 900, 8.0F, 3, 25),
    Kalendrite("Kalendrite", 0, 4, 1000, 8.0F, 3, 20),
    Amordrine("Amordrine", 0, 4, 500, 14.0F, 2, 50),
    Vulcanite("Vulcanite", 0, 5, 1500, 10.0F, 3, 20),
    Sanguinite("Sanguinite", 0, 6, 1750, 12.0F, 4, 25),
    Prometheum("Prometheum", 0, 1, 200, 4.0F, 1, 16),
    DeepIron("DeepIron", 0, 2, 250, 6.0F, 2, 14),
    BlackSteel("BlackSteel", 0, 2, 500, 8.0F, 2, 17),
    Oureclase("Oureclase", 0, 3, 750, 8.0F, 2, 18),
    Aredrite("Aredrite", 0, 3, 1000, 4.0F, 2, 18),
    AstralSilver("AstralSilver", 0, 4, 35, 12.0F, 1, 30),
    Carmot("Carmot", 0, 4, 50, 12.0F, 1, 40),
    Mithril("Mithril", 0, 4, 1000, 9.0F, 3, 18),
    Quicksilver("Quicksilver", 0, 4, 1100, 14.0F, 3, 20),
    Haderoth("Haderoth", 0, 4, 1250, 12.0F, 3, 19),
    Orichalcum("Orichalcum", 0, 5, 1350, 9.0F, 3, 20),
    Celenegil("Celenegil", 0, 5, 1600, 14.0F, 3, 50),
    Adamantine("Adamantine", 0, 6, 1550, 10.0F, 4, 22),
    Atlarus("Atlarus", 0, 6, 1750, 10.0F, 4, 22),
    Tartarite("Tartarite", 0, 7, 3000, 14.0F, 5, 25),
    Brass("Brass", 0, 0, 15, 18.0F, 1, 18),
    Silver("Silver", 0, 0, 25, 12.0F, 1, 20),
    Electrum("Electrum", 0, 1, 50, 14.0F, 1, 30),
    Platinum("Platinum", 0, 2, 100, 16.0F, 1, 50);
    private final int harvestLevel;
    private final int maxUses;
    private final float efficiencyOnProperMaterial;
    private final int damageVsEntity;
    private final int enchantability;
    private static final MetallurgyEnumToolMaterial[] allToolMaterials = new MetallurgyEnumToolMaterial[]{Copper, Bronze, Hepatizon, DamascusSteel, Angmallen, Steel, Ignatius, ShadowIron, ShadowSteel, Midasium, Vyroxeres, Ceruclase, Inolashite, Kalendrite, Amordrine, Vulcanite, Sanguinite, Mithril, Orichalcum, Adamantine, Brass, Silver, Platinum};

    private MetallurgyEnumToolMaterial(String var3, int var4, int var5, int var6, float var7, int var8, int var9)
    {
        this.harvestLevel = var5;
        this.maxUses = var6;
        this.efficiencyOnProperMaterial = var7;
        this.damageVsEntity = var8;
        this.enchantability = var9;
    }

    public int getMaxUses()
    {
        return this.maxUses;
    }

    public float getEfficiencyOnProperMaterial()
    {
        return this.efficiencyOnProperMaterial;
    }

    public int getDamageVsEntity()
    {
        return this.damageVsEntity;
    }

    public int getHarvestLevel()
    {
        return this.harvestLevel;
    }

    public int getEnchantability()
    {
        return this.enchantability;
    }
}
