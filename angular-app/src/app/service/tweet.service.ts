import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Reply } from '../Reply';
import { Tweet } from '../tweet';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class TweetService {
  deleteTweet(id: number) {
    return this.httpclient.delete(environment.baseUrl + '/tweets/' + this.userService.loggedInUser + "/delete?id=" + id);  
  }
  replyToTweet(reply: Reply, id: number) {
    return this.httpclient.post(environment.baseUrl + '/tweets/' + this.userService.loggedInUser + "/reply?id=" + id,reply);
  }

  constructor(private httpclient: HttpClient, private userService: UserService) { }

  fetchAllTweets(): Observable<Array<any>> {
    return <Observable<any>>this.httpclient.get(environment.baseUrl + '/tweets/all');
  }

  postTweets(tweet: Tweet) {
    return this.httpclient.post(environment.baseUrl + "/tweets/" + this.userService.loggedInUser + "/add", tweet);

  }

  updateTweet(tweet: Tweet) {
    return this.httpclient.put(environment.baseUrl + "/tweets/" + this.userService.loggedInUser + "/update?id=" + tweet.id, tweet);
  }

  fetchUserTweets():Observable<Array<any>> {
    return <Observable<any>>this.httpclient.get(environment.baseUrl + '/tweets/' + this.userService.loggedInUser);
  }
}
