package org.example.jakartademo.user.views;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.example.jakartademo.user.persistence.Country;
import org.example.jakartademo.user.persistence.User;
import org.example.jakartademo.user.persistence.UserRepository;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@Getter
public class UserBacking implements Serializable {
    @Inject
    private UserRepository userRepository;
    private User user;

    @Setter
    private String username;
    @Setter
    private String email;
    @Setter
    private String countryCode;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Country getCountry(String countryCode) {
        return Country.getCountryByCode(countryCode);
    }

    public void saveUser() {
        user.setUsername(username);
        user.setEmail(email);
        user.setCountryCode(countryCode);
        user = userRepository.saveUser(user);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User saved!"));
        cancel();
    }

    public void newUser() {
        cancel();
        user = new User();
    }

    public void editUser(long id) {
        var user = userRepository.findById(id);
        if (user != null) {
            this.user = user;
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.countryCode = user.getCountryCode();
        }
    }

    public void cancel() {
        user = null;
        username = null;
        email = null;
        countryCode = null;
    }

    @SuppressWarnings("all")
    public Validator<String> getEMailValidator() {
        return ((context, component, value) -> {
            if (StringUtils.isNotBlank(value)) {
                if (!value.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
                    context.validationFailed();
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "EMail is invalid!", null));
                }
            }
        });
    }
}
