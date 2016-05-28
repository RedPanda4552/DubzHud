package io.github.redpanda4552.DubzHud.modules;

public class ModuleEntities extends AbstractModule {

    public ModuleEntities(String name, int position) {
        super(name, position);
    }

    @Override
    public String getText() {
        return "[Entities: " + mc.renderGlobal.getDebugInfoEntities().split("E: ")[1].split(",")[0] + "] ";
    }

}
