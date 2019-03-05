package survey.demo.Entity;

public class ReportEntity {
	private Integer surveyId;
	private String surveyName;
	
	public ReportEntity(Integer id, String name) {
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
