package cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.services;

import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.domain.UserSkin;

import java.util.List;

public interface UserSkinService {
    UserSkin buyASkin(String skinId, String color, String userId);

    UserSkin getUserSkinById(String id, String userId);

    List<UserSkin> getAllSkinsByUserId(String userId);

    public void deleteASkin(String skinId, String userId);

    UserSkin changeColor(String skinId, String color, String userId);
}
