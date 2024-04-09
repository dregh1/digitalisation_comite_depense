import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  role: string | null | undefined;
  constructor() { }

  ngOnInit(): void {
    
    this.role =  sessionStorage.getItem("role");
    // sessionStorage.removeItem("role");


  }


}
