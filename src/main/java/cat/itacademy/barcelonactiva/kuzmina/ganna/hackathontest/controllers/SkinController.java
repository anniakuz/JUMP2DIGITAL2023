package cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.controllers;

import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.services.UserSkinService;
import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.services.SkinService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skins")
@AllArgsConstructor
public class SkinController {
    private final SkinService skinService;
    private final UserSkinService userSkinService;

    @GetMapping("/available")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(skinService.getAll());
    }


    @PostMapping("/buy")
    public ResponseEntity<?> buySkin(@RequestParam String skinId,@RequestParam String color,@RequestParam String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userSkinService.buyASkin(skinId, color, userId));
    }

    @GetMapping("/myskins")
    public ResponseEntity<?> getAllUserSkins(@RequestParam String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userSkinService.getAllSkinsByUserId(userId));
    }

    @PutMapping("/color")
    public ResponseEntity<?> changeColor(@RequestParam String skinId, @RequestParam String color, @RequestParam String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userSkinService.changeColor(skinId, color, userId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteASkin(@PathVariable String id, @RequestParam String userId) {
        userSkinService.deleteASkin(id,userId );
        return ResponseEntity.status(HttpStatus.OK).body(userSkinService.getAllSkinsByUserId(userId));
    }

    @GetMapping("/getskin/{id}")
    public ResponseEntity<?> getASkin(@PathVariable String id, @RequestParam String userId){
        return ResponseEntity.status(HttpStatus.OK).body(userSkinService.getUserSkinById(id, userId));
    }

}