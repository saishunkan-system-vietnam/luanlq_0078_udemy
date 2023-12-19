//
//  PrimaryInput.swift
//  qrgrandClones
//
//  Created by LuanLQ on 23/10/2023.
//

import SwiftUI

struct PrimaryInput: View {
    // MARK: - PROPERTIES
    var title: String = ""
    @Binding var text: String
    var frontIcon: String = ""
    var iconBehind: String = ""
    var sizefrontIcon: Double = 30
    var radius: Double = 30
    var font: Font = AppFont.regular(16)
    var backgroundColor: Color = .white
    var alignment: TextAlignment = .leading
    var textContentType: UITextContentType = .name
    var textError: String = ""
    var isDisabled: Bool = false
    var isPassword: Bool = false
    var isShowPassword: () -> Void = {}
    @FocusState var focused: Bool
    
    
    // MARK: - BODY
    var body: some View {
        let colorText = isDisabled ? borderColor : bodyText

        VStack{
            HStack {
                if !frontIcon.isEmpty {
                    HStack {
                        Image(frontIcon)
                            .resizable()
                            .foregroundStyle(primary)
                            .scaledToFit()
                            .frame(width: sizefrontIcon)
                            .padding(.bottom, 4)
                    }
                    .frame(width: 20)
                    .padding(.horizontal,10)
                }
                if !isPassword {
                    TextField(title, text: $text)
                        .tint(primary)
                        .focused($focused)
                        .font(font)
                        .foregroundColor(colorText)
                        .textContentType(textContentType)
                        .multilineTextAlignment(alignment)
                } else {
                    SecureField(title, text: $text)
                        .tint(primary)
                        .focused($focused)
                        .font(font)
                        .foregroundColor(colorText)
                        .textContentType(textContentType)
                        .multilineTextAlignment(alignment)
                }
                if !iconBehind.isEmpty {
                    Image(systemName: iconBehind)
                        .resizable()
                        .foregroundStyle(primary)
                        .scaledToFit()
                        .frame(width: 30)
                        .padding(.horizontal,10)
                        .padding(.top, 1)
                        .onTapGesture {
                            isShowPassword()
                        }
                }
            }
            .padding(.horizontal, 15)
            .frame(height: 55)
            .background(backgroundColor)
            .cornerRadius(radius)
            .overlay(
                RoundedRectangle(cornerRadius: radius)
                    .stroke($focused.wrappedValue ? primary : borderColor, lineWidth: 2))
            .onTapGesture {
                DispatchQueue.main.asyncAfter(deadline: .now() + 0.2) {
                    $focused.wrappedValue = true
                }
            }
            .onAppear{
                $focused.wrappedValue = false
            }
            
            if !textError.isEmpty {
                Text(textError)
                    .foregroundColor(errorColor)
            }
        }
    }
}

// MARK: - PREVIEWS
struct PrimaryInput_Previews: PreviewProvider {
    static var previews: some View {
        PrimaryInput(text: .constant("abc"), frontIcon: "lock", sizefrontIcon: 21,
                     textContentType: .password)
    }
}
//#Preview {
//    PrimaryInput( frontIcon: "lock", iconBehind:"eye.fill", sizefrontIcon: 21, textField: TextField(
//        "QRコードログインを",
//        text: .constant("abc")))
//}
