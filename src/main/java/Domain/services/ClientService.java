package Domain.services;

import Domain.entities.Client;
import infra.repository.ClientRepository;
import java.sql.SQLException;
import java.util.List;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() throws SQLException {
        return clientRepository.findAll();
    }

    public Client getClientById(int id) throws SQLException {
        return clientRepository.findById(id);
    }

    public boolean registerClient(Client client) throws SQLException {
        if (clientRepository.findBySsn(client.getSsn()) != null || clientRepository.findByEmail(client.getEmail()) != null) {
            throw new IllegalArgumentException("A client with the same SSN or email already exists.");
        }
        return clientRepository.insert(client);
    }

    public boolean updateClient(Client client) throws SQLException {
        return clientRepository.update(client);
    }

    public boolean deleteClient(int id) throws SQLException {
        return clientRepository.delete(id);
    }
}
