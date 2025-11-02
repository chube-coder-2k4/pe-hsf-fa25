package sum25.se180556.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sum25.se180556.pojo.Manufacturers;

@Repository
public interface ManufacturersRepository extends JpaRepository<Manufacturers, Integer> {
}
