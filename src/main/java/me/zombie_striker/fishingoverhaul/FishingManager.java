package me.zombie_striker.fishingoverhaul;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class FishingManager {

    private final List<OverhauledFish> fishes = new LinkedList<>();

    private final File fishingymlfile;
    private final FileConfiguration fishingyml;

    public FishingManager(FishingOverhaul fishingOverhaul) {
        if(!fishingOverhaul.getDataFolder().exists())
            fishingOverhaul.getDataFolder().mkdirs();
        this.fishingymlfile = new File(fishingOverhaul.getDataFolder(),"fishing.yml");
        //Checks if the fishing.yml exists, and if not, creates it.
        if(!fishingymlfile.exists()){
            try {
                fishingymlfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fishingyml = YamlConfiguration.loadConfiguration(fishingymlfile);
            createFishingList();
        }else{
            fishingyml = YamlConfiguration.loadConfiguration(fishingymlfile);
            reloadFishingList();
        }
    }

    /**
     * Populates the fishes array and saves all the contents to the fishing.yml file
     */
    private void createFishingList() {
        fishes.add(new OverhauledFish(Material.COD,1,"&fAtlantic Cod", "&6Lives in the colder waters and deeper sea regions of the Atlantic.",1,16*11,16*26));
        fishes.add(new OverhauledFish(Material.COD,2,"&fPacific Cod", "&6Another common fish.",1,16*11,16*26));
        fishes.add(new OverhauledFish(Material.COD,3,"&fAlewife", "&6Not to be confused with an Ale Wife.",1,16*11,16*26));
        fishes.add(new OverhauledFish(Material.COD,4,"&fBarracuda", "&6A fearsome creature with a ferocious attitude.",0.9,16*11,16*26));
        fishes.add(new OverhauledFish(Material.COD,5,"&fBlackfin Tuna", "&6One of the smallest tuna.",0.8,16*26,16*46));
        fishes.add(new OverhauledFish(Material.COD,6,"&fBlack Marlin", "&6Found in the Indian and Pacific Oceans.",0.25,16*1100,16*1650));
        fishes.add(new OverhauledFish(Material.COD,7,"&fBlobfish", "&6.A species of fish that lives in very deep water.",1,16*11,16*26));
        fishes.add(new OverhauledFish(Material.PUFFERFISH,1,"&fBlowfish", "&6Also known as the pufferfish.",1,16*11,16*26));
        fishes.add(new OverhauledFish(Material.SALMON,1,"&fMochokidae", "&6A catfish known as the squeakers that originates from Africa.",1,16*11,16*26));
        fishes.add(new OverhauledFish(Material.COD,8,"&fBull Trout", "&6Also known as the Dolly Varden.",0.2,16*24,16*32));
        fishes.add(new OverhauledFish(Material.COD,9,"&fChubsucker", "&6Kinda a dumb fish.",0.25,16*11,16*26));
        fishes.add(new OverhauledFish(Material.COD,10,"&fCisco", "&6Not to be confused with the cybersecurity company.",1,16*11,16*26));
        fishes.add(new OverhauledFish(Material.COD,11,"&fCombfish", "&6A common fish that is is meticulous about their hair.",0.25,16*11,16*26));
        fishes.add(new OverhauledFish(Material.COD,12,"&frDeath Valley pupfish", "&6An endangered species found in Death Valley",0.1,16*1.1,16*2.6));
        fishes.add(new OverhauledFish(Material.COD,13,"&fDevil Fish", "&6A species of ray that is currently endangered.",1,16*11,16*26));
        fishes.add(new OverhauledFish(Material.COD,14,"&fDog Shark", "&6Woof woof.",0.35,16*11,16*26));
        fishes.add(new OverhauledFish(Material.SALMON,2,"&fCatfish", "&6Meow meow, I've been catfished!",0.5,16*11,16*26));
        fishes.add(new OverhauledFish(Material.TROPICAL_FISH,1,"&fEmperor Angelfish", "&6A species with generally a stable population,",1.3,16*11,16*26));
        fishes.add(new OverhauledFish(Material.COD,15, "&fEel Cod", "&6Part eel, part cod.",0.5,16*11,16*26));

        for(OverhauledFish fish : fishes){
            fishingyml.set(fish.getDisplayName()+".material",fish.getFishMaterial().name());
            fishingyml.set(fish.getDisplayName()+".lore",fish.getLoreString());
            fishingyml.set(fish.getDisplayName()+".maxoz",fish.getMaxOZ());
            fishingyml.set(fish.getDisplayName()+".minoz",fish.getMinOZ());
            fishingyml.set(fish.getDisplayName()+".rarity",fish.getRarity());
            fishingyml.set(fish.getDisplayName()+".custommodeldata",fish.getCustomModelData());
        }
        try {
            fishingyml.save(fishingymlfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads all of the fishes registered in the fishing.yml
     */
    public void reloadFishingList(){
        for(String fishname : fishingyml.getKeys(false)){
            Material material = Material.matchMaterial(Objects.requireNonNull(fishingyml.getString(fishname + ".material")));
            String lore = fishingyml.getString(fishname+".lore");
            double rarity = fishingyml.getDouble(fishname+".rarity");
            double maxoz = fishingyml.getDouble(fishname+".maxoz");
            double minoz = fishingyml.getDouble(fishname+".minoz");
            int custommodeldata = fishingyml.getInt(fishname+".custommodeldata");
            fishes.add(new OverhauledFish(material,custommodeldata, fishname,lore,rarity,minoz,maxoz));
        }
    }

    /**
     * Returns a random fish based on all the fishes rarity
     * @return a random fish
     */
    public OverhauledFish getRandomOverhauledFish(){
        double totalRarity = 0;
        for(OverhauledFish fish : fishes){
            totalRarity+=fish.getRarity();
        }
        double randomRarity = Math.random()*totalRarity;
        for(OverhauledFish fish : fishes){
            randomRarity-=fish.getRarity();
            if(randomRarity <= 0){
                return fish;
            }
        }
        return null;
    }

    /**
     * Returns all the available fishes registered byt the plugin
     * @return all fishes
     */
    public List<OverhauledFish> getFish() {
    return fishes;
    }
}
