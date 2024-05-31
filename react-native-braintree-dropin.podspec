require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

folly_compiler_flags = '-DFOLLY_NO_CONFIG -DFOLLY_MOBILE=1 -DFOLLY_USE_LIBCPP=1 -Wno-comma -Wno-shorten-64-to-32'

Pod::Spec.new do |s|
  s.name         = "react-native-braintree-dropin"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.homepage     = "https://github.com/ehtishamali042/react-native-braintree-dropin.git"
  s.license      = "MIT"
  s.authors      = "ehtishamali042"

  s.platforms    = { :ios => "11.0" }
  s.source       = { :git => "https://github.com/ehtishamali042/react-native-braintree-dropin.git", :tag => "#{s.version}" }

  s.source_files = "ios/**/*.{h,m,mm,swift}"


  s.dependency "React-Core"
  s.dependency "BraintreeDropIn", '9.12.2'
  

  # Don't install the dependencies when we run `pod install` in the old architecture.
  if ENV['RCT_NEW_ARCH_ENABLED'] == '1' then
    s.compiler_flags = folly_compiler_flags + " -DRCT_NEW_ARCH_ENABLED=1"
    s.pod_target_xcconfig    = {
        "HEADER_SEARCH_PATHS" => "\"$(PODS_ROOT)/boost\"",
        "OTHER_CPLUSPLUSFLAGS" => "-DFOLLY_NO_CONFIG -DFOLLY_MOBILE=1 -DFOLLY_USE_LIBCPP=1",
        "CLANG_CXX_LANGUAGE_STANDARD" => "c++17"
    }
    s.dependency "React-Codegen"
    s.dependency "RCT-Folly"
    s.dependency "RCTRequired"
    s.dependency "RCTTypeSafety"
    s.dependency "ReactCommon/turbomodule/core"
  end
end
