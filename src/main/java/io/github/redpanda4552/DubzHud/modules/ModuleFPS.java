package io.github.redpanda4552.DubzHud.modules;

public class ModuleFPS extends AbstractModule {

    public ModuleFPS(String name, int position) {
        super(name, position);
    }

    @Override
    public String getText() {
        return "[FPS: " + mc.debug.split(" fps")[0] + "] ";
    }

}
