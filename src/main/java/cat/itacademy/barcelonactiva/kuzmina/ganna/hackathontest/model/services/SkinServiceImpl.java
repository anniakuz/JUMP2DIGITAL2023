package cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.services;

import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.domain.Skin;
import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.repository.SkinRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SkinServiceImpl implements SkinService{
    private final SkinRepository skinRepository;
    @Override
    public List<Skin> getAll() {
        return skinRepository.findAll();
    }

    @Override
    public void save(Skin skin){
        skinRepository.save(skin);
    }

    @Override
    public Skin findById(String id){
        List<Skin> skins = skinRepository.findAll().stream().filter(skin -> id.equals(skin.getSkinId())).collect(Collectors.toList());
    return skins.get(0);
    }
}
