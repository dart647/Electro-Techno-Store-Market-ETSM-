package com.etsm.ETSM.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

// Это тестовый контроллер, его можно поменять или удалить
// можно задавать @RequestParam, тогда при разных обращениях к странице получаем параметр (?name=имя), с которым можем работать
// в return указывается имя страницы из templates, которую необходимо открыть

@Controller
public class MainController {
    //Main Page
    @GetMapping
    ModelAndView TestHello(@RequestParam(name="name", required = false, defaultValue = "World") String name)
    {
        return new ModelAndView("main",
                Map.of("name", name),
                HttpStatus.OK);
    }

    //Second Page
    @GetMapping("/second")
    public ModelAndView TestHello()
    {
        return new ModelAndView("/secondPage",
                Map.of("text", "This is second page"),
                HttpStatus.OK);
    }
}
