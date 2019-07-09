package it.capuano.testwebdevjava.repository;

import it.capuano.testwebdevjava.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Optional<Contact> findByName(String name);

    Optional<Contact> findByEmail(String email);

    Optional<Contact> findByPhone(String phone);
}