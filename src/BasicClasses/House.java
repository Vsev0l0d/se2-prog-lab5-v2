package BasicClasses;

/**
 * базовый класс, дом
 */
public class House {
    private String name;
    private Long year;
    private Long numberOfFlatsOnFloor;

    public House(String name, Long year, Long numberOfFlatsOnFloor) {
        this.name = name;
        this.year = year;
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    public String getName() {
        return name;
    }

    public Long getYear() {
        return year;
    }

    public Long getNumberOfFlatsOnFloor() {
        return numberOfFlatsOnFloor;
    }
}