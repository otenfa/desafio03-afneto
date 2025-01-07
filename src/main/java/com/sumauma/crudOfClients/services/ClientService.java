package com.sumauma.crudOfClients.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumauma.crudOfClients.DTO.ClientDTO;
import com.sumauma.crudOfClients.entities.Client;
import com.sumauma.crudOfClients.repositories.ClientRepository;
import com.sumauma.crudOfClients.services.customExceptions.NoSuchElementException;

import jakarta.persistence.EntityNotFoundException;


@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
		
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client client = clientRepository.findById(id).orElseThrow(
				() -> new NoSuchElementException("Cliente inexistente"));
		return new ClientDTO(client);
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
		return new ClientDTO(clientRepository.save(client));
	}	
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO cleintDto) {
		try {
			Client cleint = clientRepository.getReferenceById(id);
			copyClientDtoToClient(cleintDto, cleint);
			return new ClientDTO(clientRepository.save(cleint));
		}
		catch(EntityNotFoundException e) {
			throw new NoSuchElementException("Cliente inexistente");
		}
	}
	
	@Transactional
	public void delete(Long id) {
		if (!clientRepository.existsById(id)) {
			throw new NoSuchElementException("Cliente inexistente");
		}
		clientRepository.deleteById(id);
	}
		
	private void copyClientDtoToClient(ClientDTO clientDto, Client client) {
		client.setName(clientDto.getName());
		client.setCpf(clientDto.getCpf());
		client.setIncome(clientDto.getIncome());
		client.setBirthDate(clientDto.getBirthDate());
		client.setChildren(clientDto.getChildren());
	}
	
}
