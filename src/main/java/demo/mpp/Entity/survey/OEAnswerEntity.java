package demo.mpp.Entity.survey;

import javax.persistence.*;

@Entity
@Table(name = "oe_answer")
public class OEAnswerEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "questionId")
    private Integer questionId;

    @Column(name = "content")
    private String content;

    public OEAnswerEntity() {
    }

    public OEAnswerEntity(Integer userId, Integer questionId, String content) {
        this.userId = userId;
        this.questionId = questionId;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
