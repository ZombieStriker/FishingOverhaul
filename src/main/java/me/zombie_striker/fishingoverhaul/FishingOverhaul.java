package me.zombie_striker.fishingoverhaul;

import net.kyori.adventure.text.Component;
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
    private String weightformat = "&3%weight% oz.";
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
     * @returns the fishing manager
     */
    public FishingManager getFishingmanager() {
        return fishingmanager;
    }

    /**
     * Returns the weight format for item lore
     *
     * @returns the weight format
     */
    public String getWeightformat() {
        return weightformat;
    }


    /**
     * Creates a new fish itemstack from an OverhauledFish instance
     *
     * @param fish the overhauledfish instanace
     * @returns the fish itemstack
     */
    public ItemStack getFish(OverhauledFish fish) {
        ItemStack itemStack = new ItemStack(fish.getFishMaterial());
        ItemMeta im = itemStack.getItemMeta();
        im.displayName(Component.text(fish.getDisplayName()));
        List<Component> lore = new LinkedList<>();
        lore.add(Component.text(fish.getLoreString()));
        double weight = ((fish.getMaxoz() - fish.getMinoz()) * Math.random()) + fish.getMinoz();
        String weightstring = ChatColor.translateAlternateColorCodes('&', getWeightformat().replaceAll("%weight%", decimalFormat.format(weight)));
        lore.add(Component.text(weightstring));
        im.lore(lore);
        itemStack.setItemMeta(im);
        return itemStack;
    }
}
