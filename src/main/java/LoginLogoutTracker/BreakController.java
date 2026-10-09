package LoginLogoutTracker;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/break")
public class BreakController {

    private final BreakService breakService;

    public BreakController(BreakService breakService) {
        this.breakService = breakService;
    }

    @PostMapping("/start/{userId}")
    public BreakSession startBreak(@PathVariable Long userId) {
        return breakService.startBreak(userId);
    }

    @PostMapping("/end/{breakId}")
    public BreakSession endBreak(@PathVariable Long breakId) {
        return breakService.endBreak(breakId);
    }

    @GetMapping("/history/{userId}")
    public List<BreakSession> getBreakHistory(@PathVariable Long userId) {
        return breakService.getBreakHistory(userId);
    }
}