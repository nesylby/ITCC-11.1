import java.util.*;

interface LibraryItem {
    void borrowItem();
    void returnItem();
    boolean isBorrowed();
}

abstract class LibraryUser {
    String name;
    List<LibraryItem> borrowedItems = new ArrayList<>();

    public void borrowItem(LibraryItem item) {
        item.borrowItem();
        borrowedItems.add(item);
        System.out.println("Item borrowed successfully.");
    }

    public void returnItem(LibraryItem item) {
        item.returnItem();
        borrowedItems.remove(item);
        System.out.println("Item returned successfully.");
    }

    abstract void printItemsBorrowed();
}

class Book implements LibraryItem {
    private boolean borrowed = false;

    @Override
    public void borrowItem() {
        borrowed = true;
    }

    @Override
    public void returnItem() {
        borrowed = false;
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    @Override
    public String toString() {
        return "Book";
    }
}

class DVD implements LibraryItem {
    private boolean borrowed = false;

    @Override
    public void borrowItem() {
        borrowed = true;
    }

    @Override
    public void returnItem() {
        borrowed = false;
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    @Override
    public String toString() {
        return "DVD";
    }
}

public class Library {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose your item: Book/DVD");
        String itemType = scanner.nextLine();

        LibraryItem item = null;
        if (itemType.equalsIgnoreCase("Book")) {
            item = new Book();
        } else if (itemType.equalsIgnoreCase("DVD")) {
            item = new DVD();
        } else {
            System.out.println("Invalid item type.");
            return;
        }

        LibraryUser user = new LibraryUser() {
            @Override
            void printItemsBorrowed() {
                System.out.println("--------------------------");
                System.out.println("Teacher: " + name);
                for (LibraryItem borrowedItem : borrowedItems) {
                    System.out.println("Borrowed Item: " + borrowedItem);
                }
                System.out.println("--------------------------");
            }
        };

        user.name = "John Doe";
        user.borrowItem(item);
        user.printItemsBorrowed();

        scanner.close();
    }
}
