//
//  HistoryDetail.swift
//  qrgrandClones
//
//  Created by LuanLQ on 01/12/2023.
//

import SwiftUI

struct HistoryDetail: View {
    @EnvironmentObject private var controller: HistoryController
    @EnvironmentObject private var router: Router
    
    
    var body: some View {
        VStack {
            CustomAppbar(title: "申請詳細", backgroundColor: green) {
                router.maybePop()
            }
            ZStack(alignment: .top){
                Color.white
                VStack {
                    VStack(spacing: 0) {
                        ItemDetail(title: "申請枚数", text: "\(controller.countTotalCoupon)枚")
                        ItemDetail(title: "申請額", text: "\(numberFormatter.string(from: controller.amountTotal) ?? "0")円")
                    }
                    .padding(.horizontal, defaultPadding)
                    .padding(.vertical, defaultPadding / 2)
                    .background(gray.opacity(0.2))
                    CustomDropdown(titleDropBox: "地域クーポン一覧 : \(controller.coupon.count)枚", itemCount: controller.coupon.count, height: nil, paddingHorizontal: 20, paddingBottom:15, backgroundColor: green, toggleCoupons: controller.toggleCoupons, list: controller.coupon) { controller.toggleCoupons.toggle() }
                }
            }
        }
    }
}

struct ItemDetail: View {
    var title: String = ""
    var text: String = ""
    
    var body: some View {
        VStack{
            HStack {
                Text(title)
                    .font(AppFont.regular(12))
                Spacer()
                Text(text)
                    .font(AppFont.medium(20))
            }
            .padding(5)
            Divider()
                .frame(height: 1)
                .overlay(borderColor)
        }
        .padding(5)
    }
}

struct HistoryDetail_Previews: PreviewProvider {
    static var previews: some View {
        HistoryDetail()
    }
}
