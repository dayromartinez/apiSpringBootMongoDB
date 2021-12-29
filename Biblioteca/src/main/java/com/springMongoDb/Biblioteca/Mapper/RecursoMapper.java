package com.springMongoDb.Biblioteca.Mapper;

import com.springMongoDb.Biblioteca.Collection.Recurso;
import com.springMongoDb.Biblioteca.DTO.RecursoDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecursoMapper {

    public Recurso fromDTO(RecursoDTO dto){

        Recurso recurso = new Recurso();
        recurso.setId(dto.getId());
        recurso.setName(dto.getName());
        recurso.setTipoRecurso(dto.getTipoRecurso());
        recurso.setAreaTematica(dto.getAreaTematica());
        recurso.setEnPrestamo(dto.getEnPrestamo());
        recurso.setFechaPrestamo(dto.getFechaPrestamo());
        return recurso;

    }

    public RecursoDTO fromCollection(Recurso collection){

        RecursoDTO recursoDTO = new RecursoDTO();
        recursoDTO.setId(collection.getId());
        recursoDTO.setName(collection.getName());
        recursoDTO.setTipoRecurso(collection.getTipoRecurso());
        recursoDTO.setAreaTematica(collection.getAreaTematica());
        recursoDTO.setEnPrestamo(collection.getEnPrestamo());
        recursoDTO.setFechaPrestamo(collection.getFechaPrestamo());
        return recursoDTO;

    }

    public List<RecursoDTO> fromCollectionList(List<Recurso> collection){

        if(collection == null){
            return null;
        }

        List<RecursoDTO> list = new ArrayList(collection.size());
        Iterator listTracks = collection.iterator();

        while (listTracks.hasNext()){
            Recurso recurso = (Recurso) listTracks.next();
            list.add(fromCollection(recurso));
        }
        return list;
    }
}
