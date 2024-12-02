import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

// MenuItem Class
class MenuItem {
    private int id;
    private String name;
    private double price;
    private String category;

    public MenuItem(int id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Price: $" + price + ", Category: " + category;
    }
}

// MenuManagement Class (No change here, except handling the update method correctly)
class MenuManagement {
    private final ArrayList<MenuItem> menuItems = new ArrayList<>();
    private int nextId = 1;

    public MenuManagement() {
    	menuItems.add(new MenuItem(nextId++, "Mocha", 5.50, "Beverage"));
    	menuItems.add(new MenuItem(nextId++, "Flat White", 4.50, "Beverage"));
    	menuItems.add(new MenuItem(nextId++, "Latte", 4.00, "Beverage"));
    	menuItems.add(new MenuItem(nextId++, "Cappuccino", 4.75, "Beverage"));
    	menuItems.add(new MenuItem(nextId++, "Espresso", 3.00, "Beverage"));
    	menuItems.add(new MenuItem(nextId++, "Americano", 3.50, "Beverage"));
    	menuItems.add(new MenuItem(nextId++, "Iced Coffee", 5.00, "Beverage"));
    	menuItems.add(new MenuItem(nextId++, "Affogato", 6.00, "Dessert"));
    	menuItems.add(new MenuItem(nextId++, "Cheesecake", 4.75, "Dessert"));
    	menuItems.add(new MenuItem(nextId++, "Chocolate Cake", 5.00, "Dessert"));
    	menuItems.add(new MenuItem(nextId++, "Bagel", 2.50, "Snack"));
    	menuItems.add(new MenuItem(nextId++, "Croissant", 3.00, "Snack"));
    	menuItems.add(new MenuItem(nextId++, "Muffin", 2.75, "Snack"));
    	menuItems.add(new MenuItem(nextId++, "Grilled Cheese Sandwich", 5.50, "Food"));
    	menuItems.add(new MenuItem(nextId++, "Veggie Wrap", 6.00, "Food"));
    	menuItems.add(new MenuItem(nextId++, "Chicken Caesar Salad", 7.50, "Food"));
    	menuItems.add(new MenuItem(nextId++, "BLT Sandwich", 6.50, "Food"));
    	menuItems.add(new MenuItem(nextId++, "French Fries", 2.00, "Side"));
    	menuItems.add(new MenuItem(nextId++, "Onion Rings", 2.50, "Side"));
    	menuItems.add(new MenuItem(nextId++, "Garlic Bread", 3.00, "Side"));
    }

