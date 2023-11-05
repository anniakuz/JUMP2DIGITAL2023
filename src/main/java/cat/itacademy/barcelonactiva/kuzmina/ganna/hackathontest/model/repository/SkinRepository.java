package cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.repository;

import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.domain.Skin;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SkinRepository extends MongoRepository<Skin, String> {

}
