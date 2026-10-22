
package LoginLogoutTracker;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final LoginSessionRepository loginSessionRepository;

    public AuthService(
            UserRepository userRepository,
            LoginSessionRepository loginSessionRepository) {
        this.userRepository = userRepository;
        this.loginSessionRepository = loginSessionRepository;
    }

    public LoginSession login(String username, String password) {

        Optional<User> result =
                userRepository.findByUsername(username);

        User user = result.orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Invalid username or password"));

        if (user.getPassword() == null
                || !user.getPassword().equals(password)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid username or password");
        }

        if (user.getActive() == null || !user.getActive()) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "User account is inactive");
        }

        boolean activeSession = loginSessionRepository
                .findByUser_IdOrderByLoginTimeDesc(user.getId())
                .stream()
                .anyMatch(session ->
                        "ACTIVE".equals(session.getStatus()));

        if (activeSession) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "User is already logged in");
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
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Active session not found"));

        LocalDateTime logoutTime = LocalDateTime.now();
        session.setLogoutTime(logoutTime);

        long duration = Duration.between(
                session.getLoginTime(),
                logoutTime).getSeconds();

        session.setDurationSeconds(duration);
        session.setStatus("LOGGED_OUT");

        return loginSessionRepository.save(session);
    }

    public List<LoginSession> getHistory(Long userId) {
        return loginSessionRepository
                .findByUser_IdOrderByLoginTimeDesc(userId);
    }
}
