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

public class ModuleTime extends AbstractModule {

    public ModuleTime(String name, int position) {
        super(name, position);
    }

    @Override
    public String getText() {
        String minuteStr = "";
        long time = mc.world.getWorldTime();
        int thisDayTicks = (int)(time - (Math.floor(time / 24000) * 24000));
        int totalMinutes = (int)(thisDayTicks / 16.666666666666666666666666666667);
        int hours = (int) Math.floor(thisDayTicks / 1000);
        int minutes = (int) Math.floor(totalMinutes - (hours * 60));

        if (minutes < 0)
        {
            minutes = 0;
        }

        if (minutes >= 10)
        {
            minuteStr = "" + minutes;
        }
        else
        {
            minuteStr = new StringBuilder().append("0").append(minutes).toString();
        }

        hours += 6;

        if (hours >= 24)
        {
            hours -= 24;
        }
        return "[World Time: "  + hours + ":" + minuteStr + "] ";
    }

}
