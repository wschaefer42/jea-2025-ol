package org.example.jakartademo.user.views;

import jakarta.enterprise.inject.Model;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.example.jakartademo.user.persistence.User;
import org.example.jakartademo.user.persistence.UserRepository;

@Model
public class UserConverter implements Converter<User> {
    @Inject
    private UserRepository userRepository;

    @Override
    public User getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringUtils.isBlank(value)) return null;
        try {
            return userRepository.findById(Long.parseLong(value));
        } catch (NumberFormatException e) {
            throw new ConverterException(String.format("Value is not a valid ID %s", value), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, User value) {
        return value == null ? "" : String.valueOf(value.getId());
    }
}
