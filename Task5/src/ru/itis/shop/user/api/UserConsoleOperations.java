package ru.itis.shop.user.api;

import ru.itis.shop.user.application.UserService;
import ru.itis.shop.user.domain.User;

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
                throw new RuntimeException("Не реализован метод signUp в UserRepositoryJdbcImpl");
            }
            case "2": {
                throw new RuntimeException("Не реализован метод signIn в UserRepositoryJdbcImpl");
            }
            case "3": {
                throw new RuntimeException("Не реализован метод findById");
            }
            case "4": {
                showAll();
            }
            break;
            case "5": {
                showInfoByDescription();
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
        System.out.println("4. Показать информацию о всех пользователях");
        System.out.println("5. Показать информацию о пользователях с заданным profileDescription");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регистрировать пользователя");
        System.out.println("Введите name:");
        String name = scanner.nextLine();
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();

        userService.signUp(name, email, password, profileDescription);
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

    private void showAll() {
        System.out.println("Выводим зарегистрированных пользователей..");
        userService.findAll();
    }

    private void showInfoByDescription() {
        System.out.println("Введите profileDescription: ");
        String profileDescription = scanner.nextLine();
        userService.findByDescription(profileDescription);
    }

}
