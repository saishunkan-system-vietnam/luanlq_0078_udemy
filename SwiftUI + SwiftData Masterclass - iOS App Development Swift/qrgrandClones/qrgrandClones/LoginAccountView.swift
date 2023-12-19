//
//  LoginAccount.swift
//  qrgrandClones
//
//  Created by LuanLQ on 23/10/2023.
//

import SwiftUI

struct LoginAccountView: View {    
    @StateObject private var controller: LoginAccController = .init()
    @EnvironmentObject private var router: Router

    var body: some View {
        VStack {
            CustomAppbar(title: "ログイン") {
                router.maybePop()
            }
            ZStack (alignment: .bottom) {
                VStack  {
                    Spacer()
                    Text("補助金申請\nポータルアカウントログイン")
                        .multilineTextAlignment(.center)
                        .foregroundStyle(primary)
                        .font(AppFont.regular(16))
                        .padding(.bottom, 20)
                    mailTxt
                    passwordTxt
                    submitBtn
                    Spacer()
                }
                .padding(.horizontal, 40)
                .autocorrectionDisabled(true)
                .textInputAutocapitalization(.never)
                
                SecondButton(title: "QRコードログインを\nご利用の場合はこちら", width: size.width/1.2, height: 80, action: {router.replace(.loginQr)})
                    .padding(.bottom)
            }
            .onSubmit {
                if(!controller.isLoading) {
                    dismissKeyboard()
                    controller.submit()
                }
            }

        }
        .background(.white)
        .onAppear {
            controller.initData(router: router)
        }
        .onTapGesture {
            dismissKeyboard()
        }
        .ignoresSafeArea(.keyboard)
    }
}

// MARK: - PREVIEWS
struct LoginAccountView_Previews: PreviewProvider {
    static var previews: some View {
        LoginAccountView()
    }
}
//#Preview {
//    LoginAccountView()
//        .ignoresSafeArea()
//}

private extension LoginAccountView {
    var mailTxt: some View {
        PrimaryInput(title: "メールアドレス", text: $controller.email, frontIcon: "icon_mail", backgroundColor: backgroundColor, textContentType: .emailAddress,  textError: controller.listError[0])
            .padding(.bottom, 10)
    }
    
    var passwordTxt: some View {
        PrimaryInput(title: "パスワード", text: $controller.password, frontIcon: "icon_pass", iconBehind:  controller.isShowPass ? "eye.fill" : "eye.slash.fill", sizefrontIcon: 28, backgroundColor: backgroundColor, textContentType: .password, textError: controller.listError[1], isPassword: !controller.isShowPass, isShowPassword: {controller.isShowPass.toggle()})
    }
    
    var submitBtn: some View {
        PrimaryButton(title: "ログイン", width: UIScreen.main.bounds.width/1.2, height: 55, isLoading: controller.isLoading) {
            dismissKeyboard()
            controller.submit()
        }
        .padding(.top, 30)
    }
}
