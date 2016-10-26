package biz.cosee.talks.loaning.rest;

import biz.cosee.talks.loaning.User;
import biz.cosee.talks.loaning.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRestControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserRestController userRestController;

    @Before
    public void mockUserRepository() {
        when(userRepository.findAll())
                .thenReturn(
                        asList(
                                new User("test1@cosee.biz"),
                                new User("test2@cosee.biz"),
                                new User("test3@cosee.biz")
                        ));
    }

    @Test
    public void listUsersSuccessfully() {
        List<UserDataObject> userDataObjects = userRestController.listAllUsers();
        assertThat(userDataObjects).isNotNull();
        assertThat(userDataObjects).hasSize(3);
        assertThat(userDataObjects.get(0).getUsername()).isEqualTo("test1@cosee.biz");
    }
}