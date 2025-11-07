package sum25.se180556.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sum25.se180556.pojo.Students;
import sum25.se180556.pojo.Users;
import sum25.se180556.service.CoursesService;
import sum25.se180556.service.StudentsService;

import java.time.Year;

@Controller
@RequiredArgsConstructor
public class StudentsController {

    private final StudentsService stuService;
    private final CoursesService courService;

    @GetMapping("/students")
    public String showList(@RequestParam(name = "keyword", required = false, defaultValue = "") String keyword, Model model, HttpSession ss) {
        Users acc = (Users) ss.getAttribute("loggedInUser");
        if (acc == null) {
            return "redirect:/login";
        }

        if (!keyword.isEmpty()) {
            model.addAttribute("cpts", stuService.searchBy(keyword));
        }
        else {

            model.addAttribute("cpts", stuService.findAll());
        }
        model.addAttribute("keyword", keyword);

        return "students";
    }

    @GetMapping("/students/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model, HttpSession ss) {

        Users acc = (Users) ss.getAttribute("loggedInUser");
        if (acc == null) {
            return "redirect:/login";
        }

        if (acc.getRole().equals("Staff")) {
            return "redirect:/";
        }

        model.addAttribute("selectedOne", stuService.findById(id));

        model.addAttribute("vars", courService.findAll());

        model.addAttribute("formMode", "edit");

        return "students-form";
    }


    @GetMapping("/students/create")
    public String create(Model model, HttpSession ss) {


        Users acc = (Users) ss.getAttribute("loggedInUser");
        if (acc == null) {
            return "redirect:/login";
        }

        if (acc.getRole().equals("Staff")) {
            return "redirect:/students";
        }

        model.addAttribute("selectedOne", new Students());

        model.addAttribute("vars", courService.findAll() );

        model.addAttribute("formMode", "new");

        return "students-form";
    }

    @PostMapping("/students/save")
    public String save(@Valid @ModelAttribute("selectedOne") Students cpt, BindingResult result, Model model, @RequestParam("formMode") String formMode) {

        if (result.hasErrors()) {
            model.addAttribute("vars", courService.findAll() );
            model.addAttribute("formMode", formMode);
            return "students-form";
        }
        if (formMode.equals("new")) {
            if (stuService.existsById(cpt.getId())) {
                model.addAttribute("vars", courService.findAll() );
                model.addAttribute("formMode", formMode);
                model.addAttribute("duplicated", "Duplicated Id. Input another one!");
                return "students-form";
            }
        }

        stuService.save(cpt);

        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String delete(@PathVariable("id") Integer id, HttpSession ss) {

        Users acc = (Users) ss.getAttribute("loggedInUser");
        if (acc == null) {
            return "redirect:/login";
        }

        if (acc.getRole().equals("Staff")) {
            return "redirect:/students";
        }
        stuService.delete(id);
        return "redirect:/students";
    }

}
