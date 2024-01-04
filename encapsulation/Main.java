package encapsulation;

import encapsulation.animals.types.*;
import java.util.List;

public class Main {
    public static final String ANIMALS_TYPES_PACKAGE = "animals.types";

    public static void main(String[] args) {
        // TODO Перенести в меню
        // Animals.Dog.add("Шарик");
        // Horse horse = Animals.Horse.add(List.of("галоп"));
        // horse.addCommand("test");
        // Animals.list();
        Menu.view();
    }
}