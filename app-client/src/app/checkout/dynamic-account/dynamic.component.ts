import { RegisterCheckoutComponent } from './../register-checkout/register-checkout.component';
import { GuestCheckoutComponent } from './../guest-checkout/guest-checkout.component';
import { Component, ViewChild, ViewContainerRef, Input, ReflectiveInjector, ComponentFactoryResolver } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'app-dynamic',
     // Reference to the components must be here in order to dynamically create them
    entryComponents: [RegisterCheckoutComponent, GuestCheckoutComponent],
    template: `
    <div #dynamicComponentContainer></div>
  `,
    styleUrls: ['dynamic.component.css']
})
export class DynamicComponent {

    currentComponent = null;

  @ViewChild('dynamicComponentContainer', { read: ViewContainerRef }) dynamicComponentContainer: ViewContainerRef;

  // component: Class for the component you want to create
  // inputs: An object with key/value pairs mapped to input name/input value
  @Input() set componentData(data: {component: any }) {
    if (!data) {
        data = {component: RegisterCheckoutComponent };
    }

    // passing empty resolve provider because we are not receiving any input data
    const resolvedProviders = ReflectiveInjector.resolve([]);

    // We create an injector out of the data we want to pass down and this components injector
    const injector = ReflectiveInjector.fromResolvedProviders(resolvedProviders, this.dynamicComponentContainer.parentInjector);

    // We create a factory out of the component we want to create
    const factory = this.resolver.resolveComponentFactory(data.component);

    // We create the component using the factory and the injector
    const component = factory.create(injector);

    // We insert the component into the dom container
    this.dynamicComponentContainer.insert(component.hostView);

    // We can destroy the old component is we like by calling destroy
    if (this.currentComponent) {
      this.currentComponent.destroy();
    }

    this.currentComponent = component;
  }

  constructor(private resolver: ComponentFactoryResolver) {

  }
}
