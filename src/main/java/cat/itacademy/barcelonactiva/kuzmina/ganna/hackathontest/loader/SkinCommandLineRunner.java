package cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.loader;


import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.domain.Skin;
import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.services.SkinService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
@AllArgsConstructor
public class SkinCommandLineRunner implements CommandLineRunner {
    private final String SKINS_STORAGE = "/data/skins.json";
    private final ObjectMapper objectMapper;
    private final SkinService skinService;

    @Override
    public void run(String... args) throws Exception {


        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/skins.json")) {
            List<Skin> skins = objectMapper.readValue(inputStream, new com.fasterxml.jackson.core.type.TypeReference<List<Skin>>() {
            });
            skins.forEach(skin -> skinService.save(skin));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON data", e);
        }

    }
}
