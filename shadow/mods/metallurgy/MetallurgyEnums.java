package shadow.mods.metallurgy;

import net.minecraft.item.EnumToolMaterial;
import net.minecraftforge.common.EnumHelper;

public class MetallurgyEnums
{
    public static EnumToolMaterial copper = EnumHelper.addToolMaterial("Copper", 1, 180, 5.0F, 1, 5);
    public static EnumToolMaterial bronze = EnumHelper.addToolMaterial("Bronze", 2, 250, 6.0F, 1, 9);
    public static EnumToolMaterial hepatizon = EnumHelper.addToolMaterial("Hepatizon", 2, 300, 8.0F, 1, 22);
    public static EnumToolMaterial damascusSteel = EnumHelper.addToolMaterial("DamascusSteel", 3, 500, 6.0F, 2, 18);
    public static EnumToolMaterial angmallen = EnumHelper.addToolMaterial("Angmallen", 2, 300, 8.0F, 2, 30);
    public static EnumToolMaterial steel = EnumHelper.addToolMaterial("Steel", 3, 750, 8.0F, 3, 14);
    public static EnumToolMaterial[] base = new EnumToolMaterial[] {copper, bronze, hepatizon, damascusSteel, angmallen, steel};
}
