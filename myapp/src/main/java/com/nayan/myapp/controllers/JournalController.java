package com.nayan.myapp.controllers;


import com.nayan.myapp.entity.Journal;
import com.nayan.myapp.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;


//    get all journal data
    @GetMapping("/get_all")
    public List<Journal> getAll(){
        return journalService.getAll();
    }


//    add new journal
    @PostMapping("/add")
    public ResponseEntity<Boolean> addJournal(@RequestBody Journal journal){
        try{
            return new ResponseEntity<Boolean>(journalService.save(journal), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
        }
    }

//    get any journal by id
    @GetMapping("/get/{id}")
    public ResponseEntity<Journal> getById(@PathVariable Long id){
        Optional<Journal> option  =  journalService.getJournalById(id);
        if(option.isPresent())
            return new ResponseEntity<Journal>(option.get(), HttpStatus.OK);


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    delete journal
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Journal> deleteById(@PathVariable Long id){
        Journal journal = journalService.deleteJournalById(id);
        if(journal != null)return new ResponseEntity<Journal>(journal, HttpStatus.NO_CONTENT);
        return new ResponseEntity<Journal>(HttpStatus.NOT_FOUND);
    }


//    update journal
    @PutMapping("/update/{id}")
    public ResponseEntity<Journal> updateJournalById(@RequestBody Journal newJournal, @PathVariable Long id){
        Journal journal = journalService.updateJournal(newJournal, id);
        if(journal != null)return new ResponseEntity<Journal>(journal, HttpStatus.OK);

        return new ResponseEntity<Journal>(HttpStatus.NOT_FOUND);
    }

}
