package com.xyj.study.springbootstart;

import com.xyj.study.springbootstart.web.HelloWeb;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringbootStartApplicationTests {

	private MockMvc mvc;

	public void setUp(){
		mvc = MockMvcBuilders.standaloneSetup(new HelloWeb()).build();
	}

	@Test
	public void contextLoads() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/hello"));

	}

}
