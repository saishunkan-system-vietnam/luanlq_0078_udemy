//
//  ItemHome.swift
//  qrgrandClones
//
//  Created by LuanLQ on 30/10/2023.
//

import SwiftUI

struct MenuHomeItem: View {
    var text1: String
    var text2: String
    var text3: String
    var buttonText: String
    var buttonColor: Color
    var action: () -> Void
    
    var body: some View {
        VStack {
            Text(text1)
                .font(AppFont.regular(15))
            Text(text2)
                .foregroundColor(buttonColor)
                .font(AppFont.bold(15)) +
            Text(text3)
                .font(AppFont.regular(15))
            Spacer().frame(width: 10)
            PrimaryButton(title: buttonText,
                          width: 200,
                          height: 55,
                          radius: 10,
                          backgroundColor: buttonColor, action: {action()})
        }
        .frame(width: size.width - 20)
        .padding(.vertical, defaultPadding * 2)
        .background(buttonColor.opacity(0.1))
        .cornerRadius(10)
        .padding(.bottom, 10)
    }
}

struct MenuHomeItem_Previews: PreviewProvider {
    static var previews: some View {
        MenuHomeItem(text1: "地域クーポンの", text2: "補助金申請", text3: "を行います", buttonText: "補助金申請", buttonColor: primary){}
    }
}

//#Preview {
//    MenuHomeItem(text1: "地域クーポンの", text2: "補助金申請", text3: "を行います", buttonText: "補助金申請", buttonColor: primary)
//}
