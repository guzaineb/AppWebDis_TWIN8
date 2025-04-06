package com.onsdachraoui.client.service;

import com.onsdachraoui.client.entity.Client;
import java.util.List;

public interface IClientService {
    Client addClient(Client client);
    void deleteClient(Long id);
    Client getClientById(Long id);
    List<Client> getAllClients();
    Client updateClient(Client client);

}
