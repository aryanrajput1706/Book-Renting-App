package com.accolite.demo.Database.Repository;

import com.accolite.demo.Model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
