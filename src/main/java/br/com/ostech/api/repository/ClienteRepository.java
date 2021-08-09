package br.com.ostech.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ostech.api.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	List<Cliente> findAllByNomeContaining(String nome);
}
