package biz.cosee.talks.loaning;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LiquibaseApplication.class)
@WebAppConfiguration
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void storeUser() {
        User savedUser = userRepository.save(new User("username"));

        assertThat(savedUser.getId()).isNotNull();
        User loadedUser = userRepository.findOne(savedUser.getId());
        assertThat(loadedUser).isNotNull();
        assertThat(loadedUser.isLocked()).isFalse();
    }

    @Test
    public void lockUser() {
        User user = userRepository.save(new User("userToLock"));
        user.lock();
        userRepository.save(user);

        User loadedUser = userRepository.findOne(user.getId());
        assertThat(loadedUser.isLocked()).isTrue();
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void failToStoreTwoUsersWithSameName() {
        userRepository.save(new User("duplicateUsername"));
        userRepository.save(new User("duplicateUsername"));
    }

    @Test
    public void storeUserWithDefaultLanguage() {
        User userWithDefaultLanguage = userRepository.save(new User("userWithDefaultLanguage"));

        userWithDefaultLanguage = userRepository.findOne(userWithDefaultLanguage.getId());

        assertThat(userWithDefaultLanguage.getLanguage()).isNotNull();
        assertThat(userWithDefaultLanguage.getLanguage().getDescription()).isEqualTo("English");
    }
}