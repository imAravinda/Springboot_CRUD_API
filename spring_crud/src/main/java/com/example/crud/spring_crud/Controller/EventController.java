package com.example.crud.spring_crud.Controller;

import com.example.crud.spring_crud.Model.EventData;
import com.example.crud.spring_crud.Model.Payload;
import com.example.crud.spring_crud.Model.Record;
import com.example.crud.spring_crud.Repository.EventRepository;
import com.example.crud.spring_crud.Model.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventRepository eventrepository;

    @PostMapping()
    public Event addEvent(@RequestBody Event event) {
        return eventrepository.save(event);
    }

    @GetMapping()
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventrepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> findEventById(@PathVariable(value = "id") UUID eventId) {
        Event event = eventrepository.findById(eventId).orElseThrow(
                () -> new ResourceNotFoundException("Event Not Found"));
        return ResponseEntity.ok().body(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEventById(@PathVariable(value = "id") UUID eventId,
            @RequestBody Event updateDetails) {
        Event event = eventrepository.findById(eventId).orElseThrow(
                () -> new ResourceNotFoundException("Event is not exist"));

        event.setAddrNbr(updateDetails.getAddrNbr());
        event.setEventCnt(updateDetails.getEventCnt());
        event.setClientId(updateDetails.getClientId());
        event.setRcNum(updateDetails.getRcNum());
        event.setLocationCd(updateDetails.getLocationCd());
        event.setTransId(updateDetails.getTransId());
        event.setTransTms(updateDetails.getTransTms());
        event.setLocationId2(updateDetails.getLocationId2());
        event.setLocationId1(updateDetails.getLocationId1());
        event.setAddrNbr(updateDetails.getAddrNbr());

        Event updateEvent = eventrepository.save(event);

        return ResponseEntity.ok().body(updateEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable(value = "id") UUID eventId) {
        Event event = eventrepository.findById(eventId).orElseThrow(
                () -> new ResourceNotFoundException("Event Not Found"));
        eventrepository.delete(event);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/payload")
    public ResponseEntity<Void> savePayload(@RequestBody Payload payload) {
        for (Record record : payload.getRecords()) {
            for (EventData eventData : record.getEvents()) {
                Event event = new Event();
                event.setTransId(record.getTransId());
                event.setTransTms(record.getTransTms());
                event.setRcNum(record.getRcNum());
                event.setClientId(record.getClientId());
                event.setEventCnt(eventData.getEventCnt());
                event.setLocationCd(eventData.getLocationCd());
                event.setLocationId1(eventData.getLocationId1());
                event.setLocationId2(eventData.getLocationId2());
                event.setAddrNbr(eventData.getAddrNbr());

                eventrepository.save(event);
            }
        }

        return ResponseEntity.ok().build();
    }
}
