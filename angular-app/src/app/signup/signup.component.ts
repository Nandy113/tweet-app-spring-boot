import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  error: string;

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserService) { }
  signupForm: FormGroup;
  signup: boolean;
  ngOnInit() {
    this.signupForm = new FormGroup({
      'username': new FormControl(null, [Validators.required]),
      'firstName': new FormControl(null, [Validators.required]),
      'lastName': new FormControl(null, [Validators.required]),
      'password': new FormControl(null, [Validators.required, Validators.minLength(8), Validators.maxLength(15)]),
      'emailAddress': new FormControl(null, [Validators.required, Validators.email]),
      'dateOfBirth': new FormControl(null, [Validators.required]),
      'confirmPassword': new FormControl('', [Validators.required])
    });
  }

  onSubmitSignupForm() {
    console.log("In submit");
    this.userService.insertNewUser(this.signupForm.value).subscribe(
      (response) => {
        this.error="Account created successfully. Please login"
      },
      (responseError) => {
      if(responseError.status=='400'){
        this.error='User Already Exists'
      }
      })
  }


}
