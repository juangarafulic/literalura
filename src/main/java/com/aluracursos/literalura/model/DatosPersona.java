package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosPersona(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer fechaNacimiento

) {
}
