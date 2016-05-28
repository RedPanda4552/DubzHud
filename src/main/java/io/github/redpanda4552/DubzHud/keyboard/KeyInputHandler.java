package io.github.redpanda4552.DubzHud.keyboard;

import io.github.redpanda4552.DubzHud.ElementManager;
import io.github.redpanda4552.DubzHud.DubzHud;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyInputHandler {

    
    public KeyInputHandler(DubzHud dubzHud) {
        
    }
    
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyBindings.binding.isPressed()) {
            ElementManager.getToggle("Binding").toggle();
        }
    }
}
