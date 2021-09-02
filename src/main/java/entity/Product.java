package entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
@NamedQuery(name = "Product.selectAll", query = "SELECT p FROM Product p")
@NamedQuery(name = "Product.byId", query = "SELECT p FROM Product p WHERE p.productId = ?1")
@NamedQuery(name = "Product.byName", query = "SELECT p FROM Product p WHERE p.productName = ?1")
@NamedQuery(name = "Product.byDay", query = "SELECT p FROM Product p WHERE p.day = ?1")
public class Product {
    private int productId;
    private String productName;
    private String day;
    private byte[] productImage;
    private Collection<Answer> answersByProductId;
    private Collection<Question> questionsByProductId;
    @OneToMany(mappedBy = "productByProductId", fetch = FetchType.LAZY)
    public Collection<Answer> getAnswersByProductId() {
        return answersByProductId;
    }
    public void setAnswersByProductId(Collection<Answer> answersByProductId) {
        this.answersByProductId = answersByProductId;
    }
    @OneToMany(mappedBy = "productByProductId", fetch = FetchType.LAZY)
    public Collection<Question> getQuestionsByProductId() {
        return questionsByProductId;
    }
    public void setQuestionsByProductId(Collection<Question> questionsByProductId) {
        this.questionsByProductId = questionsByProductId;
    }

    @Id
    @Column(name = "productId", nullable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "productName", nullable = false, length = 255)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "day", nullable = false)
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Basic
    @Column(name = "productImage")
    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productId != product.productId) return false;
        if (productName != null ? !productName.equals(product.productName) : product.productName != null) return false;
        if (day != null ? !day.equals(product.day) : product.day != null) return false;
        if (!Arrays.equals(productImage, product.productImage)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(productImage);
        return result;
    }


}
