package com.accolite.demo.Controller;

import com.accolite.demo.Model.Rental;
import com.accolite.demo.Service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/rentals")
public class RentalController
{

    @Autowired
    private RentalService rentalService;

    @PostMapping("/rent")
    public Rental rentBook(@RequestParam Long userId, @RequestParam Long bookId,@RequestParam String startDate, @RequestParam String endDate){
        return rentalService.rentBook(userId,bookId, LocalDate.parse(startDate),LocalDate.parse(endDate));
    }

    @PostMapping("/return/{rentalId}")
    public void returnBook(@PathVariable Long rentalId){
        rentalService.returnBook(rentalId);
    }

    @PutMapping("/extend/{rentalId}")
    public Rental extendRentalPeriod(@PathVariable Long rentalId,@RequestParam String newEndDate){
        return rentalService.extendRentalPeriod(rentalId,LocalDate.parse(newEndDate));
    }

}
