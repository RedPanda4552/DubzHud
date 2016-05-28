/**
 * This file is part of DubzHud, licensed under the MIT License (MIT)
 * 
 * Copyright (c) Brian Wood
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.redpanda4552.DubzHud;

import java.util.LinkedHashSet;

import io.github.redpanda4552.DubzHud.modules.AbstractModule;
import io.github.redpanda4552.DubzHud.modules.ModuleEntities;
import io.github.redpanda4552.DubzHud.modules.ModuleFPS;
import io.github.redpanda4552.DubzHud.modules.ModuleLight;
import io.github.redpanda4552.DubzHud.modules.ModulePosition;
import io.github.redpanda4552.DubzHud.modules.ModuleTPS;
import io.github.redpanda4552.DubzHud.modules.ModuleTime;
import io.github.redpanda4552.DubzHud.notifications.AbstractNotification;
import io.github.redpanda4552.DubzHud.notifications.NotificationSwingCooldown;
import io.github.redpanda4552.DubzHud.toggles.AbstractToggle;
import net.minecraftforge.common.config.Configuration;

public class ElementManager {
    
    private static LinkedHashSet<AbstractModule> modules = new LinkedHashSet<AbstractModule>();
    private static LinkedHashSet<AbstractNotification> notifications = new LinkedHashSet<AbstractNotification>();
    private static LinkedHashSet<AbstractToggle> toggles = new LinkedHashSet<AbstractToggle>();
    
    public static void readFromConfiguration(Configuration config) {
        modules.clear();
        notifications.clear();
        toggles.clear();
        
        config.load();
        
        // Modules
        int i = 0;
        
        i = config.getInt("fps", "modules", 0, -1, 5, "Determines position of FPS meter in HUD. -1 disables. Range: 0 to 5.");
        
        if (i != -1) {
            modules.add(new ModuleFPS("FPS", i));
        }
        
        i = config.getInt("tps", "modules", 1, -1, 5, "Determines position of TPS meter in HUD. -1 disables. Range: 0 to 5.");
        
        if (i != -1) {
            modules.add(new ModuleTPS("TPS", i));
        }
        
        i = config.getInt("light", "modules", 2, -1, 5, "Determines position of light meter in HUD. -1 disables. Range: 0 to 5.");
        
        if (i != -1) {
            modules.add(new ModuleLight("Light", i));
        }
        
        i = config.getInt("entities", "modules", 3, -1, 5, "Determines position of entites meter in HUD. -1 disables. Range: 0 to 5.");
        
        if (i != -1) {
            modules.add(new ModuleEntities("Entities", i));
        }
        
        i = config.getInt("position", "modules", 4, -1, 5, "Determines position of position meter in HUD. -1 disables. Range: 0 to 5.");
        
        if (i != -1) {
            modules.add(new ModulePosition("Position", i));
        }
        
        i = config.getInt("time", "modules", 5, -1, 5, "Determines position of position meter in HUD. -1 disables. Range: 0 to 5.");
        
        if (i != -1) {
            modules.add(new ModuleTime("Time", i));
        }
        
        modules = reorderModules(modules);
        
        // Notifications
        int j = 0, k = 0, l = 0;
        boolean b = false;
        
        b = config.getBoolean("swing-cooldown", "notifications", true, "Determines if the Swing Cooldown meter should display or not.");
        j = config.getInt("swing-cooldown-color-low", "notifications", 0xff0000, 0, Integer.MAX_VALUE, "The color of the Swing Cooldown meter when it is below 50%.");
        k = config.getInt("swing-cooldown-color-medium", "notifications", 0xffdd00, 0, Integer.MAX_VALUE, "The color of the Swing Cooldown meter when it is above 50%, but not full.");
        l = config.getInt("swing-cooldown-color-full", "notifications", 0x00ff00, 0, Integer.MAX_VALUE, "The color of the Swing Cooldown meter when it is full.");
        
        if (b) {
            notifications.add(new NotificationSwingCooldown("SwingCooldown", j, k, l));
        }
        
        // Toggles
        
        config.save();
    }
    
    private static LinkedHashSet<AbstractModule> reorderModules(LinkedHashSet<AbstractModule> unorderedModules) {
        LinkedHashSet<AbstractModule> orderedModules = new LinkedHashSet<AbstractModule>();
        for (int i = 0; i < unorderedModules.size(); i++) {
            for (AbstractModule module : unorderedModules) {
                if (module.getPosition() == i) {
                    orderedModules.add(module);
                }
            }
        }
        
        return orderedModules;
    }
    
    public static AbstractModule getModule(String name) {
        for (AbstractModule module : modules) {
            if (module.getName().equals(name)) {
                return module;
            }
        }
        
        return null;
    }
    
    public static LinkedHashSet<AbstractModule> getAllModules() {
        return modules;
    }
    
    public static AbstractNotification getNotification(String name) {
        for (AbstractNotification notification : notifications) {
            if (notification.getName().equals(name)) {
                return notification;
            }
        }
        
        return null;
    }
    
    public static LinkedHashSet<AbstractNotification> getAllNotifications() {
        return notifications;
    }
    
    public static AbstractToggle getToggle(String name) {
        for (AbstractToggle toggle : toggles) {
            if (toggle.getName().equals(name)) {
                return toggle;
            }
        }
        
        return null;
    }
    
    public LinkedHashSet<AbstractToggle> getAllToggles() {
        return toggles;
    }
}
