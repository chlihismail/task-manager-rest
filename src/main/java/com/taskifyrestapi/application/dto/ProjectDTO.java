package com.taskifyrestapi.application.dto;

import java.util.List;

public class ProjectDTO {
    private String name;
    private String description ;
    private List<Integer> members ;


    public ProjectDTO(String name, String description , List<Integer> members) {
        this.name = name;
        this.description = description;
        this.members = members ;
    }

    public ProjectDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getMembers() {
        return members;
    }

    public void setMembers(List<Integer> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", membersIds=" + members +
                '}';
    }
}
