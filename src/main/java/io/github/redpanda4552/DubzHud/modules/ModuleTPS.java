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

package io.github.redpanda4552.DubzHud.modules;

import net.minecraft.client.Minecraft;

public class ModuleTPS extends AbstractModule {

    private long lastTime = 0, tps = 0, lastWorldTicks = 0;
    String tpsDiff = "";
    
    public ModuleTPS(String name, int position) {
        super(name, position);
    }

    @Override
    public String getText() {
        long time = mc.theWorld.getWorldTime();

        if (Minecraft.getSystemTime() - lastTime >= 1000)
        {
            tps = time - lastWorldTicks;

            if (tps > 20)
            {
                tpsDiff = "+" + (tps - 20);
            }
            else if (tps < 0)
            {
                tpsDiff = "-" + (20 + Math.abs(tps));
            }
            else
            {
                tpsDiff = "-" + (20 - tps);
            }

            lastTime = Minecraft.getSystemTime();
            lastWorldTicks = time;
        }
        return "[TPS: " + tps + " (" + tpsDiff + ")] ";
    }

}
