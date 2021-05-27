import { Component, OnInit } from '@angular/core';
import { Reply } from '../Reply';
import { TweetService } from '../service/tweet.service';
import { UserService } from '../service/user.service';
import { Tweet } from '../tweet';

@Component({
  selector: 'app-view-tweets',
  templateUrl: './view-tweets.component.html',
  styleUrls: ['./view-tweets.component.css']
})
export class ViewTweetsComponent implements OnInit {
  tweets: Array<Tweet>;;
  viewReply = false;
  replyId: number
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
    this.fetchAllTweets();

  }

  postUpdate(input: boolean, id: number) {
    this.update = input;
    this.tweet.id = id;
    this.id = id;
    this.tweet.message = this.updateTweet;
    if (!input) {
      this.tweetService.updateTweet(this.tweet).subscribe((response) => {
        this.fetchAllTweets();
      })

    }
  }
  fetchAllTweets() {
    this.tweetService.fetchAllTweets().subscribe((response) => {
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
        this.fetchAllTweets;
      });
    }
  }
  postMessage(input: boolean) {
    this.post = input;
    this.tweet.message = this.insertTweet;
    if (!input) {
      this.tweetService.postTweets(this.tweet).subscribe((response) => {
        this.fetchAllTweets();
      });
    }
  }

  deleteTweet(id: number) {
    this.tweetService.deleteTweet(id).subscribe((response) => {
      this.fetchAllTweets();
    })
  }
  getCurrentUser() {
    return this.userService.loggedInUser;
  }

  viewReplies(input: boolean, id: number) {
    this.viewReply = input
    this.replyId = id;
  }
  isLoggedIn() {
    return this.userService.isLoggedIn;
  }
}
