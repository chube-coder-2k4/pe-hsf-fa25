package sum25.se180556.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;
import java.time.Year;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class Computers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int computer_id;
    @Column(length = 100, nullable = false)
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

    @AssertTrue(message = "Production year cannot be in the future")
    public boolean isProductionYearValid() {
        return production_year <= Year.now().getValue();
    }

    public Computers(String computer_model, String type, int production_year, BigDecimal price) {
        this.computer_model = computer_model;
        this.type = type;
        this.production_year = production_year;
        this.price = price;
    }
}
