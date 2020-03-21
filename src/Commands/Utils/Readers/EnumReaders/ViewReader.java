package Commands.Utils.Readers.EnumReaders;

import BasicClasses.View;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Считыватель вида.
 */
public class ViewReader {
    public static boolean checkExist(String toContains) {
        return Arrays.stream(View.values()).anyMatch((view) -> view.name().equals(toContains));
    }

    public static View read( boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите отделку из представленных(" + Arrays.asList(View.values()) + "): ");
        String toContains = in.nextLine().trim();

        if (canBeNull && toContains.equals("")) { return null; }
        else {
            while (!checkExist(toContains)) {
                System.out.print("Вы ввели несуществующее значение из представленных. Попробуйте снова: ");
                toContains = in.nextLine().trim();
                if (canBeNull && toContains.equals("")) { return null; }
            }
        }
        return Enum.valueOf(View.class, toContains);
    }
}
