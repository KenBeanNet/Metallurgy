package shadow.mods.metallurgy.precious;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import org.lwjgl.opengl.GL11;

public class FM_GuiMintStorage extends GuiContainer
{
    private IInventory upperChestInventory;
    private IInventory lowerChestInventory;
    private int inventoryRows = 0;
    private int inventoryCols = 0;
    private String image;

    public FM_GuiMintStorage(IInventory var1, IInventory var2)
    {
        super(new FM_ContainerMintStorage(var1, var2));
        this.upperChestInventory = var1;
        this.lowerChestInventory = var2;
        this.allowUserInput = false;
        short var3 = 222;
        int var4 = var3 - 108;
        this.inventoryRows = 2;
        this.inventoryCols = 3;
        this.ySize = var4 + this.inventoryRows * 18;
        this.image = "/shadow/mintstorage.png";
    }

    protected void drawGuiContainerForegroundLayer() {}

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        int var4 = this.mc.renderEngine.getTexture(this.image);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(var4);
        short var5 = 176;
        short var6 = 168;
        int var7 = this.width / 2 - var5 / 2;
        int var8 = this.height / 2 - var6 / 2;
        this.drawTexturedModalRect(var7, var8, 0, 0, var5, var6);
    }
}
