/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.adrian.colorcount.comp;

import ca.adrian.colorcount.entity.Tile;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author bcainne
 */
public class CountPanel extends JPanel {
    private List<Tile> counts = new ArrayList<>();
    
    public CountPanel() {
        super();
        setSize(new Dimension(256, 768));
        setPreferredSize(new Dimension(256, 768));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = 0;
        int x = 0;
        for (Tile tile : counts) {
            g.setColor(Color.BLACK);
            String countStr = Integer.toString(tile.getCount());
            g.drawChars(countStr.toCharArray(), 0, countStr.length(), x, y+16);
            g.setColor(tile.getAWTColor());
            g.fillRect(x+32, y, 16, 16);
            y = y + 16;
            if (y > 768) {
                y = 0;
                x = x + 48;
            }
        }        
    }    
 

    /**
     * @return the counts
     */
    public List<Tile> getCounts() {
        return counts;
    }

    /**
     * @param counts the counts to set
     */
    public void setCounts(List<Tile> counts) {
        this.counts = counts;
        this.repaint();
    }
}
