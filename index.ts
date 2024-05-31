import {
  NativeModules
} from 'react-native';

const RNBraintreeDropIn = NativeModules.RNBraintreeDropIn
  ? NativeModules.RNBraintreeDropIn
  : new Proxy(
    {},
    {
      get(): never {
        throw new Error('LINKING_ERROR');
      },
    }
  );



interface PaymentMethodDetails {
  nonce: string;
  isDefault: boolean;
  type: string;
  description: string;
}
interface ShowParams {
  token: string;
}

export function showDropIn(params: ShowParams): Promise<PaymentMethodDetails> {
  return RNBraintreeDropIn.showDropIn(params);
}