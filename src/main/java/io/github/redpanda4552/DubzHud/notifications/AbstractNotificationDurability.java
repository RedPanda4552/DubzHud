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
