package com.nisum.user.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nisum.user.model.Event;

/**
 * The Interface EventService.
 */
public interface EventService {
	
	/**
	 * save events .
	 *
	 * @param event
	 * @return the event
	 */
	
	public Event save(Event event);
	
	/**
	 * update events .
	 *
	 * @param event
	 * @return the event
	 */
	
	public Event updateEventByProcess(Event event);
	
	/**
	 * Find events by corresponding event type.
	 *
	 * @param typeId the event type id
	 * @param pageable the pageable
	 * @return the events
	 */
	
	public Page<Event> findEventsByEventTypeId(Long typeId ,  Pageable pageable);
	
	/**
	 * Find events by corresponding  app module id.
	 *
	 * @param moduleId the app module id
	 * @param pageable the pageable
	 * @return the events
	 */
	
	public Page<Event> findEventsByAppModuleId(Long moduleId , Pageable pageable);
	
	/**
	 * Find events by corresponding user id.
	 *
	 * @param userId the user id
	 * @param pageable the pageable
	 * @return the events
	 */
	
	public Page<Event> findEventsByUserId(Long userId , Pageable pageable);
	
	
	/**
	 * Find events by corresponding process number .
	 *
	 * @param processNumber the process number
	 * @param pageable the pageable
	 * @return the events
	 */
	
	public Page<Event> findAllProcessEvents(int processNumber , Pageable pageable);
	
	/**
	 * Find events by corresponding process number and user id .
	 *
	 * @param processNumber the process number
	 * @param userId the user id
	 * @param pageable the pageable
	 * @return the events
	 */
	
	
	public Page<Event> findAllProcessEventByUser(int processNumber , Long userId , Pageable pageable);
	
	/**
	 * Find events by corresponding process number and module id .
	 *
	 * @param processNumber the process number
	 * @param moduleId the module id
	 * @param pageable the pageable
	 * @return the events
	 */
	
	public Page<Event> findAllProcessEventsByAppModule(int processNumber , Long moduleId , Pageable pageable);

}
