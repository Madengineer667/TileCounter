/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.adrian.colorcount.comp;

import ca.adrian.colorcount.entity.Tile;
import ca.adrian.util.DisplayError;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
    private int size = 14;
    private int gap = 2;

    private BufferedImage image;
    
    public ImagePanel() {
        super();
        Properties prop = new Properties();
        try {
            prop.load(ImagePanel.class.getResourceAsStream("config.properties"));
        } catch (IOException ioe) {
            DisplayError.error("Error Loading Properties",ioe);
        }
        size = Integer.parseInt(prop.getProperty("SIZE"));
        gap = Integer.parseInt(prop.getProperty("GAP"));
        System.out.println("Tile Size = "+size);
        System.out.println("Gap Between Tiles = "+gap);
        this.setSize(768, 768);
        this.setPreferredSize(new Dimension(768, 768));
    }

    public void load(File file, CountPanel countPanel) {
        if (file.exists()) {
            List<Tile> tiles = countPanel.getCounts();
            try {                
                image = ImageIO.read(file);          
                int y = size/2;
                int x = size/2;
                while (y <= image.getHeight()) {
                    while (x <= image.getWidth()) {
                        tileCounter(tiles, image.getRGB(x, y));
                        x = x + size + gap;
                    }
                    x = size/2;
                    y = y + size + gap;
                }
            } catch (IOException ex) {
                DisplayError.error("Error Loading Image",ex);
            }
            int grandTotal = 0;
            for (Tile tile : tiles) {
                //System.out.println(tile.getColor()+" "+tile.getCount());
                grandTotal = grandTotal + tile.getCount();
            }
            System.out.println("Number of Total Tiles = "+ grandTotal);
            System.out.println("Number of Colors = "+ tiles.size());            
            countPanel.setCounts(tiles);
            this.repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }
    
    
    private void tileCounter(List<Tile> tiles, int color) {
        if (!tiles.isEmpty()) {
            for (Tile tile : tiles) {
                if (tile.getColor() == color) {
                    int count = tile.getCount();
                    tile.setCount(count+1);
                    return;
                }
            }
        }
        tiles.add(new Tile(color));
    }
}