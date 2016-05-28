package io.github.redpanda4552.DubzHud.notifications;

import net.minecraft.client.Minecraft;

public abstract class AbstractNotification {

    protected Minecraft mc;
    
    private String name, text;
    private boolean enabled;
    private int color;
    
    public AbstractNotification(String name, int color) {
        this.name = name;
        this.color = color;
        text = "";
        mc = Minecraft.getMinecraft();
    }
    
    public String getName() {
        return name;
    }
    
    public boolean enabled() {
        return enabled;
    }
    
    public int getColor() {
        return color;
    }
    
    public void setColor(int color) {
        this.color = color;
    }
    
    public void toggle() {
        enabled = !enabled;
    }
    
    public String getText() {
        return text;
    }
    
    /**
     * This should call {@link #updateText(String)}; it is simply a middle man.
     */
    public abstract void updateText();
    
    protected void updateText(String str) {
        text = str;
    }

}
