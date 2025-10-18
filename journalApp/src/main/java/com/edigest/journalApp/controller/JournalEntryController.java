package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.JournalEntry;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {

    private Map<Long, JournalEntry> map = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getEntry(){
        return new ArrayList<>(map.values());
    }

//    @PostMapping
//    public boolean createMapping(@RequestBody JournalEntry entry){
//        map.put(entry.getId(), entry);
//        return true;
//    }

    @GetMapping("/id/{myId}")
    public JournalEntry getJournal(@PathVariable Long myId){
        JournalEntry entry = map.get(myId);
        if(entry != null)return entry;

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Journal entry not found with ID: " + myId);
    }

    @DeleteMapping("/id/{myId}")
    public boolean deleteJournal(@PathVariable Long myId){
        return map.remove(myId) != null;
    }

    @PutMapping("/id/{myId}")
    public boolean editJournal(@PathVariable Long myId, @RequestBody JournalEntry myEntry){
        map.put(myId, myEntry);
        return true;
    }
}
