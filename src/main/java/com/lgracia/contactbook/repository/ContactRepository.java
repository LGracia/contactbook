package com.lgracia.contactbook.repository;

import com.lgracia.contactbook.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
