package shadow.mods.metallurgy.precious;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import org.lwjgl.opengl.GL11;

public class FC_GuiChest extends GuiContainer
{
    private IInventory upperChestInventory;
    private IInventory lowerChestInventory;
    private int inventoryRows = 0;
    private int inventoryCols = 0;
    private String image;

    public FC_GuiChest(IInventory var1, IInventory var2)
    {
        super(new FC_ContainerChest(var1, var2));
        this.upperChestInventory = var1;
        this.lowerChestInventory = var2;
        this.allowUserInput = false;
        short var3 = 222;
        int var4 = var3 - 108;
        this.inventoryRows = ((FC_TileEntityChest)var2).getNumRows();
        this.inventoryCols = ((FC_TileEntityChest)var2).getNumCols();
        this.ySize = var4 + this.inventoryRows * 18;
        this.xSize = this.inventoryCols * 18 + 20;
        int var5 = ((FC_TileEntityChest)var2).getType();
        this.image = "/shadow/ironcontainer.png";

        switch (var5)
        {
            case 0:
                this.image = "/shadow/ironcontainer.png";
                break;

            case 1:
                this.image = "/shadow/silvercontainer.png";
                break;

            case 2:
                this.image = "/shadow/goldcontainer.png";
                break;

            case 3:
                this.image = "/shadow/electrumcontainer.png";
                break;

            case 4:
                this.image = "/shadow/diamondcontainer.png";
        }
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
        int var5 = 11 + 18 * this.inventoryCols + 11;
        int var6 = 7 + 18 * this.inventoryRows + 4 + 54 + 4 + 18 + 7;
        int var7 = this.width / 2 - var5 / 2;
        int var8 = this.height / 2 - var6 / 2;
        this.drawTexturedModalRect(var7, var8, 0, 0, var5, var6);
    }
}
