package sum25.se180556.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Year;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Computers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int computer_id;
    @Column(length = 100, nullable = false)
    @Size(min = 5, max = 50, message = "Computer model must be between 5 and 50 characters")
    private String computer_model;
    @Column(length = 50, nullable = false)
    private String type;
    @Min(value = 1990, message = "Production year must be no earlier than 1990")
    private int production_year;
    @DecimalMin(value = "0.0",inclusive = true)
    @Digits(integer = 8, fraction = 2)
    private BigDecimal price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturers manufacturer;
//    private boolean isActive = true;

    public Computers(String computer_model, String type, int production_year, BigDecimal price) {
        this.computer_model = computer_model;
        this.type = type;
        this.production_year = production_year;
        this.price = price;
    }

//    public Computers(String computer_model, String type, int production_year, BigDecimal price, boolean isActive) {
//        this.computer_model = computer_model;
//        this.type = type;
//        this.production_year = production_year;
//        this.price = price;
//        this.isActive = isActive;
//    }
}
