package demo.mpp.Response;

public class Survey {
	private Integer surveyId;
	private String surveyName;
	
	public Survey(Integer id, String name) {
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
