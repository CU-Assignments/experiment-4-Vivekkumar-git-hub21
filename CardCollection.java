import java.util.*;

class Card {
    private String symbol;
    private String value;

    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Card{Symbol='" + symbol + "', Value='" + value + "'}";
    }
}

public class CardCollection {
    private static final Collection<Card> cards = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nCard Collection System");
            System.out.println("1. Add Card");
            System.out.println("2. Remove Card");
            System.out.println("3. Search Cards by Symbol");
            System.out.println("4. Display All Cards");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCard();
                    break;
                case 2:
                    removeCard();
                    break;
                case 3:
                    searchBySymbol();
                    break;
                case 4:
                    displayCards();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCard() {
        System.out.print("Enter Card Symbol: ");
        String symbol = scanner.nextLine();
        System.out.print("Enter Card Value: ");
        String value = scanner.nextLine();
        cards.add(new Card(symbol, value));
        System.out.println("Card added successfully.");
    }

    private static void removeCard() {
        System.out.print("Enter Card Symbol to remove: ");
        String symbol = scanner.nextLine();

        Iterator<Card> iterator = cards.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getSymbol().equalsIgnoreCase(symbol)) {
                iterator.remove();
                System.out.println("Card removed successfully.");
                return;
            }
        }
        System.out.println("Card not found.");
    }

    private static void searchBySymbol() {
        System.out.print("Enter Symbol to search: ");
        String symbol = scanner.nextLine();
        boolean found = false;

        for (Card card : cards) {
            if (card.getSymbol().equalsIgnoreCase(symbol)) {
                System.out.println(card);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No cards found with the given symbol.");
        }
    }

    private static void displayCards() {
        if (cards.isEmpty()) {
            System.out.println("No cards in the collection.");
        } else {
            System.out.println("\nCard Collection:");
            for (Card card : cards) {
                System.out.println(card);
            }
        }
    }
}
