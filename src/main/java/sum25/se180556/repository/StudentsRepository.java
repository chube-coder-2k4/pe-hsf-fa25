package sum25.se180556.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sum25.se180556.pojo.Students;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Integer> {
//    public List<Computers> getAllByOrderByCreatedAtDesc();

    @Query("SELECT c FROM Students c WHERE " +
            "LOWER(CONCAT(c.address,' ', c.course.courseName)) " +
            "LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Students> searchInAllFields(@Param("keyword") String keyword);

//    @Query("SELECT c FROM Computers c WHERE c.isActive = true")
//    List<Computers> findAllBy_isActive();
}
