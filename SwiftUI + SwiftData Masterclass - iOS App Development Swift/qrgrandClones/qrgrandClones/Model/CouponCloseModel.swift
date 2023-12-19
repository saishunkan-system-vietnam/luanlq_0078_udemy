//
//  CouponCloseModel.swift
//  qrgrandClones
//
//  Created by LuanLQ on 01/12/2023.
//

import SwiftUI

struct CouponCloseModel {
    let totalRecord: Int
    let totalPages: Int
}

struct LstClose {
    let id: Int
    let name: String
    let runDatetime: String
    let status: String
    let toDate: String
    let paymentBatchResultId: Int
    let count: Int
    let amount: Int
    let paymentSum: Int
    
    func statusText() -> String {
        if (status == "paid") {
            return " 支払完了";
        }
        return "支払処理中";
    }
}
