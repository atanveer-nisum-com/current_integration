import {ProductService} from './products/products.service';
import {AppComponent} from './app.component';

import {TestBed, getTestBed, async, inject} from '@angular/core/testing';
import {Headers, BaseRequestOptions, Response, HttpModule, Http, XHRBackend, RequestMethod} from '@angular/http';

import {ResponseOptions} from '@angular/http';
import {MockBackend, MockConnection} from '@angular/http/testing';

import {mockProducts} from './products/products.mock.json';

describe('AppComponent', function() {

  let mockBackend;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers: [
        ProductService,
        MockBackend,
        BaseRequestOptions,
        {
          provide: Http,
          deps: [MockBackend, BaseRequestOptions],
          useFactory:
          (backend: XHRBackend, defaultOptions: BaseRequestOptions) => {
            return new Http(backend, defaultOptions);
          }
        }
      ],
      imports: [
        HttpModule
      ]
    });

    mockBackend = getTestBed().get(MockBackend);
  }));

  it('should create component', async(inject([ProductService], (productService: ProductService) => {



    getTestBed().compileComponents().then(() => {
      mockBackend.connections.subscribe(
        (connection: MockConnection) => {
          connection.mockRespond(new Response(
            new ResponseOptions({
              body: mockProducts
            }
            )));
        }
      );
    });

    let pService = getTestBed().get(ProductService);

    let appComp: AppComponent = new AppComponent(pService);
    expect(appComp).toBeDefined();
  })));

});
