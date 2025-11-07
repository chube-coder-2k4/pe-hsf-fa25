package sum25.se180556.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sum25.se180556.pojo.Students;
import sum25.se180556.repository.StudentsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentsService {
    private final StudentsRepository sturepo;

    public void save(Students c) {
        sturepo.save(c);
    }

    public Students findById(Integer id) {
        return sturepo.findById(id).get();
    }

    public List<Students> findAll() {
        return sturepo.findAll();
    }

    public void delete(Integer id) {
        sturepo.deleteById(id);
    }

//    public List<Computers> findAll() {
//        return sturepo.findAllBy_isActive();
//    }
//
//    public void delete(Integer id) {
//        boolean a = existsById(id);
//        if (a) {
//            Computers c = findById(id);
//            c.setActive(false);
//            save(c);
//        }
//    }

    public boolean existsById(Integer id) {
        return sturepo.existsById(id);
    }

    public List<Students> searchBy(String name) {
        return sturepo.searchInAllFields(name);
    }
}
