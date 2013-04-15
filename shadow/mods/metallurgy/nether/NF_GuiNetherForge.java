package shadow.mods.metallurgy.nether;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class NF_GuiNetherForge extends GuiContainer
{
    private NF_TileEntityNetherForge furnaceInventory;
    public static String[] names = new String[] {"Ignatius", "Shadow Iron", "Shadow Steel", "Vyroxeres", "Inolashite", "Kalendrite", "Vulcanite", "Sanguinite"};
    private int type;

    public NF_GuiNetherForge(InventoryPlayer var1, NF_TileEntityNetherForge var2)
    {
        super(new NF_ContainerNetherForge(var1, var2));
        this.furnaceInventory = var2;
        this.type = var2.getType();
    }

    protected void drawGuiContainerForegroundLayer()
    {
        this.fontRenderer.drawString(names[this.type] + " Smelter", 30 - (names[this.type] + " Smelter").length() / 2, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        int var4 = this.mc.renderEngine.getTexture("/shadow/lavafurnace.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(var4);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
        int var8 = this.furnaceInventory.getFuelScaled(63);
        this.drawTexturedModalRect(var5 + 144, var6 + 11 + 63 - var8, 176, 94 - var8, 17, 31 + var8);
        int var7 = this.furnaceInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(var5 + 59, var6 + 33, 176, 14, var7 + 1, 16);
    }
}
