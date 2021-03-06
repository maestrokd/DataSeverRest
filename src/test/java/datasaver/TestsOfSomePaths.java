package datasaver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestsOfSomePaths {


    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getNotFoundPage() throws Exception {
        mockMvc.perform(get("/notfoundpage"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void getCorrectPageWithoutAttributes_thenBadRequest() throws Exception {
       MvcResult mvcResult = mockMvc
                .perform(get("/api/documents/downloadAndSaveToDB"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn()
        ;
    }

}
