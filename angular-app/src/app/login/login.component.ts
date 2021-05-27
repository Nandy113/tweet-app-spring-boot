import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { User } from '../User';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  emailAddress: string;
  password: string;
  error: string;

  constructor(private userService: UserService, private router: Router) { }


  ngOnInit(): void {

  }



  onSubmit(form: NgForm) {
    this.userService.authenticate(form.value.username, form.value.password).subscribe(
      (response) => {
        console.log("sign in success");
        this.userService.isLoggedIn = true
        this.userService.loggedInUser = form.value.username;
        this.router.navigate(['view-tweets']);
      },
      (responseError) => {
        if (responseError.status = '401') {
          this.error = "Invalid Credentials"
        }
      }
    );
  }
}
