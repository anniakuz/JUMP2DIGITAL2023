package cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.controllers;

import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.domain.User;
import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.services.UserService;
import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.exceptions.HttpException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody User user){
        User savedUser;
        try {
            savedUser = userService.saveUser(user);
        }catch(HttpException ex){
            return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String token;
        try{
            token = userService.login(user);
        }
        catch(HttpException ex){
            return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(token);

    }
}
