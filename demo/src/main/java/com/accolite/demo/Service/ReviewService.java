package com.accolite.demo.Service;

import com.accolite.demo.Database.Repository.BookRepository;
import com.accolite.demo.Model.Book;
import com.accolite.demo.Database.Repository.ReviewRepository;
import com.accolite.demo.Model.Review;
import com.accolite.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview(Long userId, Long bookId, String comment, Integer rating){
//        User user = new User(userId);
//        Book book = new Book(bookId);
//
//        //Actual fetching logic
//        user = userService.getUserById(userId).orElseThrow(()-> new RuntimeException("User not found"));
//        book = bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("Book not found"));

        Review review = new Review();
        review.setUser(new User(userId));
        review.setBook(new Book(bookId));
        review.setComment(comment);
        review.setRating(rating);
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByBookId(Long bookId){
        return reviewRepository.findByBookId(bookId);
    }
}
