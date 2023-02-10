package com.ltp.gradesubmission;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class GradeController {

    List<Grade> studentGrades = new ArrayList<>();
    List<Record> records = Arrays.asList(
            new Record("Chair", 20.99, 5.99),
            new Record("Table", 40.99, 8.99),
            new Record("Couch", 100.99, 105.99),
            new Record("Fridge", 200.99, 59.99),
            new Record("Bed", 150.99, 205.99)
    );
    List<Staff> officeStaff = Arrays.asList(
            new Staff("Jim Halpert", 32, "Salesman"),
            new Staff("Andy Bernard", 38, "Salesman"),
            new Staff("Pam Beesly", 32, "Receptionist"),
            new Staff("Michael Scott", 49, "Regional Manager"),
            new Staff("Ryan Howard", 28, "Temp"),
            new Staff("Angela Martin", 35, "Accountant"),
            new Staff("Dwigth Schute", 37, "Assistant to Regional Manager")
    );


    @GetMapping("/shows")
    public String getMovies(Model show1) {

        show1.addAttribute("show1", new Show("Breaking Bad", "Ozymandias", 10));
        show1.addAttribute("show2", new Show("Attack on Titan", "Hero", 5.0));
        show1.addAttribute("show3", new Show("Star Wars: The Clone Wars", "Victory or Death", 9.8));
        show1.addAttribute("show4", new Show("Mr. Robot", "407 Proxy Auth. Req.", 8.8));

        return "shows";
    }

    @GetMapping("/office")
    public String getOffice(Model office) {
        office.addAttribute("staffs", officeStaff);
        return "theOffice";
    }

    @GetMapping("/records")
    public String getSales(Model record) {
        record.addAttribute("records", records);

        return "records";

    }

    @GetMapping("/")
    public String gradeForm(Model model, @RequestParam(required = false) String id) {
int index=getGradeIndex(id);
        model.addAttribute("grade", index == Constants.NOT_FOUND ? new Grade() : studentGrades.get(index));
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(Grade grade) {
        int index = getGradeIndex(grade.getId());
        if (index == Constants.NOT_FOUND) {
            studentGrades.add(grade);
        } else
            studentGrades.set(index,grade);

        return "redirect:/grades";
    }

    public Integer getGradeIndex(String id) {
        for (int i = 0; i < studentGrades.size(); i++) {
            if (studentGrades.get(i).getId().equals(id))
                return i;
        }
        return Constants.NOT_FOUND;

    }

    @GetMapping("/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", studentGrades);
        return "grades";
    }

}
