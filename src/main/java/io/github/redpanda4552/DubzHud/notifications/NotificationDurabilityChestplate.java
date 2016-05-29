package io.github.redpanda4552.DubzHud.notifications;

public class NotificationDurabilityChestplate extends AbstractNotificationDurability {

    public NotificationDurabilityChestplate(String name, int color) {
        super(name, color);
    }

    @Override
    public void updateText() {
        // Its a magic number, but its a necessary evil.
        updateText(getDurabilityString(3));
    }
}
