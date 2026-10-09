package LoginLogoutTracker;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BreakService {

    private final UserRepository userRepository;
    private final BreakSessionRepository breakSessionRepository;

    public BreakService(UserRepository userRepository,
                        BreakSessionRepository breakSessionRepository) {
        this.userRepository = userRepository;
        this.breakSessionRepository = breakSessionRepository;
    }

    public BreakSession startBreak(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BreakSession breakSession = new BreakSession();

        breakSession.setUser(user);
        breakSession.setBreakStart(LocalDateTime.now());
        breakSession.setStatus("BREAK_ACTIVE");

        return breakSessionRepository.save(breakSession);
    }

    public BreakSession endBreak(Long breakId) {

        BreakSession breakSession = breakSessionRepository
                .findByIdAndStatus(breakId, "BREAK_ACTIVE")
                .orElseThrow(() -> new RuntimeException("Active break not found"));

        LocalDateTime breakEnd = LocalDateTime.now();

        breakSession.setBreakEnd(breakEnd);

        long duration = Duration.between(
                breakSession.getBreakStart(),
                breakEnd
        ).getSeconds();

        breakSession.setBreakDurationSeconds(duration);
        breakSession.setStatus("BREAK_ENDED");

        return breakSessionRepository.save(breakSession);
    }

    public List<BreakSession> getBreakHistory(Long userId) {
        return breakSessionRepository
                .findByUser_IdOrderByBreakStartDesc(userId);
    }
}