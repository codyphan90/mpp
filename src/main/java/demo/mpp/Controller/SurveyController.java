package demo.mpp.Controller;

import demo.mpp.Response.Survey;
import demo.mpp.Service.SurveyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import demo.mpp.Constant.URL;
import demo.mpp.Entity.SurveyEntity;
import demo.mpp.Request.SurveyRequest;
import demo.mpp.Response.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = URL.SURVEY_URL)
public class SurveyController {
    private Logger logger = LogManager.getLogger(SurveyController.class);

    @Autowired
    SurveyService surveyService;

    @RequestMapping(value = URL.ID, method = RequestMethod.GET)
    public ResponseEntity getSurveyById(@PathVariable("id") Integer surveyId) {
        logger.info("Get Survey Id [{}]", surveyId);
        SurveyEntity surveyEntity = surveyService.getSurveyWithFullContent(surveyId);
        if (surveyEntity == null) {
            return new ResponseEntity("Can not find surveyId " + surveyId);
        } else {
            return new ResponseEntity<>(surveyEntity);
        }
    }

    @RequestMapping(value = "update", method = RequestMethod.GET)
    public ResponseEntity updateSurveyById(@PathVariable("id") Integer surveyId) {
        logger.info("Get Survey Id [{}]", surveyId);
        SurveyEntity surveyEntity = surveyService.getSurveyWithFullContent(surveyId);
        if (surveyEntity == null) {
            return new ResponseEntity("Can not find surveyId " + surveyId);
        } else {
            if (!surveyService.updateSurvey(surveyEntity)) {
                return new ResponseEntity<>(false, "Update survey failed");
            } else {
                return new ResponseEntity<>("Update survey successfully");
            }
        }
    }

    @RequestMapping(value ="list", method = RequestMethod.GET)
    public ResponseEntity getAllSurveyIDs() {
        logger.info("Get all Survey Ids");
        List<SurveyEntity> surveyEntities = surveyService.getAllSurvey();
        List<Survey> surveyList = new ArrayList<>();
        if (surveyEntities == null) {
            return new ResponseEntity("Can not find any survey");
        } else {
        	for (SurveyEntity ele: surveyEntities) {
        		Survey survey = new Survey(ele.getId(),ele.getName());
        		surveyList.add(survey);
        	}
        	return new ResponseEntity<>(surveyList);
        }
    }
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ResponseEntity createSurvey(@RequestBody SurveyRequest request) {
        SurveyEntity surveyEntity = surveyService.createSurvey(request.getSurveyEntity());
        if (surveyEntity!=null) {
            String successMessage = "Survey Id " + surveyEntity.getId() + " was created successfully";
            return new ResponseEntity<>(true, null, successMessage);
        } else {
            return new ResponseEntity("Can not create survey");
        }
    }

    @RequestMapping(value = URL.ID, method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity submitSurvey(@RequestBody SurveyRequest request) {
        if (surveyService.submitSurvey(request.getSurveyEntity())) {
            return new ResponseEntity<>(true, null, "Submit successfully");
        }
        return new ResponseEntity<> ("Can not submit");
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST,produces = { MediaType.ALL_VALUE })
    public void uploadCSV(@RequestParam("file0") MultipartFile file) throws IOException {
        surveyService.createSurveyFromCSV(file);
    }

}
