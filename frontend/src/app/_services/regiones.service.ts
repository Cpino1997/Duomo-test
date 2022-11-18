import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Region } from '../models/region.model';

const baseUrl = 'http://localhost:8080/api/regiones';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class RegionesService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Region[]> {
    return this.http.get<Region[]>(baseUrl, httpOptions);
  }

  get(id: any): Observable<Region> {
    return this.http.get<Region>(`${baseUrl}/${id}`);
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
