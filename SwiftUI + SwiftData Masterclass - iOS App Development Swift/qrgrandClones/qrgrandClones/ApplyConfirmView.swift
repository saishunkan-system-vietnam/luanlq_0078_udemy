//
//  ApplyConfirmView.swift
//  qrgrandClones
//
//  Created by LuanLQ on 01/11/2023.
//

import SwiftUI

struct ApplyConfirmView: View {
    @EnvironmentObject private var controller: ApplyController
    @EnvironmentObject private var router: Router

    var body: some View {
        VStack {
            CustomAppbar(title: "内容確認") {
                router.pop()
            }
            Text("\(controller.listCoupon.count)枚")
            .foregroundStyle(link)
            .font(AppFont.medium(20))
            .padding(.top, 10)
            Text("読み取りました")
            .font(AppFont.regular(15))
            VStack (alignment: .leading) {
                Text("明細 ：")
                .font(AppFont.regular())
                GeometryReader { geometry in
                    GeometryReader { geometrys in
                        ScrollView (showsIndicators: false) {
                            VStack (alignment: .leading) {
                                ForEach(0..<controller.listCoupon.count, id: \.self){ index in
                                    let item = controller.listCoupon[index]
                                    Text("\("\(index)".padLeft(4)): \(item)")
                                        .font(AppFont.regular(16))
                                        .padding()
                                        .frame(width: geometrys.size.width, alignment: .leading)
                                        .border(.black, width: 1)
                                }
                            }
                        }
                    }
                    .padding()
                    .frame(width: geometry.size.width, height: geometry.size.height, alignment: .leading)
                    .border(.black, width: 1)

                }
            }
            .padding(.horizontal, defaultPadding)
            Spacer().frame(height: defaultPadding)
            PrimaryButton(title: "確認画面へ", width: 200, height: 50, isLoading: controller.isLoading) { controller.nextPage() }
                .padding(.bottom,10)
        }
    }
}

struct ApplyConfirmView_Previews: PreviewProvider {
    static var previews: some View {
        ApplyConfirmView()
    }
}
