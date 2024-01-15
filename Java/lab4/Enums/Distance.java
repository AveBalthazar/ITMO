package Enums;

public enum Distance {
    VeryClose("Очень близко"),
    Near("недалеко"),
    Far("далеко");
    private final String distance;
    Distance(String distance) {
        this.distance = distance;
    }
    public String getName() {
        return distance;
    }
}
