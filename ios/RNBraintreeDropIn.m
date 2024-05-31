#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(RNBraintreeDropIn, NSObject)

RCT_EXTERN_METHOD(showDropIn:(NSDictionary *)params resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

@end
