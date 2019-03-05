package survey.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import survey.demo.Constant.URL;
import survey.demo.Entity.SurveyEntity;
import survey.demo.Request.SurveyRequest;
import survey.demo.Response.ResponseEntity;
import survey.demo.Response.SurveyList;
import survey.demo.Service.SurveyService;

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

    @RequestMapping(value ="list", method = RequestMethod.GET)
    public ResponseEntity getAllSurveyIDs() {
        logger.info("Get all Survey Ids");
        List<SurveyEntity> surveyEntities = surveyService.getAllSurvey();
        List<SurveyList> surveyList = new ArrayList<>();
        if (surveyEntities == null) {
            return new ResponseEntity("Can not find any survey");
        } else {
        	for (SurveyEntity ele: surveyEntities) {
        		SurveyList survey = new SurveyList(ele.getId(),ele.getName());
        		surveyList.add(survey);
        	}
        	return new ResponseEntity<>(surveyList);
        }
    }
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ResponseEntity createSurvey(@RequestBody SurveyRequest request) {
        SurveyEntity surveyEntity = surveyService.createSurvey(request.getSurveyEntity());
        String successMessage = "Survey Id " + surveyEntity.getId() + " was created successfully";

        return surveyEntity.getId() != null ? new ResponseEntity<>(true, null, successMessage)
                : new ResponseEntity("Can not create survey");
    }

    @RequestMapping(value = URL.ID, method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity submitSurvey(@RequestBody SurveyRequest request) {
        if (surveyService.submitSurvey(request.getSurveyEntity())) {
            return new ResponseEntity<>(true, null, "Submit successfully");
        }
        return new ResponseEntity<> ("Can not submit");
    }
}
