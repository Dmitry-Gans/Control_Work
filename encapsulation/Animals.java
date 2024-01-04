package encapsulation;

import encapsulation.animals.*;
import encapsulation.*;
import encapsulation.lib.Console;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static encapsulation.Main.ANIMALS_TYPES_PACKAGE;

@Getter
public enum Animals {
    Dog(1, "собака", List.of("сидеть", "лежать", "гулять")),
    Cat(2, "кошка", List.of("кушать")),
    Hamster(3, "хомяк", null),
    Horse(4, "лошадь", List.of("но", "трр")),
    Camel(5, "верблюд", null),
    Donkey(6, "осел", null);

    public static final List<IAnimal> data = new ArrayList<>(); // Список животных

    private final int id;
    private final String type;
    private final List<String> baseCommand = new ArrayList<>();
    Animals(int id, String type, List<String> baseCommand) {
        this.id = id;
        this.type = type;
        if (baseCommand != null) this.baseCommand.addAll(baseCommand);
    }

    public <T extends IAnimal>T add() {
        return this.add(null, List.of());
    }
    public <T extends IAnimal>T add(String name) {
        return this.add(name, List.of());
    }
    public <T extends IAnimal>T add(List<String> commands) {
        return this.add(null, commands);
    }

    public <T extends IAnimal>T add(String name, List<String> commands) {
        try {
            T animal = this.create();
            if (name != null && !name.isEmpty()) animal.setName(name);
            if (!this.type.isEmpty()) animal.setType(this.type);
            if (!this.baseCommand.isEmpty()) animal.addCommands(this.baseCommand);
            animal.addCommands(commands);
            Animals.data.add(animal);
            return animal;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void list() {
        for (IAnimal animal : Animals.data) {
            Console.out.log(animal);
        }
    }

    private <T extends IAnimal> T create() {
        try {
            return (T) this.classObj().getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    private <T extends Class<? extends IAnimal>> T classObj() {
        try {
            String packageName = String.format("%s.%s", this.getClass().getPackage().getName(), ANIMALS_TYPES_PACKAGE);
            String className = this.name();
            return (T) Class.forName(String.format("%s.%s", packageName, className));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}