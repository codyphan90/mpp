package survey.demo.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import survey.demo.Entity.SurveyEntity;

public class SurveyRequest {

    @JsonProperty("surveyEntity")
    private SurveyEntity surveyEntity;

    public SurveyEntity getSurveyEntity() {
        return surveyEntity;
    }

    public void setSurveyEntity(SurveyEntity surveyEntity) {
        this.surveyEntity = surveyEntity;
    }
}
