//
//  SearchHistoryTodayView.swift
//  qrgrandClones
//
//  Created by LuanLQ on 01/12/2023.
//

import SwiftUI

struct SearchHistoryTodayView: View {
    @EnvironmentObject private var controller: HistoryController

    var body: some View {
        ScrollView(showsIndicators: false) {
            VStack {
                Text(controller.jpDateFormatter.string(from: .now))
                    .font(AppFont.medium(20))
                    .foregroundColor(divider)
                VStack {
                    VStack{
                        HStack {
                            ItemHistory(title: "申請枚数", number: controller.countTotalCoupon, text: "枚")
                            Divider()
                                .frame(width: 1.5)
                                .overlay(borderColor)
                            ItemHistory(title: "申請額", number: controller.amountTotal, text: "円")
                        }
                        .frame(alignment: .leading)
                        .background(.white)
                        .cornerRadius(10)
                        .overlay(
                            RoundedRectangle(cornerRadius: 10)
                                .stroke(divider, lineWidth: 1)
                        )
                        .padding(.horizontal, defaultPadding / 1.2)
                        .padding(.vertical, defaultPadding * 1.2)
                    }
                    .background(divider.opacity(0.15))
                    .cornerRadius(10)
                    .overlay(
                        RoundedRectangle(cornerRadius: 10)
                            .stroke(divider.opacity(0.15), lineWidth: 1)
                    )
                    .padding(.bottom, defaultPadding * 1.2)
                    .padding(.top, 15)
                }
                .onTapGesture { controller.checkButton() }
                PrimaryButton(title: "詳細へ", width: 200, height: 50, backgroundColor: green, isLoading: controller.isButtonLoadingToday, disabled: controller.disabledButton) { controller.nextPage() }
                    .disabled(controller.disabledButton)
            }
            .padding(.top, 30)
        }
        .padding(.horizontal, defaultPadding / 1.2)
    }
}

struct ItemHistory: View {
    var title: String
    var number: NSNumber
    var text: String
    
    var body: some View {
        VStack(spacing: 0) {
            Text(title)
                .font(AppFont.medium(15))
                .frame(maxWidth: .infinity, alignment: .leading)
            Spacer()
            HStack(alignment: .firstTextBaseline, spacing: 0) {
                Text(numberFormatter.string(from: number) ?? "0")
                    .scaledToFit()
                    .minimumScaleFactor(0.2)
                    .font(AppFont.medium(30))
                    .foregroundColor(link)
                Text(text)
                    .font(AppFont.regular(14))
            }
            .padding(.vertical, defaultPadding * 2 + 8)
        }
        .padding(.vertical, defaultPadding / 1.5)
        .padding(.leading, defaultPadding / 1.5)
        .padding(.trailing, defaultPadding / 4)
    }
}

struct SearchHistoryTodayView_Previews: PreviewProvider {
    static var previews: some View {
        SearchHistoryTodayView()
    }
}
