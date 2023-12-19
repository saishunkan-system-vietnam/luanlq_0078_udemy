//
//  Drawer.swift
//  qrgrandClones
//
//  Created by LuanLQ on 31/10/2023.
//

import SwiftUI

struct Drawer<Menu: View, Content: View>: View {
    @Binding private var isOpenDrawer: Bool
    
    private let menu: Menu
    private let content: Content
    
    //MARK: - Init
    init(isOpenDrawer: Binding<Bool>, @ViewBuilder menu: @escaping () -> Menu, @ViewBuilder content: @escaping () -> Content) {
        self._isOpenDrawer = isOpenDrawer
        self.menu = menu()
        self.content =  content()
    }
    
    
    //MARK: - body
    var body: some View {
        ZStack(alignment: .leading){
            content
            
            if isOpenDrawer {
                Color.black.opacity(0.5)
                    .ignoresSafeArea()
                    .onTapGesture {
                        isOpenDrawer = false
                    }
                menu
                    .transition(.move(edge: .leading))
                    .zIndex(2)
            }
        }
        .animation(.spring(), value: isOpenDrawer)
        .environment(\.drawerPersentationMode, $isOpenDrawer.mappedToDrawerPresentationMode())
    }
}


struct Drawer_Previews: PreviewProvider {
    static var previews: some View {
        Drawer(isOpenDrawer: .constant(false), menu: {
            DrawerView()
        }, content: {
            HomeView()
        })
    }
}
//#Preview {
//    Drawer(isOpenDrawer: .constant(false), menu: {
//        DrawerView()
//    }, content: {
//        HomeView()
//    })
//}
