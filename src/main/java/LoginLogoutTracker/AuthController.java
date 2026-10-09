package LoginLogoutTracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginSession login(
            @RequestParam String username,
            @RequestParam String password) {

        return authService.login(username, password);
    }

    @PostMapping("/logout/{sessionId}")
    public LoginSession logout(@PathVariable Long sessionId) {

        return authService.logout(sessionId);
    }
    @GetMapping("/history/{userId}")
    public java.util.List<LoginSession> getHistory(@PathVariable Long userId) {
        return authService.getHistory(userId);
    }
}
