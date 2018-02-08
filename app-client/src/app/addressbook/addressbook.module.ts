import {AddressBookComponent} from "./addressbook.component";
import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({
  imports: [ReactiveFormsModule, CommonModule],
  declarations: [AddressBookComponent],
  exports: [AddressBookComponent]
})
export class AddressBookModule {

}
