package me.zombie_striker.fishingoverhaul;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public class OverhauledFish {

    private final Material fishMaterial;
    private final int custommodeldata;
    private final String displayname;
    private final String lore;
    private final double rarity;
    private final double minoz;
    private final double maxoz;

    private final double minozAverage;
    private final double minozExceptional;

    public OverhauledFish(Material material, int custommodeldata, String displayname, String lore, double rarity, double minoz, double maxoz){
        this.fishMaterial = material;
        this.custommodeldata = custommodeldata;
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
     * Returns the custom model data id
     * @return the custom model data id
     */
    public int getCustomModelData() {
        return custommodeldata;
    }

    /**
     * Gets the min ounces for average quality
     * @return min ounces for average quality
     */
    public double getMinOZAverage() {
        return minozAverage;
    }

    /**
     * Returns the min ounces for exceptional quality
     * @return the min ounces for exceptional quality
     */
    public double getMinOZExceptional() {
        return minozExceptional;
    }

    /**
     * Returns the max ounces for the fish.
     * @return the max ounces
     */
    public double getMaxOZ() {
        return maxoz;
    }

    /**
     * Returns the min ounces for the fish
     * @return the min ounces
     */
    public double getMinOZ() {
        return minoz;
    }

    /**
     * Returns the rarity of the fish as a double
     * @return the rarity
     */
    public double getRarity() {
        return rarity;
    }

    /**
     * Returns the lore string to be added to the item
     * @return the lore
     */
    public String getLoreString() {
        return lore;
    }

    /**
     * Returns the material of the fish item
     * @return the fish material
     */
    public Material getFishMaterial() {
        return fishMaterial;
    }

    /**
     * Returns the display name of the fish item
     * @return the display name
     */
    public String getDisplayName() {
        return displayname;
    }
}
