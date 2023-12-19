//
//  LoadingPageView.swift
//  qrgrandClones
//
//  Created by LuanLQ on 01/12/2023.
//

import SwiftUI

struct LoadingPageView: View {
    var body: some View {
        GeometryReader { geometry in
            Loading()
                .frame(width: geometry.size.width, height: geometry.size.height, alignment: .center)
        }
    }
}

struct LoadingPageView_Previews: PreviewProvider {
    static var previews: some View {
        LoadingPageView()
    }
}
