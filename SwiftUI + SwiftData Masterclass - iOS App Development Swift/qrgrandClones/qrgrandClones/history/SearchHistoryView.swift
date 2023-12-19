//
//  SearchHistoryView.swift
//  qrgrandClones
//
//  Created by LuanLQ on 05/12/2023.
//

import SwiftUI

struct SearchHistoryView: View {
    @EnvironmentObject private var controller: HistoryController
    @EnvironmentObject private var router: Router
    
    var body: some View {
        let isSelected = controller.selectedRadio == "date"
        let disabledButtonSearch = controller.selectedRadio == "code" && controller.inputCodeCoupons.isEmpty
        
        ScrollView(showsIndicators: false) {
            VStack {
                VStack(alignment: .leading){
                    RadioButton(id: "date", label: "集計日", isMarked: isSelected) { id in
                        controller.selectedRadio = id
                        dismissKeyboard()
                    }
                    .padding(.leading, 5)
                    
                    inputDate(date: controller.dateStart, isDisabled: !isSelected)
                        .onTapGesture {
                            dismissKeyboard()
                            if isSelected {
                                controller.isShowingDateStart = true
                            }
                        }
                    HStack(spacing: 0) {
                        Spacer()
                        Text("~")
                            .font(AppFont.bold(20))
                            .foregroundColor(isSelected ? bodyText : borderColor)
                        Spacer()
                    }
                    Spacer().frame(height: 10)
                    inputDate(date: controller.dateEnd, isDisabled:  !isSelected)
                        .onTapGesture {
                            dismissKeyboard()
                            if isSelected {
                                controller.isShowingDateEnd = true
                            }
                        }
                    
                    RadioButton(id: "code", label: "クーポン番号", isMarked: !isSelected) {id in
                        controller.selectedRadio = id
                        dismissKeyboard()
                    }
                    .padding(.leading, 5)
                    HStack{
                        Spacer()
                        PrimaryInput(text: $controller.inputCodeCoupons, radius: 10, font: AppFont.medium(18), alignment: .trailing, isDisabled: isSelected)
                            .tint(primary)
                            .frame(width: size.width * 0.7)
                            .disabled(isSelected)
                    }
                }
                .padding(defaultPadding / 2)
                .background(backgroundColor)
                .cornerRadius(10)
                
                HStack {
                    PrimaryButton(title: "クリア", width: 100, height: 50, backgroundColor: gray) { controller.clearInput() }
                    Spacer()
                    PrimaryButton(title: "検索", width: 200, height: 50, backgroundColor: green, isLoading: controller.isButtonLoading, disabled: disabledButtonSearch) { controller.nextPage() }
                        .disabled(disabledButtonSearch)
                }
                .padding(defaultPadding)
                
                Divider()
                    .frame(height: 1)
                    .overlay(borderColor)
                Spacer().frame(height: 20)
                Text("または")
                    .font(AppFont.medium(15))
                PrimaryButton(title: "検索", width: 300, height: 50, backgroundColor: green) { router.push(.historyScan)}
                    .padding()
            }
            .padding(.horizontal, defaultPadding/2)
            .padding(.vertical, defaultPadding/2)
        }
        .onTapGesture {
            dismissKeyboard()
        }
        .ignoresSafeArea(.keyboard)
    }
}

struct inputDate: View {
    @EnvironmentObject private var controller: HistoryController
    var date: Date
    var isDisabled: Bool = false
    
    var body: some View {
        let colorText = isDisabled ? borderColor : bodyText
        let dateText = controller.jpDateFormatter.string(from: date)
        
        HStack{
            Spacer()
            Text(dateText.prefix(5))
                .font(AppFont.medium(18))
                .foregroundColor(colorText) +
            Text("      \(dateText[5..<8])")
                .font(AppFont.medium(18))
                .foregroundColor(colorText) +
            Text("       \(dateText[8..<11])")
                .font(AppFont.medium(18))
                .foregroundColor(colorText)
        }
        .cornerRadius(10)
        .frame(height: 55)
        .padding(.horizontal, 10)
        .background(.white)
        .overlay(
            RoundedRectangle(cornerRadius: 10)
                .stroke(borderColor, lineWidth: 2)
        )
    }
}

struct SearchHistoryView_Previews: PreviewProvider {
    static var previews: some View {
        SearchHistoryView()
    }
}
