package io.github.redpanda4552.DubzHud.notifications;

public class NotificationSwingCooldown extends AbstractNotification {

    private float cooldown = 0F;
    
    /**
     *  The field {@link #color} provided by the {@link AbstractNotification} is unused here
     */
    private int lowColor, mediumColor, fullColor;
    
    public NotificationSwingCooldown(String name, int lowColor, int mediumColor, int fullColor) {
        super(name, lowColor);
        this.lowColor = lowColor;
        this.mediumColor = mediumColor;
        this.fullColor = fullColor;
        toggle(); // So that the cooldown is shown by default
    }

    @Override
    public int getColor() {
        if (cooldown == 100) {
            return fullColor;
        } else if (cooldown >= 50) {
            return mediumColor;
        } else {
            return lowColor;
        }
    }
    
    @Override
    public void updateText() {
        cooldown = (float)Math.floor(mc.thePlayer.getCooledAttackStrength(0) * 100);
        updateText("[Swing Power: " + cooldown + "%]");
    }
}
