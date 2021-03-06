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

package io.github.redpanda4552.DubzHud.notifications;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public abstract class AbstractNotificationDurability extends AbstractNotification {

    public AbstractNotificationDurability(String name, int color) {
        super(name, color);
    }
    
    /**
     * Composes the string that displays as the low durability notification.
     */
    public String getDurabilityString(int index) {
        ItemStack stack = mc.thePlayer.inventory.armorInventory[index];
        ItemArmor armor = null;
        
        if (stack != null && stack.getItem() instanceof ItemArmor) {
            armor = (ItemArmor) stack.getItem();
            if (stack.getMaxDamage() - stack.getItemDamage() <= stack.getMaxDamage() / 8) {
                enable();
            } else {
                disable();
            }
        } else {
            disable();
        }
        
        if (!enabled()) {
            return null;
        }
        
        String armorType = "";
        switch (index) {
        case 3:
            armorType = "Helmet";
            break;
        case 2:
            armorType = "Chestplate";
            break;
        case 1:
            armorType = "Leggings";
            break;
        case 0:
            armorType = "Boots";
            break;
        }
        
        String armorMaterial = "";
        switch (armor.getArmorMaterial()) {
        case CHAIN:
            armorMaterial = "Chain";
            break;
        case DIAMOND:
            armorMaterial = "Diamond";
            break;
        case GOLD:
            armorMaterial = "Gold";
            break;
        case IRON:
            armorMaterial = "Iron";
            break;
        case LEATHER:
            armorMaterial = "Leather";
            break;
        }
        return "[" + armorMaterial + " " + armorType + ": Low Durability!]";
    }

}
