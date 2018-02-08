import { LocalCacheRepository } from './../shared/local-cache-repository';
import { Component, OnInit } from '@angular/core';
import { StoresService } from '../service/stores.service';
import { Page } from "../models/page";
import { Store } from "../models/store";
import swal from 'sweetalert2';

@Component({
  selector: 'app-preferred-store',
  templateUrl: './preferred-store.component.html',
  styleUrls: ['./preferred-store.component.css'],
  providers: [StoresService]
})
export class PreferredStoreComponent implements OnInit {

  private userStore: Store = new Store();
  private storeList: Store[] = [];
  private user: any;

  private pageStore: Page<Store>
  private localCacheRepository: LocalCacheRepository<Store> = new LocalCacheRepository<Store>(5);

  constructor(private storeSerivce: StoresService) { }

  ngOnInit() {
    console.log("innit called");
    this.user = localStorage.getItem('user');
    this.storeSerivce.getSelectedStore(this.user).subscribe(store => {
      this.userStore = store;
      this.storeList.push(store);
    });
  }

  getStore(zipcode: number = 1) {
    if (zipcode / 10000 >= 1) {
      this.storeSerivce.getStores(zipcode).subscribe(page => {
        this.pageStore = page;
        this.localCacheRepository.clear();
        if (page.content.length != 0) {
          this.localCacheRepository.cacheData(page.content);
          if (this.localCacheRepository.haveEnoughData)
            this.storeList = this.localCacheRepository.next();
          if (this.userStore.id) {
            this.storeList = this.storeList.filter(store => store.id != this.userStore.id);
            this.storeList.unshift(this.userStore);
          }
        }
      });
    }
    else{
      swal("", "Please provide proper Zipcode", "info");
    }
  }

  sendPrefferedStore(store: any) {
    if (store != null && this.user != null) {
      this.storeSerivce.postStore(store.id, this.user).subscribe(store => {
        this.storeList = this.storeList.filter(arrayStore => arrayStore.zipcode == store.zipcode).filter(arrayStore => arrayStore.id != store.id);
        this.userStore = store;
        this.storeList.unshift(this.userStore);
        swal("", "Default store has been changed!", "success");
      });
    } else {
      swal("", "User has been expired!", "error");
    }
  }

  onScroll() {
    if (this.localCacheRepository.haveEnoughData) {
      this.storeList = this.storeList.concat(this.localCacheRepository.next());
    }
  }
}