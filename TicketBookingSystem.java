import java.util.*;

class TicketBookingSystem {
    private final boolean[] seats;

    public TicketBookingSystem(int numberOfSeats) {
        this.seats = new boolean[numberOfSeats]; // False means available, true means booked
    }

    public synchronized boolean bookSeat(int seatNumber, String customer) {
        if (seatNumber < 0 || seatNumber >= seats.length) {
            System.out.println(customer + " - Invalid seat number.");
            return false;
        }
        if (!seats[seatNumber]) {
            seats[seatNumber] = true;
            System.out.println(customer + " successfully booked seat " + seatNumber);
            return true;
        } else {
            System.out.println(customer + " - Seat " + seatNumber + " is already booked.");
            return false;
        }
    }
}

class Customer extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;
    private final String customerName;

    public Customer(TicketBookingSystem system, int seatNumber, String customerName, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.customerName = customerName;
        setPriority(priority); // Higher priority for VIPs
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, customerName);
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(10); // 10 seats available
        List<Thread> customers = new ArrayList<>();

        // Creating normal and VIP customers
        customers.add(new Customer(system, 3, "VIP_Alice", Thread.MAX_PRIORITY));
        customers.add(new Customer(system, 3, "Bob", Thread.NORM_PRIORITY));
        customers.add(new Customer(system, 5, "VIP_Charlie", Thread.MAX_PRIORITY));
        customers.add(new Customer(system, 5, "David", Thread.NORM_PRIORITY));
        customers.add(new Customer(system, 1, "Eve", Thread.MIN_PRIORITY));

        // Shuffle to simulate real-world randomness
        Collections.shuffle(customers);

        // Start booking threads
        for (Thread customer : customers) {
            customer.start();
        }

        // Wait for all threads to finish
        for (Thread customer : customers) {
            try {
                customer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
