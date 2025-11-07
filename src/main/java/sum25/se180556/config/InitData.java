package sum25.se180556.config;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sum25.se180556.pojo.Courses;
import sum25.se180556.pojo.Students;
import sum25.se180556.pojo.Users;
import sum25.se180556.service.CoursesService;
import sum25.se180556.service.UsersService;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {
    private final CoursesService m;
    private final UsersService u;

    @Override
    public void run(String... args) throws Exception {
        Users us1 = new Users("admin@laptopshop.com", "@1", "Admin");
        Users us2 = new Users("staff@laptopshop.com", "@2", "Staff");
        Users us3 = new Users("member@laptopshop.com", "@3", "Member");

        Courses ma1 = new Courses("Dell", "USA","HAHA",20,new BigDecimal("20.0"), LocalDate.of(2026,03,02));
        Courses ma2 = new Courses("HAHA", "USA","HAHA",20,new BigDecimal("20.0"), LocalDate.of(2026,03,02));
        Courses ma3 = new Courses("LMAO", "USA","HAHA",20,new BigDecimal("20.0"), LocalDate.of(2026,03,02));


        Students co1 = new Students(
                "Nguyen Van A",
                "Male",
                LocalDate.of(2003, 5, 21),
                "0987123456",
                "vana@gmail.com",
                "123 Le Loi, HCM",
                85
        );

        Students co2 = new Students(
                "Tran Thi B",
                "Female",
                LocalDate.of(2002, 11, 10),
                "0909234567",
                "thib@gmail.com",
                "45 Nguyen Hue, HCM",
                90
        );

        Students co3 = new Students(
                "Le Minh C",
                "Male",
                LocalDate.of(2004, 2, 3),
                "0978123987",
                "minhc@gmail.com",
                "12 Tran Phu, Ha Noi",
                78
        );

        Students co4 = new Students(
                "Pham Thi D",
                "Female",
                LocalDate.of(2003, 8, 30),
                "0912345678",
                "thid@gmail.com",
                "89 Vo Van Tan, Da Nang",
                88
        );


        u.save(us1);
        u.save(us2);
        u.save(us3);

        ma1.add(co1);
        ma2.add(co2);
        ma3.add(co3);
        ma1.add(co4);

        m.save(ma1);
        m.save(ma2);
        m.save(ma3);




    }
}
