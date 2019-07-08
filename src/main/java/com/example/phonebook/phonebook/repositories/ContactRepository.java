package com.example.phonebook.phonebook.repositories;

import com.example.phonebook.phonebook.models.Contact;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


/**
 * scusate ma in 1 ora a fine giornata lavorativa non sono riuscito a fare di meglio
 * (il fine settimana ero al mare senza computer). Diciamo che è una base...
 * un saluto!
 *
 * fonti:
 *  https://spring.io/guides/gs/accessing-data-rest/
 *  https://www.baeldung.com/spring-boot-sqlite
 *
 *
 */
@RepositoryRestResource(collectionResourceRel = "contacts", path = "contacts")
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {

    List<Contact> findByName(@Param("name") String name);

    List<Contact> findBySurname(@Param("surname") String surname);

}
