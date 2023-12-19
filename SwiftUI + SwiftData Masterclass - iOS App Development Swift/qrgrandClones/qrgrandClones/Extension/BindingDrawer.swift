//
//  BindingDrawer.swift
//  qrgrandClones
//
//  Created by LuanLQ on 31/10/2023.
//

import SwiftUI

extension Binding where Value == Bool {
    func mappedToDrawerPresentationMode() -> Binding<DrawerPersentationMode> {
        Binding<DrawerPersentationMode>(get: {
            DrawerPersentationMode(isOpened: self)
        }, set: {
            newValue in
            self.wrappedValue = newValue.isOpened
        })
    }
}

extension DrawerPersentationMode {
    static var placeholder: DrawerPersentationMode {
        DrawerPersentationMode(isOpened: .constant(false))
    }
}

private struct DrawerPresentationModeKey: EnvironmentKey {
    static var defaultValue: Binding<DrawerPersentationMode> = .constant(.placeholder)
}

extension EnvironmentValues {
    public var drawerPersentationMode: Binding<DrawerPersentationMode> {
        get { self[DrawerPresentationModeKey.self] }
        set { self[DrawerPresentationModeKey.self] = newValue }
    }
}
