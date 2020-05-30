import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileHandlerService {

  private imageHandlerApiEndpoint: string = "/api/filehandler";

  constructor(private http: HttpClient) { }

  public getNamedImageUrl(name: string): string {
    return `${this.imageHandlerApiEndpoint}/${name}`;
  }

  public add(fileData: FormData): Observable<FormData> {
    return this.http.post<FormData>(this.imageHandlerApiEndpoint, fileData);
  }

}
