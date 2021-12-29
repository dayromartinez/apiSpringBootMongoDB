package com.springMongoDb.Biblioteca.Controller;

import com.springMongoDb.Biblioteca.DTO.RecursoDTO;
import com.springMongoDb.Biblioteca.Service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biblioteca")
public class RecursoController {

    @Autowired
    RecursoService servicioRecurso;

    @GetMapping("/recursos")
    public ResponseEntity<List<RecursoDTO>> findAll() {
        return new ResponseEntity(servicioRecurso.obtenerTodosRecursos(), HttpStatus.OK);
    }

    @GetMapping("/recurso/{id}")
    public ResponseEntity<RecursoDTO> findById(@PathVariable("id") String id) {
        return new ResponseEntity(servicioRecurso.obtenerPorId(id), HttpStatus.OK);
    }

    @GetMapping("/recurso")
    public ResponseEntity<RecursoDTO> findByName(@RequestParam String name) {
        return new ResponseEntity(servicioRecurso.obtenerPorName(name), HttpStatus.OK);
    }

    @GetMapping("/recurso/disponibilidad")
    public ResponseEntity<RecursoDTO> disponibilidad(@RequestParam String name){
        return new ResponseEntity(servicioRecurso.consultarDisponibilidadRecurso(name), HttpStatus.OK);
    }

    @PostMapping("/recurso/crear")
    public ResponseEntity<RecursoDTO> create(@RequestBody RecursoDTO recursoDTO) {
        return new ResponseEntity(servicioRecurso.crearRecurso(recursoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/recurso/modificar")
    public ResponseEntity<RecursoDTO> update(@RequestBody RecursoDTO recursoDTO){
        if (recursoDTO.getId() != null){
            return new ResponseEntity(servicioRecurso.editarRecurso(recursoDTO), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            servicioRecurso.borrarRecurso(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/prestarRecurso/{id}")
    public ResponseEntity<RecursoDTO> prestarRecurso(@PathVariable("id") String id){
        if (id != null){
            return new ResponseEntity(servicioRecurso.prestarRecurso(id), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/devolverRecurso/{id}")
    public ResponseEntity<RecursoDTO> devolverRecurso(@PathVariable("id") String id){
        if (id != null){
            return new ResponseEntity(servicioRecurso.devolverRecursoPrestado(id), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

