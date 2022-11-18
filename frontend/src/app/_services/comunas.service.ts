import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comuna } from '../models/comuna.model';

const baseUrl = 'http://localhost:8080/api/comunas';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ComunaService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Comuna[]> {
    return this.http.get<Comuna[]>(baseUrl, httpOptions);
  }

  get(id: any): Observable<Comuna> {
    return this.http.get<Comuna>(`${baseUrl}/${id}`);
  }
  getByRegion(id: any): Observable<Comuna[]> {
    return this.http.get<Comuna[]>(`${baseUrl}/reg/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }
  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }
}
