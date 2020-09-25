package wp.repository;
import ru.itmo.wp.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    int countByName(String login);

    ru.itmo.wp.domain.Team findByNameAndPassword(String login, String password);

    List<Team> findAllByOrderByIdDesc();

    void updatePasswordSha(long id, String name, String password);
}
