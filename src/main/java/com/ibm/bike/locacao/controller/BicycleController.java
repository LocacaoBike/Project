package com.ibm.bike.locacao.controller;

import com.ibm.bike.locacao.model.Bicycle;
import com.ibm.bike.locacao.model.dto.BicycleDTO;
import com.ibm.bike.locacao.service.BicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bike")
public class BicycleController {

    @Autowired
    private BicycleService service;

    @GetMapping()
    public ResponseEntity get() {
        return ResponseEntity.ok(service.getBike());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<BicycleDTO> bike = service.getBikeById(id);

        if (bike.isPresent()) {
            return ResponseEntity.ok(bike.get());
        } else {
            return ResponseEntity.notFound().build();
        }
        //return bike.map(ResponseEntity.ok()).orElse(ResponseEntity.notFound().build());

    }

    @GetMapping(value = "/modelo/{modelo}")
    public ResponseEntity getBikeByModelo(@PathVariable("modelo") String modelo) {
        List<BicycleDTO> bike = service.getBikeByModelo(modelo);

        if (bike.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(bike);
    }

    @GetMapping(value = "/cor/{cor}")
    public ResponseEntity getBikeByCor(@PathVariable("cor") String cor) {
        List<BicycleDTO> bike = service.getBikeByCor(cor);

        if (bike.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(bike);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Bicycle bike) {
        try{
            BicycleDTO b = service.insert(bike);
            URI location = getUri(b.getId());
            return ResponseEntity.created(location).build();
        } catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }
    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Bicycle bike) {
        BicycleDTO b = service.update(bike, id);
        return b != null ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        boolean ok = service.delete(id);
        return ok ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

}
