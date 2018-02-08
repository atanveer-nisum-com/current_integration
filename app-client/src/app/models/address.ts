import {Country} from './country';
import {CountryState} from './state';

export interface Address {
  id?: number;
  userId?: number;
  addressLine1: string;
  addressLine2: string;
  addressType: number;
  phoneNumber: string;
  city: string;
  stateBean?: CountryState;
  zipcode: string;
  countryBean: Country;
  isDefault?: number;
}
