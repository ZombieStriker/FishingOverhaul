package me.zombie_striker.fishingoverhaul;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

public class FishingOverhaul extends JavaPlugin {

    private FishingManager fishingmanager;
    private final String qualityPrefix = ChatColor.translateAlternateColorCodes('&',"&aQuality: ");
    private final DecimalFormat decimalFormat = new DecimalFormat("0.#");


    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new FishingListener(this), this);
        fishingmanager = new FishingManager(this);
        FishCommand fishcommand = new FishCommand(this);
        getCommand("fish").setExecutor(fishcommand);
        getCommand("fish").setTabCompleter(fishcommand);
    }

    /**
     * Returns the fishing manager
     *
     * @return the fishing manager
     */
    public FishingManager getFishingmanager() {
        return fishingmanager;
    }

    /**
     * Returns the weight format for item lore
     *
     * @return the weight format
     */
    public String getWeightformat() {
        return "&3%weight% oz.";
    }


    /**
     * Creates a new fish itemstack from an OverhauledFish instance
     *
     * @param fish the overhauledfish instanace
     * @return the fish itemstack
     */
    public ItemStack getFish(OverhauledFish fish) {
        ItemStack itemStack = new ItemStack(fish.getFishMaterial());
        ItemMeta im = itemStack.getItemMeta();
        im.displayName(Component.text(fish.getDisplayName()));
        List<Component> lore = new LinkedList<>();
        lore.add(Component.text(fish.getLoreString()));
        double weight = ((fish.getMaxOZ() - fish.getMinOZ()) * Math.random()) + fish.getMinOZ();
        String weightstring = ChatColor.translateAlternateColorCodes('&', getWeightformat().replaceAll("%weight%", decimalFormat.format(weight)));
        lore.add(Component.text(weightstring));
        im.lore(lore);
        im.setCustomModelData(fish.getCustomModelData());
        itemStack.setItemMeta(im);
        return itemStack;
    }

    /**
     * Returns the fish's weight as listed in the item's lore
     * @param fish the itemstack instance
     * @return the fish's weight
     */
    public double getFishWeight(ItemStack fish){
        if(fish.hasItemMeta()&&fish.getItemMeta().hasLore())
        for(Component lore : fish.getItemMeta().lore()){
            if(lore instanceof TextComponent){
                String loreString = ((TextComponent)lore).content();
                if(loreString.endsWith("oz.")){
                    String[] split = ChatColor.stripColor(loreString).split(" ");
                    return Double.parseDouble(split[0]);
                }
            }
        }
        return -1;
    }

    /**
     * Applies the quality to the lore of the itemstack
     * @param itemstack the itemstack of the fish
     * @param quality the quality to be added
     * @return the itemstack with the quality added to the lore
     */
    public ItemStack applyQuality(ItemStack itemstack, FishQuality quality){
        ItemMeta itemMeta = itemstack.getItemMeta();
        List<Component> lore = itemMeta.lore();
        lore.add(Component.text(qualityPrefix+quality.getQualityText()));
        itemMeta.lore(lore);
        itemstack.setItemMeta(itemMeta);
        return itemstack;
    }

    /**
     * Returns whether the fish has already been appraised
     * @param item the item of the fish
     * @return whether the item has been appraised
     */
    public boolean alreadyAppraised(ItemStack item) {
        if(item.hasItemMeta()&&item.getItemMeta().hasLore())
        for(Component lore : item.getItemMeta().lore()){
            if(lore instanceof TextComponent){
                String loreString = ((TextComponent)lore).content();
                if(loreString.startsWith(this.qualityPrefix)){
                    return true;
                }
            }
        }
        return false;
    }
}
