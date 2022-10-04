package com.taskscrud.tasks.tasks;

import javax.persistence.*;

@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "title", unique = true, nullable = false)
    private String title;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "done")
    private Boolean done = false;

    public Task() {}

    public Task(Long id, String title, String description, Boolean done) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.done = done;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
