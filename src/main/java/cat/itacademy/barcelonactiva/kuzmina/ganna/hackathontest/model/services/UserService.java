package cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.services;

import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.domain.User;

public interface UserService {
    public User saveUser(User user);

    String login(User user);

    User getUserById(String userId);
}
