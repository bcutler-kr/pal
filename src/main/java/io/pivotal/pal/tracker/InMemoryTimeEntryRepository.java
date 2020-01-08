package io.pivotal.pal.tracker;

import java.sql.Time;
import java.util.List;
import java.util.ArrayList;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
	private List<TimeEntry> myEntries;
	private long id;
	public InMemoryTimeEntryRepository(){
		this.myEntries = new ArrayList<TimeEntry>();
		this.id = 0L;
	}

	public TimeEntry create(TimeEntry timeEntry) {
		TimeEntry newEntry = getTimeEntryWithId(timeEntry);
		this.myEntries.add(newEntry);
		return newEntry;
	}

	private TimeEntry getTimeEntryWithId(TimeEntry timeEntry) {
		this.id+=1;
		return new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
	}

	public List<TimeEntry> list() {
		return this.myEntries;
	}

	public TimeEntry update(long id, TimeEntry timeEntry) {
		//TODO use a stream or something fancy? Use find logic?
		TimeEntry timeEntryWithId =  new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
		for(int i = 0; i < this.myEntries.size() ; i++){
			if (this.myEntries.get(i).getId() == id ){
				this.myEntries.set(i, timeEntryWithId);
				return timeEntryWithId;
			}
		}
		return null; // todo raise error?
	}


	public TimeEntry find(long id) {
		//TODO use a stream or something fancy
		for (TimeEntry myEntry : this.myEntries) {
			if (myEntry.getId() == id) {
				return myEntry;
			}
		}
		return null; // todo raise error?
	}

	public void delete(long id) {
		// TODO Maybe use a filter here
		for(int i = 0; i< this.myEntries.size() ; i++){
			if (this.myEntries.get(i).getId() == id ){
				this.myEntries.remove(i);
				return;
			}
		}
	}

}