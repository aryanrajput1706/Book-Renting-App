package com.accolite.demo.Database.Repository;

import java.util.*;
import com.accolite.demo.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBookId(Long bookId);
}
