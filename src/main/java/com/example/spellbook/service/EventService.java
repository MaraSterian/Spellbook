package com.example.spellbook.service;

import com.example.spellbook.dto.EventDTO;
import com.example.spellbook.mapper.EventMapper;
import com.example.spellbook.model.Event;
import com.example.spellbook.model.PastEvent;
import com.example.spellbook.repository.EventRepository;
import com.example.spellbook.repository.PastEventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Service
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Autowired
    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(eventMapper::eventToEventDTO)
                .collect(Collectors.toList());
    }

    public EventDTO getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        return eventMapper.eventToEventDTO(event);
    }

    public void createEvent(EventDTO eventDTO) {
        Event event = eventMapper.eventDTOToEvent(eventDTO);
        eventRepository.save(event);
    }

    public void updateEvent(Long eventId, EventDTO eventDTO) {
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        // Update the existingEvent based on eventDTO
        existingEvent.setTitle(eventDTO.getTitle());
        existingEvent.setDescription(eventDTO.getDescription());
        existingEvent.setLocation(eventDTO.getLocation());
        existingEvent.setDateAndTime(eventDTO.getDate_and_time());

        eventRepository.save(existingEvent);
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Autowired
    private PastEventRepository pastEventRepository;

    public void deleteExpiredEvents() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Event> expiredEvents = eventRepository.findByDateAndTimeBefore(currentDateTime);

        for (Event event : expiredEvents) {
            // Create a PastEvent instance and populate it with data from the expired event
            PastEvent pastEvent = new PastEvent();
            pastEvent.setTitle(event.getTitle());
            pastEvent.setDescription(event.getDescription());
            pastEvent.setLocation(event.getLocation());
            pastEvent.setDate_and_time(event.getDateAndTime());
            pastEvent.setUser(event.getUser());

            // Save the pastEvent to the "pastEvents" repository
            pastEventRepository.save(pastEvent);

            // Delete the expired event from the "events" repository
            eventRepository.delete(event);
        }
    }

    @Scheduled(cron = "0 0 1 * * *") // Run every day at 1:00 AM
    public void scheduledDeleteExpiredEvents() {
        deleteExpiredEvents();
    }
}
