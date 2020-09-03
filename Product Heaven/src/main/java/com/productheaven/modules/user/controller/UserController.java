package com.productheaven.modules.user.controller;

import com.productheaven.entities.Roles;
import com.productheaven.entities.UserRoles;
import com.productheaven.entities.Users;
import com.productheaven.modules.user.service.RoleService;
import com.productheaven.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class UserController {
    final UserService userService;
    final RoleService roleService;
    final MessageSource messageSource;
    final PersistentTokenRepository tokenRepository;
    final AuthenticationTrustResolver authenticationTrustResolver;
    @Autowired
    public UserController(UserService userService, RoleService roleService, MessageSource messageSource,
                          PersistentTokenRepository tokenRepository, AuthenticationTrustResolver authenticationTrustResolver) {
        this.userService = userService;
        this.roleService = roleService;
        this.messageSource = messageSource;
        this.tokenRepository = tokenRepository;
        this.authenticationTrustResolver = authenticationTrustResolver;
    }


    /**
     * This method will list all existing users.
     */
    @RequestMapping(value = { "/list" }, method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public String listUsers(ModelMap model) {

        List<Users> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", getPrincipal());
        return "userslist";
    }

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        Users user = new Users();
        model.addAttribute("user", user);
        model.addAttribute("title", "Register");
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = { "/registration" }, method = RequestMethod.POST)
    public String saveUser(Users user, BindingResult result,
                           ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

        /*
         * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation
         * and applying it on field [sso] of Model class [User].
         *
         * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
         * framework as well while still using internationalized messages.
         *
         */
        if(!userService.isUserEmailUnique(user.getId(), user.getEmailAddress())){
            FieldError ssoError =new FieldError("user","email",messageSource.getMessage("non.unique.email", new String[]{user.getEmailAddress()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }
        Set<UserRoles> userRoles = new HashSet<>();
        UserRoles userRole=null;
        for (Roles role: roleService.findAll()){
            if(role.getRoleName().equalsIgnoreCase("CUSTOMER")){
                userRole = new UserRoles();
                userRole.setRoleId(role.getId());
                userRole.setUserId(user.getId());
                userRoles.add(userRole);
            }
        }
        user.setUserRoles(userRoles);
        userService.saveUser(user);

        //model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
        String userName = getPrincipal();
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("title", "Registration");
        //return "success";
        return "registrationsuccess";
    }


    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = { "/edit-user-{emailAddress}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable String email, ModelMap model) {
        Users user = userService.findByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-user-{emailAddress}" }, method = RequestMethod.POST)
    public String updateUser(Users user, BindingResult result,
                             ModelMap model, @PathVariable String ssoId) {

        if (result.hasErrors()) {
            return "registration";
        }

		/*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}*/


        userService.updateUser(user);

        //model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "registrationsuccess";
    }


    /**
     * This method will delete an user by it's SSOID value.
     */
    @RequestMapping(value = { "/delete-user-{emailAddress}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return "redirect:/list";
    }


    /**
     * This method will provide UserProfile list to views
     */
    @ModelAttribute("roles")
    public List<Roles> initializeProfiles() {
        return roleService.findAll();
    }

    /**
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }

    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        if (isCurrentAuthenticationAnonymous()) {
            model.addAttribute("title", "Login");
            return "login";
        } else {
            return "redirect:/list";
        }
    }

    /**
     * This method handles logout requests.
     * Toggle the handlers if you are RememberMe functionality is useless in your app.
     */
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            String user_name = auth.getName();
            new SecurityContextLogoutHandler().logout(request, response, auth);
//            tokenRepository.removeUserTokens(user_name);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login";
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
}
