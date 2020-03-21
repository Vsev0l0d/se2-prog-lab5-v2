package Commands.Utils.Readers.EnumReaders;

import BasicClasses.Furnish;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Считыватель отделки.
 */
public class FurnishReader {
    public static boolean checkExist(String toContains) {
        return Arrays.stream(Furnish.values()).anyMatch((furnish) -> furnish.name().equals(toContains));
    }

    public static Furnish read( boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите отделку из представленных(" + Arrays.asList(Furnish.values()) + "): ");
        String toContains = in.nextLine().trim();

        if (canBeNull && toContains.equals("")) { return null; }
        else {
            while (!checkExist(toContains)) {
                System.out.print("Вы ввели несуществующее значение из представленных. Попробуйте снова: ");
                toContains = in.nextLine().trim();
                if (canBeNull && toContains.equals("")) { return null; }
            }
        }
        return Enum.valueOf(Furnish.class, toContains);
    }
}
