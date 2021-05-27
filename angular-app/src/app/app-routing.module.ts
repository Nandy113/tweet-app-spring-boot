import { NgModule, ViewRef } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { MyTweetsComponent } from './my-tweets/my-tweets.component';
;
import { SignupComponent } from './signup/signup.component';
import { ViewRepliesComponent } from './view-replies/view-replies.component';
import { ViewTweetsComponent } from './view-tweets/view-tweets.component';

const routes: Routes = [
  { path: 'users/login', component: LoginComponent },
  { path: 'users/signup', component: SignupComponent },
  { path: 'view-tweets', component: ViewTweetsComponent },
  { path: 'view-replies', component: ViewRepliesComponent },
  { path: 'my-tweets', component: MyTweetsComponent},
  { path: '', component: ViewTweetsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
