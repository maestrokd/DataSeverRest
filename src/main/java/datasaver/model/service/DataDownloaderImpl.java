package datasaver.model.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import datasaver.model.entity.DataFromServer;
import datasaver.model.dto.DataFromServerClassList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "dataDownloader")
public class DataDownloaderImpl implements DataDownloader {

    // Fields
    private ObjectMapper objectMapper;
    private OkHttpClient client;


    // Setters
    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setClient(OkHttpClient client) {
        this.client = client;
    }


    // Methods
    @Override
    public List<DataFromServer> downloadDataFromServer(String url) throws IOException {
        String jsonFromServer = httpGet(url);

        try {
            DataFromServerClassList dataFromServerClassList = objectMapper.readValue(jsonFromServer, DataFromServerClassList.class);
            return dataFromServerClassList.getData();
        } catch (UnrecognizedPropertyException e) {
            return new ArrayList<>();
        }
    }


    private String httpGet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}