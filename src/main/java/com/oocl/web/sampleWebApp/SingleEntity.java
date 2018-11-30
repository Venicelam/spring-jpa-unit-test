package com.oocl.web.sampleWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SingleEntity {
    @Id
    public Long id;
    public String name;

}
