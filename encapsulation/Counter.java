package encapsulation;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
class Counter implements AutoCloseable {
    private int count = 0;

    public void add() {
        this.count++;
    }

    @Override
    public void close() {
        // TODO реализовать логику проверки использования try-with-resources
        // если не в блоке - throw new Exception()
        // В данном примере реализована не будет
    }
}