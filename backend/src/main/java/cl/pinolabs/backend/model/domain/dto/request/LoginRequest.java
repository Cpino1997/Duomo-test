package cl.pinolabs.backend.model.domain.dto.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "El usuario no puede estar Estar vacio :c!")
    private String username;

    @NotBlank(message = "La contraseña no puede estar vacia :c!")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
