package cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.services;

import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.domain.Skin;
import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.domain.User;
import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.domain.UserSkin;
import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.repository.UserSkinRepository;
import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.exceptions.HttpException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserSkinServiceImpl implements UserSkinService{
    private final UserSkinRepository userSkinRepository;
    private final UserService userService;
    private final SkinService skinService;

    @Override
    public UserSkin buyASkin(String skinId, String color, String userId){
        try {
            Skin skin = skinService.findById(skinId);

            User user = userService.getUserById(userId);
            UserSkin userSkin = UserSkin.builder().name(skin.getName()).price(skin.getPrice()).
                    type(skin.getType()).color(color).user(user).build();
            if (user.getUserSkins() == null) {
                user.setUserSkins(new ArrayList<>());
            }
            UserSkin savedSkin = userSkinRepository.save(userSkin);
            user.getUserSkins().add(savedSkin);
            return savedSkin;
        }catch(IndexOutOfBoundsException e){
            throw new HttpException(HttpStatus.BAD_REQUEST,"The skin with this ID doesn't exist");
        }
    }

    @Override
    public UserSkin getUserSkinById(String id, String userId){
        try {
            List<UserSkin> skins = userSkinRepository.findAll();
            UserSkin skin = skins.stream().filter(
                            userSkin -> id.equals(userSkin.getSkinId()) && userId.equals(userSkin.getUser().getUserId()))
                    .collect(Collectors.toList()).get(0);
            return skin;
        }catch(IndexOutOfBoundsException e){
            throw new HttpException(HttpStatus.BAD_REQUEST, "The skin with this ID is not found");
        }
    }


    @Override
    public List<UserSkin> getAllSkinsByUserId(String userId) {

        return userSkinRepository.findAll().stream().filter(userSkin -> userId.equals(userSkin.getUser().getUserId()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteASkin(String skinId, String userId) {
        try {
            getUserSkinById(skinId, userId);
            userSkinRepository.deleteById(skinId);
        }catch(IndexOutOfBoundsException e){
            throw new HttpException(HttpStatus.BAD_REQUEST, "The skin with this ID is not found");
        }
    }

    @Override
    public UserSkin changeColor( String skinId,String color, String userId){
        UserSkin userSkin = getUserSkinById(skinId,userId);
        UserSkin newUserSkin = UserSkin.builder().skinId(skinId).name(userSkin.getName()).type(userSkin.getType())
                .price(userSkin.getPrice()).user(userSkin.getUser()).color(color).build();
        userSkinRepository.deleteById(skinId);
        return userSkinRepository.save(newUserSkin);
    }


}
