//
//  DismissKeyboard.swift
//  qrgrandClones
//
//  Created by LuanLQ on 30/10/2023.
//

import SwiftUI

extension View {
    func dismissKeyboard() {
        UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
    }
}
