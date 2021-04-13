import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DatetrackerService {

  private date: Date;

  constructor() {
    this.date = new Date();
  }

  public get value(): Date {
    return this.date;
  }

  public set value(v: Date) {
    this.date = v;
  }

  public getDateISOFormat() {
    this.date.toISOString().substring(0, 10);
  }

}
