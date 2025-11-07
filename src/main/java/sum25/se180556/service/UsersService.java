package sum25.se180556.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sum25.se180556.pojo.Users;
import sum25.se180556.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository userrepo;

    public void save(Users u) {
        userrepo.save(u);
    }

    public Users auth(String email, String password) {
        Users u = userrepo.findByEmail(email);
        if(u == null) return null;
        if(!u.getPassword().equals(password)) return null;
        if(u.getRole().equals("Member")) return null;
        return u;
    }
}
