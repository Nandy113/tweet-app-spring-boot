import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Reply } from '../Reply';

@Component({
  selector: 'app-view-replies',
  templateUrl: './view-replies.component.html',
  styleUrls: ['./view-replies.component.css']
})
export class ViewRepliesComponent implements OnInit {

  constructor(private route: ActivatedRoute) { }
  @Input("reply")
  reply:Reply
  ngOnInit(): void {
  

  }

  
}
