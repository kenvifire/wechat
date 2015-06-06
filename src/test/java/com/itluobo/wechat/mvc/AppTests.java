package com.itluobo.wechat.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:../../../../../../main/webapp/WEB-INF/spring-web-config.xml")
public class AppTests {
    private MockMvc mockMvc;

//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    @Autowired
//    protected WebApplicationContext wac;
//
//    @Before
//    public void setup() {
//        this.mockMvc = webAppContextSetup(this.wac).build();
//    }
//
//    @Test
//    public void simple() throws Exception {
//        mockMvc.perform(get("/"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("hello"));
//    }

    @Test
    public void shouldPass(){
    }
}
