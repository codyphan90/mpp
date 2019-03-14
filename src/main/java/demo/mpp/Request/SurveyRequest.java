package demo.mpp.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import demo.mpp.Entity.SurveyEntity;

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
