package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NamedQuery(name = "User.selectAll", query = "SELECT p FROM User p")
@NamedQuery(name = "User.selectLeaderboard", query = "SELECT p FROM User p WHERE not (p.username = 'admin') AND " +
        "p.blocked = 0 ORDER BY " +
        "p.points DESC")
@NamedQuery(name = "User.byId", query = "SELECT p FROM User p WHERE p.userId = ?1")
@NamedQuery(name = "User.byUsername", query = "SELECT p FROM User p WHERE p.username = ?1")
@NamedQuery(name = "User.byUsernamePassword", query = "SELECT p FROM User p WHERE p.username = ?1 AND p.password = ?2")
@NamedQuery(name = "User.byAnswer", query = "SELECT u FROM User u WHERE EXISTS(SELECT a FROM Answer a WHERE a" +
        ".userByUserId=u AND a.productByProductId = ?1 AND a.successfullySent = ?2)")


public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private Integer points;
    private int blocked;
    private Collection<Answer> answersByUserId;
    private Collection<Login> loginsByUserId;



    @Id
    @Column(name = "userId", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "points", nullable = true)
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Basic
    @Column(name = "blocked", nullable = true)
    public int getBlocked() {
        return blocked;
    }

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (blocked != user.blocked) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (points != null ? !points.equals(user.points) : user.points != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        result = 31 * result + blocked;
        return result;
    }

    @OneToMany(mappedBy = "userByUserId", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Collection<Answer> getAnswersByUserId() {
        return answersByUserId;
    }
    public void setAnswersByUserId(Collection<Answer> answersByUserId) {
        this.answersByUserId = answersByUserId;
    }
    @OneToMany(mappedBy = "userByUserId", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Collection<Login> getLoginsByUserId() {
        return loginsByUserId;
    }
    public void setLoginsByUserId(Collection<Login> loginsByUserId) {
        this.loginsByUserId = loginsByUserId;
    }

}
