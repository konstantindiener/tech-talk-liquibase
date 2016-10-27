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

    @OneToMany(mappedBy = "user")
    private List<Loan> loans = new ArrayList<>();

    private boolean locked;

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

    public String getUsername() {
        return username;
    }

    public void lock() {
        locked = true;
    }

    public boolean isLocked() {
        return locked;
    }

    public void unlock() {
        locked = false;
    }
}
