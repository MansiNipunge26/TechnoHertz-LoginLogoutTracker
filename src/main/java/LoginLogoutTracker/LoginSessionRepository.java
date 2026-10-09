package LoginLogoutTracker;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginSessionRepository extends JpaRepository<LoginSession, Long> {

    Optional<LoginSession> findByIdAndStatus(Long id, String status);

    List<LoginSession> findByUser_IdOrderByLoginTimeDesc(Long userId);
}