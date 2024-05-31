# react-native-braintree-dropin-wrapper

This is a React Native module for integrating the Braintree Drop-In Payment UI into your mobile application.

## Installation

```
 npm i react-native-braintree-dropin-wrapper

 or

 yarn add react-native-braintree-dropin-wrapper

```

## Usage

```
import { showDropIn } from "react-native-braintree-dropin-wrapper";

showDropIn({
      token: '// your client token here',
    })
      .then((result) => ({ // do stuff here }))
      .catch((error) => ({ // handle error here }));

```

## Important Note

In your `AndroidManifest.xml` file, you need to add the following code inside the `<application>` tag:

```xml
<activity
    android:name="com.ehtishamali042.RNBraintreeDropIn.RNBraintreeDropInActivity"
    android:exported="false"
    android:theme="@style/MyTransparentTheme">
</activity>
```

## Demo

- view the IOS demo image [here](https://github.com/ehtisham-ali-emumba/react-native-braintree-dropin/blob/main/assets/ios.png).
- view the android demo image [here](https://github.com/ehtisham-ali-emumba/react-native-braintree-dropin/blob/main/assets/android.png).
