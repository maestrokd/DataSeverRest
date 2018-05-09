package datasaver.model.service;

import datasaver.model.entity.DataFromServer;

import java.util.List;

public interface DataFromServerService {
    List<DataFromServer> saveAll(List<DataFromServer> dataFromServerList);
}
