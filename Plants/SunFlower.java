package Plants;

public class SunFlower extends Plants {
    private int type = 2;

    public SunFlower(int type, int x, int y) {
        super(1, x, y);
        super.health = 60;
    }
}
