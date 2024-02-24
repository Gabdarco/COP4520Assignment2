import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// class for each Guest/thread, it goes in the maze and either eats the cupcake available or requests one and eats it
class Guest extends Thread {
    private static boolean cupcakeAvailable = true;
    private static final Object lock = new Object();
    private static int cupcakesEaten = 0;
    private final int guestNumber;
    private boolean hasEatenCupcake;

    public Guest(int guestNumber) {
        this.guestNumber = guestNumber;
        this.hasEatenCupcake = false;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("Guest " + guestNumber + " enters the maze.");
            if (!cupcakeAvailable) {
                System.out.println("Guest " + guestNumber + " reached the end and couldn't find a cupcake!");
                System.out.println("Guest " + guestNumber + " requested a cupcake and ate it!");
                hasEatenCupcake = true;
                cupcakesEaten++;
                cupcakeAvailable = false;
                return;
            } else {
                System.out.println("Guest " + guestNumber + " reached the end and found a cupcake!");
                System.out.println("Guest " + guestNumber + " has eaten the cupcake.");
                cupcakesEaten++;
                hasEatenCupcake = true;
                cupcakeAvailable = false;
                return;
            }
        }
    }

    // get and set functions necessary for main method
    public boolean hasEatenCupcake() {
        return hasEatenCupcake;
    }

    public static int getCupcakesEaten() {
        return cupcakesEaten;
    }

    public boolean getCupcakeAvailable() {
        return cupcakeAvailable;
    }

    public int getNumber() {
        return guestNumber;
    }

    public static void setCupcake(boolean setter) {
        cupcakeAvailable = setter;
    }
}

// main class, creates and executes all threads, making sure they are chosen at random to go in the maze
public class MinotaurParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of guests: ");
        int numGuests = scanner.nextInt();
        scanner.close();

        List<Guest> guests = new ArrayList<>();
        for (int i = 0; i < numGuests; i++) {
            guests.add(new Guest(i + 1));
        }

        Random random = new Random();

        while (Guest.getCupcakesEaten() < numGuests) {
            int guestIndex = random.nextInt(numGuests);
            Guest guest = guests.get(guestIndex);
            if (!guest.hasEatenCupcake()) {
              guest.start();
              try {
                  guest.join();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
            } else {
              if (guest.getCupcakeAvailable())
              {
                System.out.println("Guest " + guest.getNumber() + " enters the maze.");
                System.out.println("Guest " + guest.getNumber() + " reached the end and found a cupcake!");
                System.out.println("Guest " + guest.getNumber() + " has already eaten, so it leaves the cupcake in the maze.");
              } else {
                System.out.println("Guest " + guest.getNumber() + " enters the maze.");
                System.out.println("Guest " + guest.getNumber() + " reached the end and couldn't find a cupcake!");
                System.out.println("Guest " + guest.getNumber() + " has already eaten, so it requested a cupcake and left it at the end of the maze!");
                guest.setCupcake(true);
              }
            }
        }

        System.out.println("All guests have eaten a cupcake! The Minotaur is pleased! Party is over!");
    }
}
