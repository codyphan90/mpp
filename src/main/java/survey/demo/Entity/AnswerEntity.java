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

    @Column(name = "count")
    private Integer count;

    private Boolean isSelected;

    @Column(name = "is_default")
    private Boolean isDefault;

    public AnswerEntity() {
    }

    public AnswerEntity(String content) {
        this.content = content;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }
}
