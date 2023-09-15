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

    public void add(User user) {
        users.add(user);
    }

    public boolean login(String name, String password) {
        if (name.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException();
        }

        Optional<User> maybeUser = users.stream()
                .filter(user -> user.getName().equals(name))
                .filter(user -> user.getPassword().equals(password))
                .findFirst();

        return maybeUser.isPresent();
    }
}
