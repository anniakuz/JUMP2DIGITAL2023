package cat.itacademy.barcelonactiva.kuzmina.ganna.hackathontest.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(collection = "users" )
public class User implements UserDetails {
    @Id
    private String userId;
    private String email;
    private String password;
    private Role role;
    private LocalDate registerDate;


    @JsonIgnore
    @DBRef(lazy = true)
    private List<UserSkin> userSkins;

    public User(String email) {
        this.email = email;
        this.registerDate = LocalDate.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
