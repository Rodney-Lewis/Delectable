import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Schedule } from './schedule';

@Injectable()
export class ScheduleService {

  private scheduleApiEndpoint: string = "/api/schedule";

  constructor(private http: HttpClient) { }

  public findAllBetweenEpochs(begin: number, end: number): Observable<any> {
    const params = new HttpParams().set("begin", begin.toString()).set("end", end.toString());
    const endpointPattern = `${this.scheduleApiEndpoint}/epochBetween`;
    return this.http.get(endpointPattern, { params, responseType: 'text' });
  }

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
