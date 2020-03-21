package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

/**
 * Конкретная команда показа содержания коллекции, отсортированной по полю creationDate.
 */
public class GroupCountingByCreationDate extends Command {
    private final CommandReceiver commandReceiver;

    public GroupCountingByCreationDate (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде group_counting_by_creation_date.");
        }
        commandReceiver.group_counting_by_creation_date();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда group_counting_by_creation_date – сгруппировать элементы коллекции по значению поля creationDate, вывести количество элементов в каждой группе.");
    }
}