package com.harm.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Event {
    @Min(1)
    int id;
    @NotBlank(groups = ValidationGroupForNotBlack.class)
    String name;
    @Min(value = 0, groups = ValidationGroupForNotBlack.class)
    int limit;
    public Event() {}

    public Event(int id, String name, int limit) {
        this.id = id;
        this.name = name;
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", limit=" + limit +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
