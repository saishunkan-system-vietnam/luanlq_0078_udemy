//
//  Loading.swift
//  qrgrandClones
//
//  Created by LuanLQ on 01/12/2023.
//

import SwiftUI

struct Loading: View {
    @State var color: Color = primary
    @State var size: Double = 40
    @State var lineWidth: Double = 5
    
    
    @State private var isCircleRotating = true
    @State private var animateStart = false
    @State private var animateEnd = true

    var body: some View {
        Circle()
            .trim(from: animateStart ? 1/3 : 1/9, to: animateEnd ? 2/5 : 1)
            .stroke(lineWidth: lineWidth)
            .rotationEffect(.degrees(isCircleRotating ? 360 : 0))
            .frame(width: size, height: size)
            .foregroundColor(color)
            .onAppear() {
                withAnimation(Animation
                    .linear(duration: 1)
                    .repeatForever(autoreverses: false)) {
                        self.isCircleRotating.toggle()
                    }
                withAnimation(Animation
                    .linear(duration: 1)
                    .delay(0.5)
                    .repeatForever(autoreverses: true)) {
                        self.animateStart.toggle()
                    }
                    withAnimation(Animation
                        .linear(duration: 1)
                        .delay(1)
                        .repeatForever(autoreverses: true)) {
                            self.animateEnd.toggle()
                        }
            }
    }
}

struct Loading_Previews: PreviewProvider {
    static var previews: some View {
        Loading()
    }
}
