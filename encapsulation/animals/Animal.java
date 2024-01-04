package encapsulation.animals;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Animal implements IAnimal {
    private String name = "без имени";
    private String type = "не указан";
    private List<String> commands = new ArrayList<>();

    public void addCommand(String command) {
        this.commands.add(command);
    }
    public void addCommands(List<String> command) {
        this.commands.addAll(command);
    }

    @Override
    public String toString() {
        return String.format(
                "%s [Кличка: %s, Знает команды: %s]",
                this.type,
                this.name,
                this.commands
        );
        // return String.format(
        //         "%s{type=%s, name=%s, commands=%s}",
        //         this.getClass().getSimpleName(),
        //         this.type,
        //         this.name,
        //         this.commands
        // );
    }
}