package web.test30.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.test30.model.User;
import web.test30.service.UserService;
import web.test30.util.UserValidator;

import java.util.List;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public PeopleController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping()
    public String index(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute( "users", users );
        return "people/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") int id, Model model) {
        User user = userService.findOne( id );
        model.addAttribute( "person", user );
        return "people/show";
    }

    @GetMapping("new")
    public String newPerson(Model model) {
        model.addAttribute( "person", new User() );
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid User user,
                         BindingResult bindingResult) {

        userValidator.validate(user, bindingResult);

        if(bindingResult.hasErrors()){
            return "people/new";
        }

        userService.save( user );
        return "redirect:/people";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute( "person", userService.findOne( id ) );
        return "people/edit";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute("person") @Valid User user,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id) {

        userValidator.validate(user, bindingResult);

        if(bindingResult.hasErrors()){
            return "people/edit";
        }
        userService.update( id, user );
        return "redirect:/people";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id){
        userService.delete( id );
        return "redirect:/people";
    }
}
