package sum25.se180556.config;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sum25.se180556.pojo.Computers;
import sum25.se180556.pojo.Manufacturers;
import sum25.se180556.pojo.Users;
import sum25.se180556.service.ManufacturersService;
import sum25.se180556.service.UsersService;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {
    private final ManufacturersService m;
    private final UsersService u;

    @Override
    public void run(String... args) throws Exception {
        Users us1 = new Users("admin@laptopshop.com", "@1", "Admin");
        Users us2 = new Users("staff@laptopshop.com", "@2", "Staff");
        Users us3 = new Users("member@laptopshop.com", "@3", "Member");

        Manufacturers ma1 = new Manufacturers("Dell", "USA");
        Manufacturers ma2 = new Manufacturers("Lenovo", "China");
        Manufacturers ma3 = new Manufacturers("HP", "USA");

        Computers co1 = new Computers("XPS 13", "Ultrabook", 2023, new BigDecimal("1299.99"));
        Computers co2 = new Computers("ThinkPad X1 Carbon", "Business Laptop", 2023, new BigDecimal("1499.99"));
        Computers co3 = new Computers("Pavilion 15", "Consumer Laptop", 2022, new BigDecimal("699.99"));
        Computers co4 = new Computers("Inspiron 14", "Budget Laptop", 2023, new BigDecimal("549.99"));

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
