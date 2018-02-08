package com.nisum.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nisum.user.model.Event;

/**
 * The Interface EventRepository.
 */
public interface EventRepository extends JpaRepository<Event, Long>{

	/**
	 * Find events by corresponding user id.
	 *
	 * @param userId the user id
	 * @param pageable the pageable
	 * @return the events
	 */
	
	@Query("SELECT e FROM Event e WHERE e.user.id=?1")
	public Page<Event> findEventsByUserId(Long userId, Pageable pageable);

	/**
	 * Find events by corresponding event type.
	 *
	 * @param typeId the event type id
	 * @param pageable the pageable
	 * @return the events
	 */
	
	@Query("SELECT e FROM Event e WHERE e.eventType.id=?1")
	public Page<Event> findEventsByEventTypeId(Long typeId, Pageable pageable);
	
	/**
	 * Find events by corresponding  app module id.
	 *
	 * @param moduleId the app module id
	 * @param pageable the pageable
	 * @return the events
	 */
	
	@Query("SELECT e FROM Event e WHERE e.appModules.id=?1")
	public Page<Event> findEventsByAppModuleID(Long moduleId, Pageable pageable);
	
	/**
	 * Find events by corresponding process number .
	 *
	 * @param processNumber the process number
	 * @param pageable the pageable
	 * @return the events
	 */
	
	@Query("SELECT e from Event e Where e.isProcessed = ?1")
	public Page<Event> findAllProcessEvents(int processNumber,Pageable pageable);
	
	/**
	 * Find events by corresponding process number and user id .
	 *
	 * @param processNumber the process number
	 * @param userId the user id
	 * @param pageable the pageable
	 * @return the events
	 */
	
	@Query("SELECT e FROM Event e WHERE e.user.id=?2 and e.isProcessed = ?1")
	public Page<Event> findAllProcessEventByUser(int processNumber, Long userId, Pageable pageable);
	
	/**
	 * Find events by corresponding process number and module id .
	 *
	 * @param processNumber the process number
	 * @param moduleId the module id
	 * @param pageable the pageable
	 * @return the events
	 */
	
	@Query("SELECT e FROM Event e WHERE e.appModules.id=?2 and e.isProcessed = ?1")
	public Page<Event> findAllProcessEventsByAppModule(int processNumber, Long moduleId, Pageable pageable);
}
