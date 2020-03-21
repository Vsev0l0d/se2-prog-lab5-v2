package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

/**
 * Конкретная команда показа содержания коллекции, отсортированной по полю numberOfRooms.
 */
public class PrintFieldAscendingNumberOfRooms extends Command {
    private final CommandReceiver commandReceiver;

    public PrintFieldAscendingNumberOfRooms (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) {
        if (args.length == 2) { commandReceiver.print_field_ascending_number_of_rooms(args[1]); }
        else { System.out.println("Некорректное количество аргументов. Для справки напишите help."); }
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда print_field_ascending_number_of_rooms. Синтаксис: print_field_ascending_number_of_rooms numberOfRooms – вывести значения поля numberOfRooms в порядке возрастания.");
    }
}