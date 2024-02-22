import Domain.Client;
import Domain.ClientBuilder;
import repository.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ClientRepository clientRepository = new ClientRepository();
        //Client client = clientRepository.findById(17);
        //if(client != null){
        //    System.out.println(client);
        //}else{
        //    System.out.println("cliente não encontrado");
        //}
        //clientRepository.insert(new Client("Paul George","1990-05-02","12345678901","paul.george@example.com","555-1234","123 Main Street"));
        clientRepository.update(new Client(21,"Paul George","1990-05-02","12345678901","paul.george@example.com",
                "555-1234","Indiana"));
        clientRepository.findAll().forEach(System.out::println);
    }

}