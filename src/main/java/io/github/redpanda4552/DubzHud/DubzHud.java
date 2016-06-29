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

import org.apache.logging.log4j.Logger;

import io.github.redpanda4552.DubzHud.keyboard.KeyInputHandler;
import io.github.redpanda4552.DubzHud.modules.AbstractModule;
import io.github.redpanda4552.DubzHud.notifications.AbstractNotification;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = DubzHud.MODID,
     version = DubzHud.VERSION/*,
     guiFactory = "io.github.redpanda4552.DubzHud.DubzHudGuiFactory"*/
)
public class DubzHud {

    public static final String MODID = "dubzhud";
    public static final String VERSION = "2.0.2";
    private Logger log;
    
    private static final Minecraft mc = Minecraft.getMinecraft();
    
    private String moduleText;
    private long lastTicks = 0, curTicks = 0;
    private int textTop = 2;
    
    public static Configuration config = null;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        log = event.getModLog();
        config = new Configuration(event.getSuggestedConfigurationFile());
        ElementManager.readFromConfiguration(config);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler(this));
//        KeyBindings.init();
        moduleText = new String("");
        log.info("Initialized and ready to go!");
    }
    
    /**
     * Called with every frame, this effectively runs the mod.
     * Calls for Modules, Notifications and Toggles to update themselves per tick,
     * and calls for these to be drawn per frame.
     */
    @SubscribeEvent
    public void renderHUD(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT && !mc.gameSettings.showDebugInfo) {
            curTicks = mc.theWorld.getWorldTime();
            
            // Update HUD Components; as to save CPU time we will update these per tick, not per frame.
            if (curTicks - lastTicks >= 1) {
                updateModules();
                updateNotifications();
                updateToggles();
                lastTicks = curTicks;
            } else if (lastTicks > curTicks) {
                lastTicks = 0;
                curTicks = 0;
            }
            
            // Draw HUD Components
            textTop = 2;
            drawModules();
            drawNotifications();
            drawToggles();
        }
    }
    
    /**
     * Draws Modules to the screen; called per frame.
     */
    private void drawModules() {
        Gui.drawRect(1, 1, mc.fontRendererObj.getStringWidth(moduleText) - 1, 10, -1873784752);
        mc.fontRendererObj.drawString(moduleText, 2, textTop, 0x00ddff);
        textTop += 10;
    }
    
    /**
     * Updates Module data; called per tick.
     */
    private void updateModules() {
        StringBuilder sb = new StringBuilder("[DubzHud v2.0.1] ");
        for (AbstractModule module : ElementManager.getAllModules()) {
            sb.append(module.getText());
        }
        moduleText = sb.toString();
    }
    
    /**
     * Draws Notifications to the screen; called per frame.
     */
    private void drawNotifications() {
        for (AbstractNotification notification : ElementManager.getAllNotifications()) {
            if (notification.enabled()) {
                mc.fontRendererObj.drawStringWithShadow(notification.getText(), 2, textTop, notification.getColor());
                textTop += 10;
            }
        }
    }
    
    /**
     * Updates Notification data; called per tick.
     */
    private void updateNotifications() {
        for (AbstractNotification notification : ElementManager.getAllNotifications()) {
            notification.updateText();
        }
    }
    
    /**
     * Not implemented yet; will draw Toggles to the screen; called per frame.
     */
    private void drawToggles() {
        
    }
    
    /**
     * Not implemented yet; will update Toggle data; called per tick.
     */
    private void updateToggles() {
        
    }
}
