package sum25.se180556.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sum25.se180556.pojo.Computers;
import sum25.se180556.pojo.Users;
import sum25.se180556.service.ComputersService;
import sum25.se180556.service.ManufacturersService;

@Controller
@RequiredArgsConstructor
public class ComputersController {

    private final ComputersService c;
    private final ManufacturersService m;

    @GetMapping("/computers")
    public String showList(@RequestParam(name = "keyword", required = false, defaultValue = "") String keyword, Model model, HttpSession ss) {
        Users acc = (Users) ss.getAttribute("loggedInUser");
        if (acc == null) {
            return "redirect:/login";
        }

        if (!keyword.isEmpty()) {
            model.addAttribute("cpts", c.searchBy(keyword));
        }
        else {

            model.addAttribute("cpts", c.findAll());
        }
        model.addAttribute("keyword", keyword);

        return "computers";
    }

    @GetMapping("/computers/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model, HttpSession ss) {

        Users acc = (Users) ss.getAttribute("loggedInUser");
        if (acc == null) {
            return "redirect:/login";
        }

        if (acc.getRole().equals("Staff")) {
            return "redirect:/computers";
        }

        model.addAttribute("selectedOne", c.findById(id));

        model.addAttribute("vars", m.findAll());

        model.addAttribute("formMode", "edit");

        return "computers-form";
    }


    @GetMapping("/computers/create")
    public String create(Model model, HttpSession ss) {


        Users acc = (Users) ss.getAttribute("loggedInUser");
        if (acc == null) {
            return "redirect:/login";
        }

        if (acc.getRole().equals("Staff")) {
            return "redirect:/computers";
        }

        model.addAttribute("selectedOne", new Computers());

        model.addAttribute("vars", m.findAll() );

        model.addAttribute("formMode", "new");

        return "computers-form";
    }

    @PostMapping("/computers/save")
    public String save(@Valid @ModelAttribute("selectedOne") Computers cpt, BindingResult result, Model model, @RequestParam("formMode") String formMode) {

        if (result.hasErrors()) {
            model.addAttribute("vars", m.findAll() );
            model.addAttribute("formMode", formMode);
            return "computers-form";
        }
        if (formMode.equals("new")) {
            if (c.existsById(cpt.getComputer_id())) {
                model.addAttribute("vars", m.findAll() );
                model.addAttribute("formMode", formMode);
                model.addAttribute("duplicated", "Duplicated Id. Input another one!");
                return "computers-form";
            }
        }

        c.save(cpt);

        return "redirect:/computers";
    }

    @GetMapping("/computers/delete/{id}")
    public String delete(@PathVariable("id") Integer id, HttpSession ss) {

        Users acc = (Users) ss.getAttribute("loggedInUser");
        if (acc == null) {
            return "redirect:/login";
        }

        if (acc.getRole().equals("Staff")) {
            return "redirect:/computers";
        }
        c.delete(id);
        return "redirect:/computers";
    }

}
