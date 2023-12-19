//
//  HomeView.swift
//  qrgrandClones
//
//  Created by LuanLQ on 30/10/2023.
//

import SwiftUI

struct HomeView: View {
    @EnvironmentObject private var router: Router
    @State var isLoading: Bool = false
    @State var isOpenDrawer = false
    
    @State var isShowDialog: Bool = false
    var body: some View {
        Drawer(isOpenDrawer: $isOpenDrawer, menu: {
            DrawerView()
        }, content: {
            VStack {
                CustomAppbar(title: "メインメニュー", isBack: false, isDrawer: true) {
                    isOpenDrawer.toggle()
                }
                VStack{
                    if isLoading { LoadingPageView() } else {
                        ScrollView(showsIndicators: false) {
                            VStack {
                                Spacer()
                                    .frame(height: defaultPadding)
                                MenuHomeItem(text1: "地域クーポンの", text2: "補助金申請", text3: "を行います", buttonText: "補助金申請", buttonColor: primary) {router.push(.apply)}
                                MenuHomeItem(text1: "補助金申請の", text2: "履歴", text3: "を確認します", buttonText: "履歴確認", buttonColor: green) {router.push(.history)}
                                MenuHomeItem(text1: "補助金申請の", text2: "精算状況", text3: "を確認します", buttonText: "精算状況確認", buttonColor: warningColor) { router.push(.batch)}
                                MenuHomeItem(text1: "補助金申請の", text2: "取り消し", text3: "を行います", buttonText: "取り消し", buttonColor: errorColor) {router.push(.cancel)}
                            }
                        }}
                }
                .onAppear{loading()}
            }
        })
    }
        
    
    func loading() {
        isLoading = true
        DispatchQueue.main.asyncAfter(deadline: .now() + 1) {
            isLoading = false
        }
    }
}
    
    struct DrawerItemMenu: View {
        // MARK: - Properties

        private let title: String
        private let onTap: () -> Void

        init(_ title: String, onTap: @escaping () -> Void) {
            self.title = title
            self.onTap = onTap
        }

        var body: some View {
            Button {
                onTap()
            } label: {
                Text(title)
                    .foregroundColor(.black)
                    .font(AppFont.medium(14))
                Spacer()
            }
            .buttonStyle(.borderless)
            .padding(.vertical, 20)
            .padding(.horizontal, 16)
        }
    }

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
//#Preview {
//    HomeView()
//}
