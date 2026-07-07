package ru.itis.shop.user.application;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String name, String email, String password, String profileDescription) {
        User user = new User(name, email, password, profileDescription);
        userRepository.save(user);
    }

    public boolean signIn(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get().getPassword().equals(password);
        } else return false;
    }

    public void findByDescription(String profileDescription) {
        List<User> users = userRepository.findAllByProfileDescription(profileDescription);
        if (!users.isEmpty()) {
            users.forEach(System.out::println);
        } else {
            System.out.println("Пользователей с таким profileDescription не найдено");
        }
    }

    public void findAll() {
        List<User> allUsers = userRepository.findAll();
        allUsers.forEach(a -> System.out.println(a.getName() + " " + a.getEmail()));
    }
}
