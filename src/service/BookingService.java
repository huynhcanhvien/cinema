package service;

import dao.BookingDAO;
import entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingService {
	private UserService userService;
	private ShowtimeService showtimeService;
	
    public BookingService() {
    	userService = new UserService();
    	showtimeService = new ShowtimeService();
    }
	public Booking createBooking(Booking booking) {
		
    	User user = userService.getUserById(booking.getUser_id());
    	if(user == null) throw new RuntimeException("User not found");
    	Showtime showtime = showtimeService.getShowtimeById(booking.getShowtime_id());
    	if(showtime == null) throw new RuntimeException("Showtime not found");
    	booking.setBookingPrice(showtime.getMovie().getPrice());
    	List<Booking> bookeds = showtime.getBookings();
    	List<Seat> seats = new ArrayList<>();
    	for(Booking booked : bookeds) {
    		seats.addAll(booked.getSeats());
    	}
    	for(Seat seat : seats) {
    		for(Seat s : booking.getSeats()) {
    			if(s.getId() == seat.getId()) {
    				throw new RuntimeException("Seat has been booked for this showtime");
    			}
    		}
    	}
    	
    	return BookingDAO.insert(booking) > 0 ? booking : null;
    }

    public Booking getBookingById(UUID id) {
        return BookingDAO.findById(id);
    }

    public List<Booking> getAllBookings() {
        return BookingDAO.findAll();
    }

    public boolean updateBooking(Booking booking) {
        return BookingDAO.update(booking) > 0;
    }

    public boolean deleteBooking(UUID id) {
        return BookingDAO.delete(id) > 0;
    }
}