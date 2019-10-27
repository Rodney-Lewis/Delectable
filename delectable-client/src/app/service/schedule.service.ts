import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Schedule } from '../model/schedule';

@Injectable()
export class ScheduleService {

  private scheduleApiUrl: string;
  private suffix: string;

  constructor(private http: HttpClient) {
    this.scheduleApiUrl='http://localhost:8080/schedule';
  }

  public findAllByTimeframe(start:number, end:number) : Observable<Schedule[]> {
    this.suffix ='/get';
    let params = new HttpParams();
    params = params.append("start", start.toString());
    params = params.append("end", end.toString());

    return this.http.get<Schedule[]>(this.scheduleApiUrl+this.suffix, {params: params})
  }

  public add(schedule: Schedule) : Observable<Schedule> {
    this.suffix = '/add';
    return this.http.post<Schedule>(this.scheduleApiUrl+this.suffix, schedule);
  }

  public delete(id: number) : Observable<Schedule> {
    this.suffix = '/delete';
    let params = new HttpParams();
    params = params.append("id", id.toString());
    return this.http.delete<Schedule>(this.scheduleApiUrl+this.suffix);
  }

}
