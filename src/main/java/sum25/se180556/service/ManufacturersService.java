package sum25.se180556.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sum25.se180556.pojo.Manufacturers;
import sum25.se180556.repository.ManufacturersRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManufacturersService {
    private final ManufacturersRepository mr;

    public void save(Manufacturers m) {
        mr.save(m);
    }

    public List<Manufacturers> findAll() {
        return mr.findAll();
    }
}
