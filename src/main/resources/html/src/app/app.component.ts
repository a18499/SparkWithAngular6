import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';
  hello = '';

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get('/hello').subscribe(
      res => this.hello = JSON.parse(JSON.stringify(res)).message);
  }
}
