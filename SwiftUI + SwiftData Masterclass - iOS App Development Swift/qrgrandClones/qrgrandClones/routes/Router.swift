//
//  ViewRouter.swift
//  qrgrandClones
//
//  Created by LuanLQ on 24/10/2023.
//

import SwiftUI

class Router: ObservableObject {
    // MARK: - Properties

    @Published var path: NavigationPath = .init()

    // MARK: - Routes

    enum Route {
        case login, loginAccount, loginQr, home, license, rule, apply, applyConfirm, applyDone, history, historyScan, historyDetail, batch, cancel, cancelConfirm, cancelDone
    }

    // MARK: - Methods

    func push(_ route: Route) {
        path.append(route)
    }

    var canPop: Bool { !path.isEmpty }

    func pop(_ k: Int = 1) { path.removeLast(k) }

    func maybePop(_ k: Int = 1) {
        if canPop { pop(k) }
    }

    func replace(_ route: Route) {
        pop()
        push(route)
    }

    func go(_ route: Route) {
        pop(path.count)
        push(route)
    }
}
