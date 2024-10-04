package ekg.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "results", schema = "ekg_test")
public class ResultsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "user_Id")
    private long userId;
    @Basic
    @Column(name = "note")
    private String note;
    @Basic
    @Column(name = "result")
    private String result;

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultsEntity that = (ResultsEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (!Objects.equals(note, that.note)) return false;
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        int result1 = (int) id;
        result1 = (int) (31 * result1 + userId);
        result1 = 31 * result1 + (note != null ? note.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        return "ResultsEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", note='" + note + '\'' +
                ", result=" + result +
                '}';
    }
}
