package gr.hua.team19.armypostponement.controller;

import gr.hua.team19.armypostponement.model.Application;
import gr.hua.team19.armypostponement.model.User;
import gr.hua.team19.armypostponement.repository.UserRepository;
import gr.hua.team19.armypostponement.service.UserService;
import gr.hua.team19.armypostponement.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    private UserRepository userRepo;

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
        List<Application> list = applicationService.getApplications();
        modelAndView.addObject("applications",list);
        modelAndView.setViewName("form_validation"); // resources/template/home.html
        return modelAndView;
    }

    @RequestMapping(value = "/form-approval", method = RequestMethod.GET)
    public ModelAndView formApproval() {
        ModelAndView modelAndView = new ModelAndView();
        List<Application> list = applicationService.getApplications();
        modelAndView.addObject("applications",list);
        modelAndView.setViewName("form_approval"); // resources/template/home.html
        return modelAndView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminHome() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> listUsers = userRepo.findAll();
        List<Application> list = applicationService.getApplications();
        modelAndView.addObject("listUsers", listUsers);
        modelAndView.addObject("applications",list);
        modelAndView.setViewName("admin"); // resources/template/home.html
        return modelAndView;
    }

    @RequestMapping(value = "/rejectApplication/{id}", method = RequestMethod.GET)
    public ModelAndView rejectApplication(@PathVariable(value = "id") int application_id){
        ModelAndView modelAndView = new ModelAndView();
        Application application = applicationService.getApplicationById(application_id);
        applicationService.rejectApplication(application);
        modelAndView.setViewName("redirect:/form-validation");
        return modelAndView;
    }

    @RequestMapping(value = "/rejectApplication2/{id}", method = RequestMethod.GET)
    public ModelAndView rejectApplication2(@PathVariable(value = "id") int application_id){
        ModelAndView modelAndView = new ModelAndView();
        Application application = applicationService.getApplicationById(application_id);
        applicationService.rejectApplication(application);
        modelAndView.setViewName("redirect:/form-approval");
        return modelAndView;
    }

    @RequestMapping(value = "/validateApplication/{id}", method = RequestMethod.GET)
    public ModelAndView validateApplication(@PathVariable(value = "id") int application_id){
        ModelAndView modelAndView = new ModelAndView();
        Application application = applicationService.getApplicationById(application_id);
        applicationService.validateApplication(application);
        modelAndView.setViewName("redirect:/form-validation");
        return modelAndView;
    }

    @RequestMapping(value = "/approveApplication/{id}", method = RequestMethod.GET)
    public ModelAndView approveApplication(@PathVariable(value = "id") int application_id){
        ModelAndView modelAndView = new ModelAndView();
        Application application = applicationService.getApplicationById(application_id);
        applicationService.approveApplication(application);
        modelAndView.setViewName("redirect:/form-approval");
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
            modelAndView.addObject("successMessage", "User already exists");
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


    //List<Application> list = applicationService.getApplications();
    @RequestMapping(value = "/form-validation", method = RequestMethod.POST)
    public ModelAndView validateApplication(ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        List<Application> list = applicationService.getApplications();
        modelMap.addAllAttributes(list);
        modelAndView.setViewName("form_validation");
        return modelAndView;
    }

    @PostMapping("/fail_login")
    public String failedLoginHandler() {
        System.out.println("User has failed to login.");
        return "redirect:/login?error";
    }

    //new

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView formSearchApplication(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search"); // resources/template/search.html
        return modelAndView;
    }


    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ModelAndView searchApplication(@RequestParam("emailKeyword") String emailKeyword)
    {
        ModelAndView modelAndView = new ModelAndView();
        String status = applicationService.findApplicationStatus(emailKeyword);
        if(status.equals("FOR_VALIDATION")){
            modelAndView.addObject("message", "Your application is in process(about to be validated)");
        }else if(status.equals("FOR_APPROVAL")){
            modelAndView.addObject("message", "Your application is in process(about to be approved)");
        }else if(status.equals("APPROVED")){
            modelAndView.addObject("message", "Your application has been approved");
        }else if(status.equals("REJECTED")){
            modelAndView.addObject("message", "Your application has been rejected");
        }else if(status.equals("null")){
            modelAndView.addObject("message", "There is no application with this email");
        }
    /*List<Application> list = applicationService.getApplications();
    modelAndView.addObject("applications",list); */
        modelAndView.setViewName("search"); // resources/template/search.html
        return modelAndView;
    }
}
