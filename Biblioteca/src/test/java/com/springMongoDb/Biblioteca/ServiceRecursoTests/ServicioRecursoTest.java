package com.springMongoDb.Biblioteca.ServiceRecursoTests;

import com.springMongoDb.Biblioteca.Collection.Recurso;
import com.springMongoDb.Biblioteca.DTO.RecursoDTO;
import com.springMongoDb.Biblioteca.Repository.RecursoRepository;
import com.springMongoDb.Biblioteca.Service.RecursoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ServicioRecursoTest {

    @MockBean
    private RecursoRepository repositorioRecurso;

    @Autowired
    private RecursoService servicioRecurso;

    @Test
    @DisplayName("Test findAll Success")
    public void obtenerRecursos() {

        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setName("Cien años de soledad");
        dato1.setTipoRecurso("Libro");
        dato1.setAreaTematica("Literatura");
        dato1.setEnPrestamo(false);
        dato1.setFechaPrestamo("");

        var dato2 = new Recurso();
        dato2.setId("2222");
        dato2.setName("El Testigo");
        dato2.setTipoRecurso("Documental");
        dato2.setAreaTematica("Cine");
        dato2.setEnPrestamo(true);
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
        String text = date.format(formatters);
        dato2.setFechaPrestamo(text);

        var lista = new ArrayList<Recurso>();
        lista.add(dato1);
        lista.add(dato2);
        Mockito.when(repositorioRecurso.findAll()).thenReturn(lista);

        var resultado = servicioRecurso.obtenerTodosRecursos();

        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals(dato1.getName(), resultado.get(0).getName());
        Assertions.assertEquals(dato2.getName(), resultado.get(1).getName());

        verify(repositorioRecurso).findAll();
    }

    @Test
    @DisplayName("Test findById Success")
    public void obtenerRecursoById() {

        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setName("Cien años de soledad");
        dato1.setTipoRecurso("Libro");
        dato1.setAreaTematica("Literatura");
        dato1.setEnPrestamo(false);
        dato1.setFechaPrestamo("");

        Mockito.when(repositorioRecurso.findById("1111")).thenReturn(Optional.of(dato1));

        var resultado = servicioRecurso.obtenerPorId("1111");

        Assertions.assertEquals(dato1.getName(), resultado.getName());
        Assertions.assertEquals(dato1.getId(), resultado.getId());
        Assertions.assertEquals(dato1.getTipoRecurso(), resultado.getTipoRecurso());
        Assertions.assertEquals(dato1.getAreaTematica(), resultado.getAreaTematica());
        Assertions.assertEquals(dato1.getFechaPrestamo(), resultado.getFechaPrestamo());
        Assertions.assertEquals(dato1.getEnPrestamo(), resultado.getEnPrestamo());

        verify(repositorioRecurso).findById("1111");
    }

    @Test
    @DisplayName("Test findByName Success")
    public void obtenerRecursoByName() {

        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setName("Cien años de soledad");
        dato1.setTipoRecurso("Libro");
        dato1.setAreaTematica("Literatura");
        dato1.setEnPrestamo(false);
        dato1.setFechaPrestamo("");

        Mockito.when(repositorioRecurso.findByName("Cien años de soledad")).thenReturn(dato1);

        var resultado = servicioRecurso.obtenerPorName("Cien años de soledad");

        Assertions.assertEquals(dato1.getName(), resultado.getName());
        Assertions.assertEquals(dato1.getId(), resultado.getId());
        Assertions.assertEquals(dato1.getTipoRecurso(), resultado.getTipoRecurso());
        Assertions.assertEquals(dato1.getAreaTematica(), resultado.getAreaTematica());
        Assertions.assertEquals(dato1.getFechaPrestamo(), resultado.getFechaPrestamo());
        Assertions.assertEquals(dato1.getEnPrestamo(), resultado.getEnPrestamo());

        verify(repositorioRecurso).findByName("Cien años de soledad");
    }

    @Test
    @DisplayName("Test Disponibilidad Recurso Success")
    public void disponibilidadRecurso() {

        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setName("Cien años de soledad");
        dato1.setTipoRecurso("Libro");
        dato1.setAreaTematica("Literatura");
        dato1.setEnPrestamo(false);
        dato1.setFechaPrestamo("");

        Mockito.when(repositorioRecurso.findByName("Cien años de soledad")).thenReturn(dato1);

        var resultado = servicioRecurso.consultarDisponibilidadRecurso("Cien años de soledad");

        Assertions.assertEquals("El recurso consultado se encuentra disponible para su préstamo", resultado);
        verify(repositorioRecurso).findByName("Cien años de soledad");
    }

    @Test
    @DisplayName("Test save Success")
    public void crear() {

        var dato1 = new Recurso();
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

        Mockito.when(repositorioRecurso.save(any())).thenReturn(dato1);

        var resultado = servicioRecurso.crearRecurso(dato2);

        Assertions.assertNotNull(resultado, "El valor guardado no debe ser nulo");
        Assertions.assertEquals(dato1.getName(), resultado.getName(), "El nombre no corresponde");
        Assertions.assertEquals(dato1.getTipoRecurso(), resultado.getTipoRecurso(), "El tipo del recurso no corresponde");
        Assertions.assertEquals(dato1.getAreaTematica(), resultado.getAreaTematica(), "El area temática no corresponde");
        Assertions.assertEquals(dato1.getFechaPrestamo(), resultado.getFechaPrestamo(), "La fecha de préstamo no corresponde");
        Assertions.assertEquals(dato1.getId(), resultado.getId(), "El ID no corresponde");
        Assertions.assertEquals(dato1.getEnPrestamo(), resultado.getEnPrestamo(), "La disponibilidad no corresponde");

        //verify(repositorioRecurso).save(dato1);
    }

    @Test
    @DisplayName("Test delete Success")
    public void eliminar() {

        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setName("Cien años de soledad");
        dato1.setTipoRecurso("Libro");
        dato1.setAreaTematica("Literatura");
        dato1.setEnPrestamo(false);
        dato1.setFechaPrestamo("");

        Mockito.when(repositorioRecurso.save(any())).thenReturn(dato1);

        var resultado = servicioRecurso.borrarRecurso(dato1.getId());

        Assertions.assertEquals("El recurso ha sido eliminado satisfactoriamente", resultado);
    }

    @Test
    @DisplayName("Test Prestar Recurso Success")
    public void PrestarRecurso() {

        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setName("Cien años de soledad");
        dato1.setTipoRecurso("Libro");
        dato1.setAreaTematica("Literatura");
        dato1.setEnPrestamo(false);
        dato1.setFechaPrestamo("");

        Mockito.when(repositorioRecurso.findById("1111")).thenReturn(Optional.of(dato1));

        var resultado = servicioRecurso.prestarRecurso(dato1.getId());

        Assertions.assertEquals("Recurso prestado con éxito", resultado);
        verify(repositorioRecurso).findById(dato1.getId());
    }

    @Test
    @DisplayName("Test Devolver Recurso Success")
    public void DevolverRecurso() {

        var dato2 = new Recurso();
        dato2.setId("2222");
        dato2.setName("El Testigo");
        dato2.setTipoRecurso("Documental");
        dato2.setAreaTematica("Cine");
        dato2.setEnPrestamo(true);
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
        String text = date.format(formatters);
        dato2.setFechaPrestamo(text);

        Mockito.when(repositorioRecurso.findById("2222")).thenReturn(Optional.of(dato2));

        var resultado = servicioRecurso.devolverRecursoPrestado(dato2.getId());

        Assertions.assertEquals("Recurso devuelto satisfactoriamente a la biblioteca", resultado);
        verify(repositorioRecurso).findById(dato2.getId());
    }
}
