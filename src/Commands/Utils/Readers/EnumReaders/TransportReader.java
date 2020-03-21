package Commands.Utils.Readers.EnumReaders;

import BasicClasses.Transport;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Считыватель транспорта.
 */
public class TransportReader {
    public static boolean checkExist(String toContains) {
        return Arrays.stream(Transport.values()).anyMatch((transport) -> transport.name().equals(toContains));
    }

    public static Transport read( boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите отделку из представленных(" + Arrays.asList(Transport.values()) + "): ");
        String toContains = in.nextLine().trim();

        if (canBeNull && toContains.equals("")) { return null; }
        else {
            while (!checkExist(toContains)) {
                System.out.print("Вы ввели несуществующее значение из представленных. Попробуйте снова: ");
                toContains = in.nextLine().trim();
                if (canBeNull && toContains.equals("")) { return null; }
            }
        }
        return Enum.valueOf(Transport.class, toContains);
    }
}
