package service;

import com.iglaz.maven2.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final List<User> users = new ArrayList<>();

    public List<User> getAll() {
        return users;
    }

    // TODO: 16.09.2023 Протестировать получение пользователя по id.
    public Optional<User> getById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    // TODO: 16.09.2023 Протестировать метод на уникальность id
    //  пользователей в системе при добавлении нового пользователя.
    public void create(User newUser) {
        boolean unique = users.stream()
                .noneMatch(user -> user.getId() == newUser.getId());

        if (unique) {
            users.add(newUser);
        } else {
            throw new IllegalArgumentException();
        }
    }

    // TODO: 16.09.2023 Протестировать смену username
    public User updateUsername(int id, String newUsername) {
        User targetUser = users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        targetUser.setUsername(newUsername);
        return targetUser;
    }

    // TODO: 16.09.2023 Протестировать метод на то, что получает пользователя, если такой пользователь есть.
    public Optional<User> login(String name, String password) {
        if (name.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException();
        }

        return users.stream()
                .filter(user -> user.getUsername().equals(name))
                .filter(user -> user.getPassword().equals(password))
                .findFirst();
    }

    // TODO: 16.09.2023 Протестировать удаление пользователя.
    public boolean delete(User user) {
        return users.remove(user);
    }
}
