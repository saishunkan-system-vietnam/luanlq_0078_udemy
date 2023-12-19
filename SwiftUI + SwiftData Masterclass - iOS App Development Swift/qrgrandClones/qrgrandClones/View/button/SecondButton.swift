//
//  SecondButton.swift
//  qrgrandClones
//
//  Created by LuanLQ on 23/10/2023.
//

import SwiftUI

struct SecondButton: View {
    // MARK: - PROPERTIES
    var title: String
    var width: Double = 0
    var height: Double = 0
    var action: () -> Void

    // MARK: - BODY
    var body: some View {
        Button(action: {action()}) {
            Text(title)
                .font(AppFont.regular(16))
                .foregroundStyle(primary)
                .frame(width: width, height: height)
        }
        .foregroundColor(.white)
        .frame(width: width, height: height)
        .overlay(
            RoundedRectangle(cornerRadius:10)
                .stroke(primary, lineWidth: 2))
        .frame(width: width, height: height)
        
    }
}

// MARK: - PREVIEWS
struct SecondButton_Previews: PreviewProvider {
    static var previews: some View {
        SecondButton(title: "QRコードログインを\nご利用の場合はこちら", width: 300, height: 80, action: {})
            .previewLayout(.sizeThatFits)
            .padding()
    }
}
//#Preview {
//    SecondButton(title: "QRコードログインを\nご利用の場合はこちら", width: 300, height: 80, action: {})
//        .previewLayout(.sizeThatFits)
//        .padding()
//}
