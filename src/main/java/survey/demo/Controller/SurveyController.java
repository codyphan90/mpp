package survey.demo.Controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import survey.demo.Constant.URL;
import survey.demo.Entity.SurveyEntity;
import survey.demo.Response.ResponseEntity;
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
}
