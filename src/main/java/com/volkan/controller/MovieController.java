package com.volkan.controller;

import com.volkan.repository.entity.Movie;
import com.volkan.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/findAllByRatingGreaterThan/{rate}")
    public ResponseEntity<List<Movie>> findAllByRatingGreaterThan (@PathVariable double rate) {
        //localhost:8085/movie/findAllByRatingGreaterThan/5     @PathVariable olduğu için rate ' i 5' e eşitler.
        //@Requestparam olsaydı ===> localhost:8085/movie/findAllByRatingGreaterThan?rate=5
        return ResponseEntity.ok(movieService.findAllByRatingGreaterThan(rate));
    }
    @GetMapping("/findbyusersgenre/{userid}")
    public ResponseEntity<List<Movie>> findAllByUserGenre(@PathVariable Long userid) {
        return ResponseEntity.ok(movieService.findAllByUserGenre(userid));
    }
    @GetMapping("/beforedate")
    public ResponseEntity<List<Movie>> findAllByPremieredBefore(@RequestParam (defaultValue = "2023-03-07") String date) {
        return ResponseEntity.ok(movieService.findAllByPremiredBefore(date));
    }
    @GetMapping("/searchbyrating")
    public ResponseEntity<Object []> searchByRating() {
        return ResponseEntity.ok(movieService.searchByRating());
    }
    @GetMapping("/searchbyrating2")
    public ResponseEntity<Object> searchByRating(double rate) {
        return ResponseEntity.ok(movieService.searchByRating(rate));
    }
    @GetMapping("/findAllByRatingIn")
    public List<Movie> findAllByRatingIn(Double[] ratings) {
        return movieService.findAllByRatingIn(ratings);
    }
    @GetMapping("/findAllByNameIn")
    public List<Movie> findAllByNameIn(String[] names) {
        return movieService.findAllByNameIn(names);
    }

}
