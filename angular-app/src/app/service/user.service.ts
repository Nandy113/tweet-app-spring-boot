import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { User } from '../User';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  isLoggedIn=false;
  loggedInUser='';

  constructor(private httpClient: HttpClient) { }

  insertNewUser(user: User) {
    return this.httpClient.post(environment.baseUrl + '/tweets/register', user);
  }

  authenticate(emailAddress: string, password: string) {
    let credentials = btoa(emailAddress + ':' + password);
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', 'Basic ' + credentials);
    console.log(headers)
    return this.httpClient.get(environment.baseUrl + '/tweets/login', { headers });
  }
}
