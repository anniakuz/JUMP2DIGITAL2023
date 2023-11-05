package cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(collection="userSkins")
public class UserSkin {
    @Id
    private String skinId;
    private String name;
    private Double price;
    private SkinType type;
    private String color;
    private User user;

}
