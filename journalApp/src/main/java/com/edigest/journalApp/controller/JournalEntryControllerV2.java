package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getEntry(){
        return journalEntryService.getAll();
    }

    @PostMapping
    public Boolean createMapping(@RequestBody JournalEntry entry){
//        System.out.println(entry);
        journalEntryService.saveEntry(entry);
        return true;
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getJournal(@PathVariable Long myId){
        return null;
    }

    @DeleteMapping("/id/{myId}")
    public Boolean deleteJournal(@PathVariable Long myId){
        return null;
    }

    @PutMapping("/id/{myId}")
    public Boolean editJournal(@PathVariable Long myId, @RequestBody JournalEntry myEntry){
        return null;
    }
}
