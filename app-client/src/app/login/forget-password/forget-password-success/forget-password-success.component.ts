import { Component, OnInit, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
/**
 * forget password success component
 */
@Component({
    moduleId: module.id,
    selector: 'app-forget-password-success',
    templateUrl: 'forget-password-success.component.html'

})
export class ForgetPasswordSuccessComponent {
    @Input() title: string;

}
