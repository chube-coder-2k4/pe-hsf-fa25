package sum25.se180556.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private int id;
    private String courseName;
    private String description;
    private String level;
    @Min(value = 10)
    @Max(value = 100)
    private int duration;
    @PositiveOrZero
    private BigDecimal fee;
    @Future
    private LocalDate startTime;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
    private List<Students> studentsList = new ArrayList<>();

    public void add(Students st){
        studentsList.add(st);
        st.setCourse(this);
    }

    public void remove(Students st){
        studentsList.remove(st);
        st.setCourse(null);
    }

    public Courses(String courseName, String description, String level, int duration, BigDecimal fee, LocalDate startTime) {
        this.courseName = courseName;
        this.description = description;
        this.level = level;
        this.duration = duration;
        this.fee = fee;
        this.startTime = startTime;
    }
}
