package com.whereyourmoneygoes.controllers;

import com.whereyourmoneygoes.business.Movement;
import com.whereyourmoneygoes.servers.BankAccountServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class BankAccountController {

    @Autowired
    private BankAccountServer bankAccountServer;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome(ModelMap modelMap) {
        List<Movement> bankAccountMovements = bankAccountServer.findAllMovements();
        modelMap.put("bankAccountMovements", bankAccountMovements);
        return "home";
    }
}
