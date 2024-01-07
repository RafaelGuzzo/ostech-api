package br.com.ostech.repository;

import br.com.ostech.controller.response.ClientResponse;
import br.com.ostech.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {
    List<Client> findByName(String name);

    List<Client> findByCpf(String cpf);

    @Query(nativeQuery = true, value = "SELECT * FROM ostech.client "
            + "WHERE name = :name AND cpf = :cpf")
    List<Client> findByNameAndCpf(@Param("name") String name, @Param("cpf") String cpf);
}
