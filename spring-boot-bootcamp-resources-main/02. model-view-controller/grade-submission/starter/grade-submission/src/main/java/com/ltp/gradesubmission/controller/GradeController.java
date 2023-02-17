package com.ltp.gradesubmission.controller;


import com.ltp.gradesubmission.service.GradeService;
import com.ltp.gradesubmission.Constants;
import com.ltp.gradesubmission.Grade;
import com.ltp.gradesubmission.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class GradeController {


    @Autowired
    GradeService gradeService;


    @GetMapping("/shows")
    public String getMovies(Model show1) {

        show1.addAttribute("show1", new Show("Breaking Bad", "Ozymandias", 10));
        show1.addAttribute("show2", new Show("Attack on Titan", "Hero", 5.0));
        show1.addAttribute("show3", new Show("Star Wars: The Clone Wars", "Victory or Death", 9.8));
        show1.addAttribute("show4", new Show("Mr. Robot", "407 Proxy Auth. Req.", 8.8));
        return "shows";
    }
    @GetMapping("/")
    public String gradeForm(Model model, @RequestParam(required = false) String id) {
        model.addAttribute("grade", gradeService.getGradeById(id));
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(@Valid Grade grade, BindingResult result) {
        if (result.hasErrors())
            return "form";
        gradeService.submitGrade(grade);
        return "redirect:/grades";
    }

    public Integer getGradeIndex(String id) {
        for (int i = 0; i < gradeService.getStudentGrades().size(); i++) {
            if (gradeService.getStudentGrades().get(i).getId().equals(id))
                return i;
        }
        return Constants.NOT_FOUND;

    }

    @GetMapping("/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", gradeService.getStudentGrades());
        return "grades";
    }

}
