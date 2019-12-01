package com.etsm.ETSM.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

// Это тестовый контроллер, его можно поменять или удалить
// можно задавать @RequestParam, тогда при разных обращениях к странице получаем параметр (?name=имя), с которым можем работать
// в return указывается имя страницы из templates, которую необходимо открыть

@Controller
public class TestController {
    //Main Page
    @GetMapping
    public String TestHello(@RequestParam(name="name", required = false, defaultValue = "World") String name,
                            Map<String, Object> model)
    {
        model.put("name", name);
        return "main";
    }

    //Second Page
    @GetMapping("/second")
    public String TestHello(Map<String, Object> model)
    {
        model.put("text", "This is second page");
        return "secondPage";
    }
}
