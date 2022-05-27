package com.example.bilabonnmenteksamensprojekt.services;

import com.example.bilabonnmenteksamensprojekt.models.cars.Car;
import com.example.bilabonnmenteksamensprojekt.models.customers.Customer;
import com.example.bilabonnmenteksamensprojekt.models.bookings.Booking;
import com.example.bilabonnmenteksamensprojekt.models.tickets.Ticket;
import com.example.bilabonnmenteksamensprojekt.models.system.Search;
import com.example.bilabonnmenteksamensprojekt.repositories.cars.CarRepository;
import com.example.bilabonnmenteksamensprojekt.services.cars.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service


public class SearchService {


    @Autowired
    CarService carService;

    @Autowired
    BookingService bookingService;

    @Autowired
    CustomerService customerService;

    @Autowired
    TicketService ticketService;

    public List<Search> getSearchResults(String searchQuery){
        List<Search> outputList = new ArrayList<Search>(); //This will be the list returned

       List<Car> carResultsUnsorted = carService.getCars();
       for(int i=0;i<carResultsUnsorted.size();i++){
           if((carResultsUnsorted.get(i).getAllInfoToString()).toLowerCase().contains(searchQuery.toLowerCase())){  //Searching for car results matching input string
               outputList.add(new Search("Bil", carResultsUnsorted.get(i).getAllInfoToString(), "/cars/" + carResultsUnsorted.get(i).getCarId(), "/cars"));
           }
        }

        List<Customer> customerResultsUnsorted = customerService.getCustomers();
        for(int i=0;i<customerResultsUnsorted.size();i++){
            if((customerResultsUnsorted.get(i).getAllInfoToString()).toLowerCase().contains(searchQuery.toLowerCase())){  //Searching for customer results matching input string
                outputList.add(new Search("Kunde", customerResultsUnsorted.get(i).getAllInfoToString(), "/customers/" + customerResultsUnsorted.get(i).getCustomerId(), "/customers"));
            }
        }

        List<Booking> bookingResultsUnsorted = bookingService.getBookings();
        for(int i=0;i<bookingResultsUnsorted.size();i++){
            if(bookingResultsUnsorted.get(i).getAllInfoToString()!=null) {
                if ((bookingResultsUnsorted.get(i).getAllInfoToString()).toLowerCase().contains(searchQuery.toLowerCase())) {  //Searching for customer results matching input string
                    outputList.add(new Search("Booking", bookingResultsUnsorted.get(i).getAllInfoToString(), "/bookings/" + bookingResultsUnsorted.get(i).getBookingId(), "/bookings"));
                }
            }
        }

        List<Ticket> ticketResultsUnsorted = ticketService.getTickets();
        for(int i=0;i<ticketResultsUnsorted.size();i++){
            if(ticketResultsUnsorted.get(i).getAllInfoToString()!=null) {
                if ((ticketResultsUnsorted.get(i).getAllInfoToString()).toLowerCase().contains(searchQuery.toLowerCase())) {  //Searching for customer results matching input string
                    outputList.add(new Search("Sag", ticketResultsUnsorted.get(i).getAllInfoToString(), "/tickets/" + ticketResultsUnsorted.get(i).getTicketId(), "/tickets"));
                }
            }
        }
        return outputList;
    }
}
