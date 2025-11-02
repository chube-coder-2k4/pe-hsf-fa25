package sum25.se180556.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    @Column(unique = true, nullable = false, length = 100)
    private String email;
    @Column(nullable = false, length = 50)
    private String password;
    @Column(length = 20, nullable = false)
    private String role;

    public Users(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
