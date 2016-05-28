package io.github.redpanda4552.DubzHud.modules;

public class ModuleTime extends AbstractModule {

    public ModuleTime(String name, int position) {
        super(name, position);
    }

    // TODO Time is going through 24 instead of resetting to 0 after 23:59... why?
    @Override
    public String getText() {
        String minuteStr = "";
        long time = mc.theWorld.getWorldTime();
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

        if (hours > 24)
        {
            hours -= 24;
        }
        return "[World Time: "  + hours + ":" + minuteStr + "] ";
    }

}
