package survey.demo.Entity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name = "question")
public class QuestionEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "surveyId")
    private Integer surveyId;

    @Column(name = "content")
    private String content;

    @Column(name = "rating")
    private Integer rating;

    public QuestionEntity() {
    }

    public QuestionEntity(String content, Integer rating) {
        this.content = content;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
