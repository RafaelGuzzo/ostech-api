package br.com.ostech.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ostech.api.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
