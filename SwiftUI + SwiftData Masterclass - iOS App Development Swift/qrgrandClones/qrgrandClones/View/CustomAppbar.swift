//
//  CustomAppbar.swift
//  qrgrandClones
//
//  Created by LuanLQ on 23/10/2023.
//

import SwiftUI

struct CustomAppbar: View {
    @State var title: String = ""
    @State var isBack: Bool = true
    @State var isDrawer: Bool = false
    @State var backgroundColor: Color = primary
    var action: () -> Void = {}
    
    var body: some View {
        ZStack{
            HStack {
                // MARK: - Leading
                if isBack {
                    Button(action: action) {
                        Image(systemName: "chevron.backward")
                            .resizable()
                            .frame(width: 10, height: 15)
                        Text("戻る").font(AppFont.regular())
                    }
                    
                }
                if isDrawer {
                    Button(action: action) {
                        Image(systemName: "line.3.horizontal")
                            .resizable()
                            .frame(width: 20, height: 15)
                    }
                }
                Spacer()
            }
            .padding(.leading)
            .padding(.top, 2)
            // MARK: - Title
            Text(title).font(AppFont.regular(20))
        }
        .foregroundColor(.white)
        .frame(width: size.width, height: appBarHeight)
        .background(backgroundColor)
        .frame(height: 40)
    }
}

// MARK: - PREVIEWS
struct CustomAppbar_Previews: PreviewProvider {
    static var previews: some View {
        CustomAppbar(title: "ログイン")
    }
}
//#Preview {
//    CustomAppbar(title: "ログイン")
//}
