package com.alex.unijourneybackend.modules.user.infrastructure.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.alex.unijourneybackend.modules.user.domain.valueobject.FiscalCode;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;





/**
| Caso                                              | Azione consigliata                               |
| ------------------------------------------------- | ------------------------------------------------ |
| Form di ricerca                                   | Ritorna `Optional` o `null`, mostra un messaggio |
| Operazione su entità esistente (modifica/elimina) | Lancia un'eccezione se non trovata               |
| API REST                                          | Usa `404 Not Found` se l’oggetto non esiste      |
| Interfaccia utente (MVC)                          | Mostra pagina “nessun risultato”                 |
*/

public interface UserRepository extends JpaRepository<User, UserId> {


    /**
     * Find user by username
     * @param username
     * @return an optional user
     */
    Optional<User> findByUsername(String username);


    /**
     * Find user by dob
     * @param dob
     * @return List<User>
     */
    List<User> findByDob(LocalDate dob);


    /**
     * Find user by fiscal code
     * @param fiscalCode
     * @return User
     */
    Optional<User> findByFiscalCode(FiscalCode fiscalCode);


    /**
     * Find user by id
     * @param id
     * @return User
     */
    @EntityGraph(attributePaths = {"passwordHistory"})
    Optional<User> findWithPasswordHistoryById(@Param("id") UserId id);


    /**
     * Check if a user with the given username exists
     * @param username the username to check
     * @return true if the user exists, false otherwise
     */
    boolean existsByUsername(String username);


    /**
     * Check if a user with the given fiscal code exists
     * @param fiscalCode the fiscal code to check
     * @return true if the user exists, false otherwise
     */
    boolean existsByFiscalCode(FiscalCode fiscalCode);


    /**
     * Check if a user with the given username exists
     * @param username the username to check
     * @param id the id to exclude
     * @return true if the user exists, false otherwise
     */
    boolean existsByUsernameAndIdNot(String username, UserId id);


    /**
     * Check if a user with the given fiscal code exists
     * @param fiscalCode the fiscal code to check
     * @param id the id to exclude
     * @return true if the user exists, false otherwise
     */
    boolean existsByFiscalCodeAndIdNot(FiscalCode fiscalCode, UserId id);


}
