//
//  ApplyController.swift
//  qrgrandClones
//
//  Created by LuanLQ on 07/12/2023.
//

import SwiftUI

@MainActor
class ApplyController: ObservableObject {
    private var router: Router?

    @Published var listCoupon: [String] = .init()
    
    //scan
    @Published var isShowingScanner = true
    
    //confirm
    @Published var isLoading: Bool = false
    
    //done
    @Published var toggleCoupons: Bool = false

    
    func initData(router: Router) {
        self.router = router
    }
    
    func rest() {
        self.listCoupon = .init()
        self.toggleCoupons = false
    }
    
    func nextPage() {
        if self.isLoading {return}
        self.isLoading = true
        DispatchQueue.main.asyncAfter(deadline: .now() + 1) {
            self.router?.go(.applyDone)
            self.isLoading = false
        }
    }
}
