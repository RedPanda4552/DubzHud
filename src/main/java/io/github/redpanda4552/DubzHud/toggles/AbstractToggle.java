package io.github.redpanda4552.DubzHud.toggles;

public abstract class AbstractToggle {

    private String name;
    private boolean enabled;
    
    public AbstractToggle(String name) {
        this.name = name;
        enabled = false;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean enabled() {
        return enabled;
    }
    
    public void toggle() {
        enabled = !enabled;
    }
    
    public abstract String getText();

}
