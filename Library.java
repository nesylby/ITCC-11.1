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

        System.out.println("1. Return Item \n2. Borrow Item");
        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            System.out.println("Choose item to return: Book/DVD");
            String itemType = scanner.nextLine();
            LibraryItem item;
            if (itemType.equalsIgnoreCase("Book")) {
                item = new Book();
            } else if (itemType.equalsIgnoreCase("DVD")) {
                item = new DVD();
            } else {
                System.out.println("Invalid item type.");
                return;
            }

            System.out.println("Enter " + item + " Title: ");
            String itemTitle = scanner.nextLine();
            // Assuming you want to do something with the title, add the necessary logic here

            // Now we have an item, you can do whatever operations you want with it
            item.returnItem();
            System.out.println("Item returned successfully.");
    
        } else if (choice.equals("2")) {
            System.out.println("Choose item type: Book/DVD");
            String itemType = scanner.nextLine();
    
            LibraryItem item;
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
                public void printItemsBorrowed() {
                    System.out.println("--------------------------");
                    System.out.println("User: " + name);
                    for (LibraryItem borrowedItem : borrowedItems) {
                        System.out.println("Borrowed Item: " + borrowedItem);
                    }
                    System.out.println("--------------------------");
                }
            };
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}
