package com.edigest.journalApp.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "journal_entries")
public class JournalEntry {

    @Id
    private Object objectId;
    private String name;
    private String content;
    private Date date;

    public Object getObjectId() {
        return objectId;
    }

    public void setObjectId(Object objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
