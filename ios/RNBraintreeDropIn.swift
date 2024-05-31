import Foundation
import React
import BraintreeDropIn
import UIKit

@objc(RNBraintreeDropIn)
class RNBraintreeDropIn: RCTEventEmitter {
    private var count = 0

    @objc(showDropIn:resolver:rejecter:)
    func showDropIn(params: NSDictionary, resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) {
        guard let token = params["token"] as? String else {
            reject("INVALID_PARAMETERS", "Token is missing", nil)
            return
        }
        DispatchQueue.main.async {
            guard let topController = UIApplication.shared.keyWindow?.rootViewController else {
                reject("NO_ROOT_CONTROLLER", "No root view controller", nil)
                return
            }
            
            let request = BTDropInRequest()
            let dropIn = BTDropInController(authorization: token, request: request) { (controller, result, error) in
                if let error = error {
                    reject("BT_DROPIN_ERROR", error.localizedDescription, error)
                } else if result?.isCanceled == true {
                    reject("BT_DROPIN_CANCELED", "User canceled the drop-in", nil)
                } else if let result = result, let paymentMethod = result.paymentMethod {
                    // Prepare the result dictionary
                    let resultData: [String: Any] = [
                        "nonce": paymentMethod.nonce,
                        "isDefault": paymentMethod.isDefault,
                        "type": paymentMethod.type,
                        "description": result.paymentDescription
                    ]
                    resolve(resultData)
                } else {
                    reject("BT_DROPIN_UNKNOWN", "Unknown error occurred", nil)
                }
                controller.dismiss(animated: true, completion: nil)
            }
            topController.present(dropIn!, animated: true, completion: nil)
            
        }
    }
    
    @objc
    override static func requiresMainQueueSetup() -> Bool {
        return true
    }
    
    override func supportedEvents() -> [String]! {
        return []
    }
}
