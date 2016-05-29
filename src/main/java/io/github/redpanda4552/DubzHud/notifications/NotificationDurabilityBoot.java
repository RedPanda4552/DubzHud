package io.github.redpanda4552.DubzHud.notifications;

public class NotificationDurabilityBoot extends AbstractNotificationDurability {

    public NotificationDurabilityBoot(String name, int color) {
        super(name, color);
    }

    @Override
    public void updateText() {
        // Its a magic number, but its a necessary evil.
        updateText(getDurabilityString(0));
    }
}
