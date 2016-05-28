package io.github.redpanda4552.DubzHud.modules;

import net.minecraft.client.Minecraft;

public abstract class AbstractModule {
    
    protected Minecraft mc;
    
    protected String name;
    protected int position;
    
    public AbstractModule(String name, int position) {
        mc = Minecraft.getMinecraft();
        this.name = name;
        this.position = position;
    }
    
    public String getName() {
        return name;
    }
    
    /**
     * Get the position that the module will be in on the HUD.
     * -1 will disable the module.
     * @return
     */
    public int getPosition() {
        return position;
    }
    
    public abstract String getText();

}
