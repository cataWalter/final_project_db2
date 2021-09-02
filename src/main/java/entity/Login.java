package entity;

import javax.persistence.*;

@Entity
public class Login {
    private int loginId;
    private String loginDate;
    private String loginTime;
    private User userByUserId;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    public User getUserByUserId() {
        return userByUserId;
    }
    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }


    @Id
    @Column(name = "loginId", nullable = false)
    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }



    @Basic
    @Column(name = "loginDate", nullable = true)
    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    @Basic
    @Column(name = "loginTime", nullable = true)
    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Login login = (Login) o;

        if (loginId != login.loginId) return false;
        if (loginDate != null ? !loginDate.equals(login.loginDate) : login.loginDate != null) return false;
        if (loginTime != null ? !loginTime.equals(login.loginTime) : login.loginTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = loginId;
        result = 31 * result + (loginDate != null ? loginDate.hashCode() : 0);
        result = 31 * result + (loginTime != null ? loginTime.hashCode() : 0);
        return result;
    }


}
