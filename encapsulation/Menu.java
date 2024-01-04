package encapsulation;

import encapsulation.animals.IAnimal;
import encapsulation.lib.Console;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    protected static final Console console = Console.out;
    protected static final Scanner in = new Scanner(System.in);
    protected static List<Menu.Command> commands = new ArrayList<>(List.of(
            new Menu.Command(1, "Вывести список животных", "animalsShow"),
            new Menu.Command(2, "Добавить животное", "animalsAdd"),
            new Menu.Command(3, "Вывести список команд", "commandsShow"),
            new Menu.Command(4, "Обучить новым командам", "commandsAdd"),
            new Menu.Command(9, "Выход", "exit")
    ));

    public static void view() {
        Command command;
        while (true) {
            if ((command = Menu.getCommand()) == null) continue;
            if (command.func.equals("exit")) break;
            try {
                Menu.class.getDeclaredMethod(command.func).invoke(Menu.class);
            } catch (NoSuchMethodException e) {
                console.err("#Error - Команда не найдена");
            } catch (Exception e) {
                console.err("#Error - %s\n", e.getMessage());
            }
            console.log("");
        }
    }

    // Service
    protected static String getData(String message) {
        console.color("g").log("%s: ", message);
        return in.nextLine();
    }
    protected static Command getCommand() {
        Integer commandId = null;
        Command useCommand = null;
        for (Command command : commands) console.color("b").log(command);
        commandId = Integer.parseInt(Menu.getData("ID команды"));
        for (Command command : commands) {
            if (command.id != commandId) continue;
            useCommand = command;
            break;
        }
        return useCommand;
    }

    // Commands
    protected static void animalsShow() {
        for (IAnimal item : Animals.data) {
            console.color("y").log(item);
        }
    }
    protected static void animalsAdd() {
        try (Counter counter = new Counter()) {
            console.log("Какое животное добавляем:");
            for (Animals enumConstant : Animals.class.getEnumConstants()) {
                console.color("b").log("\t%2d - %s\n", enumConstant.getId(), enumConstant.getType());
            }
            int id = Integer.parseInt(Menu.getData("\tID команды"));
            Animals animal = null;
            for (Animals enumConstant : Animals.class.getEnumConstants()) {
                if (enumConstant.getId() == id) {
                    animal = enumConstant;
                    break;
                }
            }
            if (animal == null) throw new Exception("Животное не найдено");
            console.log("%s: ", "Введите кличку");
            String name;
            if ((name = in.nextLine()).isEmpty()) Animals.class.getDeclaredMethod("add").invoke(animal);
            else Animals.class.getDeclaredMethod("add", String.class).invoke(animal, name);
            counter.add();
        } catch (Exception e) {
            console.err(e.getMessage());
        }
    }
    protected static void commandsAdd() {
        // console.log("TEST ADD COMMANDS");
        // TODO дописать добавление команд с выбором животного из списка
        // В данном примере реализована не будет
    }
    protected static void commandsShow() {
        // console.log("TEST SHOW COMMANDS");
        // TODO дописать вывод команд с выбором животного из списка
        // В данном примере реализована не будет
    }

    @AllArgsConstructor
    static class Command {
        private final int id;
        private final String name;
        private final String func;

        @Override
        public String toString() {
            return String.format("%2d - %s", this.id, this.name);
        }
    }
}