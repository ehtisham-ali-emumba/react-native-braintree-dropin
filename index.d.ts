declare module 'react-native-braintree-dropin-wrapper' {
    export interface PaymentMethodDetails {
        nonce: string;
        isDefault: boolean;
        type: string;
        description: string;
    }

    interface ShowDropInParams {
        token: string;
    }

    export function showDropIn(params: ShowDropInParams): Promise<PaymentMethodDetails>;
}
