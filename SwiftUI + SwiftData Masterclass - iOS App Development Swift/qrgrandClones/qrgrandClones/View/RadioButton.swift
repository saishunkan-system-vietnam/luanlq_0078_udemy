//
//  RadioButton.swift
//  qrgrandClones
//
//  Created by LuanLQ on 04/12/2023.
//

import SwiftUI

struct RadioButton: View {
    var id: String
    var label: String
    var size: CGFloat = 40
    var textSize: CGFloat = 15
    var colorText: Color = bodyText
    var dotColor: Color = primary
    var isMarked: Bool
    var callBack: (String) -> ()
    
    var body: some View {
        Button(action: {
            self.callBack(self.id)
        }) {
            HStack {
                ZStack{
                    Circle()
                        .fill(dotColor)
                        .frame(width: size/2, height: size/2)
                    Circle()
                        .fill(.white)
                        .frame(width: size/2.5, height: size/2.5)
                    if isMarked {
                        Circle()
                            .fill(dotColor)
                            .frame(width: size/4, height: size/4)
                    }
                }
                Text(label)
                    .font(AppFont.medium(textSize))
                    .foregroundColor(isMarked ? colorText : borderColor)
            }
        }
    }
}

struct RadioButton_Previews: PreviewProvider {
    static var previews: some View {
        VStack{
            RadioButton(id: "1", label: "1", isMarked: true){_ in }
            RadioButton(id: "2", label: "2", isMarked: false){_ in }
        }
    }
}
