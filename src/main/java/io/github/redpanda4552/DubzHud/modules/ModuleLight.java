package io.github.redpanda4552.DubzHud.modules;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.chunk.Chunk;

public class ModuleLight extends AbstractModule {

    public ModuleLight(String name, int position) {
        super(name, position);
    }

    @Override
    public String getText() {
        BlockPos blockPos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.getEntityBoundingBox().minY, mc.thePlayer.posZ);
        Chunk chunk = mc.theWorld.getChunkFromBlockCoords(blockPos);
        return "[Light (S/B): " + chunk.getLightFor(EnumSkyBlock.SKY, blockPos) + "/" + chunk.getLightFor(EnumSkyBlock.BLOCK, blockPos) + "] ";
    }

}
