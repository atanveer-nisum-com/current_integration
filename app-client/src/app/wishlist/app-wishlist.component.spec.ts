import { NO_ERRORS_SCHEMA } from "@angular/core";
import { AppWishlistComponent } from "./app-wishlist.component";
import { ComponentFixture, TestBed } from "@angular/core/testing";

describe("AppWishlistComponent", () => {

  let fixture: ComponentFixture<AppWishlistComponent>;
  let component: AppWishlistComponent;
  beforeEach(() => {
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      providers: [
      ],
      declarations: [AppWishlistComponent]
    });

    fixture = TestBed.createComponent(AppWishlistComponent);
    component = fixture.componentInstance;

  });

  it("should be able to create component instance", () => {
    expect(component).toBeDefined();
  });
  
});
