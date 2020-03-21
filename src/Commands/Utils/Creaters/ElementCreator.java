package Commands.Utils.Creaters;

import BasicClasses.*;
import Commands.Utils.Readers.EnumReaders.*;
import Commands.Utils.Readers.PrimitiveAndReferenceReaders.*;

import java.util.ArrayList;

/**
 * Класс содержащий методы для создания квартиры.
 */
public class ElementCreator {
    public static Flat createFlat() {
        String name = StringReader.read("Введите имя квартиры: ", false);
        Long x = RefLongReader.read("Введите подъезд: ", false, 0L, "MIN");
        long y = PrimitiveLongReader.read("Введите этаж: ", 0, "MIN");
        long area = PrimitiveLongReader.read("Введите площадь квартиры (в м^2): ", 0, "MIN");
        Long numberOfRooms = RefLongReader.read("Введите количество комнат в квартире: ", false, 0L, "MIN");
        Furnish furnish = FurnishReader.read(false);
        View view = ViewReader.read(true);
        Transport transport = TransportReader.read(true);
        return new Flat(name, new Coordinates(x,y), area, numberOfRooms, furnish, view, transport, createHouse());
    }

    public static House createHouse() {
        String name = StringReader.read("Введите название дома: ", false);
        Long yearHouse = RefLongReader.read("Введите год построки дома: ", false, 0L, "MIN");
        Long numberOfFlatsOnFloor = RefLongReader.read("Введите количество квартир на этаже: ", false, 0L, "MIN");
        return new House(name, yearHouse, numberOfFlatsOnFloor);
    }

    public static Flat createScriptFlat(ArrayList<String> parameters) {
        if (validateArray(parameters)) {
            View view = null;
            Transport transport = null;
            if (!parameters.get(6).isEmpty()) { view = View.valueOf(parameters.get(4)); }
            if (!parameters.get(7).isEmpty()) { transport = Transport.valueOf(parameters.get(4)); }
            return new Flat(parameters.get(0),
                    new Coordinates(Long.parseLong(parameters.get(1)), Long.parseLong(parameters.get(2))),
                    Long.parseLong(parameters.get(3)),
                    Long.parseLong(parameters.get(4)),
                    Furnish.valueOf(parameters.get(5)),
                    view,
                    transport,
                    new House(parameters.get(8), Long.parseLong(parameters.get(9)), Long.parseLong(parameters.get(10))));
        } else { System.out.println("Один из параметров не соответствует требованиям."); }

        return null;
    }

    private static boolean validateArray(ArrayList<String> parameters) {
        try {
            return !parameters.get(0).isEmpty()
                    && Long.parseLong(parameters.get(1)) > 0
                    && Long.parseLong(parameters.get(2)) > 0
                    && Long.parseLong(parameters.get(3)) > 0
                    && Long.parseLong(parameters.get(4)) > 0
                    && !parameters.get(5).isEmpty()
                    && (ViewReader.checkExist(parameters.get(6)) || parameters.get(6).isEmpty())
                    && (TransportReader.checkExist(parameters.get(7)) || parameters.get(7).isEmpty())
                    && !parameters.get(8).isEmpty()
                    && Long.parseLong(parameters.get(9)) > 0
                    && Long.parseLong(parameters.get(10)) > 0;
        } catch (NumberFormatException ex) { return false;}
    }
}
