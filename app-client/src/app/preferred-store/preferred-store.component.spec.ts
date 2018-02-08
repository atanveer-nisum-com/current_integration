import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PreferredStoreComponent } from './preferred-store.component';

describe('PreferredStoreComponent', () => {
  let component: PreferredStoreComponent;
  let fixture: ComponentFixture<PreferredStoreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PreferredStoreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PreferredStoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
