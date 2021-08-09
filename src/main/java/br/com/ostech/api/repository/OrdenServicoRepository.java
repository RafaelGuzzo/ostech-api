package br.com.ostech.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ostech.api.model.Cliente;
import br.com.ostech.api.model.OrdemServico;

public interface OrdenServicoRepository extends JpaRepository<OrdemServico, Long> {
	List<OrdemServico> findAllByClienteId(Long clienteId);

	Long deleteByCliente(Cliente cliente);
}
