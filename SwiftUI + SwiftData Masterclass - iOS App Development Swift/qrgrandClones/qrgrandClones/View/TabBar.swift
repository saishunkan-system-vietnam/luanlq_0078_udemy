//
//  TabBar.swift
//  qrgrandClones
//
//  Created by LuanLQ on 01/12/2023.
//

import SwiftUI

struct TabBar: View {
    @Binding var currentTab: Int
    @Namespace var namespace
    
    @State var tabBarOptions: Array<String> = ["今日","詳細検索"]
    var body: some View {
        HStack(spacing: 0) {
            ForEach(0..<tabBarOptions.count,
                    id: \.self) {
                index in
                TabBarItem(currentTab: self.$currentTab,
                           namespace: namespace.self,
                           tabBarItemName: tabBarOptions[index],
                           tab: index)
            }
        }
        .frame(height: appBarHeight)
        .background(Color.white)
    }
}

struct TabBarItem: View {
    @Binding var currentTab: Int
    let namespace: Namespace.ID
    
    var tabBarItemName: String
    var tab: Int
    
    var body: some View {
        GeometryReader { geometry in
            Button {
                self.currentTab = tab
            } label: {
                VStack (spacing: 0) {
                    ZStack(alignment: .center){
                        if currentTab == tab {
                            gray
                                .opacity(0.2)
                                .frame(width: geometry.size.width, height: geometry.size.height)
                        }
                        Text(tabBarItemName)
                            .foregroundColor(link)
                            .font(AppFont.regular(18))
                    }
                    .frame(height: geometry.size.height)
                    ZStack{
                        divider
                            .opacity(0.5)
                            .frame(width: geometry.size.width, height: 3.5)
                        if currentTab == tab {
                            link
                                .frame(width: geometry.size.width, height: 3)
                                .matchedGeometryEffect(id: "underline",
                                                       in: namespace,
                                                       properties: .frame)
                        } else {
                            Color.clear.frame(width: geometry.size.width, height: 2)
                        }
                        
                    }
                }
                .animation(.spring(), value: self.currentTab)
            }
        }
    }
}

struct TabBar_Previews: PreviewProvider {
    static var previews: some View {
        ZStack(alignment: .top){
            TabView(selection: .constant(0)) {
                        Text("tab1").tag(0)
                        Text("tab2").tag(1)
                    }
                    .tabViewStyle(.page(indexDisplayMode: .never))
                    
            TabBar(currentTab:.constant(0))
        }
    }
}
