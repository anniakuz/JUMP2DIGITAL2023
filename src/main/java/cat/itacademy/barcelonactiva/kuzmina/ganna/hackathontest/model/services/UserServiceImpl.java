package cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.services;

import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.domain.Role;
import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.domain.User;
import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.repository.UserRepository;
import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.security.JwtService;
import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.exceptions.HttpException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User saveUser(User user) {
        if(userRepository.getUserByEmail(user.getEmail())!=null){
            throw new HttpException(HttpStatus.BAD_REQUEST,"Player with this name already exist");
        }
        User userToSave = new User(user.getEmail());
        userToSave.setPassword(passwordEncoder.encode(user.getPassword()));
        userToSave.setRole(Role.USER);
        return userRepository.save(userToSave);
    }

    @Override
    public String login(User user) {
        User user1 = userRepository.getUserByEmail(user.getEmail());
        if(user1==null||!passwordEncoder.matches(user.getPassword(), user1.getPassword())){
            throw new HttpException(HttpStatus.UNAUTHORIZED,"Login or password is not valid");
        }
        return jwtService.generateToken((UserDetails) user1);
    }

    @Override
    public User getUserById(String userId){
        return userRepository.findAll().stream().filter(user -> userId.equals(user.getUserId())).toList().get(0);
    }
}
