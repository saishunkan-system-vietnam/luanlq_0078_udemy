//
//  HistoryController.swift
//  qrgrandClones
//
//  Created by LuanLQ on 01/12/2023.
//

import SwiftUI

@MainActor
class HistoryController: ObservableObject {
    private var router: Router?
    @Published var tabIndex = 0
    
    //search today
    @Published var disabledButton: Bool = true
    @Published var isButtonLoadingToday: Bool = false
    @Published var countTotalCoupon: NSNumber = 0
    @Published var amountTotal: NSNumber = 0
    
    //search
    @Published var selectedRadio: String = "date"
    @Published var inputCodeCoupons: String = ""
    @Published var isButtonLoading: Bool = false
    
    @Published var isShowingDateStart: Bool = false
    @Published var isShowingDateEnd: Bool = false
    @Published var dateStart: Date = .now
    @Published var dateEnd: Date = .now
    
    //detail
    @Published var coupon = ["1000001","1000002","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003"]
    @Published var toggleCoupons: Bool = false
    
    //scan
    @Published var isShowingScanner: Bool = true
    
    
    func initData(router: Router) {
        self.router = router
    }

    let jpDateFormatter: DateFormatter = {
        let f = DateFormatter()
        f.dateFormat = "yyyy年MM月dd日"
        return f
    }()
    
    func nextPage() {
        if self.isButtonLoadingToday || self.isButtonLoading {return}

        self.LoadingButton(isLoading: true)
        DispatchQueue.main.asyncAfter(deadline: .now() + 1) {
            self.router?.push(.historyDetail)
            self.LoadingButton()
        }
    }
    
    func LoadingButton(isLoading: Bool = false) {
        if isLoading {
            if tabIndex == 0 {
                self.isButtonLoadingToday = true
            } else {
                self.isButtonLoading = true
            }
        } else {
            if tabIndex == 0 {
                self.isButtonLoadingToday = false
            } else {
                self.isButtonLoading = false
            }
        }
    }
    
    //search today
    func checkButton() {
        disabledButton.toggle()
        print(disabledButton)
        if(disabledButton) {
            countTotalCoupon = 0
            amountTotal = 0
        } else {
            countTotalCoupon = coupon.count as NSNumber
            amountTotal = 10000
        }
    }
    
    func reset() {        
        //search today
        disabledButton = true
        countTotalCoupon = 0
        amountTotal = 0
        
        //search
        clearInput()
    }
    
    //search
    func clearInput() {
        selectedRadio = "date"
        inputCodeCoupons = ""
        dateStart = .now
        dateEnd = .now
    }
}
