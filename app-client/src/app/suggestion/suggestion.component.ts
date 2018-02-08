import { FormBuilder, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs/Rx';
import { Http } from '@angular/http';
import { SearchService } from './../service/search.service';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeHtml } from "@angular/platform-browser";

@Component({
  selector: 'suggestion-box',
  templateUrl: 'suggestion.component.html',
  styleUrls: ['suggestion.component.css'],
  providers: [SearchService]
})
export class SuggestionComponent implements OnInit {

  public myForm: FormGroup;
  keyword: string;

  constructor(public searchService: SearchService, private _sanitizer: DomSanitizer, private builder: FormBuilder) { }

  public searchData: any[] = [];

  reloadListInDelay = (evt: any): void => {
    let keyword = evt.target.value;
    if (keyword && keyword.length >= 2) {
     /* this.searchService.doSuggest(keyword).subscribe(page => {
        this.searchData = page;
        this.autocompleListFormatter(page);
      });*/
    } else {

    }
  };

  autocompleListFormatter = (data: any) => {
    const [name, parent] = data.name.split('+');
    let html = `<small class="productinfo"><var>${parent}</var></small> <b style="color :rgba(254, 138, 15, 0.8);">${name}</b>`;
    return this._sanitizer.bypassSecurityTrustHtml(html);
  }

  ngOnInit() {
    this.myForm = this.builder.group({
      continent: "",
    });
    this.searchService.doSuggest("i").subscribe(page => {
      this.searchData = page;
    //  this.autocompleListFormatter(page);
    });
  }

}
