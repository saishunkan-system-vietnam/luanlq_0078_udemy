//
//  ApplyDoneView.swift
//  qrgrandClones
//
//  Created by LuanLQ on 01/11/2023.
//

import SwiftUI

struct ApplyDoneView: View {
    @EnvironmentObject private var controller: ApplyController
    @EnvironmentObject private var router: Router
    
    
    var body: some View {
        VStack {
            CustomAppbar(title: "完了", isBack: false)
            ScrollView (showsIndicators: false) {
                Image("icon_ok")
                    .resizable()
                    .frame(width: 116,
                           height: 116)
                    .padding(.top, 30)
                    .padding(.bottom, 10)
                Text("申請が完了しました")
                    .font(AppFont.medium(20))
                    .foregroundColor(link)
                    .padding(.top, 10)
                    .padding(.bottom, 20)
                CustomDropdown(titleQuantity: "●申請完了 : \(controller.listCoupon.count)枚", itemCount: controller.listCoupon.count, toggleCoupons: controller.toggleCoupons, list: controller.listCoupon) { controller.toggleCoupons.toggle() }
                if !controller.listCoupon.isEmpty {
                    VStack(alignment: .leading) {
                        Text("●申請対象外 : \(controller.listCoupon.count * 2)枚")
                            .font(AppFont.bold(18))
                            .padding(.horizontal, defaultPadding)
                        CustomDropdown(titleDropBox: "存在しないクーポン : \(controller.listCoupon.count)枚", itemCount: controller.listCoupon.count, toggleCoupons: controller.toggleCoupons, list: controller.listCoupon) { controller.toggleCoupons.toggle() }
                        Divider()
                            .frame(height: 2)
                            .overlay(divider)
                            .padding(.horizontal, 30)
                            .padding(.vertical, 5)
                        CustomDropdown(titleDropBox: "過去申請済み : \(controller.listCoupon.count)枚", itemCount: controller.listCoupon.count, toggleCoupons: controller.toggleCoupons, list: controller.listCoupon) { controller.toggleCoupons.toggle() }
                    }
                    .padding(.bottom, defaultPadding)
                }
                PrimaryButton(title: "メインメニューへ", width: 200,
                              height: 50) {
                    controller.rest()
                    router.go(.home)
                }
                .padding(.bottom, 10)
            }
        }
    }
}

struct ApplyDoneView_Previews: PreviewProvider {
    static var previews: some View {
        ApplyDoneView()
    }
}
