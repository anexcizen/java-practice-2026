package ru.itis.shop.user.api;

import ru.itis.shop.user.application.UserService;

import java.util.Scanner;

public class UserConsoleOperations {

    private final UserService userService;
    private final Scanner scanner;

    public UserConsoleOperations(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        printUserMenu();

        String command = scanner.nextLine();

        switch (command) {
            case "1": {
                signUp();
            }
            break;
            case "2": {
                signIn();
            }
            break;
            case "3": {
                getEmailFromId();
            }
            break;
            case "4": {
                updateData();
            }
            break;
            case "0": {
                System.exit(0);
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить данные пользователя");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регистрировать пользователя");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();

        userService.signUp(email, password, profileDescription);
    }


    private void signIn() {
        System.out.println("Вы можете войти в приложение");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();

        if (userService.signIn(email, password)) {
            System.out.println("Вы вошли в приложение");
        } else {
            System.out.println("Email или пароль не верны");
        }
    }

    private void getEmailFromId() {
        System.out.println("Ищем пользователя по id");
        System.out.println("Введите id: ");
        String id = scanner.nextLine();
        String foundEmail = userService.getEmailFromId(id);
        if (foundEmail != null) {
            System.out.println(foundEmail);
        } else {
            System.out.println("Пользователя с таким id не существует");
        }
    }

    private void updateData() {
        System.out.println("Введите email пользователя, данные которого хотите обновить: ");
        String email = scanner.nextLine();
        if (userService.emailIsExist(email)) {
            updateDescription(email);
        } else {
            System.out.println("Пользователь с таким email не зарегистрирован");
        }
    }

    private void updateDescription(String email) {
        System.out.println("Вы хотите обновить описание пользователя " + email + "?");
        if (scanner.nextLine().toLowerCase().equals("да")) {
            System.out.println("Введите новое описание пользователя");
            String newDescription = scanner.nextLine();
            userService.updateDescription(email, newDescription);
        } else {
            System.out.println("Данные пользователя не обновлены");
        }
    }


}
