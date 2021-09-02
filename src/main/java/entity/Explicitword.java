package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Explicitword.selectAll", query = "SELECT e FROM Explicitword e")
public class Explicitword {
    private String word;

    @Id
    @Column(name = "word", nullable = false, length = 255)
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Explicitword that = (Explicitword) o;

        if (word != null ? !word.equals(that.word) : that.word != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return word != null ? word.hashCode() : 0;
    }
}
