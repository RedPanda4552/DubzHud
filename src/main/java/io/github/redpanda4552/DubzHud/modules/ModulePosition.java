package io.github.redpanda4552.DubzHud.modules;

public class ModulePosition extends AbstractModule {

    public ModulePosition(String name, int position) {
        super(name, position);
    }

    @Override
    public String getText() {
        return "[Position: x:" + (int)Math.floor(Double.valueOf(mc.thePlayer.posX)) + " y:" + (int)Math.floor(Double.valueOf(mc.thePlayer.getEntityBoundingBox().minY)) + " z:" + (int)Math.floor(Double.valueOf(mc.thePlayer.posZ)) + "] ";
    }

}
