package encapsulation.animals;

import java.util.List;

public interface IAnimal {
    String getName();
    String getType();
    List<String> getCommands();

    void setName(String name);
    void setType(String type);
    void setCommands(List<String> commands);

    void addCommand(String command);
    void addCommands(List<String> command);
}
