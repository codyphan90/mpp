package survey.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import survey.demo.Constant.*;

import survey.demo.Entity.ReportEntity;
import survey.demo.Entity.SurveyEntity;
import survey.demo.Response.ResponseEntity;
import survey.demo.Service.SurveyService;
import survey.demo.Service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@CrossOrigin
@RestController
@RequestMapping(value = URL.SURVEY_URL)
public class ReportController {
	@Autowired
	private SurveyService surveyService;
	
	private Logger logger = LogManager.getLogger(ReportController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ResponseEntity generateReport(@RequestBody Integer surveyId) {
		logger.info("Generate report for Survey Id [{}]", surveyId);
        SurveyEntity surveyEntity = surveyService.getSurveyWithFullContent(surveyId);
        if (surveyEntity == null) {
            return new ResponseEntity("Can not find report for surveyId " + surveyId);
        } else {
            return new ResponseEntity<>(surveyEntity);
        }		
	}
}
