package encapsulation;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws ParseException, IOException {
        show_menu();
    }

    static void show_menu() throws IOException {
        System.out.println("Выберете необходимое действие:\n" +
                "1) Вывести общий реестр питомцев на экран.\n" +
                "2) Добавить питомца.\n" +
                "3) Узнать список команд, которые может выполнять питомец.\n" +
                "4) Научить питомца новым командам.\n");
        Scanner scanner = new Scanner(System.in, "UTF-8");
        int menu = scanner.nextInt();
        switch (menu) {
            case 1:
                AnimalRegistry.printRegistry();
                show_menu();
                break;
            case 2:
                System.out.println("Как зовут питомца?");
                String name = scanner.next();
                System.out.println("Дата рождения? (день-месяц-год)");
                String birthDate = scanner.next();
                System.out.println("Какой вид этого питомца?:\n" +
                        "1 - Кот/Кошка.\n" +
                        "2 - Собака.\n" +
                        "3 - Хомяк.\n" +
                        "4 - Лошадь.\n" +
                        "5 - Осел.\n" +
                        "6 - Верблюд.\n");
                int typeForAdd = scanner.nextInt();
                try {
                    Counter counter = new Counter();
                    switch (typeForAdd) {
                        case 1:
                            Cat cat = new Cat(name, birthDate);
                            AnimalRegistry.addAnimal(name, Cat.getType(), birthDate, Cat.getCommands());
                            break;
                        case 2:
                            Dog dog = new Dog(name, birthDate);
                            AnimalRegistry.addAnimal(name, Dog.getType(), birthDate, Dog.getCommands());
                            break;
                        case 3:
                            Hamster hamster = new Hamster(name, birthDate);
                            AnimalRegistry.addAnimal(name, Hamster.getType(), birthDate, Hamster.getCommands());
                            break;
                        case 4:
                            Horse horse = new Horse(name, birthDate);
                            AnimalRegistry.addAnimal(name, Horse.getType(), birthDate, Horse.getCommands());
                            break;
                        case 5:
                        Donkey donkey = new Donkey(name, birthDate);
                            AnimalRegistry.addAnimal(name, Donkey.getType(), birthDate, Donkey.getCommands());
                            break;
                        case 6:
                        Camel camel = new Camel(name, birthDate);
                            AnimalRegistry.addAnimal(name, Camel.getType(), birthDate, Camel.getCommands());
                            break;
                        default:
                            show_menu();
                            break;
                    }
                    show_menu();
                    break;
                } finally {
                    throw new IOException("Счетчик не закрыт.");
                }
            case 3:
                System.out.println("Назовите имя питомца, набор команд которого хотите узнать?");
                String nameForCommands = scanner.next();
                AnimalRegistry.findCommandsByName(nameForCommands);
                show_menu();
                break;
            case 4:
                System.out.println("Назовите имя питомца, которого хотите научить новой команде?");
                String nameForAddCommands = scanner.next();
                System.out.println("Какой команде хотите научить?");
                String commandsForAdd = scanner.next();
                AnimalRegistry.addCommandsByName(nameForAddCommands, commandsForAdd);
                show_menu();
                break;
            default:
                System.out.println("Вы ввели неправильную команду, перезапустите реестр.");
                break;
        }
    }
}

class Counter implements AutoCloseable {
    int temp;
    boolean closed = false;
    public Counter() {
        temp = 0;
    }

    public void add(String name, String birthDate, Integer typeForAdd) throws IOException {
        if (isClose(name, birthDate, typeForAdd)) {
            throw new IOException("Счетчик закрыт.");
        }
        temp++;
    }

    public boolean isClose(String name, String birthDate, Integer typeForAdd) {
        if (name != null && birthDate != null && (typeForAdd == 1 | typeForAdd == 2 | typeForAdd == 3)) {
            close();
        }
        if (closed) {
            return true;
        } else return false;
    }

    @Override
    public void close() {
        closed = true;
    }
}