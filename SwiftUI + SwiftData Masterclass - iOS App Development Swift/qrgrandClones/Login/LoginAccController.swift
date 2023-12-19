//
//  LoginAccController.swift
//  qrgrandClones
//
//  Created by LuanLQ on 06/12/2023.
//

import SwiftUI

@MainActor
class LoginAccController: ObservableObject {
    @AppStorage("isLogin") var isLogin: Bool = false
    private var router: Router?

    //login acc
    @Published var isLoading: Bool = false
    @Published var isShowPass: Bool = false
    @Published var listError = ["", ""];
    @Published var email: String = ""
    @Published var password: String = ""
    
    
    func initData(router: Router) {
        self.router = router
    }

    func validate() -> Bool {
        var result = true;
        self.listError = ["", ""];
        if self.email.isEmpty {
            self.listError[0] = "メールアドレスを入力してください";
            result = false;
        }
        if self.password.isEmpty {
            self.listError[1] = "パスワードを入力してください";
            result = false;
        }
        return result;
    }

    func submit() {
        if self.isLoading {return}
        print("Acc: \(self.email), \(self.password)")
        if !self.validate() {
            return;
        }
        self.isLoading = true
        DispatchQueue.main.asyncAfter(deadline: .now() + 1) {
            self.isLogin = true
            self.router?.go(.home)
            self.isLoading = false
            self.resetInput()
        }
    }
    
    func resetInput() {
        self.email = ""
        self.password = ""
        self.isShowPass = false
    }
}
