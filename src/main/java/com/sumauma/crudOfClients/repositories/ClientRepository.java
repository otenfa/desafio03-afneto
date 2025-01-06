package com.sumauma.crudOfClients.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumauma.crudOfClients.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
