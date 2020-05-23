import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileHandlerService {

  private imageHandlerApiEndpoint: string = "/api/filehandler";

  constructor(private http: HttpClient) {
  }

  public getFileByName(name: string): Observable<ImageData> {
    const endpointPattern = `${this.imageHandlerApiEndpoint}/${name}`;
    return this.http.get<ImageData>(endpointPattern);
  }

  public add(fileData: FormData): Observable<FormData> {
    return this.http.post<FormData>(this.imageHandlerApiEndpoint, fileData);
  }

}
