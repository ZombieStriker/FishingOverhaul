package me.zombie_striker.fishingoverhaul;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FishCommand implements CommandExecutor, TabCompleter {

    private FishingOverhaul main;

    public FishCommand(FishingOverhaul fishingOverhaul) {
    this.main = fishingOverhaul;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED+"You must be a player to use this command!");
            return true;
        }
        Player player = (Player) sender;
        //The help command if the player does not provide an arg or runs /fish help.
        if(args.length == 0 || args[0].equalsIgnoreCase("help")){
            sender.sendMessage("---===[Fish Commands]===---");
            sender.sendMessage("/fish help : Sends the help command");
            sender.sendMessage("/fish appraise : appraises the fish in your main hand.");
            return true;
        }

        //The appraise command
        if(args[0].equalsIgnoreCase("appraise")){
            ItemStack item = player.getInventory().getItemInMainHand();
            for(OverhauledFish fish : main.getFishingmanager().getFish()){
                
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
