package wp.Service;

import org.springframework.stereotype.Service;
import wp.form.TeamCredentials;
import wp.repository.TeamRepository;
import wp.domain.Team;
import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team register(TeamCredentials teamCredentials) {
        Team team = new Team();
        team.setName(teamCredentials.getName());
        teamRepository.save(team);
        teamRepository.updatePasswordSha(team.getId(), teamCredentials.getName(), teamCredentials.getPassword());
        return team;
    }

    public boolean isNameVacant(String login) {
        return teamRepository.countByName(login) == 0;
    }

    public Team findByNameAndPassword(String login, String password) {
        return login == null || password == null ? null : teamRepository.findByNameAndPassword(login, password);
    }

    public Team findById(Long id) {
        return id == null ? null : teamRepository.findById(id).orElse(null);
    }

    public List<Team> findAll() {
        return teamRepository.findAllByOrderByIdDesc();
    }

}
