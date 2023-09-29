package org.example;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserDataApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные в формате: Фамилия Имя Отчество дата рождения номер телефона пол");
        String userInput = scanner.nextLine();

        try {
            String[] data = userInput.split(" ");

            if (data.length != 6) {
                throw new IllegalArgumentException("Введено неверное количество данных");
            }

            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            String birthDate = data[3];
            String phoneNumber = data[4];
            String gender = data[5];

            validateData(lastName, firstName, middleName, birthDate, phoneNumber, gender);

            String fileName = lastName + ".txt";
            String fileContent = lastName +" "+ firstName +" "+ middleName +" "+ birthDate + " " + phoneNumber +" "+ gender;

            saveToFile(fileName, fileContent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void validateData(String lastName, String firstName, String middleName, String birthDate,
                                     String phoneNumber, String gender) {
        if (lastName.isBlank() || firstName.isBlank() || middleName.isBlank()) {
            throw new IllegalArgumentException("Фамилия, имя или отчество не могут быть пустыми");
        }

        if (!birthDate.matches("\\d{2}.\\d{2}.\\d{4}")) {
            throw new IllegalArgumentException("Неправильный формат даты рождения. Используйте dd.mm.yyyy");
        }

        try {
            Long.parseLong(phoneNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Номер телефона должен быть целым числом без форматирования");
        }

        if (!gender.equals("f") && !gender.equals("m")) {
            throw new IllegalArgumentException("Пол должен быть символом 'f' или 'm'");
        }
    }

    private static void saveToFile(String fileName, String content) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(content);
            writer.newLine();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}