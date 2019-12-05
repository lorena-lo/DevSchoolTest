package com.ing.tech.devschool.testing.resource;

import com.ing.tech.devschool.testing.api.model.Account;
import com.ing.tech.devschool.testing.api.resource.AccountResource;
import com.ing.tech.devschool.testing.api.service.AccountService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountResource.class)
@ContextConfiguration(classes = {AccountResource.class})
public class AccountResourceIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;



    @Test
    public void shouldReturnOkWhenAccountIsPresent () throws Exception {
        MockHttpServletRequestBuilder requestBuilder= MockMvcRequestBuilders
                .get("http://localhost:8081/accounts/100");
        Mockito.when(accountService.findById(any())).thenReturn(new Account());
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatus());


    }

    @Test
    public void shouldReturnBadRequestWhenAccountIsNotPresent() throws Exception {
        MockHttpServletRequestBuilder requestBuilder= MockMvcRequestBuilders
                .get("http://localhost:8081/accounts/10");
        Mockito.when(accountService.findById(any())).thenReturn(null);
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(),response.getStatus());

    }

    @Test
    public void shouldDeleteAccountIfExists() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/accounts/{id}",1);

        when(accountService.deleteIfExists(1L)).thenReturn(true);
        MvcResult result = mockMvc.perform( MockMvcRequestBuilders.delete("/accounts/1", 1) )
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void shouldNotDeleteAccountIfNotExists() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/accounts/{id}",1);

        when(accountService.deleteIfExists(1000L)).thenReturn(false);
        MvcResult result = mockMvc.perform( MockMvcRequestBuilders.delete("/accounts/1000", 1000) )
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

    }

}
