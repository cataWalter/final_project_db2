package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NamedQuery(name = "Question.byProduct", query = "SELECT p FROM Question p WHERE p.productByProductId = ?1")
@NamedQuery(name = "Question.byDefault", query = "SELECT p FROM Question p WHERE p.marketingQuestion = ?1")
@NamedQuery(name = "Question.byId", query = "SELECT p FROM Question p WHERE p.questionId = ?1")

public class Question {
    private int questionId;
    private int marketingQuestion;
    private String questionText;
    private Collection<Answer> answersByQuestionId;
    private Product productByProductId;

    @Id
    @Column(name = "questionId", nullable = false)
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "marketingQuestion", nullable = true)
    public int getMarketingQuestion() {
        return marketingQuestion;
    }

    public void setMarketingQuestion(int marketingQuestion) {
        this.marketingQuestion = marketingQuestion;
    }

    @Basic
    @Column(name = "questionText", nullable = true, length = 255)
    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (questionId != question.questionId) return false;
        if (marketingQuestion != question.marketingQuestion) return false;
        if (questionText != null ? !questionText.equals(question.questionText) : question.questionText != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = questionId;
        result = 31 * result + marketingQuestion;
        result = 31 * result + (questionText != null ? questionText.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "questionByQuestionId", fetch = FetchType.LAZY)
    public Collection<Answer> getAnswersByQuestionId() {
        return answersByQuestionId;
    }

    public void setAnswersByQuestionId(Collection<Answer> answersByQuestionId) {
        this.answersByQuestionId = answersByQuestionId;
    }

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }
}
