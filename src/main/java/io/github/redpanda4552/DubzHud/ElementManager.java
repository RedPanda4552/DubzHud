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
import io.github.redpanda4552.DubzHud.notifications.NotificationDurabilityBoot;
import io.github.redpanda4552.DubzHud.notifications.NotificationDurabilityChestplate;
import io.github.redpanda4552.DubzHud.notifications.NotificationDurabilityHelmet;
import io.github.redpanda4552.DubzHud.notifications.NotificationDurabilityLegging;
import io.github.redpanda4552.DubzHud.notifications.NotificationSaturation;
import io.github.redpanda4552.DubzHud.notifications.NotificationSwingCooldown;
import io.github.redpanda4552.DubzHud.toggles.AbstractToggle;
import net.minecraftforge.common.config.Configuration;

public class ElementManager {
    
    private static LinkedHashSet<AbstractModule> modules = new LinkedHashSet<AbstractModule>();
    private static LinkedHashSet<AbstractNotification> notifications = new LinkedHashSet<AbstractNotification>();
    private static LinkedHashSet<AbstractToggle> toggles = new LinkedHashSet<AbstractToggle>();
    
    /**
     * Reconstructs the above LinkedHashSets to match the current contents of the config file.
     */
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
        int j = 0, k = 0, l = 0, m = 0;
        boolean b = false, c = false, d = false, e = false, f = false, g = false;
        
        b = config.getBoolean("swing-cooldown", "notifications", true, "Determines if the Swing Cooldown meter should display or not.");
        j = config.getInt("swing-cooldown-color-low", "notifications", 0xff0000, 0, Integer.MAX_VALUE, "The color of the Swing Cooldown meter when it is below 50%.");
        k = config.getInt("swing-cooldown-color-medium", "notifications", 0xffdd00, 0, Integer.MAX_VALUE, "The color of the Swing Cooldown meter when it is above 50%, but not full.");
        l = config.getInt("swing-cooldown-color-full", "notifications", 0x00ff00, 0, Integer.MAX_VALUE, "The color of the Swing Cooldown meter when it is full.");
        
        c = config.getBoolean("helmet-durability", "notifications", true, "Whether or not the helmet durability warning should show when helmet durability is below 12.5%.");
        d = config.getBoolean("chestplate-durability", "notifications", true, "Whether or not the chestplate durability warning should show when chestplate durability is below 12.5%.");
        e = config.getBoolean("legging-durability", "notifications", true, "Whether or not the legging durability warning should show when legging durability is below 12.5%.");
        f = config.getBoolean("boot-durability", "notifications", true, "Whether or not the boot durability warning should show when boot durability is below 12.5%.");
        m = config.getInt("durability-color", "notifications", 0xffdd00, 0, Integer.MAX_VALUE, "The color of the low armor durability notifications.");

        g = config.getBoolean("saturation-level", "notifications", true, "Determines if the Saturation Level meter should display or not");
        
        if (b) {
            notifications.add(new NotificationSwingCooldown("SwingCooldown", j, k, l));
        }
        
        if (c) {
            notifications.add(new NotificationDurabilityHelmet("DurabilityHelmet", m));
        }
        
        if (d) {
            notifications.add(new NotificationDurabilityChestplate("DurabilityChestplate", m));
        }
        
        if (e) {
            notifications.add(new NotificationDurabilityLegging("DurabilityLegging", m));
        }
        
        if (f) {
            notifications.add(new NotificationDurabilityBoot("DurabilityBoot", m));
        }
        
        if (g) {
            notifications.add(new NotificationSaturation("Saturation", j, k, l));
        }
        
        // Toggles
        
        config.save();
    }
    
    /**
     * Reorders the Modules LinkedHashSet so that it is ordered by the module's position values,
     * instead of insertion order.
     */
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
    
    /**
     * Get a Module from the currently active Modules by name.
     */
    public static AbstractModule getModule(String name) {
        for (AbstractModule module : modules) {
            if (module.getName().equals(name)) {
                return module;
            }
        }
        
        return null;
    }
    
    /**
     * Get a LinkedHashSet containing every active Module, sorted in proper order.
     */
    public static LinkedHashSet<AbstractModule> getAllModules() {
        return modules;
    }
    
    /**
     * Get a Notification from the currently active Notifications by name.
     */
    public static AbstractNotification getNotification(String name) {
        for (AbstractNotification notification : notifications) {
            if (notification.getName().equals(name)) {
                return notification;
            }
        }
        
        return null;
    }
    
    /**
     * Get a LinkedHashSet containing every active Notification, sorted in proper order.
     */
    public static LinkedHashSet<AbstractNotification> getAllNotifications() {
        return notifications;
    }
    
    /**
     * Get a Toggle from the currently active Toggles by name.
     */
    public static AbstractToggle getToggle(String name) {
        for (AbstractToggle toggle : toggles) {
            if (toggle.getName().equals(name)) {
                return toggle;
            }
        }
        
        return null;
    }
    
    /**
     * Get a LinkedHashSet containing every active Toggle, sorted in proper order.
     */
    public LinkedHashSet<AbstractToggle> getAllToggles() {
        return toggles;
    }
}
