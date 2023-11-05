package cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(collection="skins")
public class Skin {
    @Id
    private String skinId;
    private String name;
    private Double price;
    private SkinType type;
    @JsonIgnore
    @DBRef(lazy=true)
    private User user;
}
