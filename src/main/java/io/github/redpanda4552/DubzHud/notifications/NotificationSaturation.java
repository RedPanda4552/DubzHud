package io.github.redpanda4552.DubzHud.notifications;

public class NotificationSaturation extends AbstractNotification {

    private float saturation = 0F;
    
    /**
     *  The field {@link #color} provided by the {@link AbstractNotification} is unused here
     */
    private int lowColor, mediumColor, fullColor;
    
    public NotificationSaturation(String name, int lowColor, int mediumColor, int fullColor) {
        super(name, lowColor);
        this.lowColor = lowColor;
        this.mediumColor = mediumColor;
        this.fullColor = fullColor;
        toggle();
    }
    
    @Override
    public int getColor() {
        if (saturation >= 50.0F) {
            return fullColor;
        } else if (saturation >= 25.0F) {
            return mediumColor;
        } else {
            return lowColor;
        }
    }

    @Override
    public void updateText() {
        saturation = (float) Math.floor(mc.player.getFoodStats().getSaturationLevel() * 5);
        updateText("[Saturation Level: " + saturation + "%]");
    }

}
