//
//  LoginQRController.swift
//  qrgrandClones
//
//  Created by LuanLQ on 07/12/2023.
//

import SwiftUI

@MainActor
class LoginQRController: ObservableObject {
    @Published var isShowingScanner: Bool = true
    
    func validateLoginQRCode(scanText: String?) -> Bool {
        if (scanText == nil){ return false; }
        if (scanText!.count < 6){ return false; }
        if (scanText!.prefix(5) != "hojo."){ return false; }
        
        return true;
    }
}
