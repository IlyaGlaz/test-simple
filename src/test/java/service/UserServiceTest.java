package service;

import com.iglaz.maven2.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserServiceTest {
    private static final User VANYA = new User(1, "Ivan", "1111");
    private static final User PETYA = new User(2, "Patya", "2222");

    private UserService userService;

    @BeforeEach
    void init() {
        userService = new UserService();
        System.out.println("BeforeEach " + this);
    }

    @Test
    @DisplayName("Пустая коллекция")
    void shouldReturnEmptyCollectionWhenNoUserAdded() {
        // GIVEN
        System.out.println("Test1");

        // WHEN
        List<User> list = userService.getAll();

        // THEN
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    void shouldReturnUserIfCollectionNotEmpty() {
        System.out.println("Test2");
        userService.create(VANYA);
        userService.create(PETYA);

        List<User> list = userService.getAll();

        Assertions.assertEquals(2, list.size());
    }

    @Test
    void noLogin() {
        Assertions.assertDoesNotThrow(() -> userService.login("111", "1111"));
    }

    @AfterEach
    void end() {
        System.out.println("AfterEach " + this);
    }
}
