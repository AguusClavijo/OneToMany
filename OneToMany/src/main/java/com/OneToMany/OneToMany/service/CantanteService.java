package com.OneToMany.OneToMany.service;

import com.OneToMany.OneToMany.models.Cantante;
import com.OneToMany.OneToMany.repositories.CantanteRepositories;
import com.OneToMany.OneToMany.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CantanteService {

    @Autowired
    private CantanteRepositories cantanteRepository;

    public List<Cantante> getCantantes() {
        return (List<Cantante>) cantanteRepository.findAll();
    }

    public Optional<Cantante> getById(Long id) {
        return cantanteRepository.findById(id);
    }

    public Cantante saveCantante(Cantante cantante) {
        if (cantante.getAlbums() != null) {
            cantante.getAlbums().forEach(album -> album.setCantante(cantante));
        }
        return cantanteRepository.save(cantante);
    }

    public Cantante updateById(Cantante request, Long id) {
        return cantanteRepository.findById(id)
                .map(cantante -> {
                    cantante.setNombre(request.getNombre());
                    cantante.setGenero(request.getGenero());
                    cantante.getAlbums().clear();
                    request.getAlbums().forEach(album -> {
                        album.setCantante(cantante);
                        cantante.getAlbums().add(album);
                    });
                    return cantanteRepository.save(cantante);
                }).orElseThrow(() -> new ResourceNotFoundException("Cantante no encontrado con id " + id));
    }

    public boolean deleteCantante(Long id) {
        try {
            cantanteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}


