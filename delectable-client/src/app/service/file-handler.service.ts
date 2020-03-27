import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs'; 

@Injectable({
  providedIn: 'root'
})
export class FileHandlerService {

  private fileUrl: string;
  private suffix: string;

  constructor(private http: HttpClient) { 
    this.fileUrl = 'http://localhost:8000/filehandler';
  }

  public add(fileData: FormData) : Observable<FormData> {
    this.suffix = '/add';
    return this.http.post<FormData>(this.fileUrl+this.suffix, fileData);
  }

}
