package shadow.mods.metallurgy;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockSmokeEater extends Block
{
    public boolean active;

    public BlockSmokeEater(int var1, Boolean var2)
    {
        super(var1, Material.wood);
        this.active = var2.booleanValue();
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate());
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate()
    {
        return 20;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        super.updateTick(var1, var2, var3, var4, var5);

        if (!var1.isRemote)
        {
            if (var1.getBlockId(var2 + 1, var3, var4) == 1001 || var1.getBlockId(var2 + 1, var3, var4) == 1000)
            {
                var1.setBlock(var2 + 1, var3, var4, this.blockID);
            }

            if (var1.getBlockId(var2 - 1, var3, var4) == 1001 || var1.getBlockId(var2 - 1, var3, var4) == 1000)
            {
                var1.setBlock(var2 - 1, var3, var4, this.blockID);
            }

            if (var1.getBlockId(var2, var3 + 1, var4) == 1001 || var1.getBlockId(var2, var3 + 1, var4) == 1000)
            {
                var1.setBlock(var2, var3 + 1, var4, this.blockID);
            }

            if (var1.getBlockId(var2, var3 - 1, var4) == 1001 || var1.getBlockId(var2, var3 - 1, var4) == 1000)
            {
                var1.setBlock(var2, var3 - 1, var4, this.blockID);
            }

            if (var1.getBlockId(var2, var3, var4 + 1) == 1001 || var1.getBlockId(var2, var3, var4 + 1) == 1000)
            {
                var1.setBlock(var2, var3, var4 + 1, this.blockID);
            }

            if (var1.getBlockId(var2, var3, var4 - 1) == 1001 || var1.getBlockId(var2, var3, var4 - 1) == 1000)
            {
                var1.setBlock(var2, var3, var4 - 1, this.blockID);
            }

            var1.setBlock(var2, var3, var4, 0);
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate());
        }
    }
}
