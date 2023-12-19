//
//  LoginQR.swift
//  qrgrandClones
//
//  Created by LuanLQ on 30/10/2023.
//

import SwiftUI

struct LoginQRView: View {
    @AppStorage("isLogin") var isLogin: Bool = false
    @EnvironmentObject private var authController: AuthController
    @StateObject private var controller: LoginQRController  = .init()
    @EnvironmentObject private var router: Router
    
    var body: some View {
        VStack {
            CustomAppbar(title: "QRコードログイン") {router.maybePop()}
            VStack {
                Text("ログイン用QRコード")
                    .font(AppFont.medium(20))
                    .foregroundStyle(link)
                    .padding(.top, 10)
                Text("を読み取ってください")
                    .font(AppFont.regular(15))
                    .foregroundStyle(bodyText)
                ScannerView(isShowingScanner: controller.isShowingScanner, scanMode: .oncePerCode) { result in
                    if case  .success(let code) = result {
                        authController.playSound()
                        if !controller.validateLoginQRCode(scanText: code.string) {
                            return
                        }
                        self.isLogin = true
                        controller.isShowingScanner = false
                        router.go(.home)
                    }
                }
                .padding(.bottom, 20)
                SecondButton(title: "ポータルアカウントを\nご利用の場合はこちら", width: size.width/1.2, height: 80, action: {router.replace(.loginAccount)})
                    .padding(.bottom)
            }
        }
    }
}

struct LoginQRView_Previews: PreviewProvider {
    static var previews: some View {
        LoginQRView()
            .ignoresSafeArea()
    }
}
//#Preview {
//    LoginQRView()
//        .ignoresSafeArea()
//}
