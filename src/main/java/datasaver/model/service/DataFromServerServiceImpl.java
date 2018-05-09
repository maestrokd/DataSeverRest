package datasaver.model.service;

import datasaver.model.entity.DataFromServer;
import datasaver.model.repository.DataFromServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "dataFromServerService")
public class DataFromServerServiceImpl implements DataFromServerService {

    private DataFromServerRepository dataFromServerRepository;

    @Autowired
    public void setDataFromServerRepository(DataFromServerRepository dataFromServerRepository) {
        this.dataFromServerRepository = dataFromServerRepository;
    }

    @Override
    public List<DataFromServer> saveAll(List<DataFromServer> dataFromServerList) {
        List<DataFromServer> savedDataFromServerList = new ArrayList<>();
        for (DataFromServer dfs : dataFromServerList) {
            savedDataFromServerList.add(dataFromServerRepository.save(dfs));
        }
        return savedDataFromServerList;
    }
}
