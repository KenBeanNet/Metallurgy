package shadow.mods.metallurgy.fantasy;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class FF_GuiMetalFurnace extends GuiContainer
{
    private FF_TileEntityMetalFurnace furnaceInventory;
    String[] names = new String[] {"Prometheum", "Deep Iron", "Black Steel", "Oureclase", "Aredrite", "Mithril", "Haderoth", "Orichalcum", "Adamantine", "Atlarus", "Tartarite"};
    int type;

    public FF_GuiMetalFurnace(InventoryPlayer var1, FF_TileEntityMetalFurnace var2)
    {
        super(new FF_ContainerMetalFurnace(var1, var2));
        this.furnaceInventory = var2;
        this.type = var2.getType();
    }

    protected void drawGuiContainerForegroundLayer()
    {
        this.fontRenderer.drawString(StatCollector.translateToLocal(this.names[this.type] + " Abstractor"), this.xSize / 2 - (this.names[this.type] + " Abstractor").length() * 5 / 2, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        int var4 = this.mc.renderEngine.getTexture("/shadow/abstracter.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(var4);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
        int var7;

        if (this.furnaceInventory.isBurning())
        {
            var7 = this.furnaceInventory.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(var5 + 56, var6 + 36 + 12 - var7, 176, 12 - var7, 14, var7 + 2);
        }

        var7 = this.furnaceInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(var5 + 79, var6 + 34, 176, 14, var7 + 1, 16);
    }
}
