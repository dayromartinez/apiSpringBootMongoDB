package com.springMongoDb.Biblioteca.ControllerRecursoTests;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.springMongoDb.Biblioteca.DTO.RecursoDTO;
import com.springMongoDb.Biblioteca.Service.RecursoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class ControllerRecursoTest {

    @MockBean
    private RecursoService servicioRecurso;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /recursos success")
    public void findAll() throws Exception {

        //setup mock service
        var dato1 = new RecursoDTO();
        dato1.setId("1111");
        dato1.setName("Cien años de soledad");
        dato1.setTipoRecurso("Libro");
        dato1.setAreaTematica("Literatura");
        dato1.setEnPrestamo(false);
        dato1.setFechaPrestamo("");

        var dato2 = new RecursoDTO();
        dato2.setId("2222");
        dato2.setName("El Testigo");
        dato2.setTipoRecurso("Documental");
        dato2.setAreaTematica("Cine");
        dato2.setEnPrestamo(true);
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
        String text = date.format(formatters);
        dato2.setFechaPrestamo(text);

        var lista = new ArrayList<RecursoDTO>();
        lista.add(dato1);
        lista.add(dato2);
        Mockito.when(servicioRecurso.obtenerTodosRecursos()).thenReturn(lista);

        //execute Get request
        mockMvc.perform(get("/biblioteca/recursos"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("1111")))
                .andExpect(jsonPath("$[0].name", is("Cien años de soledad")))
                .andExpect(jsonPath("$[0].tipoRecurso", is("Libro")))
                .andExpect(jsonPath("$[0].areaTematica", is("Literatura")))
                .andExpect(jsonPath("$[0].enPrestamo", is(false)))
                .andExpect(jsonPath("$[0].fechaPrestamo", is("")))
                .andExpect(jsonPath("$[1].id", is("2222")))
                .andExpect(jsonPath("$[1].name", is("El Testigo")))
                .andExpect(jsonPath("$[1].tipoRecurso", is("Documental")))
                .andExpect(jsonPath("$[1].areaTematica", is("Cine")))
                .andExpect(jsonPath("$[1].enPrestamo", is(true)));
    }

    @Test
    @DisplayName("POST /recurso/crear success")
    public void create() throws Exception {

        // Setup our mocked service
        var datoPost = new RecursoDTO();
        datoPost.setName("Cien años de soledad");
        datoPost.setTipoRecurso("Libro");
        datoPost.setAreaTematica("Literatura");
        datoPost.setEnPrestamo(false);
        datoPost.setFechaPrestamo("");

        var datoReturn = new RecursoDTO();
        datoReturn.setId("1111");
        datoReturn.setName("Cien años de soledad");
        datoReturn.setTipoRecurso("Libro");

        Mockito.when(servicioRecurso.crearRecurso(Mockito.refEq(datoPost))).thenReturn(datoReturn);

        // Execute the POST request
        mockMvc.perform(post("/biblioteca/recurso/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(datoPost)))

                // Validate the response code and content type
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$.id", is("1111")))
                .andExpect(jsonPath("$.name", is("Cien años de soledad")))
                .andExpect(jsonPath("$.tipoRecurso", is("Libro")));
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
