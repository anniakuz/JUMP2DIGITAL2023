package cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.services;

import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.domain.Skin;

import java.util.List;

public interface SkinService {
    List<Skin> getAll();
    void save(Skin skin);

    Skin findById(String skinId);
}
