package me.zombie_striker.fishingoverhaul;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

public class FishCommand implements CommandExecutor, TabCompleter {

    private final FishingOverhaul main;

    public FishCommand(FishingOverhaul fishingOverhaul) {
        this.main = fishingOverhaul;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
            return true;
        }
        //The help command if the player does not provide an arg or runs /fish help.
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage("---===[Fish Commands]===---");
            sender.sendMessage("/fish help : Sends the help command");
            sender.sendMessage("/fish appraise : appraises the fish in your main hand.");
            return true;
        }

        //The appraise command
        if (args[0].equalsIgnoreCase("appraise")) {
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item == null) {
                sender.sendMessage(ChatColor.RED + "You must hold the fish in your main hand!");
                return true;
            }
            if (main.alreadyAppraised(item)) {
                sender.sendMessage(ChatColor.RED + "This fish has already been appraised!");
                return true;
            }
            //Loops through all the fishes, finds the fish, and gives it the quality lore
            for (OverhauledFish fish : main.getFishingmanager().getFish()) {
                if (fish.getFishMaterial() == item.getType()) {
                    if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                        if(item.getItemMeta().displayName() instanceof TextComponent textComponent && textComponent.content().equals(fish.getDisplayName())) {
                            double weight = main.getFishWeight(item);
                            FishQuality quality;
                            if (weight >= fish.getMinOZExceptional()) {
                                quality = FishQuality.EXCEPTIONAL;
                            } else if (weight >= fish.getMinOZAverage()) {
                                quality = FishQuality.AVERAGE;
                            } else {
                                quality = FishQuality.POOR;
                            }
                            ItemStack newItemStack = main.applyQuality(item, quality);
                            player.getInventory().setItemInMainHand(newItemStack);
                            player.sendMessage(ChatColor.GRAY+"Fish appraised to be of "+quality.getQualityText()+" quality!");
                            return true;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length == 1){
            List<String> tabs = new LinkedList<>();
            appendIfPossibleSolution(tabs,args[0],"help");
            appendIfPossibleSolution(tabs,args[0],"appraise");
            return tabs;
        }
        return null;
    }

    /**
     * Adds a solution to the list if the argument is the beginning of the solution.
     * @param tabs
     * @param argument
     * @param solution
     */
    public void appendIfPossibleSolution(List<String> tabs, String argument, String solution){
        if(solution.startsWith(argument.toLowerCase()))
            tabs.add(solution);
    }
}
