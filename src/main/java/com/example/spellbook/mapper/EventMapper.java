package com.example.spellbook.mapper;

import com.example.spellbook.dto.EventDTO;
import com.example.spellbook.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public EventDTO eventToEventDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setTitle(event.getTitle());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setLocation(event.getLocation());
        eventDTO.setDate_and_time(event.getDateAndTime());
        eventDTO.setUser(UserMapper.userToUserDTO(event.getUser()));
        return eventDTO;
    }

    public Event eventDTOToEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setLocation(eventDTO.getLocation());
        event.setDateAndTime(eventDTO.getDate_and_time());
        event.setUser(UserMapper.userDTOToUser(eventDTO.getUser()));
        return event;
    }
}
