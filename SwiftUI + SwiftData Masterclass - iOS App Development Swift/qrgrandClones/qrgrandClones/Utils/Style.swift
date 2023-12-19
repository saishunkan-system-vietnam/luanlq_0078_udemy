//
//  PrimaryStyle.swift
//  qrgrandClones
//
//  Created by LuanLQ on 30/10/2023.
//

import SwiftUI

struct AppFont {
    static func bold(_ fontSize: CGFloat = 14) -> Font {
        .custom("NotoSansJP-Bold", size: fontSize)
    }
    
    static func medium(_ fontSize: CGFloat = 14) -> Font {
        .custom("NotoSansJP-Medium", size: fontSize)
    }
    
    static func regular(_ fontSize: CGFloat = 14) -> Font {
        .custom("NotoSansJP-Regular", size: fontSize)
    }
    
    static func Light(_ fontSize: CGFloat = 14) -> Font {
        .custom("NotoSansJP-Light", size: fontSize)
    }
}
