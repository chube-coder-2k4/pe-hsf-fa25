package sum25.se180556.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sum25.se180556.pojo.Courses;
import sum25.se180556.repository.CoursesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoursesService {
    private final CoursesRepository courserepo;

    public void save(Courses m) {
        courserepo.save(m);
    }

    public List<Courses> findAll() {
        return courserepo.findAll();
    }
}
