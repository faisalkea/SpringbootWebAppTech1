package com.faisaljarkass.demo.domains;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "testTable")
public class TestRestModel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @NotEmpty
    @Column(name = "createdBy")
    private String createdBy;

    @NotEmpty
    @Column(name = "timestamp")
    private String timestamp;

    @NotEmpty
    @Column(name = "description")
    private String description;

    @Column(name = "reCreatable")
    private boolean reCreatable = true;


    public TestRestModel() {
    }

    public TestRestModel(String createdBy, String timestamp, String description, boolean reCreatable) {
        this.createdBy = createdBy;
        this.timestamp = timestamp;
        this.description = description;
        this.reCreatable = reCreatable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isReCreatable() {
        return reCreatable;
    }

    public void setReCreatable(boolean reCreatable) {
        this.reCreatable = reCreatable;
    }

    @Override
    public String toString() {
        return "TestRestModel{" +
                "id=" + id +
                ", createdBy='" + createdBy + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", description='" + description + '\'' +
                ", reCreatable=" + reCreatable +
                '}';
    }

}
