package com.example.crud.spring_crud.Repository;

import com.example.crud.spring_crud.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

}
