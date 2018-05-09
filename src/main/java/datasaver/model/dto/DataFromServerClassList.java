package datasaver.model.dto;

import datasaver.model.entity.DataFromServer;

import java.util.List;

public class DataFromServerClassList {
    private List<DataFromServer> data;

    public List<DataFromServer> getData() {
        return data;
    }

    public void setData(List<DataFromServer> data) {
        this.data = data;
    }
}
