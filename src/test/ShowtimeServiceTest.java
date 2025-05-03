package test;

import service.*;
import entities.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ShowtimeServiceTest {
    public static void main(String[] args) {
        MovieService movieService = new MovieService();
        RoomService roomService = new RoomService();
        ShowtimeService showtimeService = new ShowtimeService();

        // 1. Tạo Movie và Room để dùng cho showtime
        Movie movie = new Movie("TestCountry", "TestLang", "TestShowtimeMovie", 90,
                                "DirTest", "GenreTest", 0, null,
                                LocalDate.now(), "Desc", "URL");
        movie = movieService.createMovie(movie);

        Room room = new Room("TestRoom", 12, 4);
        room = roomService.createRoom(room);

        // 2. Tạo Showtime thành công
        System.out.println("== Create Showtime ==");
        Showtime s1 = new Showtime(LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(11, 30), movie, room);
        Showtime created1 = showtimeService.createShowtime(s1);
        System.out.println("Created: " + created1);

        // 3. Attempt overlap: should throw RuntimeException
        System.out.println("\n== Test Overlap Showtime ==");
        try {
            Showtime sOverlap = new Showtime(LocalDate.now(), LocalTime.of(11, 0), LocalTime.of(12, 30), movie, room);
            showtimeService.createShowtime(sOverlap);
            System.out.println("ERROR: Overlap not detected");
        } catch (RuntimeException ex) {
            System.out.println("Expected overlap exception: " + ex.getMessage());
        }

        // 4. Lấy theo ID
        System.out.println("\n== Get by ID ==");
        Showtime fetched = showtimeService.getShowtimeById(created1.getId());
        System.out.println("Fetched: " + fetched);

        // 5. List all
        System.out.println("\n== List All Showtimes ==");
        List<Showtime> all = showtimeService.getAllShowtimes();
        all.forEach(System.out::println);

        // 6. Lấy theo ngày
        System.out.println("\n== Showtimes By Date ==");
        List<Showtime> byDate = showtimeService.getShowtimeByDate(LocalDate.now());
        byDate.forEach(System.out::println);

        // 7. Lấy theo ngày và phòng
        System.out.println("\n== Showtimes By Room ==");
        List<Showtime> byDateRoom = showtimeService.getShowtimeByRoomId(room.getId());
        byDateRoom.forEach(System.out::println);

        // 8. Lấy ghế của suất chiếu
        System.out.println("\n== Seats Of Showtime ==");
        Map<String, List<Seat>> seatMap = showtimeService.getSeatOfShowtime(created1.getId().toString());
        seatMap.forEach((k,v) -> {
            System.out.println(k + ": " + v);
        });

        // 9. Lấy theo phim
        System.out.println("\n== Showtimes By Movie ==");
        List<Showtime> byMovie = showtimeService.getShowtimeByMovieId(movie.getId());
        byMovie.forEach(System.out::println);

        // 10. Lấy theo phòng
        System.out.println("\n== Showtimes By Room ==");
        List<Showtime> byRoom = showtimeService.getShowtimeByRoomId(room.getId());
        byRoom.forEach(System.out::println);

        // // 11. Xóa showtime
        // System.out.println("\n== Delete Showtime ==");
        // boolean deleted = showtimeService.deleteShowtime(created1.getId());
        // System.out.println("Deleted: " + deleted);
    }
}