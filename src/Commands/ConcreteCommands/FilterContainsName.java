package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

/**
 * Конкретная команда показа элементов, значение поля name которых содержит заданную подстроку.
 */
public class FilterContainsName extends Command {
    private final CommandReceiver commandReceiver;

    public FilterContainsName (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) {
        if (args.length == 2) { commandReceiver.filter_contains_name(args[1]); }
        else { System.out.println("Некорректное количество аргументов. Для справки напишите help."); }
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда filter_contains_name. Синтаксис: filter_contains_name name – вывести элементы, значение поля name которых содержит заданную подстроку.");
    }
}