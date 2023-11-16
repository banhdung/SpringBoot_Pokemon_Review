package com.pokemonreview.api.Controller;



import com.pokemonreview.api.dto.ReviewDto;
import com.pokemonreview.api.service.ReviewService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {
    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("/pokemon/{pokemonId}/review")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ReviewDto> createReview(@PathVariable(value="pokemonId") int pokemonId , @RequestBody ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.createReview(pokemonId , reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/pokemon/{pokemonId}/reviews")
    public List<ReviewDto> getReviewByPokemonId(@PathVariable(value = "pokemonId") int pokemonId){
        return  reviewService.getReviewsByPokemonId(pokemonId);

    }

    @PutMapping("pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable("pokemonId") int pokemonId ,@PathVariable("id") int reviewId, @RequestBody ReviewDto reviewDto){
        ReviewDto updateReview = reviewService.updateReview(pokemonId , reviewId , reviewDto);
        return new ResponseEntity<>(updateReview , HttpStatus.OK);
    }

    @DeleteMapping("pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable("pokemonId") int pokemonId , @PathVariable("id") int reviewId){
        reviewService.deleteReview(pokemonId , reviewId);
        return new ResponseEntity<>("Delete review successfully " , HttpStatus.OK);
    }

}
