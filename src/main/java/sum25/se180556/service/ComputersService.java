package sum25.se180556.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sum25.se180556.pojo.Computers;
import sum25.se180556.repository.ComputersRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComputersService {
    private final ComputersRepository cr;

    public void save(Computers c) {
        cr.save(c);
    }

    public Computers findById(Integer id) {
        return cr.findById(id).get();
    }

    public List<Computers> findAll() {
        return cr.findAll();
    }

    public void delete(Integer id) {
        cr.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return cr.existsById(id);
    }

    public List<Computers> searchBy(String name) {
        return cr.searchInAllFields(name);
    }
}
