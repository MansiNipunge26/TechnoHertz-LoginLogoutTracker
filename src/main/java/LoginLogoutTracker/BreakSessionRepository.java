package LoginLogoutTracker;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BreakSessionRepository extends JpaRepository<BreakSession, Long> {

    Optional<BreakSession> findByIdAndStatus(Long id, String status);

    List<BreakSession> findByUser_IdOrderByBreakStartDesc(Long userId);
}