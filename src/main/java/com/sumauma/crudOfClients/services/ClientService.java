package com.sumauma.crudOfClients.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumauma.crudOfClients.DTO.ClientDTO;
import com.sumauma.crudOfClients.entities.Client;
import com.sumauma.crudOfClients.repositories.ClientRepository;


@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
		
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> result = clientRepository.findById(id);
		Client client = result.get();
		ClientDTO clientDto = new ClientDTO(client);
		return clientDto;
		
	}
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable) {
		Page<Client> listOfClients = clientRepository.findAll(pageable);
		return listOfClients.map(x -> new ClientDTO(x));
	}
	
	@Transactional
	public ClientDTO insert(ClientDTO clientDto) {
		Client client = new Client();
		copyClientDtoToClient(clientDto, client);
		client = clientRepository.save(client);
		return new ClientDTO(client);
		
	}	
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO cleintDto) {
		Client cleint = clientRepository.getReferenceById(id);
		copyClientDtoToClient(cleintDto, cleint);
		cleint = clientRepository.save(cleint);
		return new ClientDTO(cleint);
	}
	
	
	public void delete(Long id) {
		clientRepository.deleteById(id);
	}
		
	private void copyClientDtoToClient(ClientDTO clientDto, Client client) {
		client.setId(clientDto.getId());
		client.setName(clientDto.getName());
		client.setCpf(clientDto.getCpf());
		client.setIncome(clientDto.getIncome());
		client.setBirthDate(clientDto.getBirthDate());
		client.setChildren(clientDto.getChildren());
	}
	
}
