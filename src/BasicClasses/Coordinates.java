package BasicClasses;

/**
 * базовый класс, хранит координаты
 */
public class Coordinates {
    private Long x;
    private long y;

    public Coordinates(Long x, long y) {
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public long getY() {
        return y;
    }
}
