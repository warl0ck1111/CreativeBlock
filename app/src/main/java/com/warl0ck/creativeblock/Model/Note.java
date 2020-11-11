package com.warl0ck.creativeblock.Model;

public class Note{
    private long id;
    private String title;
    private String content;
    private long dateCreated;
    private long dataModified;


    public Note(String title, String content, long dateCreated, long dataModified) {
        this.title = title;
        this.content = content;
        this.dateCreated = dateCreated;
        this.dataModified = dataModified;
    }

    public Note(long id, String title, String content, long dateCreated, long dataModified) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateCreated = dateCreated;
        this.dataModified = dataModified;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title)  {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public long getDataModified() {
        return dataModified;
    }

    public void setDataModified(long dataModified) {
        this.dataModified = dataModified;
    }

}
