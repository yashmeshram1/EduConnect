import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavBarComponent implements OnInit {
  role: string | null = null;

  constructor() { }

  ngOnInit(): void {
    this.role = localStorage.getItem('role');
  }
}