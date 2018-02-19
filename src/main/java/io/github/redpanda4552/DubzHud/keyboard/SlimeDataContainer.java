package io.github.redpanda4552.DubzHud.keyboard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import io.github.redpanda4552.DubzHud.DubzHud;

public class SlimeDataContainer {

    /**
     * Container for coords of chunks slimes have spawned in.
     * Really should be called SlimeChunkCoordSet or something
     * like that, but refactor would be annoying cause comments.
     * We know what it does so it's fine for now.
     */
    private class SlimeDataChunk {
        
        private int x, z;
        
        SlimeDataChunk(int x, int z) {
            this.x = x;
            this.z = z;
        }
        
        int getX() {
            return x;
        }
        
        int getZ() {
            return z;
        }
    }
    
    private ArrayList<SlimeDataChunk> data;
    private int addCounter = 0;
    
    public SlimeDataContainer() {
        data = new ArrayList<SlimeDataChunk>();
        populate();
        sort();
    }
    
    /**
     * Adds a new chunk to SQL and the end of the SlimeDataChunk ArrayList.
     * No regard for sorting.
     */
    public void addNewChunk(int x, int z) {
        data.add(new SlimeDataChunk(x, z));
        PreparedStatement ps = null;
        
        try {
             ps = DubzHud.connection.prepareStatement("INSERT INTO dubzhud (xPos, zPos) VALUES (?, ?);");
             ps.setInt(1, x);
             ps.setInt(2, z);
             ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                
                if (++addCounter >= 5) {
                    addCounter = 0;
                    sort();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Check if the chunk at the specified chunk coords has had a slime spawn.
     */
    public boolean entryExists(int x, int z) {
        for (SlimeDataChunk sdc : data) {
            if (sdc.getX() == x && sdc.getZ() == z) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * Wipe SlimeDataChunks, pull data from SQL into memory.
     */
    public void populate() {
        data.clear();
        PreparedStatement ps = null;
        
        try {
            ps = DubzHud.connection.prepareStatement("SELECT * FROM dubzhud;");
            ResultSet res = ps.executeQuery();
            
            while (res.next()) {
                data.add(new SlimeDataChunk(res.getInt("xPos"), res.getInt("zPos")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Pretty shotty bubble sort type sorting method.
     * Operates on the data object in this class.
     */
    public void sort() {
        if (data.isEmpty()) {
            return;
        }
        
        ArrayList<SlimeDataChunk> ret = new ArrayList<SlimeDataChunk>();
        SlimeDataChunk min = data.get(0);
        
        for (int i = 0; i < data.size(); i++) {
            for (int j = i + 1; j < data.size() - 1; j++) {
                if (data.get(i).getX() < data.get(j).getX()) {
                    min = data.get(i);
                } else {
                    min = data.get(j);
                }
            }
            
            ret.add(min);
        }
        
        data = ret;
    }
}
