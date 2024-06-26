package com.taskifyrestapi.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.taskifyrestapi.application.enums.Role;
import java.util.List;

@Entity
@DiscriminatorValue("teamleader")
public class TeamLeader extends User {
    @JsonIgnore
    @OneToMany(mappedBy = "teamLeader", fetch = FetchType.LAZY)
    private List<Project> projects;

    public TeamLeader(String email, String firstName, String lastName, String password) {
        super(email, firstName, lastName, password, Role.TEAMLEADER);
    }

    public TeamLeader() {
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}