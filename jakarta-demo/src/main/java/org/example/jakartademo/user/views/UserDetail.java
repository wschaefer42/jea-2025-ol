package org.example.jakartademo.user.views;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.example.jakartademo.user.persistence.Country;
import org.example.jakartademo.user.persistence.User;
import org.example.jakartademo.user.persistence.UserRepository;

import java.io.Serializable;
import java.util.List;

@Log
@Named
@ViewScoped
@Getter
public class UserDetail implements Serializable {
    @Setter
    private User user = null;

    @Setter
    private String username;
    @Setter
    private String email;
    @Setter
    private String countryCode;

    @Inject
    private UserRepository userRepository;

    public List<Country> getCountries() {
        return Country.getCountries();
    }

    public void save() {
        var user = this.user;
        if (user == null) {
            user = new User();
        }
        user.setUsername(username);
        user.setEmail(email);
        user.setCountryCode(countryCode);
        this.user = userRepository.saveUser(user);
    }

    public Validator<String> getEMailValidator() {
        return ((context, component, value) -> {
            if (StringUtils.isNotBlank(value)) {
                if (!value.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
                    log.warning("Invalid email: " + value);
                    context.validationFailed();
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "EMail is invalid!", null));
                }
            }
        });
    }

    public void debug() {
        log.info(String.format("User: %s, %s, %s", username, email, countryCode));
    }
}
