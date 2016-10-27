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
        assertThat(userRepository.findOne(savedUser.getId())).isNotNull();
    }

    @Test
    public void lockAndUnlockUser() {
        User userToLock = userRepository.save(new User("userToLock"));
        assertThat(userToLock.isLocked()).isFalse();

        userToLock.lock();
        User lockedUser = userRepository.save(userToLock);
        assertThat(lockedUser.isLocked());

        lockedUser.unlock();
        User unlockedUser = userRepository.save(lockedUser);
        assertThat(unlockedUser.isLocked()).isFalse();
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void failOnDuplicateUsername() {
        userRepository.save(new User("duplicateUsername"));
        userRepository.save(new User("duplicateUsername"));
    }

    @Test
    public void checkUsersDefaultLangToBeEnglish() {
        User userWithDefaultLanguage = userRepository.save(new User("userWithDefaultLanguage"));
        Language enLanguage = userWithDefaultLanguage.getLanguage();
        assertThat(enLanguage.getDescription()).isEqualTo("English");
    }
}