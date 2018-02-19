package io.github.redpanda4552.DubzHud;

import java.util.HashMap;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;

public class DubzHudGuiFactory implements IModGuiFactory {

    @Override
    public void initialize(Minecraft minecraftInstance) {
        
    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return DubzHudConfigGui.class;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    @SuppressWarnings("deprecation")
    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
        return null;
    }
    
    public static class DubzHudConfigGui extends GuiConfig {
        
        private HashMap<String, Integer> modulePositions;
        private HashMap<String, Boolean> notificationStates;
        
        private HashMap<Integer, String> moduleButtonIds;
        private HashMap<Integer, String> notificationButtonIds;
        
        public DubzHudConfigGui(GuiScreen parentScreen) {
            super(parentScreen, new ConfigElement(DubzHud.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), DubzHud.MODID, false, false, "DubzHud Configuration");
        }
        
        @Override
        public void initGui() {
            super.initGui();
            modulePositions = new HashMap<String, Integer>();
            notificationStates = new HashMap<String, Boolean>();
            
            moduleButtonIds = new HashMap<Integer, String>();
            notificationButtonIds = new HashMap<Integer, String>();
            
            ConfigCategory modules = DubzHud.config.getCategory("modules");
            ConfigCategory notifications = DubzHud.config.getCategory("notifications");
            
            for (String key : modules.keySet()) {
                modulePositions.put(key, modules.get(key).getInt());
            }
            
            for (String key : notifications.keySet()) {
                notificationStates.put(key, notifications.get(key).getBoolean());
            }
            
            int buttonId = 0;
            int buttonWidth = longestStringLength(modulePositions.keySet(), notificationStates.keySet()) + 20;
            int buttonHeight = 20;
            
            int xPos = this.width / 2 - (buttonWidth / 2) / 2;
            int yPos = this.height / 6;
            
            for (String module : modulePositions.keySet()) {
                buttonList.add(new GuiButton(buttonId, xPos, yPos, buttonWidth, buttonHeight, module + " = " + modulePositions.get(module)));
                moduleButtonIds.put(buttonId, module);
                yPos += 24;
                buttonId++;
            }
            
            xPos = (int) (this.width / 2 + (buttonWidth / 2) * 1.5);
            yPos = this.height / 6;
            
            for (String notification : notificationStates.keySet()) {
                buttonList.add(new GuiButton(buttonId, xPos, yPos, buttonWidth, buttonHeight, notification + " = " + notificationStates.get(notification)));
                notificationButtonIds.put(buttonId, notification);
                yPos += 24;
                buttonId++;
            }
        }
        
        @Override
        public void drawScreen(int mouseX, int mouseY, float partialTicks) {
            super.drawScreen(mouseX, mouseY, partialTicks);
        }
        
        @Override
        public void actionPerformed(GuiButton button) {
            super.actionPerformed(button);
            
            if (button.enabled) {
                if (moduleButtonIds.containsKey(button.id)) {
                    String key = moduleButtonIds.get(button.id);
                    int oldValue = modulePositions.get(key);
                    int newValue = oldValue++;
                    
                    if (newValue > modulePositions.size() - 1) {
                        newValue = -1;
                    }
                    
                    modulePositions.put(key, newValue);
                    button.displayString = key + " = " + newValue;
                    
                } else if (notificationButtonIds.containsKey(button.id)) {
                    String key = notificationButtonIds.get(button.id);
                    boolean oldValue = notificationStates.get(key);
                    boolean newValue = !oldValue;
                    
                    notificationStates.put(key, newValue);
                    button.displayString = key + " = " + newValue;
                }
            }
        }
        
        private int longestStringLength(Set<String> mStrings, Set<String> nStrings) {
            int longest = 0;
            
            for (String str : mStrings) {
                if (fontRendererObj.getStringWidth(str) > longest) {
                    longest = fontRendererObj.getStringWidth(str);
                }
            }
            
            for (String str : nStrings) {
                if (fontRendererObj.getStringWidth(str) > longest) {
                    longest = fontRendererObj.getStringWidth(str);
                }
            }
            
            return longest;
        }
        
    }
}
