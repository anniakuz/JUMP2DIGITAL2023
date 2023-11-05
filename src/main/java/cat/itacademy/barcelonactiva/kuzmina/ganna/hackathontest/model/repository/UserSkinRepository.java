package cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.repository;

import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.domain.UserSkin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserSkinRepository extends MongoRepository<UserSkin, String> {
}
