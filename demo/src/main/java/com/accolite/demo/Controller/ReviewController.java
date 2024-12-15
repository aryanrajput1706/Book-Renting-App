package com.accolite.demo.Controller;

import com.accolite.demo.Model.Review;
import com.accolite.demo.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public Review addReview(@RequestParam Long userId, @RequestParam Long bookId,@RequestParam String comment, @RequestParam Integer rating){
        return reviewService.addReview(userId, bookId, comment, rating);
    }

    @GetMapping("/book/{bookId}")
    public List<Review> getReviewsByBookId(@PathVariable Long bookId){
        return reviewService.getReviewsByBookId(bookId);
    }
}
