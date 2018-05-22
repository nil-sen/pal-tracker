package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;



public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    public InMemoryTimeEntryRepository(){


    }

    public Map<Long, TimeEntry> getEntries() {
        return entries;
    }

    public void setEntries(Map<Long, TimeEntry> entries) {
        this.entries = entries;
    }

    public Long getCounter() {
        return counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    private Map<Long,TimeEntry> entries = new HashMap<Long,TimeEntry>();
    private Long counter = 0L;

    private Long add(TimeEntry timeEntry){
        System.out.println("counter"+this.getCounter());
        this.setCounter(this.getCounter() + 1);
        timeEntry.setId(this.getCounter());
        this.getEntries().put(this.getCounter(),timeEntry);
        System.out.println("counter"+this.getCounter());
        return this.getCounter();
    }

    public TimeEntry create(TimeEntry timeEntry){

       Long id = add(timeEntry);

       return this.getEntries().get(id);

    }


    public TimeEntry find(long id){

        return this.getEntries().get(new Long(id));
    }



    public TimeEntry update(long id,TimeEntry timeEntry){

        TimeEntry obj = this.getEntries().get(new Long(id));
        obj.setProjectId(timeEntry.getProjectId());
        obj.setUserId(timeEntry.getUserId());
        obj.setDate(timeEntry.getDate());
        obj.setHours(timeEntry.getHours());

        this.getEntries().put(new Long(id),obj);

        return this.getEntries().get(new Long(id));
    }

    public void delete(long id){
        this.getEntries().remove(new Long(id));
    }

    public List<TimeEntry> list(){

        List<TimeEntry> list = new ArrayList<TimeEntry>();
        for (Long key : this.getEntries().keySet()) {
            TimeEntry entry = this.getEntries().get(key);
            list.add(entry);
        }


        return list;
    }
}