package LoginLogoutTracker;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final LoginSessionRepository loginSessionRepository;

    public AuthService(UserRepository userRepository,
                       LoginSessionRepository loginSessionRepository) {
        this.userRepository = userRepository;
        this.loginSessionRepository = loginSessionRepository;
    }

    public LoginSession login(String username, String password) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        if (!user.getActive()) {
            throw new RuntimeException("User account is inactive");
        }
        if (loginSessionRepository.findByUser_IdOrderByLoginTimeDesc(user.getId())
                .stream()
                .anyMatch(session -> "ACTIVE".equals(session.getStatus()))) {

            throw new RuntimeException("User is already logged in");
        }

        LoginSession session = new LoginSession();

        session.setUser(user);
        session.setLoginTime(LocalDateTime.now());
        session.setStatus("ACTIVE");

        return loginSessionRepository.save(session);
    }

    public LoginSession logout(Long sessionId) {

        LoginSession session = loginSessionRepository
                .findByIdAndStatus(sessionId, "ACTIVE")
                .orElseThrow(() -> new RuntimeException("Active session not found"));

        LocalDateTime logoutTime = LocalDateTime.now();

        session.setLogoutTime(logoutTime);

        long duration = Duration.between(
                session.getLoginTime(),
                logoutTime
        ).getSeconds();

        session.setDurationSeconds(duration);
        session.setStatus("LOGGED_OUT");

        return loginSessionRepository.save(session);
    }
    
    public java.util.List<LoginSession> getHistory(Long userId) {
        return loginSessionRepository.findByUser_IdOrderByLoginTimeDesc(userId);
    }
}