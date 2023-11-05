package cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.repository;


import cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.Optional;
public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);
    User getUserByEmail(String email);
    Optional<User> findById(String id);

}
