import Collection.CollectionManager;
import Commands.CommandInvoker;
import Commands.CommandReceiver;
import Commands.ConcreteCommands.*;

import java.io.IOException;
import java.util.Scanner;

/**
 * Класс управления и регистрацией консолью.
 */
class ConsoleManager {
    void startInteractiveMode() throws IOException {
        CommandInvoker commandInvoker = new CommandInvoker();
        CommandReceiver commandReceiver = new CommandReceiver(commandInvoker);
        CollectionManager.initSet();

        commandInvoker.register("help", new Help(commandReceiver));
        commandInvoker.register("add", new Add(commandReceiver));
        commandInvoker.register("info", new Info(commandReceiver));
        commandInvoker.register("show", new Show(commandReceiver));
        commandInvoker.register("update", new Update(commandReceiver));
        commandInvoker.register("remove_by_id", new RemoveByID(commandReceiver));
        commandInvoker.register("clear", new Clear(commandReceiver));
        commandInvoker.register("exit", new Exit(commandReceiver));
        commandInvoker.register("add_if_min", new AddIfMin(commandReceiver));
        commandInvoker.register("remove_greater", new RemoveGreater(commandReceiver));
        commandInvoker.register("remove_lower", new RemoveLower(commandReceiver));
        commandInvoker.register("group_counting_by_creation_date", new GroupCountingByCreationDate(commandReceiver));
        commandInvoker.register("filter_contains_name", new FilterContainsName(commandReceiver));
        commandInvoker.register("print_field_ascending_number_of_rooms", new PrintFieldAscendingNumberOfRooms(commandReceiver));
        commandInvoker.register("execute_script", new ExecuteScript(commandReceiver));
        commandInvoker.register("save", new Save(commandReceiver));


        try(Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                commandInvoker.executeCommand(scanner.nextLine().trim().split(" "));
            }
        }
    }
}
