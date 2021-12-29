package me.zombie_striker.fishingoverhaul;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class FishingListener implements Listener {

    private final FishingOverhaul main;


    public FishingListener(FishingOverhaul fishingOverhaul) {
        this.main = fishingOverhaul;
    }

    @EventHandler
    public void onFish(PlayerFishEvent event){
        if(event.getState() == PlayerFishEvent.State.CAUGHT_FISH){
            OverhauledFish fish = main.getFishingmanager().getRandomOverhauledFish();
            ItemStack item = main.getFish(fish);
            if(event.getCaught().getType()== EntityType.DROPPED_ITEM){
                ((Item)event.getCaught()).setItemStack(item);
            }
        }
    }
}
