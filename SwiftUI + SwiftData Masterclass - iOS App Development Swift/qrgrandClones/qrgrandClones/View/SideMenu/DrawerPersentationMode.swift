//
//  DrawerPersentationMode.swift
//  qrgrandClones
//
//  Created by LuanLQ on 31/10/2023.
//

import SwiftUI

public struct DrawerPersentationMode{
    @Binding private var _isOpened: Bool
    
    init(isOpened: Binding<Bool>) {
        __isOpened = isOpened
    }
    
    public var isOpened: Bool {
        _isOpened
    }
    
    public mutating func open() {
        if !_isOpened {
            _isOpened = true
        }
        
    }
    public mutating func close() {
        if _isOpened {
            _isOpened = false
        }
    }
}
