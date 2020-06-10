import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Schedule } from './schedule';

@Injectable()
export class ScheduleService {

  private scheduleApiEndpoint: string = "/api/schedule";

  constructor(private http: HttpClient) {}

  public findAllScheduled(): Observable<Schedule[]> {
    return this.http.get<Schedule[]>(this.scheduleApiEndpoint)
  }

  public findAllScheduledByEpoch(epoch: number): Observable<Schedule[]> {
    const endpointPattern = `${this.scheduleApiEndpoint}/${epoch}`;
    return this.http.get<Schedule[]>(endpointPattern);
  }

  public getAllMealTypes(): Observable<string[]> {
    const endpointPattern = `${this.scheduleApiEndpoint}/mealtypes`;
    return this.http.get<string[]>(endpointPattern)
  }

  public getAllScheduleTypes(): Observable<string[]> {
    const endpointPattern = `${this.scheduleApiEndpoint}/scheduletypes`;
    return this.http.get<string[]>(endpointPattern)
  }

  public add(schedule: Schedule): Observable<Schedule> {
    return this.http.post<Schedule>(this.scheduleApiEndpoint, schedule);
  }

  public delete(id: number): Observable<Schedule> {
    const endpointPattern = `${this.scheduleApiEndpoint}/${id}`;
    return this.http.delete<Schedule>(endpointPattern);
  }

}
