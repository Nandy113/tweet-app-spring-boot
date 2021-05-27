import { Component, OnInit } from '@angular/core';
import { Reply } from '../Reply';
import { TweetService } from '../service/tweet.service';
import { UserService } from '../service/user.service';
import { Tweet } from '../tweet';

@Component({
  selector: 'app-view-tweets',
  templateUrl: './my-tweets.component.html',
  styleUrls: ['./my-tweets.component.css']
})
export class MyTweetsComponent implements OnInit {
  tweets: Array<Tweet>;;
  viewReply = false;
  i :number;
  replyId: number
  userName = ''
  insertTweet = ''
  updateTweet = ""
  replyMessage = ''
  post = false;
  reply = false;
  id: number;
  update = false;
  tweet: Tweet = {};
  replyObj: Reply;
  constructor(private tweetService: TweetService, private userService: UserService) { }

 
  ngOnInit(): void {
    this.fetchUserTweets();
  }

  getCurrentUser() {
    this.userName =  this.userService.loggedInUser;
    return this.userName;
  }

  postUpdate(input: boolean, id: number) {
    this.update = input;
    this.tweet.id = id;
    this.id = id;
    this.tweet.message = this.updateTweet;
    if (!input) {
      this.tweetService.updateTweet(this.tweet).subscribe((response) => {
        this.fetchUserTweets();
      })

    }
  }

  fetchUserTweets() {
    this.tweetService.fetchUserTweets().subscribe((response) => {
      console.log(response)
      this.tweets = response;
    });
  }

  postReply(input: boolean, id: number) {
    this.reply = input;
    this.id = id;
    let reply: Reply = {};
    reply.replyMessage = this.replyMessage;
    if (!input) {
      this.tweetService.replyToTweet(reply, id).subscribe((response) => {
          this.tweetService.fetchUserTweets().subscribe((response) => {
            console.log(response)
            this.tweets = response;
            this.viewReplies(true,this.id)
          });
      });
    }
  }

  postMessage(input: boolean) {
    this.post = input;
    this.tweet.message = this.insertTweet;
    if (!input) {
      this.tweetService.postTweets(this.tweet).subscribe((response) => {
        this.fetchUserTweets();
      });
    }
  }

  deleteTweet(id: number) {
    this.tweetService.deleteTweet(id).subscribe((response) => {
      this.fetchUserTweets();
    })
  }

  viewReplies(input: boolean, id: number) {
    this.viewReply = input
    this.replyId = id;
  }

  isLoggedIn() {
    return this.userService.isLoggedIn;
  }
}
