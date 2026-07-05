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

    public void signUp(String email, String password, String profileDescription) {
        User user = new User(email, password, profileDescription);
        userRepository.save(user);
    }

    public boolean signIn(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get().getPassword().equals(password);
        } else return false;
    }

    public String getEmailFromId(String id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            return userOptional.get().getEmail();
        }
        return null;
    }

    public boolean emailIsExist(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return true;
        }
        return false;
    }

    public void updateDescription(String email, String newDescription) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            userOptional.get().setProfileDescription(newDescription);
            userRepository.updateUser(userOptional.get());
        }
    }

    public void getAllEmails() {
        List<User> allUsers = userRepository.findAll();
        allUsers.stream().forEach(a -> System.out.println(a.getEmail()));
    }
}
