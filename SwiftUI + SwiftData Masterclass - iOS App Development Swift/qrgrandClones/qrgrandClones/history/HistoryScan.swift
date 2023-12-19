//
//  HistoryScan.swift
//  qrgrandClones
//
//  Created by LuanLQ on 01/12/2023.
//

import SwiftUI

struct HistoryScan: View {
    @EnvironmentObject private var authController: AuthController
    @EnvironmentObject private var controller: HistoryController
    @EnvironmentObject private var router: Router
    
    var body: some View {
        VStack {
            CustomAppbar(title: "クーポン読み取り", backgroundColor: green) {
                router.maybePop()
            }
            ScannerView(isShowingScanner: controller.isShowingScanner, scanMode: .oncePerCode) { result in
                DispatchQueue.main.asyncAfter(deadline: .now() + 0.1) {
                    if case .success(_) = result {
                        authController.playSound()
                        controller.isShowingScanner = false
                        router.replace(.historyDetail)
                    }
                }
            }
        }
        .onAppear {
            controller.initData(router: router)
        }
    }
}

struct HistoryScan_Previews: PreviewProvider {
    static var previews: some View {
        HistoryScan()
    }
}
