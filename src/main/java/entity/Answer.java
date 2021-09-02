package entity;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Answer.selectAll", query = "SELECT a FROM Answer a")
@NamedQuery(name = "Answer.byUserProduct", query = "SELECT a FROM Answer a WHERE a.userByUserId = ?1 AND a" +
        ".productByProductId = ?2")
@NamedQuery(name = "Answer.byProductSent", query = "SELECT a FROM Answer a WHERE a.productByProductId = ?1 AND a" +
        ".successfullySent=1")
@NamedQuery(name = "Answer.byUserProductSent", query = "SELECT a FROM Answer a WHERE a.successfullySent = 1 AND a" +
        ".userByUserId = ?1 AND a" +
        ".productByProductId = ?2")

public class Answer {
    private int successfullySent;
    private String answerText;
    private int answerId;
    private User userByUserId;
    private Product productByProductId;
    private Question questionByQuestionId;



    @Basic
    @Column(name = "successfullySent", nullable = true)
    public int getSuccessfullySent() {
        return successfullySent;
    }

    public void setSuccessfullySent(int successfullySent) {
        this.successfullySent = successfullySent;
    }

    @Basic
    @Column(name = "answerText", nullable = true, length = 255)
    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    @Id
    @Column(name = "answerId", nullable = false)
    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;


        if (successfullySent != answer.successfullySent) return false;
        if (answerId != answer.answerId) return false;
        if (answerText != null ? !answerText.equals(answer.answerText) : answer.answerText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = successfullySent;

        result = 31 * result + (answerText != null ? answerText.hashCode() : 0);
        result = 31 * result + answerId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }
    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", nullable = false)
    public Product getProductByProductId() {
        return productByProductId;
    }
    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }

    @ManyToOne
    @JoinColumn(name = "questionId", referencedColumnName = "questionId", nullable = false)
    public Question getQuestionByQuestionId() {
        return questionByQuestionId;
    }
    public void setQuestionByQuestionId(Question questionByQuestionId) {
        this.questionByQuestionId = questionByQuestionId;
    }
}
