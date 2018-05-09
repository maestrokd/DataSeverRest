package datasaver.model.service;

import datasaver.model.entity.DataFromServer;

import java.io.IOException;
import java.util.List;

public interface DataDownloader {

    List<DataFromServer> downloadDataFromServer(String url) throws IOException;

}
