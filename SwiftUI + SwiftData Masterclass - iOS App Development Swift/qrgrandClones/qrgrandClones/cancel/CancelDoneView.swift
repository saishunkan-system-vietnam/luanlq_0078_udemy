//
//  CancelDoneView.swift
//  qrgrandClones
//
//  Created by LuanLQ on 30/11/2023.
//

import SwiftUI

struct CancelDoneView: View {
    @EnvironmentObject private var controller: CancelController
    @EnvironmentObject private var router: Router
    
    var body: some View {
        VStack {
            CustomAppbar(title: "完了", isBack: false, backgroundColor: errorColor)
            VStack(alignment: .center) {
                Spacer().frame(height: size.height * 0.2)
                Image("icon_ok")
                    .resizable()
                    .frame(width: 116, height: 116)
                    .padding(.top, 30)
                    .padding(.bottom, 10)
                Text("補助金申請の")
                    .font(AppFont.regular(20)) +
                Text("取り消し")
                    .font(AppFont.regular(20))
                    .foregroundColor(errorColor) +
                Text("が")
                    .font(AppFont.regular(20))
                Text("完了しました")
                    .font(AppFont.regular(20))
                Spacer()
                PrimaryButton(title: "メインメニューへ", width: 200,
                              height: 50, backgroundColor: errorColor) {
                    controller.rest()
                    router.go(.home)
                }
                              .padding(.bottom, 10)
            }
        }
    }
}

struct CancelDoneView_Previews: PreviewProvider {
    static var previews: some View {
        CancelDoneView()
    }
}
