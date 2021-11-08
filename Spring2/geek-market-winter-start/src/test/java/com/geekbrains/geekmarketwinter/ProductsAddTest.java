package com.geekbrains.geekmarketwinter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductsAddTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void isLoginRequiredToAddProduct() throws Exception {
        mvc.perform(get("http://localhost:8080/products/add"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("http://localhost:8080/login"))
        ;
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void isLoggedInAdminCanAddProduct() throws Exception {
        mvc.perform(get("http://localhost:8080/products/add")).andExpect(status().is(200));
    }

    @Test
    @WithMockUser(username = "user")
    public void isLoggedInWithoutAdminRoleCannotAddProduct() throws Exception {
        mvc.perform(get("http://localhost:8080/products/add")).andExpect(status().is(403));
    }


}
