package io.pivotal.pal.tracker;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TimeEntryController {
	private TimeEntryRepository timeEntryRepository;
	public TimeEntryController(TimeEntryRepository timeEntryRepository) {
		this.timeEntryRepository = timeEntryRepository;
	}

	@PostMapping("/time-entries")
	public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
		return new ResponseEntity<TimeEntry>(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
	}

	@GetMapping("/time-entries/{id}")
	public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {
		TimeEntry entry = timeEntryRepository.find(timeEntryId);
		if (entry == null){
			return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TimeEntry>(entry, HttpStatus.OK);
	}

	@GetMapping("/time-entries")
	public ResponseEntity<List<TimeEntry>> list() {
		return new ResponseEntity<List<TimeEntry>>(timeEntryRepository.list(), HttpStatus.OK);
	}

	@PutMapping("/time-entries/{id}")
	public ResponseEntity update(@PathVariable("id") long timeEntryId, @RequestBody TimeEntry expected) {
		TimeEntry entry = timeEntryRepository.update(timeEntryId, expected);
		if (entry == null){
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(entry, HttpStatus.OK);
	}

	@DeleteMapping("/time-entries/{id}")
	public ResponseEntity delete(@PathVariable("id") long timeEntryId) {
		timeEntryRepository.delete(timeEntryId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
    
}