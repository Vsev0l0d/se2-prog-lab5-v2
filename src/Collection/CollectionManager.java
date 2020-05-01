package Collection;

import BasicClasses.Flat;
import java.time.LocalDate;
import java.util.*;

/**
 * Менеджер коллекцией. Описывает логику команд, выполняющих работу с коллекцией.
 */
public class CollectionManager {
    private static TreeSet<Flat> collection;
    private static java.time.LocalDate collectionCreationDate;
    private static List res = new ArrayList();

    public static void initSet() {
        if (collection == null) { collection = new TreeSet<>(); collectionCreationDate = java.time.LocalDate.now(); }
    }

    public static TreeSet<Flat> getCollection() {
        return collection;
    }

    public static void add(Flat flat) {
        collection.add(flat);
    }

    public static void addJsonObject(Flat flat) {
        flat.setId(IDGenerator.generateID(flat.getId()));
        collection.add(flat);
    }

    public static void getInfo() {
        System.out.println("Тип коллекции – " + collection.getClass().getName());
        System.out.println("Дата инициализации коллекции – " + collectionCreationDate);
        System.out.println("Количество элементов в коллекции – " + collection.size());
        System.out.println("_________________________________________________________\n");
    }

    public static void show() {
        collection.forEach(CollectionUtils::display);
    }

    public static void update(Flat flatToUpdate, Integer elementId) {
        collection.forEach(flat -> {
            if (flat.getId().equals(elementId)) {
                flat.setName(flatToUpdate.getName());
                flat.setCoordinates(flatToUpdate.getCoordinates());
                flat.setArea(flatToUpdate.getArea());
                flat.setNumberOfRooms(flatToUpdate.getNumberOfRooms());
                flat.setFurnish(flatToUpdate.getFurnish());
                flat.setView(flatToUpdate.getView());
                flat.setTransport(flatToUpdate.getTransport());
                flat.setHouse(flatToUpdate.getHouse());
            }
        });
    }

    public static void remove_by_id(Integer groupId) {
        collection.forEach(flat -> {
            if (flat.getId().equals(groupId)) { collection.remove(flat); }
        });
    }

    public static void clear() {
        collection.clear();
    }

    public static void remove_greater(Flat flat) {
        res.clear();
        collection.forEach(listFlat -> {
            if (listFlat.compareTo(flat) > 0) {
                appendToList(listFlat.getId());
                collection.remove(listFlat);
            }
        });

        if (res.isEmpty()) System.out.println("Таких элементов не найдено");
        System.out.println("Из коллекции удалены элементы с ID: " + res.toString().replaceAll("[\\[\\]]", ""));

    }

    public static void remove_lower(Flat flat) {
        res.clear();
        collection.forEach(listFlat -> {
            if (listFlat.compareTo(flat) < 0) {
                appendToList(listFlat.getId());
                collection.remove(listFlat);
            }
        });

        if (res.isEmpty()) System.out.println("Таких элементов не найдено");
        System.out.println("Из коллекции удалены элементы с ID: " + res.toString().replaceAll("[\\[\\]]", ""));
    }

    public static void print_field_ascending_number_of_rooms(Long numberOfRooms) {
        ArrayList<Long> arrayListNumbersOfRooms = new ArrayList<>();
        for (Object i : collection){
            if(((Flat)i).getNumberOfRooms() >= numberOfRooms) arrayListNumbersOfRooms.add(((Flat)i).getNumberOfRooms());
        }
        Collections.sort(arrayListNumbersOfRooms);
        arrayListNumbersOfRooms.forEach(System.out::println);
    }

    public static void filter_contains_name(String name) {
        if (!collection.isEmpty()){
            int counter = 0;
            for (Object i : collection){
                Flat flat = (Flat) i;
                if (flat.getName().contains(name)){
                    CollectionUtils.display(flat);
                    counter++;
                }
            }
            System.out.printf("Найдено %d элементов, название которых содержит '%s'. \n", counter, name);
        } else System.out.println("Коллекция не содержит элементов.");
    }

    public static boolean add_if_min(Flat flat) {
        try {
            if ((collection.first()).compareTo(flat) < 0) { return collection.add(flat); }
        } catch (NoSuchElementException e){
            return collection.add(flat);
        }
        return false;
    }

    public static void group_counting_by_creation_date(){
        if (collection.isEmpty()){
            System.out.println("Коллекция не содеждит элементов.");
            return;
        }
        HashMap<LocalDate, TreeSet<Flat>> groupMap = new HashMap<>();
        for (Flat i : collection){
            if (groupMap.get((i).getCreationDate()) == null){
                TreeSet<Flat> x = new TreeSet<>();
                x.add(i);
                groupMap.put((i).getCreationDate(), x);
            } else groupMap.get((i).getCreationDate()).add(i);
        }
        for (Map.Entry<LocalDate, TreeSet<Flat>> entry : groupMap.entrySet()){
            System.out.println("    Элементы созданные " + entry.getKey() + " :\n");
            entry.getValue().forEach(CollectionUtils::display);
        }
    }

    public static void appendToList(Object o){
        res.add(o);
    }
}
