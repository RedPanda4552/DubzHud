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
