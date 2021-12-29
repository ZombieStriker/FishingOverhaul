package me.zombie_striker.fishingoverhaul;

public enum FishQuality {
    POOR("Poor"),
    AVERAGE("Average"),
    EXCEPTIONAL("Exceptional")
        ;

    private final String qualityText;

    FishQuality(String qualityText){
        this.qualityText = qualityText;
    }

    /**
     * Returns the quality text for the fish quality
     * @returns the quality text
     */
    public String getQualityText() {
        return qualityText;
    }
}
