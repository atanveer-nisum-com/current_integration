import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
    moduleId: module.id,
    selector: 'app-preferences',
    templateUrl: 'preferences.component.html',
    styleUrls: ['preferences.component.css']
})
export class PreferencesComponent implements OnInit {
    constructor(private router: Router) {}

    preferences: any = ['Categories', 'Notification', 'Preferred store'];

    ngOnInit(): void {
        if (localStorage.getItem('isLoged') === 'false') {
            this.router.navigateByUrl('/login');
            localStorage.setItem('oldstate', this.router.url);
        }
   }
}
