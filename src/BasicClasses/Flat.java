package BasicClasses;

import Collection.IDGenerator;
import java.time.LocalDate;

/**
 * базовый класс, коллекция хранит объекты этого класса
 * реализует интерфейс  Comparable, чтобы обьекты этого класса автоматически сравнивались при добавлении в коллекцию
 */
public class Flat implements Comparable<Flat> {
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private long area;
    private Long numberOfRooms;
    private Furnish furnish;
    private View view;
    private Transport transport;
    private House house;

    public Flat(String name, Coordinates coordinates, long area, Long numberOfRooms, Furnish furnish, View view, Transport transport, House house) {
        this.id = IDGenerator.generateID();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.furnish = furnish;
        this.view = view;
        this.transport = transport;
        this.house = house;
    }

    public Flat(Integer id, String name, Coordinates coordinates, LocalDate creationDate, long area, Long numberOfRooms, Furnish furnish, View view, Transport transport, House house) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.furnish = furnish;
        this.view = view;
        this.transport = transport;
        this.house = house;
    }

    public long getArea(){
        return area;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Long getNumberOfRooms() {
        return numberOfRooms;
    }

    public Furnish getFurnish() {
        return furnish;
    }

    public View getView() {
        return view;
    }

    public Transport getTransport() {
        return transport;
    }

    public House getHouse() {
        return house;
    }

    public boolean setName(String name) {
        if (name.equals("") || name == null) return false;
        this.name = name;
        return true;
    }

    public boolean setCoordinates(Coordinates coordinates) {
        if (coordinates == null) return false;
        this.coordinates = coordinates;
        return true;
    }

    public boolean setArea(long area) {
        if (area < 0) return false;
        this.area = area;
        return  true;
    }

    public boolean setNumberOfRooms(Long numberOfRooms) {
        if (numberOfRooms < 0) return false;
        this.numberOfRooms = numberOfRooms;
        return true;
    }

    public boolean setFurnish(Furnish furnish) {
        if (furnish == null) return false;
        this.furnish = furnish;
        return true;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public int compareTo(Flat o) {
        return this.id - o.getId();
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
