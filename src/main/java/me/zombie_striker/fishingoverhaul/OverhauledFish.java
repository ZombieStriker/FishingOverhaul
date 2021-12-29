package me.zombie_striker.fishingoverhaul;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public class OverhauledFish {

    private Material fishMaterial;
    private String displayname;
    private String lore;
    private double rarity;
    private double minoz;
    private double maxoz;

    private double minozAverage;
    private double minozExceptional;

    public OverhauledFish(Material material, String displayname, String lore, double rarity, double minoz, double maxoz){
        this.fishMaterial = material;
        this.displayname =  ChatColor.translateAlternateColorCodes('&',displayname);
        this.lore= ChatColor.translateAlternateColorCodes('&',lore);
        this.rarity = rarity;
        this.maxoz = maxoz;
        this.minoz = minoz;

        // Gets the difference between the max and min ounces and sets the quality markers to be at 1/3 increments.
        double difference = (maxoz - minoz) /3;

        minozAverage = minoz+difference;
        minozExceptional = minozAverage+difference;
    }

    /**
     * Gets the min ounces for average quality
     * @returns min ounces for average quality
     */
    public double getMinozAverage() {
        return minozAverage;
    }

    /**
     * Returns the min ounces for exceptional quality
     * @returns the min ounces for exceptional quality
     */
    public double getMinozExceptional() {
        return minozExceptional;
    }

    /**
     * Returns the max ounces for the fish.
     * @returns the max ounces
     */
    public double getMaxoz() {
        return maxoz;
    }

    /**
     * Returns the min ounces for the fish
     * @returns the min ounces
     */
    public double getMinoz() {
        return minoz;
    }

    /**
     * Returns the rarity of the fish as a double
     * @returns the rarity
     */
    public double getRarity() {
        return rarity;
    }

    /**
     * Returns the lore string to be added to the item
     * @returns the lore
     */
    public String getLoreString() {
        return lore;
    }

    /**
     * Returns the material of the fish item
     * @returns the fish material
     */
    public Material getFishMaterial() {
        return fishMaterial;
    }

    /**
     * Returns the display name of the fish item
     * @returns the display name
     */
    public String getDisplayName() {
        return displayname;
    }
}
