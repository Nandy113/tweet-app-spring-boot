import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }


  getCurrentUser() {
    return this.userService.loggedInUser;
  }

  isLoggedIn() {
    return this.userService.isLoggedIn;
  }

  logOut() {
    this.userService.loggedInUser = ''
    this.userService.isLoggedIn = false;
  }
}
