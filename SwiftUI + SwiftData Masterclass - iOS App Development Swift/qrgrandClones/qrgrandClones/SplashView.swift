//
//  ContentView.swift
//  qrgrandClones
//
//  Created by LuanLQ on 18/10/2023.
//

import SwiftUI

struct SplashView: View {
    @AppStorage("isLogin") var isLogin: Bool = false
    @EnvironmentObject private var router: Router
    
    var body: some View {
        ZStack {
            ZStack {
                primary
                VStack {
                    Image("logos")
                        .resizable()
                        .frame(width: 200,height: 200)
                        .padding(.bottom, 30)
                    Text("QR補助金申請App")
                        .font(AppFont.regular())
                        .foregroundStyle(.white)
                        .frame(width: 200, height: 32)
                        .overlay(
                            RoundedRectangle(cornerRadius: 10)
                                .stroke(.white)
                        )
                }
                VStack{
                    Spacer()
                    Text("Ver-\(version)")
                        .foregroundStyle(.white)
                        .font(AppFont.regular(17))
                        .padding(.bottom, defaultPadding)
                }
            }
            .task {
                try? await Task.sleep(for:.seconds(1.5))
                if isLogin{
                    router.go(.home)
                } else {
                    router.go(.login)
                }
            }
        }
        .ignoresSafeArea()
    }
}

// MARK: - PREVIEWS
struct SplashView_Previews: PreviewProvider {
    static var previews: some View {
        SplashView()
            .environmentObject(Router())
            .ignoresSafeArea()
    }
}
//#Preview {
//    SplashView()
//        .environmentObject(Router())
//        .ignoresSafeArea()
//}
