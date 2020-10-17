package ma.ensa.ArticlePub;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class ArticlePubApplicationTests {
	@Autowired
    private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}
	
    @Test
    public void Login() throws Exception {
        String user = "{\"username\":\"Khalid Oukssim\",\"password\":\"admin\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/token/generate-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
    
    @Test
    public void getsAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }


}
