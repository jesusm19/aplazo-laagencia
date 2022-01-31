import { Injectable, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';
import { Request } from 'src/app/domain/payments/request';

@Injectable({
  providedIn: 'root'
})
export class PaymentsService {

  constructor(
    private http: HttpClient
  ) { }

  payments(payment: Request): Observable<any> {
    const url = `${environment.apiUrl}/interests/payments`;
    return this.http.post<any>(url, payment);
    
  }
}
