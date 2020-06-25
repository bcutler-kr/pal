package io.pivotal.pal.tracker;

import java.util.List;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TimeEntryController {
	private TimeEntryRepository timeEntryRepository;
	private final DistributionSummary timeEntrySummary;
	private final Counter actionCounter;

	public TimeEntryController(TimeEntryRepository timeEntryRepository, MeterRegistry meterRegistry) {
		this.timeEntryRepository = timeEntryRepository;
		this.timeEntrySummary = meterRegistry.summary("timeEntry.summary");
		this.actionCounter = meterRegistry.counter("timeEntry.actionCounter");


	}


	@PostMapping("/time-entries")
	public ResponseEntity create(@RequestBody TimeEntry timeEntry) {
		TimeEntry createdTimeEntry = timeEntryRepository.create(timeEntry);
		actionCounter.increment();
		timeEntrySummary.record(timeEntryRepository.list().size());

		return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);
	}

	@GetMapping("/time-entries/{id}")
	public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {
		TimeEntry entry = timeEntryRepository.find(timeEntryId);
		if (entry == null){
			return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
		}
		actionCounter.increment();
		return new ResponseEntity<TimeEntry>(entry, HttpStatus.OK);
	}

	@GetMapping("/time-entries")
	public ResponseEntity<List<TimeEntry>> list() {
		actionCounter.increment();
		return new ResponseEntity<List<TimeEntry>>(timeEntryRepository.list(), HttpStatus.OK);
	}

	@PutMapping("/time-entries/{id}")
	public ResponseEntity update(@PathVariable("id") long timeEntryId, @RequestBody TimeEntry expected) {
		TimeEntry entry = timeEntryRepository.update(timeEntryId, expected);
		if (entry == null){
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		actionCounter.increment();
		return new ResponseEntity(entry, HttpStatus.OK);
	}

	@DeleteMapping("/time-entries/{id}")
	public ResponseEntity delete(@PathVariable("id") long timeEntryId) {
		timeEntryRepository.delete(timeEntryId);
		actionCounter.increment();
		timeEntrySummary.record(timeEntryRepository.list().size());

		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
    
}