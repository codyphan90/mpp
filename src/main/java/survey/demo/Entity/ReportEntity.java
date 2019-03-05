package survey.demo.Entity;

public class ReportEntity {
	private Integer surveyID;
	private String surveyName;
	
	public ReportEntity(Integer id, String name) {
		this.surveyID=id;
		this.surveyName=name;
	}

	public String getName() {
		return surveyName;
	}

	public Integer getSurveyID() {
		return surveyID;
	}
}
