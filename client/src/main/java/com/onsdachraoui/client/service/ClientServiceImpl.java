package com.onsdachraoui.client.service;

import com.onsdachraoui.client.entity.Client;
import com.onsdachraoui.client.repository.ClientRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements IClientService {

    ClientRepo clientRepo;

    @Override
    public Client addClient(Client client) {
        return clientRepo.save(client);
    }
    @Override
    public Client updateClient(Client client) {
        return clientRepo.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepo.deleteById(id);
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepo.findById(id).orElse(null);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepo.findAll();
    }

}
