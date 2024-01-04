package encapsulation;

import java.io.*;
import java.util.*;


public class AnimalRegistry {
    private static Map<String, String> AnimalRegistry = new HashMap<>();
    static File file = new File("AnimalRegistry.xls");

    public static void printRegistry() throws IOException {

        try (Reader r = new InputStreamReader(new FileInputStream(file), "UTF-8")) {
            int i;
            while ((i = r.read()) != -1) {

                System.out.print((char) i);
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    public static void addAnimal(String name, String type, String birthDate, String commands) throws IOException {
        try (final Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"))) {
            if (file.length() == 0) {
                writer.write("Имя\t");
                writer.write("Тип\t");
                writer.write("Дата рождения\t");
                writer.write("Команды\n");
            }
            writer.write(name + "\t");
            writer.write(type + "\t");
            writer.write(birthDate + "\t");
            writer.write(commands + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Возникла ошибка во время записи, проверьте данные.");
        }
    }

    public static void findCommandsByName(String name) throws IOException {
        try {
            Reader r = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader reader = new BufferedReader(r);
            String line = reader.readLine();
            String[] commands = new String[]{""};
            while (line != null) {
                if (line.contains(name)) {
                    commands = line.split("\t");
                    System.out.println(commands[3]);
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    //TODO
    public static void addCommandsByName(String name, String commandsForAdd) throws IOException {
        try {
            Reader r = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader reader = new BufferedReader(r);
            String line = reader.readLine();
            File tempFile = new File("temp.xls");
            while (line != null) {
                if (line.contains(name)) {
                    String[] temp = new String[]{""};
                    temp = line.split("\t");
                    temp[3] = temp[3] + " " + commandsForAdd;
                    try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"))) {
                        writer.write(temp[0] + "\t");
                        writer.write(temp[1] + "\t");
                        writer.write(temp[2] + "\t");
                        writer.write(temp[3] + "\n");
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("Возникла ошибка во время записи, проверьте данные.");
                    }
                    break;
                }
                line = reader.readLine();
            }
            String temp1;
            Reader r1 = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader reader1 = new BufferedReader(r1);
            while ((temp1 = reader1.readLine()) != null) {
                if (!temp1.equals(line)) {
                    String[] temp = new String[]{""};
                    temp = temp1.split("\t");
                    try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile, true), "UTF-8"))) {
                        writer.write(temp[0] + "\t");
                        writer.write(temp[1] + "\t");
                        writer.write(temp[2] + "\t");
                        writer.write(temp[3] + "\n");
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("Возникла ошибка во время записи, проверьте данные.");
                    }
                }

            }
            r1.close();
            reader1.close();
            r.close();
            reader.close();
            File oldFile = new File(file.toURI());
            file.delete();
            tempFile.renameTo(oldFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}