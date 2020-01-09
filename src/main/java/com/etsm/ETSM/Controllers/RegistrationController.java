/*
 * Copyright (c) 2019. Smalkov Nikita.
 */

package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Annotations.EmailExistsException;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Models.VerificationToken;
import com.etsm.ETSM.Services.HeaderService;
import com.etsm.ETSM.Services.OnRegistrationCompleteEvent;
import com.etsm.ETSM.Services.RegistrationService;
import com.etsm.ETSM.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Locale;
import java.util.Map;

/*
Контроллер, отвечающий за регистрацию пользователей.
 */
@Controller
public class RegistrationController {

    private RegistrationService registrationService;

    private UserService userService;

    private HeaderService headerService;

    private ApplicationEventPublisher eventPublisher;

    public RegistrationController(RegistrationService registrationService, UserService userService) {
        this.registrationService = registrationService;
        this.userService = userService;
    }



    @GetMapping("/registration")
    public ModelAndView registration(Principal principal) {
        headerService.setHeader(principal);
        User user = new User();
        return new ModelAndView("/registration",
                Map.of("user", user,
                        "role", headerService.getHeaderRole(),
                        "categories", headerService.getHeaderCategories()),
                HttpStatus.OK);
    }
/*
    @PostMapping("/registration")
    public String addUser(@ModelAttribute @Valid User user,
                          BindingResult result) {
        if (registrationService.AddNewUser(user))
            return "redirect:/";
        else
            return "/registration";
    }
*/
@PostMapping("/registration")
public ModelAndView addUser(@ModelAttribute("user") @Valid User user,
                      BindingResult result,
                      WebRequest request,
                      Errors errors) {
    User registered = new User();
    if (!result.hasErrors()) {
        registered = createUserAccount(user, result);
    }
    if (registered == null) {
        result.rejectValue("username","error.user","Account already exists");
    }
    if (result.hasErrors()) {
        return new ModelAndView("registration","user",user);
    } else {
        try {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                    (registered, Locale.ENGLISH, appUrl));
        } catch (Exception e) {
            System.out.println(e);
            return new ModelAndView("registration", "user", user);
        }

        return new ModelAndView("redirect:/user");
    }
}

@GetMapping("/registrationConfirm")
public String confirmRegistration(Model model, @RequestParam("token") String token) {
    VerificationToken verificationToken = registrationService.getVerificationToken(token);
    if (verificationToken == null) {
        String message = "Invalid token!";
        model.addAttribute("message", message);
        return "redirect:/login";
    }

    User user = verificationToken.getUser();
    user.setActive(true);
    registrationService.saveRegisteredUser(user);
    return "redirect:/login";
}

private User createUserAccount(User user, BindingResult result) {
    User registered = null;
    try {
        registered = registrationService.AddNewUser(user);
    } catch (EmailExistsException e) {
        return null;
    }
    return registered;
}

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setHeaderService(HeaderService headerService) {
        this.headerService = headerService;
    }
    @Autowired
    public void setEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
}
