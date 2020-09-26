package wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import wp.Service.TeamService;
import wp.form.TeamCredentials;

@Component
public class TeamCredentialsRegisterValidator {
    private final TeamService teamService;

    public TeamCredentialsRegisterValidator(TeamService teamService) {
        this.teamService = teamService;
    }

    public boolean supports(Class<?> clazz) {
        return TeamCredentials.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            TeamCredentials registerForm = (TeamCredentials) target;
            if (!teamService.isNameVacant(registerForm.getName())) {
                errors.rejectValue("name", "name.is-in-use", "name is in use already");
            }
        }
    }
}
