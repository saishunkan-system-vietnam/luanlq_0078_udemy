//
//  BatchController.swift
//  qrgrandClones
//
//  Created by LuanLQ on 07/12/2023.
//

import SwiftUI

@MainActor
class BatchController: ObservableObject {
    @Published var page: Int = 0
    @Published var isLoading: Bool = false
    @Published var isLoadMore: Bool = false
    @Published var batch: CouponCloseModel = CouponCloseModel(totalRecord: 10, totalPages: 2)
    @Published var listBatch: [LstClose] = []
    
    func initData() {
        self.listBatch = [
            LstClose(id: 1, name: "Test123", runDatetime: "2022-09-14", status: "paid", toDate: "2022-09-14", paymentBatchResultId: 1, count: 2, amount: 200, paymentSum: 200),
            LstClose(id: 2, name: "Test123", runDatetime: "2022-09-15", status: "processing", toDate: "2022-09-15", paymentBatchResultId: 2, count: 1, amount: 100, paymentSum: 100),
            LstClose(id: 3, name: "Test123", runDatetime: "2022-09-16", status: "paid", toDate: "2022-09-16", paymentBatchResultId: 3, count: 2, amount: 200, paymentSum: 200),
            LstClose(id: 4, name: "Test123", runDatetime: "2022-09-17", status: "processing", toDate: "2022-09-17", paymentBatchResultId: 4, count: 1, amount: 100, paymentSum: 100),
            LstClose(id: 5, name: "Test123", runDatetime: "2022-09-18", status: "paid", toDate: "2022-09-18", paymentBatchResultId: 5, count: 2, amount: 200, paymentSum: 200),
            LstClose(id: 6, name: "Test123", runDatetime: "2022-09-19", status: "processing", toDate: "2022-09-19", paymentBatchResultId: 6, count: 1, amount: 100, paymentSum: 100),
            LstClose(id: 7, name: "Test123", runDatetime: "2022-09-20", status: "paid", toDate: "2022-09-20", paymentBatchResultId: 7, count: 2, amount: 200, paymentSum: 200),
            LstClose(id: 8, name: "Test123", runDatetime: "2022-09-21", status: "processing", toDate: "2022-09-21", paymentBatchResultId: 8, count: 1, amount: 100, paymentSum: 100),
            LstClose(id: 9, name: "Test123", runDatetime: "2022-09-22", status: "paid", toDate: "2022-09-22", paymentBatchResultId: 9, count: 2, amount: 200, paymentSum: 200),
            LstClose(id: 10, name: "Test123", runDatetime: "2022-09-23", status: "processing", toDate: "2022-09-23", paymentBatchResultId: 10, count: 1, amount: 100, paymentSum: 100),
            LstClose(id: 8, name: "Test123", runDatetime: "2022-09-21", status: "processing", toDate: "2022-09-21", paymentBatchResultId: 8, count: 1, amount: 100, paymentSum: 100),
            LstClose(id: 9, name: "Test123", runDatetime: "2022-09-22", status: "paid", toDate: "2022-09-22", paymentBatchResultId: 9, count: 2, amount: 200, paymentSum: 200),
            LstClose(id: 10, name: "Test123", runDatetime: "2022-09-23", status: "processing", toDate: "2022-09-23", paymentBatchResultId: 10, count: 1, amount: 100, paymentSum: 100),
        ]
        self.page = 0
    }
    
    func loadData() {
        if isLoading || isLoadMore  { return }
        if page < batch.totalPages {
            page += 1
            self.loading(isLoading: true)
            DispatchQueue.main.asyncAfter(deadline: .now() + 1) {
                self.loading()
                self.listBatch.append(contentsOf: self.listBatch)
            }
        }
    }
    
    func loading(isLoading: Bool = false) {
        if isLoading {
            if page > 1 {
                self.isLoadMore = true
            } else {
                self.isLoading = true
            }
        } else {
            if page > 1 {
                self.isLoadMore = false
            } else {
                self.isLoading = false
            }
        }
    }
}
