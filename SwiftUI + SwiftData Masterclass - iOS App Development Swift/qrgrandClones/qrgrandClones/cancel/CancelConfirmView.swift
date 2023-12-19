//
//  CancelConfirmView.swift
//  qrgrandClones
//
//  Created by LuanLQ on 30/11/2023.
//

import SwiftUI

struct CancelConfirmView: View {
    @EnvironmentObject private var controller: CancelController
    @EnvironmentObject private var router: Router
    
    
    var body: some View {
        VStack {
            CustomAppbar(title: "内容確認", backgroundColor: errorColor) {
                router.pop()
            }
            ScrollView (showsIndicators: false) {
                Text("\(controller.listCoupon.count * 2)枚")
                .foregroundStyle(link)
                .font(AppFont.medium(20))
                .padding(.top, 10)
                Text("読み取りました")
                    .font(AppFont.regular(15))
                    .padding(.bottom, 20)
                CustomDropdown(titleQuantity: "●申請完了 : \(controller.listCoupon.count)枚", itemCount: controller.listCoupon.count, toggleCoupons: controller.toggleCoupons, list: controller.listCoupon) { controller.toggleCoupons.toggle() }
                CustomDropdown(titleQuantity: "●取消対象外 : \(controller.listCoupon.count)枚", itemCount: controller.listCoupon.count, toggleCoupons: controller.toggleCoupons, list: controller.listCoupon) { controller.toggleCoupons.toggle() }
                PrimaryButton(title: "メインメニューへ", width: 200,
                              height: 50, backgroundColor: errorColor, isLoading: controller.isLoading)  { controller.nextPage(isConfirm: true) }
                    .padding(.bottom, 10)
            }
        }
    }
}

struct CancelConfirmView_Previews: PreviewProvider {
    static var previews: some View {
        CancelConfirmView()
    }
}
