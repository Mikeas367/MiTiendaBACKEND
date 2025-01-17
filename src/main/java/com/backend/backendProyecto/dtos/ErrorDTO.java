package com.backend.backendProyecto.dtos;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

// este objeto lo utilizo para mandar el msj de error, se podria poner un codigo de error para tener un
// listado de los posibles errores
public class ErrorDTO {
    private String message;

    // Constructor vacío
    public ErrorDTO() {
    }

    // Constructor con argumentos
    public ErrorDTO(String message) {
        this.message = message;
    }

    // Getter
    public String getMessage() {
        return message;
    }

    // Setter
    public void setMessage(String message) {
        this.message = message;
    }

    // Método toString
    @Override
    public String toString() {
        return "ErrorDTO{" +
                "message='" + message + '\'' +
                '}';
    }

    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorDTO errorDTO = (ErrorDTO) o;
        return Objects.equals(message, errorDTO.message);
    }

    // Método hashCode
    @Override
    public int hashCode() {
        return Objects.hash(message);
    }
}
