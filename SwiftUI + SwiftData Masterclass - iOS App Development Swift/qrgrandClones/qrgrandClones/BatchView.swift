//
//  Batch.swift
//  qrgrandClones
//
//  Created by LuanLQ on 01/11/2023.
//

import SwiftUI

struct BatchView: View {
    @EnvironmentObject private var controller: BatchController
    @EnvironmentObject private var router: Router
    
    var body: some View {
        VStack {
            CustomAppbar(title: "精算状況確認", backgroundColor: warningColor) { router.pop() }
            VStack {
                if controller.isLoading {
                    LoadingPageView()
                } else {
                    ScrollView (showsIndicators: false) {
                        ForEach(0..<controller.listBatch.count, id: \.self){ i in
                            let item = controller.listBatch[i]
                            LazyVStack(alignment: .leading) {
                                Text("\(item.name)")
                                    .font(AppFont.medium(14))
                                Text("読み取り期間 : \(item.toDate)まで")
                                    .font(AppFont.regular(14))
                                Text("ステータス : \(item.statusText())")
                                    .font(AppFont.regular(14))
                                Text("精算枚数 : \(item.count)枚")
                                    .font(AppFont.regular(14))
                                if i == controller.listBatch.count - 1 {
                                    Text("精算額 : \(item.amount)円")
                                        .font(AppFont.regular(14))
                                        .onAppear {
                                            controller.loadData()
                                            print("loadMore")
                                        }
                                } else {
                                    Text("精算額 : \(item.amount)円")
                                        .font(AppFont.regular(14))
                                }
                                Spacer().frame(height: defaultPadding / 1.5)
                                if i != controller.listBatch.count - 1 {
                                    Divider()
                                        .frame(height: 1)
                                        .overlay(borderColor)
                                }
                            }
                            .padding(.top, i == 0 ? 20 : 0)
                            .padding(.horizontal, 20)
                            .frame(width: size.width, alignment: .leading)
                        }
                        if controller.isLoadMore {
                            ProgressView()
                        }
                    }
                }
            }
            .task{ controller.loadData() }
        }
        .onAppear{
            controller.initData()
        }
    }
}

struct BatchView_Previews: PreviewProvider {
    static var previews: some View {
        BatchView()
    }
}
