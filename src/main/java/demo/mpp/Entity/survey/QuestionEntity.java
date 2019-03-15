package demo.mpp.Entity.survey;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question")
public class QuestionEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "survey_id")
    private Integer surveyId;

    @Column(name = "content")
    private String content;

    @Column(name = "type")
    private String type;

    @Column(name = "rating")
    private Double rating;

    @OneToMany(mappedBy = "questionId")
    List<MCAnswerEntity> mcAnswerEntityList;

    @OneToMany(mappedBy = "questionId")
    List<OEAnswerEntity> oeAnswerEntityList;

    public QuestionEntity() {
    }

    public QuestionEntity(Integer surveyId, String content) {
        this.surveyId = surveyId;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<MCAnswerEntity> getMcAnswerEntityList() {
        return mcAnswerEntityList;
    }

    public void setMcAnswerEntityList(List<MCAnswerEntity> mcAnswerEntityList) {
        this.mcAnswerEntityList = mcAnswerEntityList;
    }

    public List<OEAnswerEntity> getOeAnswerEntityList() {
        return oeAnswerEntityList;
    }

    public void setOeAnswerEntityList(List<OEAnswerEntity> oeAnswerEntityList) {
        this.oeAnswerEntityList = oeAnswerEntityList;
    }
}
