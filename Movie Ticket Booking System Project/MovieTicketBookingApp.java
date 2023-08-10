import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Movie {
    private String name;
    private String genre;
    private int availableSeats;

    public Movie(String name, String genre, int availableSeats) {
        this.name = name;
        this.genre = genre;
        this.availableSeats = availableSeats;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void decreaseAvailableSeats() {
        availableSeats--;
    }
}

class Theater {
    private String name;
    private String location;

    public Theater(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}

class Seat {
    private int seatNumber;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}

class Booking {
    private Movie movie;
    private Theater theater;
    private Seat seat;

    public Booking(Movie movie, Theater theater, Seat seat) {
        this.movie = movie;
        this.theater = theater;
        this.seat = seat;
    }

    public Movie getMovie() {
        return movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public Seat getSeat() {
        return seat;
    }
}

class MovieTicketBookingSystem {
    private List<Movie> movies;
    private List<Theater> theaters;
    private List<Booking> bookings;

    public MovieTicketBookingSystem() {
        movies = new ArrayList<>();
        theaters = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    public void addMovie(String name, String genre, int availableSeats) {
        Movie movie = new Movie(name, genre, availableSeats);
        movies.add(movie);
        System.out.println("++++MOVIE ADDED SUCCESSFULLY!++++");
    }

    public void addTheater(String name, String location) {
        Theater theater = new Theater(name, location);
        theaters.add(theater);
        System.out.println("++++THEATER ADDED SUCCESSFULLY!++++");
    }

    public void bookTicket(Movie movie, Theater theater, Seat seat) {
        if (movie.getAvailableSeats() > 0) {
            movie.decreaseAvailableSeats();
            Booking booking = new Booking(movie, theater, seat);
            bookings.add(booking);
            System.out.println("++++TICKET ADDED SUCCESSFULLY!++++");
        } else {
            System.out.println("No available seats for the selected movie!");
        }
    }

    public void cancelTicket(Booking booking) {
        Movie movie = booking.getMovie();
        movie.decreaseAvailableSeats();
        bookings.remove(booking);
        System.out.println("++++TICKET CANCELLED SUCCESSFULLY!++++");
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Theater> getTheaters() {
        return theaters;
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}

public class MovieTicketBookingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MovieTicketBookingSystem bookingSystem = new MovieTicketBookingSystem();

        System.out.println("Welcome to the Movie Ticket Booking System!");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nPlease select an option:-");
            System.out.println("1. Add Movie");
            System.out.println("2. Add Theater");
            System.out.println("3. Book Ticket");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. Show Movies");
            System.out.println("6. Show Theaters");
            System.out.println("7. Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (option) {
                case 1:
                    System.out.print("Enter movie name: ");
                    String movieName = scanner.nextLine();
                    System.out.print("Enter movie genre: ");
                    String movieGenre = scanner.nextLine();
                    System.out.print("Enter available seats: ");
                    int availableSeats = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    bookingSystem.addMovie(movieName, movieGenre, availableSeats);
                    break;
                case 2:
                    System.out.print("Enter theater name: ");
                    String theaterName = scanner.nextLine();
                    System.out.print("Enter theater location: ");
                    String theaterLocation = scanner.nextLine();
                    bookingSystem.addTheater(theaterName, theaterLocation);
                    break;
                case 3:
                    List<Movie> movies = bookingSystem.getMovies();
                    List<Theater> theaters = bookingSystem.getTheaters();

                    System.out.println("Available Movies:");
                    for (int i = 0; i < movies.size(); i++) {
                        Movie movie = movies.get(i);
                        System.out.println((i + 1) + ". " + movie.getName() + " (" + movie.getGenre() + ")");
                    }

                    System.out.println("Available Theaters:");
                    for (int i = 0; i < theaters.size(); i++) {
                        Theater theater = theaters.get(i);
                        System.out.println((i + 1) + ". " + theater.getName() + " - " + theater.getLocation());
                    }

                    System.out.print("Enter the movie number: ");
                    int movieNumber = scanner.nextInt();
                    System.out.print("Enter the theater number: ");
                    int theaterNumber = scanner.nextInt();
                    System.out.print("Enter the seat number: ");
                    int seatNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    if (movieNumber >= 1 && movieNumber <= movies.size()
                            && theaterNumber >= 1 && theaterNumber <= theaters.size()) {
                        Movie selectedMovie = movies.get(movieNumber - 1);
                        Theater selectedTheater = theaters.get(theaterNumber - 1);
                        Seat seat = new Seat(seatNumber);
                        bookingSystem.bookTicket(selectedMovie, selectedTheater, seat);
                    } else {
                        System.out.println("Invalid movie or theater selection!");
                    }
                    break;
                case 4:
                    List<Booking> bookings = bookingSystem.getBookings();

                    System.out.println("Select a ticket to cancel:");
                    for (int i = 0; i < bookings.size(); i++) {
                        Booking booking = bookings.get(i);
                        System.out.println((i + 1) + ". " + booking.getMovie().getName() + " - "
                                + booking.getTheater().getName() + " - Seat " + booking.getSeat().getSeatNumber());
                    }

                    System.out.print("Enter the ticket number: ");
                    int ticketNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    if (ticketNumber >= 1 && ticketNumber <= bookings.size()) {
                        Booking selectedBooking = bookings.get(ticketNumber - 1);
                        bookingSystem.cancelTicket(selectedBooking);
                    } else {
                        System.out.println("Invalid ticket selection!");
                    }
                    break;
                case 5:
                    List<Movie> availableMovies = bookingSystem.getMovies();

                    System.out.println("Available Movies:");
                    for (int i = 0; i < availableMovies.size(); i++) {
                        Movie movie = availableMovies.get(i);
                        System.out.println((i + 1) + ". " + movie.getName() + " (" + movie.getGenre() + ")");
                    }
                    break;
                case 6:
                    List<Theater> availableTheaters = bookingSystem.getTheaters();

                    System.out.println("Available Theaters:");
                    for (int i = 0; i < availableTheaters.size(); i++) {
                        Theater theater = availableTheaters.get(i);
                        System.out.println((i + 1) + ". " + theater.getName() + " - " + theater.getLocation());
                    }
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }

        scanner.close();
    }
}
