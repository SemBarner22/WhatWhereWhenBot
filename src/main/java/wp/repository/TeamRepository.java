package wp.repository;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import wp.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TeamRepository extends JpaRepository<Team, Long> {
    int countByName(String login);

    @Query(value = "SELECT * FROM team WHERE name=?1 AND passwordSha=SHA1(CONCAT('1be3db47a7684152', ?1, ?2))", nativeQuery = true)
    Team findByNameAndPassword(String login, String password);

    List<Team> findAllByOrderByIdDesc();

    @Transactional
    @Modifying
    @Query(value = "UPDATE team SET passwordSha=SHA1(CONCAT('1be3db47a7684152', ?2, ?3)) WHERE id=?1", nativeQuery = true)
    void updatePasswordSha(long id, String name, String password);
}
