import { Subscription } from 'rxjs/Subscription';
import { GuestCheckoutComponent } from './../guest-checkout/guest-checkout.component';
import { ShipmentMethodComponent } from './../shipment-method/shipment-method.component';
import { CustomerCheckoutComponent } from './../customer-checkout/customer-checkout.component';
import { CartViewComponent } from './../../cart/cart-view/cart-view.component';
import { Component, ViewChild, Input, ViewContainerRef, ReflectiveInjector, ComponentFactoryResolver} from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'app-dynamic-checkout',
    entryComponents: [CartViewComponent, CustomerCheckoutComponent, ShipmentMethodComponent, GuestCheckoutComponent],
    templateUrl: 'dynamic-checkout.component.html',
    styleUrls: ['dynamic-checkout.component.css']
})
export class DynamicCheckoutComponent {
  currentComponent = null;

  @ViewChild('dynamicCheckoutComponentContainer', { read: ViewContainerRef }) dynamicCheckoutComponentContainer: ViewContainerRef;

  // component: Class for the component you want to create
  // inputs: An object with key/value pairs mapped to input name/input value
  @Input() set componentData(data: {component: any}) {
    if (!data) {
      data = {component: CustomerCheckoutComponent};
    }

    // this will hide the total amount div on cart view component and show relevant data for checkout
    if (data.component === CartViewComponent) {
      localStorage.setItem('isDisplay', 'hide');
    }

    // passing empty resolve provider because we are not receiving any input data
    const resolvedProviders = ReflectiveInjector.resolve([]);

    // We create an injector out of the data we want to pass down and this components injector
    const injector = ReflectiveInjector.fromResolvedProviders(resolvedProviders, this.dynamicCheckoutComponentContainer.parentInjector);

    // We create a factory out of the component we want to create
    const factory = this.resolver.resolveComponentFactory(data.component);

    // We create the component using the factory and the injector
    const component = factory.create(injector);

    // We insert the component into the dom container
    this.dynamicCheckoutComponentContainer.insert(component.hostView);

    // We can destroy the old component is we like by calling destroy
    if (this.currentComponent) {
      this.currentComponent.destroy();
    }

    this.currentComponent = component;
  }

  constructor(private resolver: ComponentFactoryResolver) {

  }
}
