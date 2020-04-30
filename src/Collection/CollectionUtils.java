package Collection;

import BasicClasses.Flat;

/**
 * Класс, содержащий утилиты для работы с коллекцией.
 */
public class CollectionUtils {
    public static boolean checkExist(Integer ID) {
        for (Flat flat:CollectionManager.getCollection()) {
            if (flat.getId().equals(ID)){
                return true;
            }
        }
        return false;
    }

    static void display(Flat flat) {
        System.out.println("ID элемента коллекции – " + flat.getId());
        System.out.println("Название – " + flat.getName());
        System.out.println("Подъезд – " + flat.getCoordinates().getX());
        System.out.println("Этаж – " + flat.getCoordinates().getY());
        System.out.println("Дата создания элемента – " + flat.getCreationDate());
        System.out.println("Площадь – " + flat.getArea());
        System.out.println("Количество комнат – " + flat.getNumberOfRooms());
        System.out.println("Отделка – " + toStringWithNull(flat.getFurnish()));
        System.out.println("Вид – " + toStringWithNull(flat.getView()));
        System.out.println("Транспорт – " + toStringWithNull(flat.getTransport()));
        System.out.println("Название дома: " + flat.getHouse().getName());
        System.out.println("Год построки дома – " + flat.getHouse().getYear());
        System.out.println("Количество квартир на этаже – " + flat.getHouse().getNumberOfFlatsOnFloor());
        System.out.println("_________________________________________________________\n");
    }

    private static String toStringWithNull(Object o){
        if (o == null) return "отсутствует";
        return o.toString();
    }

}
