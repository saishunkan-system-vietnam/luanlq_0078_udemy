//
//  History.swift
//  qrgrandClones
//
//  Created by LuanLQ on 01/12/2023.
//

import SwiftUI

struct HistoryView: View {
    @EnvironmentObject private var controller: HistoryController
    @EnvironmentObject private var router: Router
    
    var body: some View {
        VStack {
            CustomAppbar(title: "履歴確認", backgroundColor: green) {
                router.maybePop()
                controller.reset()
            }
            ZStack(alignment: .top) {
                TabView(selection: $controller.tabIndex) {
                    SearchHistoryTodayView().tag(0)
                    SearchHistoryView().tag(1)
                }
                .padding(.top, appBarHeight)
                .tabViewStyle(.page(indexDisplayMode: .never))
                TabBar(currentTab: $controller.tabIndex, tabBarOptions: ["今日","詳細検索"])
            }
        }
        .datePickerDialog(
            isShowing: $controller.isShowingDateStart,
            selection: $controller.dateStart
        )
        .datePickerDialog(
            isShowing: $controller.isShowingDateEnd,
            selection: $controller.dateEnd
        )
        .onAppear{
            controller.initData(router: router)
        }
    }
}

struct HistoryView_Previews: PreviewProvider {
    static var previews: some View {
        HistoryView()
    }
}
