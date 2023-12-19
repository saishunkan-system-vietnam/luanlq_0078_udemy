//
//  AuthController.swift
//  qrgrandClones
//
//  Created by LuanLQ on 07/12/2023.
//

import SwiftUI
import AVFAudio

@MainActor
class AuthController: ObservableObject {
    private var player: AVAudioPlayer?
    func playSound() {
        guard let url = Bundle.main.url(forResource: "beep", withExtension: "mp3") else {
            print("Do nothing if this url is empty")
            return
        }
        
        do {
            player = try AVAudioPlayer(contentsOf: url)
            player?.play()
        } catch {
            print("\(error)")
        }
        
    }
}
