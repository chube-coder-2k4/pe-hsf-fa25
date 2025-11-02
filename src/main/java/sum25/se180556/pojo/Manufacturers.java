package sum25.se180556.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class Manufacturers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int manufacturer_id;
    @Column(unique = true, nullable = false, length = 100)
    private String manufacturer_name;
    @Column(length = 100)
    private String country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacturer")
    private List<Computers> computers = new ArrayList<>();

    public void add(Computers computer) {
        computer.setManufacturer(this);
        this.computers.add(computer);
    }

    public void remove(Computers computer) {
        computer.setManufacturer(null);
        this.computers.remove(computer);
    }


    public Manufacturers(String manufacturer_name, String country) {
        this.manufacturer_name = manufacturer_name;
        this.country = country;
    }
}
