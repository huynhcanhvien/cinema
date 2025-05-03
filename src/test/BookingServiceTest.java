package test;

import service.*;
import entities.User;
import entities.Movie;
import entities.Room;
import entities.Showtime;
import entities.Booking;
import entities.Seat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BookingServiceTest {
    public static void main(String[] args) {
        UserService userService = new UserService();
        MovieService movieService = new MovieService();
        RoomService roomService = new RoomService();
        ShowtimeService showtimeService = new ShowtimeService();
        BookingService bookingService = new BookingService();

        // // Prerequisites: user, movie, room, showtime
        // User u = new User("huynhcanh", "bkpass", "bk0192@example.com", "Bk", "User", LocalDate.of(1992, 2, 2));
        // u = userService.register(u);

        // Movie m = new Movie("USAC", "English", "Booking Movie", 100, "Dir", "Comedy", 12,
        //                 new BigDecimal("7.00"), LocalDate.now(), "Desc", "url");
        // m = movieService.createMovie(m);

        // Room r = new Room("Room F", 30, 6);
        // r = roomService.createRoom(r);

        // Showtime s = new Showtime(LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0), m, r);
        // s = showtimeService.createShowtime(s);

        // // Prepare booking
        // Booking booking = new Booking();
        // booking.setBookingDate(LocalDate.now());
        // booking.setBookingTime(LocalTime.of(12, 0));
        // booking.setBookingPrice(s.getMovie().getPrice());
        // booking.setUser(u);
        // booking.setUser_id(u.getId());
        // booking.setShowtime(s);
        // booking.setShowtime_id(s.getId());
        // // Select first available seat
        // Seat seat = r.getSeats().get(0);
        // booking.addSeat(seat);
        // booking.getSeats_ids().add(seat.getId());

        // // Create booking
        // System.out.println("== Create Booking ==");
        // Booking created = bookingService.createBooking(booking);
        // System.out.println(created);

        // // Test findById
        // System.out.println("\n== Get Booking by ID ==");
        // Booking fetched = bookingService.getBookingById(created.getId());
        // System.out.println(fetched);

        // // Test getAllBookings
        // System.out.println("\n== List All Bookings ==");
        // List<Booking> all = bookingService.getAllBookings();
        // all.forEach(System.out::println);

        // // Test new service methods
        // System.out.println("\n== Find by User ==");
        // List<Booking> byUser = bookingService.getBookingsByUser(u.getId());
        // byUser.forEach(System.out::println);

        // System.out.println("\n== Find by Date ==");
        // List<Booking> byDate = bookingService.getBookingsByDate(LocalDate.now());
        // byDate.forEach(System.out::println);

        // System.out.println("\n== Find by Movie ==");
        // List<Booking> byMovie = bookingService.getBookingsByMovie(m.getId());
        // byMovie.forEach(System.out::println);

        // Test revenue methods
        System.out.println("\n== Revenue by Date ==");
        BigDecimal revDay = bookingService.getRevenueByDate(LocalDate.now());
        System.out.println("Revenue today: " + revDay);

        System.out.println("\n== Daily Revenue by Month ==");
        Map<LocalDate, BigDecimal> revMonth = bookingService.getDailyRevenueByMonth(LocalDate.now().getYear(), LocalDate.now().getMonthValue());
        revMonth.forEach((date, rev) -> System.out.println(date + " => " + rev));

        System.out.println("\n== Total Revenue by Month ==");
        BigDecimal totalRevMonth = bookingService.getTotalRevenueByMonth(LocalDate.now().getYear(), LocalDate.now().getMonthValue());
        System.out.println("Total Revenue this month: " + totalRevMonth);
    }
}
