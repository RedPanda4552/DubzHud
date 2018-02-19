package io.github.redpanda4552.DubzHud.modules;

import io.github.redpanda4552.DubzHud.DubzHud;
import net.minecraft.entity.player.EntityPlayer;

public class ModuleSlime extends AbstractModule {

    private DubzHud dubzHud;
    
    public ModuleSlime(String name, int position) {
        super(name, position);
        this.dubzHud = DubzHud.me;
    }

    @Override
    public String getText() {
        EntityPlayer ep = mc.player;
        String str = "No";
        
        if (dubzHud.sdc.entryExists(ep.chunkCoordX, ep.chunkCoordZ)) {
            str = "Yes";
        }
        
        return "[Slime Chunk: " + str + "]";
    }

}
