package com.delectable.schedule;

public class Schedulable {

  Long id;
  String name;
  ScheduleType scheduleType;

  public Schedulable(Long id, String name, ScheduleType scheduleType) {
    this.id = id;
    this.name = name;
    this.scheduleType = scheduleType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ScheduleType getScheduleType() {
    return scheduleType;
  }

  public void setScheduleType(ScheduleType scheduleType) {
    this.scheduleType = scheduleType;
  }
}
