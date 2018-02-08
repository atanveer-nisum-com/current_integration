package com.nisum.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nisum.user.model.Event;
import com.nisum.user.repository.EventRepository;
import com.nisum.user.service.EventService;

/**
 * The Class EventServiceImpl.
 */
@Service("eventService")
public class EventServiceImpl implements EventService {

	/** The event repository. */
	@Autowired 
	private EventRepository eventRepository;
	
	 /* (non-Javadoc)
     * @see com.nisum.service.EventService#save(com.nisum.model.Event)
     */
	
	@Override
	public Event save(Event event) {
		
		return eventRepository.save(event);
	}

	 /* (non-Javadoc)
     * @see com.nisum.service.EventService#updateEventByProcess(com.nisum.model.Event)
     */
	
	@Override
	public Event updateEventByProcess(Event event) { 

		return eventRepository.save(event);
	}

	 /* (non-Javadoc)
     * @see com.nisum.service.EventService#findEventsByEventTypeId(java.lang.Long,Pageable)
     */
	
	@Override
	public Page<Event> findEventsByEventTypeId(Long typeId ,  Pageable pageable) {
		
		return eventRepository.findEventsByEventTypeId(typeId, pageable);
	}

	 /* (non-Javadoc)
     * @see com.nisum.service.EventService#findEventsByAppModuleId(java.lang.Long, Pageable)
     */
	
	@Override
	public Page<Event> findEventsByAppModuleId(Long moduleId, Pageable pageable) {
		
		return eventRepository.findEventsByAppModuleID(moduleId, pageable);
	}

	 /* (non-Javadoc)
     * @see com.nisum.service.EventService#findEventsByUserId(java.lang.Long, Pageable)
     */
	
	@Override
	public Page<Event> findEventsByUserId(Long userId, Pageable pageable) {
		
		return eventRepository.findEventsByUserId(userId, pageable);
	}

	 /* (non-Javadoc)
     * @see com.nisum.service.EventService#findAllProcessEvents(java.lang.Integer, Pageable)
     */
	
	@Override
	public Page<Event> findAllProcessEvents(int processNumber, Pageable pageable) {
		
		return eventRepository.findAllProcessEvents(processNumber, pageable);
	}

	 /* (non-Javadoc)
     * @see com.nisum.service.EventService#findAllProcessEventByUser(java.lang.Integer, java.lang.Long, Pageable)
     */
	
	@Override
	public Page<Event> findAllProcessEventByUser(int processNumber, Long userId, Pageable pageable) {
		
		return eventRepository.findAllProcessEventByUser(processNumber, userId, pageable);
	}

	 /* (non-Javadoc)
     * @see com.nisum.service.EventService#findAllProcessEventsByAppModule(java.lang.Integer, java.lang.Long, Pageable)
     */
	
	@Override
	public Page<Event> findAllProcessEventsByAppModule(int processNumber, Long moduleId, Pageable pageable) {
		
		return eventRepository.findAllProcessEventsByAppModule(processNumber, moduleId, pageable);
	}

	
}
