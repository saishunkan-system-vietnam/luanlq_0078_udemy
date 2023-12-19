//
//  LicenseView.swift
//  qrgrandClones
//
//  Created by LuanLQ on 31/10/2023.
//

import SwiftUI

struct LicenseView: View {
    @EnvironmentObject private var router: Router
    
    var body: some View {
        VStack {
            CustomAppbar(title: "ライセンス") {
                router.pop()
            }
            Color.white
        }
    }
}

struct LicenseView_Previews: PreviewProvider {
    static var previews: some View {
        LicenseView()
    }
}
//#Preview {
//    LicenseView()
//}
