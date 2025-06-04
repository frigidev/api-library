package br.com.frigidev.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.frigidev.library.entity.User;
import br.com.frigidev.library.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public User getUserDetails(){
        // Recuperando o e-mail a partir do contexto de segurança
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Devolvendo os dados do usuario a partir do e-mail informado
        return userService.findByEmail(email);
    }

}
