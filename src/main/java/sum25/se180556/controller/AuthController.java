package sum25.se180556.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sum25.se180556.pojo.Users;
import sum25.se180556.service.UsersService;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UsersService us;

    @GetMapping({"/", "/login"})
    public String showLogin() {
        return "login";
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession ses) {
        ses.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/auth")
    public String doLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        Users acc = us.auth(email, password);
        if (acc == null) {
            model.addAttribute("error", "You don't have permission to access this function!");
            return "login";
        }
        session.setAttribute("loggedInUser", acc);
        return "redirect:/students";
    }

}