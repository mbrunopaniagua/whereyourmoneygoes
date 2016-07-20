package com.whereyourmoneygoes.controllers;

import com.whereyourmoneygoes.business.Movement;
import com.whereyourmoneygoes.servers.BankAccountServer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountControllerTest {

    @InjectMocks
    private BankAccountController bankAccountController;
    @Mock
    private BankAccountServer bankAccountServer;
    @Mock
    private View mockView;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(bankAccountController)
                .setSingleView(mockView)
                .build();
    }

    @Test
    public void testWhen_TheRequestIsAbsolutePath_Then_ReturnHomeView() throws Exception {
        List<Movement> expectedMovements = Collections.emptyList();
        when(bankAccountServer.findAllMovements()).thenReturn(expectedMovements);
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("bankAccountMovements", expectedMovements))
            .andExpect(view().name("home"));
    }


}