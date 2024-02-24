import java.util.Random;
import java.util.Scanner;

// class for the room with the vase, keeps track of when the room is available
class Showroom {
    private volatile boolean available = true;

    public synchronized boolean enter() {
        if (available) {
            available = false;
            return true;
        }
        return false;
    }

    public synchronized void leave() {
        available = true;
    }
}

// class for each Guest/thread, it enters the room if it is available and leaves after three seconds
// if room is busy, walks around Minotaur's home for a 1 - 5 seconds
class Guest extends Thread {
    private static int guestCount = 0;
    private final int guestNumber;
    private final Showroom showroom;
    private final Random random = new Random();

    public Guest(Showroom showroom) {
        this.guestNumber = ++guestCount;
        this.showroom = showroom;
    }

    @Override
    public void run() {
        while (true) {
            if (showroom.enter()) {
                System.out.println("Guest " + guestNumber + " enters the showroom.");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                showroom.leave();
                System.out.println("Guest " + guestNumber + " leaves the showroom.");
                return;
            } else {
                System.out.println("Guest " + guestNumber + " finds showroom busy, taking a walk.");
                try {
                    Thread.sleep(random.nextInt(5000) + 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

// main class, creates all the threads and executes them until every single guest has visited the room
public class MinotaurCrystal {
    public static void main(String[] args) {
        Showroom showroom = new Showroom();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of guests: ");
        int numGuests = scanner.nextInt();
        scanner.close();

        Guest[] guests = new Guest[numGuests];
        for (int i = 0; i < numGuests; i++) {
            guests[i] = new Guest(showroom);
            guests[i].start();
        }

        for (Guest guest : guests) {
            try {
                guest.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("The Minotaur is happy that all guest got to see his impressive crystal vase! Party is over!");
    }
}
