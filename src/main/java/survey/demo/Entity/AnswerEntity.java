package survey.demo.Entity;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class AnswerEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "content")
    private String content;

    public AnswerEntity() {
    }

    public AnswerEntity(Integer questionId, String content) {
        this.questionId = questionId;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
