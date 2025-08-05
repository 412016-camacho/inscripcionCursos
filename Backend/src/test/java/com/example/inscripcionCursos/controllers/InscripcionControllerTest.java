package com.example.inscripcionCursos.controllers;

import com.example.inscripcionCursos.dtos.InscripcionDto;
import com.example.inscripcionCursos.services.InscripcionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(InscripcionController.class)
class InscripcionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InscripcionService inscripcionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void altaInscripcionTest() throws Exception {
        InscripcionDto inscripcion = new InscripcionDto(1L, 1L, 1L);

        when(inscripcionService.postInscripcion(any())).thenReturn(inscripcion);

        mockMvc.perform(post("/api/v1/inscripciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inscripcion))) //el param q recibe
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.alumno_id", is(1)))
                .andExpect(jsonPath("$.curso_id", is(1)));
    }

    @Test
    void cancelarInscripcionTest() throws Exception {
        mockMvc.perform(delete("/api/v1/inscripciones")
                        .param("alumnoId", "1")
                        .param("cursoId", "1"))
                .andExpect(status().isOk());
    }
}