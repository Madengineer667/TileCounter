/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.adrian.colorcount.entity;

import java.awt.Color;

/**
 *
 * @author bcainne
 */
public class Tile {
    private Integer color;
    private Integer count = 1;
    
    public Tile(Integer color) {
        this.color = color;
    }

    /**
     * @return the color
     */
    public Integer getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Integer color) {
        this.color = color;
    }

    /**
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(Integer count) {
        this.count = count;
    }
    
    public Color getAWTColor() {
        return new Color(color);
    }
}
