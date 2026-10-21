
package LoginLogoutTracker;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "break_sessions")
public class BreakSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    @Column(name = "break_start")
    private LocalDateTime breakStart;

    @Column(name = "break_end")
    private LocalDateTime breakEnd;

    @Column(name = "break_duration_seconds")
    private Long breakDurationSeconds;

    @Column(name = "status")
    private String status;

    public BreakSession() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getBreakStart() {
        return breakStart;
    }

    public void setBreakStart(LocalDateTime breakStart) {
        this.breakStart = breakStart;
    }

    public LocalDateTime getBreakEnd() {
        return breakEnd;
    }

    public void setBreakEnd(LocalDateTime breakEnd) {
        this.breakEnd = breakEnd;
    }

    public Long getBreakDurationSeconds() {
        return breakDurationSeconds;
    }

    public void setBreakDurationSeconds(Long breakDurationSeconds) {
        this.breakDurationSeconds = breakDurationSeconds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
