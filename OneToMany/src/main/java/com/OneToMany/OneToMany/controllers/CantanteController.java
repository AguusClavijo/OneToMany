package com.OneToMany.OneToMany.controllers;

import com.OneToMany.OneToMany.models.Cantante;
import com.OneToMany.OneToMany.service.CantanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cantantes")
public class CantanteController {

    @Autowired
    private CantanteService cantanteService;

    @GetMapping
    public List<Cantante> getCantantes() {
        return cantanteService.getCantantes();
    }

    @GetMapping("/{id}")
    public Optional<Cantante> getCantanteById(@PathVariable Long id) {
        return cantanteService.getById(id);
    }

    @PostMapping
    public Cantante createCantante(@RequestBody Cantante cantante) {
        if (cantante.getAlbums() != null) {
            cantante.getAlbums().forEach(album -> album.setCantante(cantante));
        }
        return cantanteService.saveCantante(cantante);
    }

    @PutMapping("/{id}")
    public Cantante updateCantanteById(@RequestBody Cantante request, @PathVariable Long id) {
        return cantanteService.updateById(request, id);
    }

    @DeleteMapping("/{id}")
    public String deleteCantanteById(@PathVariable Long id) {
        boolean ok = cantanteService.deleteCantante(id);
        return ok ? "Cantante eliminado con id " + id : "Error, no se pudo eliminar el cantante con id " + id;
    }
}

