//
//  CustomDropdown.swift
//  qrgrandClones
//
//  Created by LuanLQ on 02/11/2023.
//

import SwiftUI

struct CustomDropdown: View {
    @State var titleQuantity: String = ""
    @State var titleDropBox: String = ""
    @State var itemCount: Int
    @State var height: CGFloat? = 300
    @State var paddingHorizontal: CGFloat = 30
    @State var paddingBottom: CGFloat = 0
    @State var backgroundColor: Color = primary
    @State var toggleCoupons: Bool = true
    @State var list: Array<String> = []
    @State var action: () -> Void
    
    var body: some View {
        if itemCount != 0 {
            VStack{
                VStack (alignment: .leading) {
                    if titleQuantity != "" {
                        HStack {
                            Text(titleQuantity)
                                .font(AppFont.bold(18))
                            Spacer()
                            if titleDropBox == "" {
                                buttonDropDown
                                    .padding(.top, 10)
                            }
                        }
                        .padding(.horizontal, defaultPadding)
                        .frame(width: size.width, height: 48)
                    }
                    if titleDropBox != "" {
                        HStack {
                            Text(titleDropBox)
                                .font(AppFont.regular(15))
                                .padding(.top, 2)
                            Spacer()
                            buttonDropDown
                        }
                        .padding(.horizontal, paddingHorizontal)
                        .frame(width: size.width, height: 37)
                    }
                }
                .foregroundColor(bodyText)
                if toggleCoupons {
                    Section {
                        GeometryReader { geometry in
                            ScrollView(showsIndicators: false) {
                                ForEach(0..<list.count, id: \.self){ index in
                                    ItemListCoupons(code: list[index], index: index, width: geometry.size.width)
                                }
                                .padding(.bottom, paddingBottom)
                            }
                        }
                    }
                    .frame(height: height)
                    .padding(.horizontal, paddingHorizontal)
                }
            }
        }
    }
    
    var buttonDropDown: some View {
        PrimaryButton(title: toggleCoupons ? "明細を非表示" : "明細を表示", width: 90, height: 30, backgroundColor: backgroundColor, font: AppFont.regular(10)) { toggleCoupons.toggle() }
            .frame(alignment: .topTrailing)
    }
}

struct ItemListCoupons: View {
    var code: String
    var date: String = ""
    var index: Int = 0
    var colorText: Color = bodyText
    var width: CGFloat
    
    var body: some View {
        VStack {
            Text("\("\(index + 1 )".padLeft(4)): \(code)")
                .foregroundColor(colorText) +
            Text(date)
                .font(AppFont.regular(12))
                .foregroundColor(colorText)
        }
        .padding()
        .frame(width: width, alignment: .leading)
        .border(.black, width: 1)
    }
}

struct CustomDropdown_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            CustomDropdown(titleQuantity: "abc", titleDropBox: "aaaa", itemCount: 1, toggleCoupons: true, list: ["1000001","1000002","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003"]) {}
            CustomDropdown(titleQuantity: "ahgjbc", titleDropBox: "aaaa", itemCount: 1, toggleCoupons: true, list: ["1000001","1000002","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003","1000003"]) {}
        }
    }
}
