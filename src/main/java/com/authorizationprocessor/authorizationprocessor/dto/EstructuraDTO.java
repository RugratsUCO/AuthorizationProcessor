package com.authorizationprocessor.authorizationprocessor.dto;

import com.authorizationprocessor.authorizationprocessor.domain.estructura.Estructura;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstructuraDTO {
    HttpStatus estado;
    Estructura estructura;
}
