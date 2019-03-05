package survey.demo.Response;

public class SurveyList {
	private Integer surveyId;
	private String surveyName;
	
	public SurveyList(Integer id, String name) {
		this.surveyId =id;
		this.surveyName=name;
	}

	public String getName() {
		return surveyName;
	}

	public Integer getSurveyId() {
		return surveyId;
	}
}
