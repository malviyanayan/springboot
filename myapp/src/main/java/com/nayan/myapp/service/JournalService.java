package com.nayan.myapp.service;

import com.nayan.myapp.entity.Journal;
import com.nayan.myapp.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    public List<Journal> getAll(){
        return journalRepository.findAll();
    }

    public Boolean save(Journal journal){
        journalRepository.save(journal);
        return true;
    }

    public Optional<Journal> getJournalById(Long id){
        return journalRepository.findById(id);
    }

    public Journal deleteJournalById(Long id){
        Journal journal = getJournalById(id).orElse(null);
        if(journal != null){
            journalRepository.delete(journal);
            return journal;
        }

        return null;
    }

    public Journal updateJournal(Journal newJournal, Long id){
        if(newJournal != null){
            Journal oldJournal = getJournalById(id).orElse(null);
            if(oldJournal != null){
                oldJournal.setName(newJournal.getName());
                oldJournal.setContent(newJournal.getContent());
                journalRepository.saveAndFlush(oldJournal);
                return oldJournal;
            }
        }

        return newJournal;
    }
}