    public void manageMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Menu Management ===");
            System.out.println("1. Add Item");
            System.out.println("2. Update Item");
            System.out.println("3. Delete Item");
            System.out.println("4. View Menu");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addItem(scanner);
                case 2 -> updateItem(scanner);
                case 3 -> deleteItem(scanner);
                case 4 -> viewMenu();
                case 5 -> {
                    System.out.println("Returning to Main Menu...");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public void viewMenu() {
        if (menuItems.isEmpty()) {
            System.out.println("The menu is empty.");
        } else {
            for (MenuItem item : menuItems) {
                System.out.println(item);
            }
        }
    }

    private void addItem(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        MenuItem item = new MenuItem(nextId++, name, price, category);
        menuItems.add(item);
        System.out.println("Item added successfully!");
    }

    private void updateItem(Scanner scanner) {
        System.out.print("Enter the ID of the item to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        MenuItem item = findItemById(id);

        if (item != null) {
            System.out.print("Enter new name: ");
            item.setName(scanner.nextLine());
            System.out.print("Enter new price: ");
            item.setPrice(scanner.nextDouble());
            scanner.nextLine();
            System.out.print("Enter new category: ");
            item.setCategory(scanner.nextLine());
            System.out.println("Item updated successfully!");
        } else {
            System.out.println("Item not found!");
        }
    }

    private void deleteItem(Scanner scanner) {
        System.out.print("Enter the ID of the item to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        MenuItem item = findItemById(id);

        if (item != null) {
            menuItems.remove(item);
            System.out.println("Item deleted successfully!");
        } else {
            System.out.println("Item not found!");
        }
    }

    protected MenuItem findItemById(int id) {
        for (MenuItem item : menuItems) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}

// OrderManagement Class (Remains unchanged)
class OrderManagement extends MenuManagement {
    private final ArrayList<MenuItem> orderItems = new ArrayList<>();

    public void manageOrder() {
        Scanner scannerOrder = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Order Management ===");
            System.out.println("1. Add Item");
            System.out.println("2. Update Item");
            System.out.println("3. Delete Item");
            System.out.println("4. View Order");
            System.out.println("5. Generate Bill");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choiceOrder = scannerOrder.nextInt();
            scannerOrder.nextLine();

            switch (choiceOrder) {
                case 1 -> addOrderItem(scannerOrder);
                case 2 -> updateOrderItem(scannerOrder);
                case 3 -> deleteOrderItem(scannerOrder);
                case 4 -> viewOrder();
                case 5 -> generateBill();
                case 6 -> {
                    System.out.println("Returning to Main Menu...");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public void addOrderItem(Scanner scannerOrder) {
        System.out.print("Enter the ID of the item to add: ");
        int id = scannerOrder.nextInt();
        scannerOrder.nextLine();
        MenuItem menuItem = findItemById(id);

        if (menuItem != null) {
            orderItems.add(menuItem);
            System.out.println("Item added to order successfully!");
        } else {
            System.out.println("Item not found in menu!");
        }
    }

    public void updateOrderItem(Scanner scannerOrder) {
        System.out.print("Enter the ID of the item to update in the order: ");
        int id = scannerOrder.nextInt();
        scannerOrder.nextLine();

        MenuItem orderItem = findOrderItemById(id);
        if (orderItem != null) {
            System.out.print("Enter new ID for the item to replace: ");
            int newId = scannerOrder.nextInt();
            scannerOrder.nextLine();
            MenuItem newMenuItem = findItemById(newId);

            if (newMenuItem != null) {
                orderItems.remove(orderItem);
                orderItems.add(newMenuItem);
                System.out.println("Order item updated successfully!");
            } else {
                System.out.println("New item not found in menu!");
            }
        } else {
            System.out.println("Item not found in the order!");
        }
    }

    public void deleteOrderItem(Scanner scannerOrder) {
        System.out.print("Enter the ID of the item to delete from the order: ");
        int id = scannerOrder.nextInt();
        scannerOrder.nextLine();

        MenuItem orderItem = findOrderItemById(id);
        if (orderItem != null) {
            orderItems.remove(orderItem);
            System.out.println("Item removed from order successfully!");
        } else {
            System.out.println("Item not found in the order!");
        }
    }

    public void viewOrder() {
        if (orderItems.isEmpty()) {
            System.out.println("No items in the order.");
        } else {
            System.out.println("\n=== Current Order ===");
            for (MenuItem item : orderItems) {
                System.out.println(item.getName() + " - $" + item.getPrice());
            }
        }
    }

    private MenuItem findOrderItemById(int id) {
        for (MenuItem item : orderItems) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    void generateBill() {
        if (orderItems.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No items in the order to generate a bill.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            double total = 0.0;
            StringBuilder bill = new StringBuilder();
            bill.append("===== Receipt =====\n");

            for (MenuItem item : orderItems) {
                bill.append(item.getName()).append(" - $").append(item.getPrice()).append("\n");
                total += item.getPrice();
            }

            bill.append("\nTotal: $").append(total);
            bill.append("\n====================");

            JOptionPane.showMessageDialog(null, bill.toString(), "Receipt", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}



// Main Menu Class
class MainMenu {
    private final MenuManagement menuManagement = new MenuManagement();
    OrderManagement orderManagement = new OrderManagement();

    public void display() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display current menu items
            System.out.println("\n=== Current Menu Items ===");
            menuManagement.viewMenu();

            // Display main menu options
            System.out.println("\n-----WELCOME!-----\n=== Cafe Management System ===");
            System.out.println("1. Menu Management");
            System.out.println("2. Order Management");
            System.out.println("3. Billing");
            System.out.println("4. Reports");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> menuManagement.manageMenu();
                case 2 -> orderManagement.manageOrder();
                case 3 -> orderManagement.generateBill();
                case 4 -> {
                    JOptionPane.showMessageDialog(null, "Insufficient data.", "Reports", JOptionPane.INFORMATION_MESSAGE);
                }
                case 5 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

// Login Frame Class
class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        setTitle("Cafe System Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Login");
        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if ((username.equals("admin") && password.equals("password"))|| (username.equals("cafe") && password.equals("cafe123")) 
        		|| (username.equals("CAFE") && password.equals("password123"))){
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            MainMenu mainMenu = new MainMenu();
            mainMenu.display();
            dispose();
        } 
        else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

// Main Class
public class UpdatedCafeManagementSystem {
    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
}
