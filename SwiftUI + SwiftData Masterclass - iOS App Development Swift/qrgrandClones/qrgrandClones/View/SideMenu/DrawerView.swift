//
//  DrawerView.swift
//  qrgrandClones
//
//  Created by LuanLQ on 31/10/2023.
//

import SwiftUI

struct DrawerView: View {
    @Environment(\.drawerPersentationMode) var drawerMode
    @Environment(\.openURL) var openURL
    @EnvironmentObject private var router: Router
    @AppStorage("isLogin") var isLogin: Bool = false

    @State var isShowDialog: Bool = false

    var body: some View {
        VStack(alignment: .center) {
            VStack(alignment: .leading) {
                Text("Test")
                    .font(AppFont.medium(16))
                Spacer()
                Text("ID:00000025")
                    .font(AppFont.regular(16))
                Text("Test")
                    .font(AppFont.bold(16))
            }
            .padding(.horizontal)
            .foregroundColor(.white)
            .padding(.vertical, 10)
            .frame(width: size.width/1.4, height: size.height/5, alignment: .leading)
            .background(primary)
            itemSideBar(title: "プライバシーポリシー", action: {            openURL(URL(string: "https://www.denso-wave.com/ja/privacy/")!)
            })
            itemSideBar(title: "ライセンス", action: {router.push(.license)})
            itemSideBar(title: "利用規約", action: {router.push(.rule)})
            itemSideBar(title: "ログアウト", action: {isShowDialog.toggle()})
            Spacer()
            Text("Ver-\(version)")
                .font(AppFont.regular())
                .foregroundColor(borderColor.opacity(1))
                .padding(.bottom, 10)
        }
        .frame(width: size.width/1.4, alignment: .leading)
        .background(.white)
        .alert("ログアウト", isPresented: $isShowDialog) {
            Button(role: .cancel) {} label: {
                Text("キャンセル")
                    .font(AppFont.medium(17))
            }
            Button(role: .destructive) {
                router.go(.login)
                isLogin = false
            } label: {
                Text("OK")
                    .font(AppFont.bold(17))
            }
        } message: {
            Text("ログアウトしてもよろしいですか？")
                .font(AppFont.regular())
        }
    }
}

struct itemSideBar: View {
    var title: String
    var action: () -> Void
    
    var body: some View {
        Button(action: action) {
            Text(title)
                .foregroundColor(bodyText)
                .font(AppFont.medium())
                .padding(.horizontal)
                .frame(width: size.width/1.43, alignment: .leading)
        }
        .padding(.vertical, 10)
    }
}

struct DrawerView_Previews: PreviewProvider {
    static var previews: some View {
        DrawerView()
    }
}
//#Preview {
//    DrawerView()
//}
