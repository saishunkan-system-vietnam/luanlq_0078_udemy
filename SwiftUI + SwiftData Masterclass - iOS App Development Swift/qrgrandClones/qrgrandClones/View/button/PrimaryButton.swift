//
//  PrimaryButtonView.swift
//  qrgrandClones
//
//  Created by LuanLQ on 18/10/2023.
//

import SwiftUI

struct PrimaryButton: View {
    // MARK: - PROPERTIES
    var title: String
    var width: CGFloat = 0
    var height: CGFloat = 0
    var radius: CGFloat = 50
    var backgroundColor: Color = primary
    var font: Font = AppFont.bold(16)
    var isLoading: Bool = false
    var disabled: Bool = false
    var action: () -> Void
    
    // MARK: - BODY
    var body: some View {
        Button(action: {action()}) {
            if isLoading {
                Loading(color: .white, size: 30, lineWidth: 3)
            } else {
                Text(title)
                    .font(font)
                    .frame(width: width, height: height)
            }
        }
        .foregroundColor(disabled ? divider : .white)
        .frame(width: width, height: height)
        .background(disabled ? divider.opacity(0.3) : backgroundColor)
        .cornerRadius(radius)
    }
}

// MARK: - PREVIEWS
struct PrimaryButton_Previews: PreviewProvider {
    static var previews: some View {
        PrimaryButton(title: "ポータルアカウントでログイン", width: 300, height: 55, action: {})
            .previewLayout(.sizeThatFits)
            .padding()
    }
}
//#Preview {
//    PrimaryButton(title: "ポータルアカウントでログイン", width: 300, height: 55, action: {})
//        .previewLayout(.sizeThatFits)
//        .padding()
//}
