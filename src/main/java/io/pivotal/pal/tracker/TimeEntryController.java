
package io.pivotal.pal.tracker;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.http.HttpStatus;


@RestController

public class TimeEntryController{


    TimeEntryRepository timeEntryRepository;





    public TimeEntryController(TimeEntryRepository repo){
        timeEntryRepository = repo;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create( @RequestBody TimeEntry timeEntryToCreate) {

        TimeEntry entry = timeEntryRepository.create(timeEntryToCreate);


        return new ResponseEntity<>(entry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read( @PathVariable("id") Long id) {

        System.out.println("object is "+timeEntryRepository);
        TimeEntry entry = timeEntryRepository.find(id);

        if(entry == null){
            return new ResponseEntity<>(entry,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(entry, HttpStatus.OK);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {

        List<TimeEntry> list = timeEntryRepository.list();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }



    @RequestMapping(value = "/time-entries/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TimeEntry> update( @PathVariable("id") Long id, @RequestBody TimeEntry timeEntryToUpdate) {

        TimeEntry entry = timeEntryRepository.update(id, timeEntryToUpdate);

        if(entry == null){
            return new ResponseEntity<>(entry,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entry, HttpStatus.OK);
    }


    @RequestMapping(value = "/time-entries/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TimeEntry>  delete( @PathVariable("id") Long id) {

        timeEntryRepository.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}