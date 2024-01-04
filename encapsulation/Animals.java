package encapsulation;

abstract class Animals {
    public static String name;
    public static String birthDate;
    public static String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public static String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

}

class Cat extends Animals {
    public Cat(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public static String getType() {
        String type = "Кот";
        return type;
    }

    public static String getCommands() {
        return "Мяукает";
    }
}

class Dog extends Animals {
    public Dog(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public static String getType() {
        String type = "Собака";
        return type;
    }

    public static String getCommands() {
        return "Лает";
    }

}

class Hamster extends Animals {

    public Hamster(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public static String getType() {
        String type = "Хомячок";
        return type;
    }

    public static String getCommands() {
        return "Крутит колесо";
    }
}

class Horse extends Animals {

    public Horse(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public static String getType() {
        String type = "Лошадь";
        return type;
    }

    public static String getCommands() {
        return "Скачет";
    }
}

class Donkey extends Animals {

    public Donkey(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public static String getType() {
        String type = "Осел";
        return type;
    }

    public static String getCommands() {
        return "";
    }
}

class Camel extends Animals {

    public Camel(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public static String getType() {
        String type = "Верблюд";
        return type;
    }

    public static String getCommands() {
        return "Брр стой";
    }
}