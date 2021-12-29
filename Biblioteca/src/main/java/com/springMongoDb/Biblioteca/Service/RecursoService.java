package com.springMongoDb.Biblioteca.Service;

import com.springMongoDb.Biblioteca.Collection.Recurso;
import com.springMongoDb.Biblioteca.DTO.RecursoDTO;
import com.springMongoDb.Biblioteca.Mapper.RecursoMapper;
import com.springMongoDb.Biblioteca.Repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RecursoService {

    @Autowired
    RecursoRepository recursoRepository;
    RecursoMapper mapper = new RecursoMapper();

    public List<RecursoDTO> obtenerTodosRecursos(){
        List<Recurso> recursos = (List<Recurso>) recursoRepository.findAll();
        return mapper.fromCollectionList(recursos);
    }

    public RecursoDTO obtenerPorId(String id){
        Objects.requireNonNull(id);
        Recurso recurso = recursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        return mapper.fromCollection(recurso);
    }

    public RecursoDTO obtenerPorName(String name){
        Objects.requireNonNull(name);
        Recurso recurso = recursoRepository.findByName(name);
        return mapper.fromCollection(recurso);
    }

    public String consultarDisponibilidadRecurso(String name){
        Objects.requireNonNull(name);
        Recurso recurso = recursoRepository.findByName(name);
        Objects.requireNonNull(recurso);
        if(recurso.getEnPrestamo()){
            return "El recurso consultado se encuentra en préstamo";
        }
        return "El recurso consultado se encuentra disponible para su préstamo";
    }

    public RecursoDTO crearRecurso(RecursoDTO recursoDTO){
        Recurso recurso = mapper.fromDTO(recursoDTO);
        //recurso.setEnPrestamo(false);
        return mapper.fromCollection(recursoRepository.save(recurso));
    }

    public RecursoDTO editarRecurso(RecursoDTO recursoDTO){
        Recurso recurso = mapper.fromDTO(recursoDTO);
        recursoRepository.findById(recurso.getId()).orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        return mapper.fromCollection(recursoRepository.save(recurso));
    }

    public String borrarRecurso(String id){
        recursoRepository.deleteById(id);
        return "El recurso ha sido eliminado satisfactoriamente";
    }

    public String prestarRecurso(String id){
        Recurso recurso = recursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        if(!recurso.getEnPrestamo() && recurso != null){
            recurso.setEnPrestamo(true);
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
            String text = date.format(formatters);
            recurso.setFechaPrestamo(text);
            recursoRepository.save(recurso);
            return "Recurso prestado con éxito";
        }
        return "El recurso consultado ya se encuentra en préstamo";
    }

    public String devolverRecursoPrestado(String id){
        Recurso recurso = recursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        if(recurso.getEnPrestamo()){
            recurso.setEnPrestamo(false);
            recurso.setFechaPrestamo("");
            recursoRepository.save(recurso);
            return "Recurso devuelto satisfactoriamente a la biblioteca";
        }
        return "El recurso consultado no se encuentra en préstamo. Inténtelo nuevamente";
    }
}
