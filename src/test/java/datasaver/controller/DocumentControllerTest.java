package datasaver.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import datasaver.model.entity.DataFromServer;
import datasaver.model.service.DataDownloader;
import datasaver.model.service.DataFromServerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DocumentController.class)
public class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DataDownloader dataDownloaderMock;
    @MockBean
    private DataFromServerService dataFromServerServiceMock;

    private DataFromServer dataFromServer;
    private List<DataFromServer> dataFromServerList;

    //Methods
    @Before
    public void setup() throws IOException {
        dataFromServer = new DataFromServer(
                "3c3326ca4c234a828f892cc5716cd10b",
                "d6bd9bb606ad860ff141526b5c81b5d8",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                "https://public.docs-sandbox.openprocurement.org/get/499160aad8ea4b6281bc15a155f5a81b?KeyID=1331dc52&Signature=Un0Hs0DjdXKnefSEbbP7O38lHHOUZjcbmDObH4idj6pHfeniocqB3v7l0lVhID3fwGL0H9UpJIswjnIM%252BFrYCg%253D%253D",
                "\\u0414\\u043e\\u043a\\u0443\\u043c\\u0435\\u043d\\u0442\\u0430\\u0446\\u0438\\u044f \\u043f\\u043e \\u043b\\u043e\\u0442\\u0443 5.docx",
                "tender",
                "2017-09-15T16:08:34.036357+03:00",
                "2017-09-15T16:08:34.036392+03:00",
                null
        );
        dataFromServerList = new ArrayList<>();
        dataFromServerList.add(dataFromServer);


        given(dataDownloaderMock.downloadDataFromServer("https://lb.api-sandbox.openprocurement.org/api/2.4/contracts/CorrectUrl")).willReturn(dataFromServerList);
        given(dataDownloaderMock.downloadDataFromServer("https://lb.api-sandbox.openprocurement.org/api/2.4/contracts/IncorrectUrl")).willReturn(new ArrayList<>());

        given(dataFromServerServiceMock.saveAll(dataFromServerList)).willReturn(dataFromServerList);

    }


    @Test
    public void getIndexWithoutUrlToDownload_thenBadRequest() throws Exception {

        MvcResult mvcResult = mockMvc
                .perform(get("/api/documents/downloadAndSaveToDB"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }


    @Test
    public void getIndexWithEmptyUrlToDownload_thenBadRequest() throws Exception {

        MvcResult mvcResult = mockMvc
                .perform(get("/api/documents/downloadAndSaveToDB?endingUrl="))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }


    @Test
    public void getIndexWithCorrectUrlToDownload_thenOK() throws Exception {

        MvcResult mvcResult = mockMvc
                .perform(get("/api/documents/downloadAndSaveToDB?endingUrl=CorrectUrl"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String referenceJson = objectMapper.writeValueAsString(dataFromServerList);
        String jsonFromResponse = mvcResult.getResponse().getContentAsString();
        assertEquals(referenceJson, jsonFromResponse);

        List<DataFromServer> dataFromServerListFromResponse =
                objectMapper.readValue(jsonFromResponse, new TypeReference<List<DataFromServer>>() {
                });
        assertEquals(dataFromServerList, dataFromServerListFromResponse);

    }


    @Test
    public void getIndexWithIncorrectUrlToDownload_thenBadRequest() throws Exception {

        MvcResult mvcResult = mockMvc
                .perform(get("/api/documents/downloadAndSaveToDB?endingUrl=IncorrectUrl"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        String jsonFromResponse = mvcResult.getResponse().getContentAsString();
        List<DataFromServer> dataFromServerListFromResponse =
                objectMapper.readValue(jsonFromResponse, new TypeReference<List<DataFromServer>>() {
                });
        assertEquals(new ArrayList<>(), dataFromServerListFromResponse);
    }


}
