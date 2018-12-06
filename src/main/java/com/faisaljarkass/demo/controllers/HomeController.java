package com.faisaljarkass.demo.controllers;

import com.faisaljarkass.demo.domains.Blog;
import com.faisaljarkass.demo.domains.MyUser;
import com.faisaljarkass.demo.services.BlogService;
import com.faisaljarkass.demo.services.UserService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class HomeController {

    private static Logger logger = Logger.getLogger(HomeController.class.getName());

    private UserService userService;
    private BlogService blogService;

    private MyUser loggedInUser;

    public HomeController(UserService userService, BlogService blogService) {
        this.userService = userService;
        this.blogService = blogService;
    }

//    @RequestMapping(value = {"","/","index"}, method = RequestMethod.GET)
//    public String index(Model model){
//        logger.info("index method called...");
//
//        model.addAttribute("user", new MyUser());
//        return "login";
//    }

    @RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              /*@ModelAttribute MyUser user,*/ Model model){
        //logger.info("login method called with: " + user);
        logger.info("login method called with: ");

        ModelAndView modelAndView = new ModelAndView();

        if (error != null) {
            model.addAttribute("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }

        modelAndView.setViewName("login");
        return modelAndView;


//        user = userService.getUser(user.getUsername(), user.getPassword());
//        if(user != null){
//            List<Blog> allBlogs = blogService.getAllBlogs();
//            model.addAttribute("listOfBlogs", allBlogs);
//
//            if(Arrays.asList(user.getRoles()).contains("admin")){
//                model.addAttribute("admin", true);
//            }
//
//            loggedInUser = user;
//            return "home";
//        }
//        model.addAttribute("error", true);
//        model.addAttribute("logout", true);


    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request){

        if (request.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("showSection", true);
        }

        return "home";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        return "logout";
    }

    @RequestMapping(value = "/sendData", method = RequestMethod.POST)
    public String sendData(@RequestParam String blog, Model model){
        //logger.info("login sendData called...");

        blogService.addBlog(blog);

        List<Blog> allBlogs = blogService.getAllBlogs();
        model.addAttribute("listOfBlogs", allBlogs);

//        if(Arrays.asList(loggedInUser.getRoles()).contains("admin")){
//            model.addAttribute("admin", true);
//        }

        return "home";
    }

}
