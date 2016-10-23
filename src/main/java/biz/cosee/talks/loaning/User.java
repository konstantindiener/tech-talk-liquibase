package biz.cosee.talks.loaning;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    private boolean locked;

    @ManyToOne
    @JoinColumn(name = "language_code")
    private Language language = Language.EN;

    @OneToMany(mappedBy = "user")
    private List<Loan> loans = new ArrayList<>();

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public boolean hasMoreThanNumberOfLoans(int loanThreshold) {
        return loans.size() >= loanThreshold;
    }

    public void lock() {
        this.locked = true;
    }

    public boolean isLocked() {
        return locked;
    }

    public Language getLanguage() {
        return language;
    }
}
