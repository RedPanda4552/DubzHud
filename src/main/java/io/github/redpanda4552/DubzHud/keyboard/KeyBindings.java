package io.github.redpanda4552.DubzHud.keyboard;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyBindings {

    public static KeyBinding binding;
    
    public static void init() {
        binding = new KeyBinding("key.babyGuard", Keyboard.KEY_L, "key.categories.dubzhud");
        
        ClientRegistry.registerKeyBinding(binding);
    }

}
