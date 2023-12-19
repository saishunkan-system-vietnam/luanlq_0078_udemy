//
//  qrgrandClonesApp.swift
//  qrgrandClones
//
//  Created by LuanLQ on 18/10/2023.
//

import SwiftUI

@main
struct qrgrandClonesApp: App {
    // MARK: - Properties
    @StateObject private var router: Router = .init()
    @StateObject private var authController: AuthController = .init()
    @StateObject private var loginAccController: LoginAccController = .init()
    @StateObject private var loginQRController: LoginQRController = .init()
    @StateObject private var applyController: ApplyController = .init()
    @StateObject private var cancelController: CancelController = .init()
    @StateObject private var historyController: HistoryController = .init()
    @StateObject private var batchController: BatchController = .init()
    
    
    var body: some Scene {
        WindowGroup {
            NavigationStack(path: $router.path) {
                SplashView()
                    .navigationDestination(for: Router.Route.self) { route in
                        Group {
                            switch route {
                            case .login: LoginView()
                            case .loginAccount: LoginAccountView()
                            case .loginQr:
                                LoginQRView()
                            case .home:
                                HomeView()
                            case .license:
                                LicenseView()
                            case .rule:
                                RuleView()
                            case .apply:
                                ApplyView()
                            case .applyConfirm:
                                ApplyConfirmView()
                            case .applyDone:
                                ApplyDoneView()
                            case .history:
                                HistoryView()
                            case .historyScan:
                                HistoryScan()
                            case .historyDetail:
                                HistoryDetail()
                            case .batch:
                                BatchView()
                            case .cancel:
                                CancelView()
                            case .cancelConfirm:
                                CancelConfirmView()
                            case .cancelDone:
                                CancelDoneView()
                            }
                        }
                        .toolbar(.hidden)
                        .edgesIgnoringSafeArea(.bottom)
                    }
            }
            .preferredColorScheme(.light)
            .environmentObject(router)
            .environmentObject(authController)
            .environmentObject(loginAccController)
            .environmentObject(loginQRController)
            .environmentObject(applyController)
            .environmentObject(cancelController)
            .environmentObject(historyController)
            .environmentObject(batchController)
            .environment(\.locale, Locale(identifier: "ja"))
            .onAppear {
                #if DEBUG
                    UserDefaults.standard.set(false, forKey: "_UIConstraintBasedLayoutLogUnsatisfiable")
                #endif
            }
        }
    }
}
