//
//  GenericDialog.swift
//  qrgrandClones
//
//  Created by LuanLQ on 04/12/2023.
//

import SwiftUI

struct GenericDialog<DialogContent: View>: ViewModifier {
    @Binding private var isShowing: Bool
    private let cancelOnTapOutside: Bool
    private let backgroundColor: Color
    private let dialogContent: () -> DialogContent

    init(
        isShowing: Binding<Bool>,
        cancelOnTapOutside: Bool,
        backgroundColor: Color,
        @ViewBuilder dialogContent: @escaping () -> DialogContent
    ) {
        _isShowing = isShowing
        self.cancelOnTapOutside = cancelOnTapOutside
        self.backgroundColor = backgroundColor
        self.dialogContent = dialogContent
    }

    func body(content: Content) -> some View {
        ZStack {
            content
            if self.isShowing {
                Group {
                    Rectangle()
                        .foregroundColor(Color.black.opacity(0.5))
                        .onTapGesture {
                            if self.cancelOnTapOutside {
                                self.isShowing = false
                            }
                        }

                    self.dialogContent()
                        .background(
                            Rectangle().foregroundColor(self.backgroundColor)
                        )
                }
                .edgesIgnoringSafeArea(.all)
            }
        }
    }
}

extension View {
    func genericDialog<DialogContent: View>(
        isShowing: Binding<Bool>,
        cancelOnTapOutside: Bool = true,
        backgroundColor: Color = .white,
        @ViewBuilder dialogContent: @escaping () -> DialogContent
    ) -> some View {
        self.modifier(
            GenericDialog(
                isShowing: isShowing,
                cancelOnTapOutside: cancelOnTapOutside,
                backgroundColor: backgroundColor,
                dialogContent: dialogContent
            )
        )
    }
}

struct GenericDialogTest: View {
    @State private var isShowingGeneric: Bool = false

    var body: some View {
        Button("Generic") {
            self.isShowingGeneric.toggle()
        }
        .genericDialog(isShowing: self.$isShowingGeneric) {
            Text("non")
                .padding()
                .frame(maxWidth: .infinity)
        }
    }
}

struct GenericDialog_Previews: PreviewProvider {
    static var previews: some View {
        GenericDialogTest()
    }
}
