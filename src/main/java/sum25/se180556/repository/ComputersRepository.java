package sum25.se180556.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sum25.se180556.pojo.Computers;

import java.util.List;

@Repository
public interface ComputersRepository extends JpaRepository<Computers, Integer> {
//    public List<Computers> getAllByOrderByCreatedAtDesc();

    @Query("SELECT c FROM Computers c WHERE " +
            "LOWER(CONCAT(c.computer_model, ' ', c.type, ' ', c.manufacturer.manufacturer_name)) " +
            "LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Computers> searchInAllFields(@Param("keyword") String keyword);

}
