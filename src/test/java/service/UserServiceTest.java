package service;

import com.iglaz.testsimple.dao.UserDao;
import com.iglaz.testsimple.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

class UserServiceTest {
    private static final User VANYA = new User(1, "Ivan", "1111");
    private static final User PETYA = new User(2, "Petya", "2222");

    private UserService userService;
    private UserDao userDao;

    @BeforeEach
    void init() {
        userDao = Mockito.mock(UserDao.class);
        userService = new UserService(userDao);
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

    @ParameterizedTest
    @MethodSource("loginDataSource")
    void login(String name, String password, User expectedUser) {
        userService.add(VANYA, PETYA);

        Optional<User> maybeUser = userService.login(name, password);

        Assertions.assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> Assertions.assertEquals(expectedUser, user));
    }

    static Stream<Arguments> loginDataSource() {
        return Stream.of(
                Arguments.of("Ivan", "1111", VANYA),
                Arguments.of("Petya", "2222", PETYA)
        );
    }

    @Test
    void delete() {
        userService.create(VANYA);

        Mockito.when(userDao.delete()).thenReturn(true);

        Assertions.assertTrue(userService.delete(VANYA));
    }

    @AfterEach
    void end() {
        System.out.println("AfterEach " + this);
    }
}
