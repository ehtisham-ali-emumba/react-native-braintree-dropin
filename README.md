# react-native-braintree-dropin-wrapper

This is a React Native module for integrating the Braintree Drop-In Payment UI into your mobile application.

## Installation

```
 npm i react-native-braintree-dropin-wrapper

 or

 yarn add react-native-braintree-dropin-wrapper

```

### For React Native v0.60 and above (Autolinking)

As [react-native@0.60](https://reactnative.dev/blog/2019/07/03/version-60) and above supports autolinking there is no need to run the linking process.
Read more about autolinking [here](https://github.com/react-native-picker/cli/blob/master/docs/autolinking.md).

## Usage

```
 import RNBraintreeDropIn from "react-native-braintree-dropin-wrapper";

 RNBraintreeDropIn.showDropIn({
    token: '// your client token here',
  })
   .then((result) => ({ // do stuff here }))
   .catch((error) => ({ // handle error here }));

```

## Important Note

### Android

Additionally, add the following Maven repository and (non-sensitive) credentials to your app-level gradle, this is also mentioned [here](https://github.com/braintree/braintree-android-drop-in?tab=readme-ov-file#adding-it-to-your-project):

```groovy
repositories {
    maven {
        url "https://cardinalcommerceprod.jfrog.io/artifactory/android"
        credentials {
            username 'braintree_team_sdk'
            password 'AKCp8jQcoDy2hxSWhDAUQKXLDPDx6NYRkqrgFLRc3qDrayg6rrCbJpsKKyMwaykVL8FWusJpp'
        }
    }
}
```

In your `res/values/styles.xml` file, you need to add the following code inside the `<resources>` tag:

```xml
  <style name="MyTransparentTheme" parent="Theme.AppCompat.NoActionBar">
      <item name="android:windowIsTranslucent">true</item>
      <item name="android:windowBackground">@android:color/transparent</item>
      <item name="android:backgroundDimEnabled">false</item>
      <item name="android:windowNoTitle">true</item>
      <item name="android:windowActionBar">false</item>
      <item name="android:windowFullscreen">false</item>
      <item name="android:windowContentOverlay">@null</item>
  </style>
```

In your `AndroidManifest.xml` file, you need to add the following code inside the `<application>` tag:

```xml
  <activity
    android:name="com.ehtishamali042.RNBraintreeDropIn.RNBraintreeDropInActivity"
    android:exported="false"
    android:theme="@style/MyTransparentTheme" />
```

### IOS

You must have a iOS deployment target >= 9.0.

If you don't have a Podfile or are unsure on how to proceed, see the [CocoaPods](http://guides.cocoapods.org/using/using-cocoapods.html) usage guide.

Add this in your ios/Podfile

```
pod 'BraintreeDropIn', '9.12.2', :modular_headers => true
```

## Demo

![](/assets/sample.gif)
