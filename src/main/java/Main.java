import Domain.entities.*;
import infra.repository.*;
import Domain.services.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            boolean continueProgram = true;

            while (continueProgram) {
                System.out.println("Select an option:");
                System.out.println("1. Client Services");
                System.out.println("2. Delivery Services");
                System.out.println("3. Order Services");
                System.out.println("4. Order Product Services");
                System.out.println("5. Product Services");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        clientServices();
                        break;
                    case 2:
                        deliveryServices();
                        break;
                    case 3:
                        orderServices();
                        break;
                    case 4:
                        orderProductServices();
                        break;
                    case 5:
                        productServices();
                        break;
                    case 6:
                        continueProgram = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            }
            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void clientServices() throws SQLException {
        ClientRepository clientRepository = new ClientRepository();
        ClientService clientService = new ClientService(clientRepository);
        Scanner scanner = new Scanner(System.in);
        boolean continueClientServices = true;

        while (continueClientServices) {
            System.out.println("\n------------ Client Services ------------");
            System.out.println("1. Find All Clients");
            System.out.println("2. Find All inactive Clients");
            System.out.println("3. Find Client by ID");
            System.out.println("4. Insert Client");
            System.out.println("5. Update Client");
            System.out.println("6. Delete Client");
            System.out.println("7. Active Client");
            System.out.println("8. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    List<Client> clients = clientService.getAllClients();
                    printTableIndexClient();
                    for (Client client : clients) {
                        System.out.println(client);
                    }
                    break;
                case 2:
                    List<Client> clients2 = clientService.getAllClientsInactive();
                    printTableIndexClient();
                    for (Client client : clients2) {
                        System.out.println(client);
                    }
                    break;
                case 3:
                    System.out.print("Enter Client ID: ");
                    int clientId = scanner.nextInt();
                    Client foundClient = clientService.getClientById(clientId);
                    if (foundClient != null) {
                        printTableIndexClient();
                        System.out.println(foundClient);
                    } else {
                        System.out.println("Client with ID " + clientId + " not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Client Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Client Birth Date (YYYY-MM-DD): ");
                    String birthDate = scanner.nextLine();
                    System.out.print("Enter Client SSN: ");
                    String ssn = scanner.nextLine();
                    System.out.print("Enter Client Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Client Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter Client Address: ");
                    String address = scanner.nextLine();

                    Client newClient = new Client(name, birthDate, ssn, email, phoneNumber, address);
                    clientService.registerClient(newClient);
                    System.out.println("New Client inserted successfully.");
                    break;
                case 5:
                    System.out.print("Enter client ID to update: ");
                    int updateClientId = scanner.nextInt();
                    scanner.nextLine();
                    Client clientToUpdate = clientService.getClientById(updateClientId);
                    if (clientToUpdate != null) {
                        System.out.println("Select fields to update:");
                        System.out.println("1. Name");
                        System.out.println("2. Birth Date");
                        System.out.println("3. SSN");
                        System.out.println("4. Email");
                        System.out.println("5. Phone Number");
                        System.out.println("6. Address");
                        System.out.print("Enter your choice (comma-separated): ");
                        String[] choices = scanner.nextLine().split(",");
                        for (String choice1 : choices) {
                            switch (choice1.trim()) {
                                case "1":
                                    System.out.print("Enter new name: ");
                                    String newName = scanner.nextLine();
                                    clientToUpdate.setFullName(newName);
                                    break;
                                case "2":
                                    System.out.print("Enter new birth date (YYYY-MM-DD): ");
                                    String newBirthDate = scanner.nextLine();
                                    clientToUpdate.setBirthDate(newBirthDate);
                                    break;
                                case "3":
                                    System.out.print("Enter new SSN: ");
                                    String newSSN = scanner.nextLine();
                                    clientToUpdate.setSsn(newSSN);
                                    break;
                                case "4":
                                    System.out.print("Enter new email: ");
                                    String newEmail = scanner.nextLine();
                                    clientToUpdate.setEmail(newEmail);
                                    break;
                                case "5":
                                    System.out.print("Enter new phone number: ");
                                    String newPhoneNumber = scanner.nextLine();
                                    clientToUpdate.setPhoneNumber(newPhoneNumber);
                                    break;
                                case "6":
                                    System.out.print("Enter new address: ");
                                    String newAddress = scanner.nextLine();
                                    clientToUpdate.setAddress(newAddress);
                                    break;
                                default:
                                    System.out.println("Invalid choice.");
                                    break;
                            }
                        }
                        boolean updated = clientService.updateClient(clientToUpdate);
                        if (updated) {
                            System.out.println("Client with ID " + updateClientId + " updated successfully.");
                        } else {
                            System.out.println("Failed to update client with ID " + updateClientId + ".");
                        }
                    } else {
                        System.out.println("Client with ID " + updateClientId + " not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter Client ID to Delete: ");
                    int deleteClientId = scanner.nextInt();
                    scanner.nextLine();
                    if (clientService.delete(deleteClientId)) {
                        System.out.println("Client with ID " + deleteClientId + " deleted successfully.");
                    } else {
                        System.out.println("Client with ID " + deleteClientId + " not found.");
                    }
                    break;
                case 7:
                    System.out.print("Enter Client ID to active: ");
                    int activeClientId = scanner.nextInt();
                    scanner.nextLine();
                    if (clientService.active(activeClientId)) {
                        System.out.println("Client with ID " + activeClientId + " active successfully.");
                    } else {
                        System.out.println("Client with ID " + activeClientId + " not found.");
                    }
                    break;
                case 8:
                    continueClientServices = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
        scanner.close();
    }

    private static void deliveryServices() throws SQLException {
        DeliveryRepository deliveryRepository = new DeliveryRepository();
        DeliveryService deliveryService = new DeliveryService(deliveryRepository);
        Scanner scanner = new Scanner(System.in);
        boolean continueDeliveryServices = true;

        while (continueDeliveryServices) {
            System.out.println("\n------------ Delivery Services ------------");
            System.out.println("1. Find All Deliveries");
            System.out.println("2. Find All Deliveries delivered");
            System.out.println("3. Find by id");
            System.out.println("4. Update Delivery to In Transit");
            System.out.println("5. Update Delivery to Delivered");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            deliveryService.deleteDelivery();

            switch (choice) {
                case 1:
                    List<Delivery> deliveries = deliveryService.getAllDeliverie();
                    printTableIndexDelivery();
                    for (Delivery delivery : deliveries) {
                        System.out.println(delivery);
                    }
                    break;
                case 2:
                    List<Delivery> deliveries2 = deliveryService.getAllDeliveriefinal();
                    printTableIndexDelivery();
                    for (Delivery delivery : deliveries2) {
                        System.out.println(delivery);
                    }
                    break;
                case 3:
                    System.out.print("Enter delivery ID: ");
                    int deliveryId = scanner.nextInt();
                    scanner.nextLine();
                    Delivery delivery = deliveryService.getDeliveryById(deliveryId);
                    if (delivery != null) {
                        System.out.println(delivery);
                    } else {
                        System.out.println("Delivery finalized.");
                    }
                    break;
                case 4:
                    System.out.print("Enter delivery ID to update: ");
                    int deliveryIdToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    Delivery deliveryToUpdate = deliveryService.getDeliveryById(deliveryIdToUpdate);
                    if (deliveryToUpdate != null) {
                        boolean updated = deliveryService.updateDeliveryInTransit(deliveryToUpdate);
                        if (updated) {
                            System.out.println("Delivery updated successfully.");
                        } else {
                            System.out.println("Failed to update delivery.");
                        }
                    } else {
                        System.out.println("Delivery not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter delivery ID to update: ");
                    deliveryIdToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    deliveryToUpdate = deliveryService.getDeliveryById(deliveryIdToUpdate);
                    if (deliveryToUpdate != null) {
                        boolean updated = deliveryService.updateDeliveryFinal(deliveryToUpdate);
                        if (updated) {
                            System.out.println("Delivery updated successfully.");
                        } else {
                            System.out.println("Failed to update delivery.");
                        }
                    } else {
                        System.out.println("Delivery not found.");
                    }
                    break;
                case 6:
                    continueDeliveryServices = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }

        }
        scanner.close();
    }

    private static void orderServices() throws SQLException {
        OrderRepository orderRepository = new OrderRepository();
        OrderService orderService = new OrderService(orderRepository);

        Scanner scanner = new Scanner(System.in);
        boolean continueOrderServices = true;

        while (continueOrderServices) {
            System.out.println("\n------------ Order Services ------------");
            System.out.println("1. Find All Orders");
            System.out.println("2. Find All Orders inactive");
            System.out.println("3. Find Order by ID");
            System.out.println("4. Create Order");
            System.out.println("5. Update Order: Waiting payment");
            System.out.println("6. Update Order: Paid");
            System.out.println("7. Update Order: Preparing for delivery");
            System.out.println("8. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            orderService.deleteOrder();

            switch (choice) {
                case 1:
                    List<Order> orders = orderService.getAllOrders();
                    printTableIndexOrder();
                    for (Order order : orders) {
                        System.out.println(order);
                    }
                    break;
                case 2:
                    List<Order> orders2 = orderService.getAllOrdersDelivered();
                    printTableIndexOrder();
                    for (Order order : orders2) {
                        System.out.println(order);
                    }
                    break;
                case 3:
                    System.out.print("Enter order ID: ");
                    int orderId = scanner.nextInt();
                    scanner.nextLine();
                    Order foundOrder = orderRepository.findById(orderId);
                    if (foundOrder != null) {
                        System.out.println(foundOrder);
                    } else {
                        System.out.println("Order finalized.");
                    }
                    break;
                case 4:
                    System.out.println("Enter client ID: ");
                    int clientId = scanner.nextInt();
                    scanner.nextLine();
                    Order newOrder = new Order();
                    newOrder.setClient_id(clientId);
                    boolean created = orderService.createOrder(newOrder);
                    if (created) {
                        System.out.println("Order created successfully.");
                    } else {
                        System.out.println("Failed to create order.");
                    }
                    break;
                case 5:
                    System.out.print("Enter order ID to update: ");
                    int orderIdToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    Order orderToUpdate = orderService.getOrderById(orderIdToUpdate);
                    if (orderToUpdate != null) {
                        boolean updated = orderService.updateOrderToStatusAwaitingPayment(orderToUpdate);
                        if (updated) {
                            System.out.println("Order updated successfully.");
                        } else {
                            System.out.println("Failed to update order.");
                        }
                    } else {
                        System.out.println("Order not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter order ID to update: ");
                    orderIdToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    orderToUpdate = orderService.getOrderById(orderIdToUpdate);
                    if (orderToUpdate != null) {
                        boolean updated = orderService.updateOrderToStatusPaid(orderToUpdate);
                        if (updated) {
                            System.out.println("Order updated successfully.");
                        } else {
                            System.out.println("Failed to update order.");
                        }
                    } else {
                        System.out.println("Order not found.");
                    }
                    break;
                case 7:
                    System.out.print("Enter order ID to update: ");
                    orderIdToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    orderToUpdate = orderService.getOrderById(orderIdToUpdate);
                    if (orderToUpdate != null) {
                        boolean updated = orderService.updateOrderPreparingDelivery(orderToUpdate);
                        if (updated) {
                            System.out.println("Order updated successfully.");
                        } else {
                            System.out.println("Failed to update order.");
                        }
                    } else {
                        System.out.println("Order not found.");
                    }
                    break;
                case 8:
                    continueOrderServices = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
        scanner.close();
    }


    private static void orderProductServices() throws SQLException {
        OrderProductRepository orderProductRepository = new OrderProductRepository();
        OrderProductService orderProductService = new OrderProductService(orderProductRepository);

        Scanner scanner = new Scanner(System.in);
        boolean continueOrderProductServices = true;

        while (continueOrderProductServices) {
            System.out.println("\n------------ Order Product Services ------------");
            System.out.println("1. Find All Order Products");
            System.out.println("2. Find All Order Products inactive" );
            System.out.println("3. Find Order Product by ID");
            System.out.println("4. Register Order Product");
            System.out.println("5. Update Order Product");
            System.out.println("6. Delete Order Product");
            System.out.println("7. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    List<OrderProduct> orderProducts = orderProductService.getAllOrderProducts();
                    printTableIndexOrderProduct();
                    for (OrderProduct orderProduct : orderProducts) {
                        System.out.println(orderProduct);
                    }
                    break;
                case 2:
                    List<OrderProduct> orderProducts2 = orderProductService.getAllOrderProductsInactive();
                    printTableIndexOrderProduct();
                    for (OrderProduct orderProduct : orderProducts2) {
                        System.out.println(orderProduct);
                    }
                    break;
                case 3:
                    System.out.print("Enter order product ID: ");
                    int orderProductId = scanner.nextInt();
                    scanner.nextLine();
                    OrderProduct foundOrderProduct = orderProductService.getOrderProductById(orderProductId);
                    if (foundOrderProduct != null) {
                        System.out.println(foundOrderProduct);
                    } else {
                        System.out.println("Order product not found.");
                    }
                    break;
                case 4:
                    System.out.println("Enter order ID: ");
                    int orderId = scanner.nextInt();
                    scanner.nextLine();
                    ProductRepository productRepository = new ProductRepository();
                    ProductService productService = new ProductService(productRepository);
                    List<Product> products = productService.getAllProducts();
                    printTableIndexProduct();
                    for (Product product : products) {
                        System.out.println(product);
                    }

                    System.out.println("Enter product ID: ");
                    int productId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                    OrderProduct newOrderProduct = new OrderProduct();
                    newOrderProduct.setOrder_id(orderId);
                    newOrderProduct.setProduct_id(productId);
                    newOrderProduct.setQuantity(quantity);
                    boolean registered = orderProductService.registerOrderProduct(newOrderProduct);
                    if (registered == false) {
                        System.out.println("Order product registered successfully.");
                    } else {
                        System.out.println("Failed to register order product.");
                    }
                    break;
                case 5:
                    System.out.print("Enter order product ID to update: ");
                    int orderProductIdToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    OrderProduct orderProductToUpdate = orderProductService.getOrderProductById(orderProductIdToUpdate);
                    if (orderProductToUpdate != null) {
                        boolean updated = orderProductService.updateOrderProduct(orderProductToUpdate);
                        if (updated==false) {
                            System.out.println("Order product updated successfully.");
                        } else {
                            System.out.println("Failed to update order product.");
                        }
                    } else {
                        System.out.println("Order product not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter order product ID to delete: ");
                    int orderProductIdToDelete = scanner.nextInt();
                    scanner.nextLine();
                    boolean deleted = orderProductService.deleteOrderProduct(orderProductIdToDelete);
                    if (deleted == false) {
                        System.out.println("Order product deleted successfully.");
                    } else {
                        System.out.println("Failed to delete order product.");
                    }
                    break;
                case 7:
                    continueOrderProductServices = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
        scanner.close();
    }


    private static void productServices() throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);

        Scanner scanner = new Scanner(System.in);
        boolean continueProductServices = true;

        while (continueProductServices) {
            System.out.println("\n------------ Product Services ------------");
            System.out.println("1. Find All Products");
            System.out.println("1. Find All Products inactive");
            System.out.println("2. Find Product by ID");
            System.out.println("3. Register Product");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    List<Product> products = productService.getAllProducts();
                    printTableIndexProduct();
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;
                case 2:
                    List<Product> products2 = productService.getAllProductsDeleted();
                    printTableIndexProduct();
                    for (Product product : products2) {
                        System.out.println(product);
                    }
                    break;
                case 3:
                    System.out.print("Enter product ID: ");
                    int productId = scanner.nextInt();
                    scanner.nextLine();
                    Product foundProduct = productService.getProductById(productId);
                    if (foundProduct != null) {
                        System.out.println(foundProduct);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double productPrice = scanner.nextDouble();
                    scanner.nextLine();
                    Product newProduct = new Product(productName, productPrice);
                    boolean registered = productService.registerProduct(newProduct);
                    if (registered) {
                        System.out.println("Product registered successfully.");
                    } else {
                        System.out.println("Failed to register product.");
                    }
                    break;
                case 5:
                    System.out.print("Enter product ID to update: ");
                    int productIdToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    Product productToUpdate = productService.getProductById(productIdToUpdate);
                    if (productToUpdate != null) {
                        boolean updated = productService.updateProduct(productToUpdate);
                        if (updated) {
                            System.out.println("Product updated successfully.");
                        } else {
                            System.out.println("Failed to update product.");
                        }
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter product ID to delete: ");
                    int productIdToDelete = scanner.nextInt();
                    scanner.nextLine();
                    boolean deleted = productService.deleteProduct(productIdToDelete);
                    if (deleted) {
                        System.out.println("Product deleted successfully.");
                    } else {
                        System.out.println("Failed to delete product.");
                    }
                    break;
                case 7:
                    continueProductServices = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
        scanner.close();
    }
    private static void printTableIndexClient() {
        System.out.println("| ID   | Name                  | Birth Date  | SSN                          | Email                         | Phone Number  | Address                                         |");
        System.out.println("-".repeat(167));
    }
    private static void printTableIndexDelivery() {
        System.out.println("| ID   | Order ID | Delivery Date  | Delivery Address                | Delivery Status  | Received Date       | Received By                          |");
        System.out.println("-".repeat(127));
    }
    private static void printTableIndexOrder() {
        System.out.println("| ID   | Client ID | Created At     | Order Value | Order Status |");
        System.out.println("-".repeat(62));
    }
    private static void printTableIndexOrderProduct() {
        System.out.println("| ID   | Order ID | Product ID | Quantity |");
        System.out.println("-".repeat(43));
    }
    private static void printTableIndexProduct() {
        System.out.println("| ID   | Name                  | Price $      |");
        System.out.println("-".repeat(40));
    }

}
