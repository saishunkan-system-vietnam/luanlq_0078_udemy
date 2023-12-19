//
//  ContentView.swift
//  qrgrandClones
//
//  Created by LuanLQ on 18/10/2023.
//

import SwiftUI

struct LoginView: View {    
    @EnvironmentObject private var router: Router
    
    var body: some View {
        VStack {
            Spacer()
            Image("logo")
                .resizable()
                .frame(width: 150,height: 150)
                .padding(.bottom, 30)
            Text("QR補助金申請App")
                .font(AppFont.regular())
                .frame(width: 200, height: 32)
                .overlay(
                    RoundedRectangle(cornerRadius: 10)
                        .stroke(primary)
                )
                .padding(.bottom, 20)
                .foregroundStyle(primary)
            PrimaryButton(title: "ポータルアカウントでログイン", width: 300, height: 55, action: {router.push(.loginAccount)})
            .padding(.bottom, 10)
            PrimaryButton(title: "ログインQRコードでログイン", width: 300, height: 55, action: {router.push(.loginQr)})
                .padding(.bottom, 10)
            Text(                    "ポータルアカウントとは、補助金申請ポータルサイトのメールアドレスとパスワードになります。\nログインQRコードは、補助金申請ポータルサイトの「アプリ用ログインQRコード」で表示できます。"
            )
            .foregroundStyle(link)
            .font(AppFont.regular())
            .padding(.horizontal, 10)
            Spacer()
            Text("Ver-\(version)")
                .foregroundStyle(primary)
                .font(AppFont.regular(17))
                .padding(.bottom, 10)
        }
    }
}

// MARK: - PREVIEWS
struct LoginView_Previews: PreviewProvider {
    static var previews: some View {
        LoginView()
            .ignoresSafeArea()
    }
}
//#Preview {
//    LoginView()
//        .ignoresSafeArea()
//}
