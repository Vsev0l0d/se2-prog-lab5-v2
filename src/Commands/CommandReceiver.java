package Commands;

import BasicClasses.Flat;
import Collection.CollectionManager;
import Collection.CollectionUtils;
import Commands.ConcreteCommands.ExecuteScript;
import Commands.Utils.Creaters.ElementCreator;
import Commands.Utils.JSON.ParserJson;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Ресивер(получатель), описывает основную логику команд, при надобности делегирует ее консольному менеджеру.
 */
public class CommandReceiver {
    private final CommandInvoker commandInvoker;

    public CommandReceiver(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }

    public void help() {
        commandInvoker.getCommandMap().forEach((name, command) -> command.writeInfo());
    }

    public void info() { // добавить класс
        CollectionManager.getInfo();
    }

    public void show() {
        CollectionManager.show();
    }

    public void add() {
        CollectionManager.add(ElementCreator.createFlat());
        System.out.println("Элемент добавлен в коллекцию.");
    }

    /**
     *
     * @param ID - апдейт элемента по ID.
     */
    public void update(String ID) {
        Integer groupId;
        try {
            groupId = Integer.parseInt(ID);
            if (CollectionUtils.checkExist(groupId)) { CollectionManager.update(ElementCreator.createFlat(), groupId); }
            else {System.out.println("Элемента с таким ID нет в коллекции.");}
        } catch (NumberFormatException e) {
            System.out.println("Команда не выполнена. Вы ввели некорректный аргумент.");
        }
    }

    /**
     *
     * @param ID - удаление по ID.
     */
    public void remove_by_id(String ID) {
        Integer groupId;
        try {
            groupId = Integer.parseInt(ID);
            if (CollectionUtils.checkExist(groupId)) {
                CollectionManager.remove_by_id(groupId);
                System.out.println("Элемент с ID " + groupId + " успешно удален из коллекции.");
            } else {System.out.println("Элемента с таким ID нет в коллекции.");}
        } catch (NumberFormatException e) {
            System.out.println("Команда не выполнена. Вы ввели некорректный аргумент.");
        }
    }

    public void clear() {
        CollectionManager.clear();
        System.out.println("Коллекция успешно очищена.");
    }

    public void exit() {
        System.out.println("Завершение работы программы.");
        System.exit(0);
    }

    public void remove_greater() {
        CollectionManager.remove_greater(ElementCreator.createFlat());
    }

    public void remove_lower() {
        CollectionManager.remove_lower(ElementCreator.createFlat());
    }

    /**
     *
     * @param path - Путь до файла, который будем считывать.
     */
    public void executeScript(String path) {
        String line;
        String command;
        ArrayList<String> parameters = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(path)), StandardCharsets.UTF_8))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] arg = line.trim().split(" ");
                if (arg[0].equals("update")) {
                    if (arg.length < 12){ System.out.println("Не хватает параметров для создания объекта."); break; }
                    for (int i = 2; i <= 12; i++) { parameters.add(arg[i]); }
                    Flat flat = ElementCreator.createScriptFlat(parameters);
                    CollectionManager.update(flat, Integer.parseInt(arg[1]));
                }
                if (arg[0].matches("add|remove_lower|remove_greater|add_if_min")) {
                    command = arg[0];
                    if (arg.length < 11){ System.out.println("Не хватает параметров для создания объекта."); break; }
                    for (int i = 1; i <= 11; i++) { parameters.add(arg[i]); }
                    Flat flat = ElementCreator.createScriptFlat(parameters);
                    switch (command) {
                        case "add":
                            CollectionManager.add(flat);
                            break;
                        case "remove_greater":
                            CollectionManager.remove_greater(flat);
                            break;
                        case "remove_lower":
                            CollectionManager.remove_lower(flat);
                            break;
                        case "add_if_min":
                            CollectionManager.add_if_min(flat);
                            break;
                    }
                } else if (line.split(" ")[0].equals("execute_script")
                        && line.split(" ")[1].equals(ExecuteScript.getPath())) { System.out.println("Пресечена попытка рекурсивного вызова скрипта."); }
                else { commandInvoker.executeCommand(line.split(" ")); }
            }
        } catch (IOException e) {
            System.out.println("Ошибка! " + e.getMessage());
        }
    }

    /**
     * Сохранение коллекции
     */
    public void save() {
        ParserJson.collectionToJson();
    }

    public void add_if_min() {
        if (CollectionManager.add_if_min(ElementCreator.createFlat())){
            System.out.println("Элемент добавлен в коллекцию.");
        } else {System.out.println("Элемент не добавлен в коллекцию. Значение этого элемента больше, чем у наименьшего элемента коллекции.");}
    }

    public void filter_contains_name(String name) {
        CollectionManager.filter_contains_name(name);
    }

    public void group_counting_by_creation_date() {
        CollectionManager.group_counting_by_creation_date();
    }

    public void print_field_ascending_number_of_rooms(String stringNumberOfRooms){
        Long numberOfRooms;
        try {
            numberOfRooms = Long.parseLong(stringNumberOfRooms);
            CollectionManager.print_field_ascending_number_of_rooms(numberOfRooms);
        } catch (NumberFormatException e) {
            System.out.println("Команда не выполнена. Вы ввели некорректный аргумент.");
        }
    }
}
