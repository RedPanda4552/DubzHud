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

import net.minecraft.client.Minecraft;

public abstract class AbstractNotification {

    protected Minecraft mc;
    
    private String name, text;
    private boolean enabled = false;
    private int color;
    
    public AbstractNotification(String name, int color) {
        this.name = name;
        this.color = color;
        text = "";
        mc = Minecraft.getMinecraft();
    }
    
    public String getName() {
        return name;
    }
    
    public boolean enabled() {
        return enabled;
    }
    
    public int getColor() {
        return color;
    }
    
    public void setColor(int color) {
        this.color = color;
    }
    
    /**
     * Enable this notification so that it will appear on screen.
     */
    public void enable() {
        enabled = true;
    }
    
    /**
     * Disable this notification so that it will not appear on screen.
     */
    public void disable() {
        enabled = false;
    }
    
    public void toggle() {
        enabled = !enabled;
    }
    
    public String getText() {
        return text;
    }
    
    /**
     * This should call {@link #updateText(String)}; it is simply a middle man.
     */
    public abstract void updateText();
    
    protected void updateText(String str) {
        text = str;
    }

}
