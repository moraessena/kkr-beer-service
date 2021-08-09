package br.com.kkrbeerservice.web.controller;

import br.com.kkrbeerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @GetMapping(value = "/{id}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<BeerDto> createBeer(@RequestBody BeerDto beerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateBeer(@PathVariable("id") UUID id, @RequestBody BeerDto beerDto) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBeer(@PathVariable("id") UUID id) {
        return ResponseEntity.accepted().build();
    }

}
