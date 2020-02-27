import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Schedule } from '../model/schedule';

@Injectable()
export class ScheduleService {

  private scheduleApiUrl: string;
  private suffix: string;

  constructor(private http: HttpClient) {
    this.scheduleApiUrl='http://localhost:8000/schedule';
  }

  public findAllScheduled() : Observable<Schedule[]> {
    this.suffix ='/get';
    return this.http.get<Schedule[]>(this.scheduleApiUrl+this.suffix)
  }

  public findAllScheduledByEpoch(epoch: number) : Observable<Schedule[]> {
    this.suffix ='/get/';
    return this.http.get<Schedule[]>(this.scheduleApiUrl+this.suffix+epoch.toString())
  }

  public getAllMealTypes() : Observable<string[]> {
    this.suffix ='/get/mealtypes';
    return this.http.get<string[]>(this.scheduleApiUrl+this.suffix)
  }

  public add(schedule: Schedule) : Observable<Schedule> {
    this.suffix = '/add';
    return this.http.post<Schedule>(this.scheduleApiUrl+this.suffix, schedule);
  }

  public delete(id: number) : Observable<Schedule> {
    this.suffix = '/delete/';
    return this.http.delete<Schedule>(this.scheduleApiUrl+this.suffix+id.toString());
  }

}
