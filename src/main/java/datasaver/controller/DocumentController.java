package datasaver.controller;

import datasaver.model.entity.DataFromServer;
import datasaver.model.service.DataDownloader;
import datasaver.model.service.DataFromServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DocumentController {

    // Fields
    private static final String STARTING_URL = "https://lb.api-sandbox.openprocurement.org/api/2.4/contracts/";

    private DataDownloader dataDownloader;
    private DataFromServerService dataFromServerService;


    // Setters
    @Autowired
    public void setDataDownloader(DataDownloader dataDownloader) {
        this.dataDownloader = dataDownloader;
    }

    @Autowired
    public void setDataFromServerService(DataFromServerService dataFromServerService) {
        this.dataFromServerService = dataFromServerService;
    }


    // Methods
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public ModelAndView getIndex(
//            @ModelAttribute("endingUrl")
//                    String endingUrl
//    ) throws IOException {
//        if ("".equals(endingUrl)) {
//            return new ModelAndView("index");
//        }
//
//        String url = STARTING_URL + endingUrl;
//        ModelAndView modelAndView = new ModelAndView();
//
//        List<DataFromServer> dataFromServerList = dataDownloader.downloadDataFromServer(url);
//
//        if (dataFromServerList.isEmpty()) {
//            modelAndView.addObject("errorMessage", "Bad URL");
//            modelAndView.setViewName("index");
//            return modelAndView;
//        }
//
//        List<DataFromServer> dataFromServerListFromDB = dataFromServerService.saveAll(dataFromServerList);
//        modelAndView.addObject("numberOfSavedObjects", dataFromServerListFromDB.size());
//        modelAndView.addObject("myObjects", dataFromServerListFromDB);
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }





    @RequestMapping(value = "/api/documents/downloadAndSaveToDB", method = RequestMethod.GET)
    public ResponseEntity<List<DataFromServer>> doDownloadAndSaveToDB(
            @ModelAttribute("endingUrl")
                    String endingUrl
    ) throws IOException {
        if ("".equals(endingUrl)) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }

        String url = STARTING_URL + endingUrl;

        List<DataFromServer> dataFromServerList = dataDownloader.downloadDataFromServer(url);

        if (dataFromServerList.isEmpty()) {
            return new ResponseEntity<List<DataFromServer>>(dataFromServerList, HttpStatus.BAD_REQUEST);
        }

        List<DataFromServer> dataFromServerListFromDB = dataFromServerService.saveAll(dataFromServerList);
        return new ResponseEntity<List<DataFromServer>>(dataFromServerListFromDB, HttpStatus.OK);
    }


}
