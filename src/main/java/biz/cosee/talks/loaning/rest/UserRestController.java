package biz.cosee.talks.loaning.rest;

import biz.cosee.talks.loaning.User;
import biz.cosee.talks.loaning.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {

    private UserRepository userRepository;

    @Autowired
    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDataObject> listAllUsers() {
        List<UserDataObject> userDataObjects = new ArrayList<>();
        userRepository.findAll()
                .forEach((User user) -> userDataObjects.add(
                        new UserDataObject(user.getUsername())));
        return userDataObjects;
    }
}
