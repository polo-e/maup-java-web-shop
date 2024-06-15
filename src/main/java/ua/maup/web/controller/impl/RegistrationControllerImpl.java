package ua.maup.web.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.maup.service.UserService;
import ua.maup.model.User;
import ua.maup.web.controller.RegistrationController;
import ua.maup.web.utils.Cryptor;
import ua.maup.web.utils.Generator;
import ua.maup.web.utils.Validator;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Controller
public class RegistrationControllerImpl implements RegistrationController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/registration")
    public String showRegistrationForm(@RequestParam(value = "letters", required = false) String letters, HttpSession session) {

        if (letters != null) {
            if (session.getAttribute("generatedLetter").toString().replaceAll(" ", "")
                    .equals(letters.replaceAll(" ", ""))) {

                session.setAttribute("regCounter", 0);
                session.removeAttribute("isShowAntiBotForm");
            }
        }
        session.setAttribute("generatedLetter", Generator.generateRandomChars());
        return "registration";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST, params = {"name", "login", "password", "address", "sex", "comment"})
    public String registration(@RequestParam("name") String name, @RequestParam("login") String login, @RequestParam("password") String password,
                               @RequestParam("retypepassword") String retypepassword, @RequestParam("address") String address, @RequestParam("sex") String sex,
                               @RequestParam("comment") String comment, HttpSession session) {


        if (session.getAttribute("regCounter") == null) {
            session.setAttribute("regCounter", 0);
        }

        session.setAttribute("name", name);
        session.setAttribute("login", login);
        session.setAttribute("password", password);
        session.setAttribute("retypepassword", retypepassword);
        session.setAttribute("address", address);
        session.setAttribute("sex", sex);
        session.setAttribute("comment", comment);

        StringBuilder errorMessage = new StringBuilder();
        boolean error = false;

        if (session.getAttribute("name") != null) {
            errorMessage.append("<ul>");

            if (session.getAttribute("name").toString().isEmpty()) {
                error = true;
                errorMessage.append("<li>Поле з ім'ям не заповнено</li>");
            }

            if (session.getAttribute("login").toString().isEmpty()) {
                error = true;
                errorMessage.append("<li>Поле з логіном не заповнено</li>");
            } else if (!Validator.isValidEmail(session.getAttribute("login").toString())) {
                error = true;
                errorMessage.append("<li>Введено не вірний логін!");
            }

            if (session.getAttribute("password").toString().isEmpty()) {
                error = true;
                errorMessage.append("<li>Поле з паролем не заповнено</li>");

            } else if (!Validator.isValidPassword(session.getAttribute("password").toString())) {
                error = true;
                errorMessage.append("<li>Введено неправильний пароль! Він повинен містити принаймні 1 цифру, 1 нижню літеру, 1 велику літеру та спеціальний символ, мінімум 8 і максимум 20 символів</li>");
            }

            if (!session.getAttribute("password").toString().equals(session.getAttribute("retypepassword").toString())) {
                error = true;
                errorMessage.append("<li>Помилка повторного введення пароля</li>");
            }

            if (session.getAttribute("address").toString().isEmpty()) {
                error = true;
                errorMessage.append("<li>Поле з регіоном не заповнено</li>");
            }

            if (!error) {

                User user = null;
                try {
                    user = new User()
                            .setName(name)
                            .setLogin(login)
                            .setPassword(Cryptor.encrypt(password))
                            .setAddress(address)
                            .setSex(sex)
                            .setComment(comment);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                user = userService.registration(user);

                if (user != null) {
                    session.setAttribute("user", user);
                    session.setAttribute("regCounter", 0);
                    return "login";
                }
                errorMessage.append(String.format("Login %s already registered!", login));
            }

            errorMessage.append("<ul>");
            session.setAttribute("errorMessage", errorMessage.toString());
        }

        int regCounter = Integer.parseInt(session.getAttribute("regCounter").toString());
        session.setAttribute("regCounter", ++regCounter);

        if (regCounter >= 3) {
            session.setAttribute("isShowAntiBotForm", "true");
        }
        return "registration";
    }
}