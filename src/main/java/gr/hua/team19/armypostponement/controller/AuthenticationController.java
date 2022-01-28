package gr.hua.team19.armypostponement.controller;

import gr.hua.team19.armypostponement.model.Application;
import gr.hua.team19.armypostponement.model.User;
import gr.hua.team19.armypostponement.service.UserService;
import gr.hua.team19.armypostponement.service.ApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;



@Controller
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    ApplicationService applicationService;

    @RequestMapping(value =  "" , method = RequestMethod.GET) // /login
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login"); // resources/template/login.html
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register"); // resources/template/register.html
        return modelAndView;
    }

    @RequestMapping(value = "/form-application", method = RequestMethod.GET)
    public ModelAndView formApplication() {
        ModelAndView modelAndView = new ModelAndView();
        Application application = new Application();
        modelAndView.addObject("application",application);
        modelAndView.setViewName("form_application"); // resources/template/home.html
        return modelAndView;
    }

    @RequestMapping(value = "/form-validation", method = RequestMethod.GET)
    public ModelAndView formValidation() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("form_validation"); // resources/template/home.html
        return modelAndView;
    }

    @RequestMapping(value = "/form-approval", method = RequestMethod.GET)
    public ModelAndView formApproval() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("form_approval"); // resources/template/home.html
        return modelAndView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin"); // resources/template/home.html
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        // check for validations.
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else if (userService.isUserAlreadyPresent(user)){
            // save the user registration form
            modelAndView.addObject("successMessage", "User already exist");
        }
        // we will save the user if he hasn't binding errors
        else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User is registered successfully");
        }
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value = "/form-application", method = RequestMethod.POST)
    public ModelAndView submitApplication(@Valid Application application, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()){
            modelAndView.addObject("successMessage", "Please correct the errors in form");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else if (applicationService.isApplicationAlreadyPresent(application)){
            // save the user registration form
            modelAndView.addObject("successMessage", "Application already exists");
        }
        else {
            applicationService.saveApplication(application);
            modelAndView.addObject("successMessage", "Application is submitted successfully");
        }
        modelAndView.addObject("application", new Application());
        modelAndView.setViewName("form_application");
        return modelAndView;
    }
}
