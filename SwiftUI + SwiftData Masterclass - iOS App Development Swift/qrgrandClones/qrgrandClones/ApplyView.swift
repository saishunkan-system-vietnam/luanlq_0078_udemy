//
//  ApplyView.swift
//  qrgrandClones
//
//  Created by LuanLQ on 01/11/2023.
//

import SwiftUI

struct ApplyView: View {
    @EnvironmentObject private var authController: AuthController
    @EnvironmentObject private var controller: ApplyController
    @EnvironmentObject private var router: Router
    
    var body: some View {
        VStack {
            CustomAppbar(title: "補助金申請") {
                controller.rest()
                router.maybePop()
            }
            VStack(spacing: 0) {
                Text("地域クーポン")
                    .font(AppFont.medium(20))
                    .foregroundStyle(primary)
                    .padding(.top, 20)
                Text("を読み取ってください\n※一度に読み取れるのは最大1,000枚です")
                    .multilineTextAlignment(.center)
                    .foregroundStyle(bodyText)
                    .font(AppFont.regular(15))
                    .padding(.bottom, 5)
                ScannerView(isShowingScanner: controller.isShowingScanner, scanMode: .oncePerCode) { result in
                    if case let .success(code) = result {
                        authController.playSound()
                        controller.listCoupon.append(code.string)
                    }
                }
                Group{
                    Text("\(controller.listCoupon.count)")
                        .foregroundColor(link)
                        .font(AppFont.bold(48)) +
                    Text("  枚")
                        .foregroundColor(bodyText)
                        .font(AppFont.regular(15))
                }
                .padding(.vertical, -10)
                PrimaryButton(title: "確認画面へ", width: 200, height: 50, disabled: controller.listCoupon.isEmpty) {
                    if !controller.listCoupon.isEmpty{
                        router.push(.applyConfirm)
                    }
                }
                    .disabled(controller.listCoupon.isEmpty)
                    .padding(.top, defaultPadding * 2)
                    .padding(.bottom, 10)
            }
        }
        .onChange(of: controller.listCoupon.count) { count in
            if count == qrLimit { router.push(.applyConfirm) }
        }
        .onAppear{
            controller.initData(router: router)
        }
    }
}

struct ApplyView_Previews: PreviewProvider {
    static var previews: some View {
        ApplyView()
    }
}
