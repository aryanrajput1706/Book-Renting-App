package com.accolite.demo.Service;

import com.accolite.demo.Model.Book;
import com.accolite.demo.Model.Rental;
import com.accolite.demo.Database.Repository.BookRepository;
import com.accolite.demo.Database.Repository.RentalRepository;
import com.accolite.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserService userService;

    public Rental rentBook(Long userId, Long bookId, LocalDate startDate, LocalDate endDate){
        User user = userService.getUserById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        Book book  = bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("Book not found"));

        if(book.getStock()<=0){
            throw new RuntimeException("Book is out of stock");
        }

        double rentalPrice = book.getRentalPrice();
        if(user.getWalletBalance()<rentalPrice){
            throw new RuntimeException("Insufficient wallet balance");
        }
        //Deducting rental price from the user's wallet
        user.setWalletBalance(user.getWalletBalance() - rentalPrice);
        userService.updateUser(user);

        Rental rental = new Rental();
        rental.setUser(user);
        rental.setBook(book);
        rental.setStartDate(startDate);
        rental.setEndDate(endDate);

        //Reducing the stock when book is rented
        book.setStock(book.getStock()-1);
        bookRepository.save(book);

        return rentalRepository.save(rental);
    }

    public void returnBook(Long rentalId){
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(()->new RuntimeException("Rental not found"));
        Book book = rental.getBook();
        //Increasing stock when the book is returned
        book.setStock(book.getStock()+1);
        bookRepository.save(book);
        rentalRepository.delete(rental);
    }

    public Rental extendRentalPeriod(Long rentalId, LocalDate newEndDate) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(()->new RuntimeException("Rental not found"));

        if(newEndDate.isBefore(rental.getEndDate())) {
            throw new RuntimeException("New end date must be after the cyurrent end date");
        }

            rental.setEndDate(newEndDate);
            return rentalRepository.save(rental);
    }
}
