//
//  Scanner.swift
//  qrgrandClones
//
//  Created by LuanLQ on 30/10/2023.
//

import SwiftUI
import CodeScanner
import AVFoundation

struct ScannerView: View {
    private var isShowingScanner: Bool
    private var scanMode: ScanMode
    var completion: (Result<ScanResult, ScanError>) -> Void
    
    var scannerSheet : some View {
        CodeScannerView(codeTypes: [.qr], scanMode: scanMode, shouldVibrateOnSuccess: false)
        { result in
            switch result {
            case .success(let data):
                print("Scanning success: \(data.string)")
            case .failure(let error):
                print("Scanning failed: \(error.localizedDescription)")
            }
            completion(result)
        }
    }
    
    //MARK: - Init
    init(
        isShowingScanner: Bool = true,
        scanMode: ScanMode = .once,
        completion: @escaping (Result<ScanResult, ScanError>) -> Void
    ) {
        self.isShowingScanner = isShowingScanner
        self.scanMode = scanMode
        self.completion = completion
    }
    
    var body: some View {
//        GeometryReader { geometry in
            VStack {
                if isShowingScanner{
                    #if !targetEnvironment(simulator)
                    scannerSheet
//                        .listRowInsets(EdgeInsets(top: -20, leading: -20, bottom: -20, trailing: -20))
                    #else
                    Color.black
                    Image(systemName: "exclamationmark.triangle.fill")
                        .resizable()
                        .frame(width: 50, height: 50)
                        .foregroundColor(.white)
                    #endif
                } else {
                    Color.black
                }
            }
//            .frame(width: geometry.size.width, height: geometry.size.height)
//        }
//        .onAppear(perform: checkCameraPermission)
    }
    
    //Check camera permission
//    func checkCameraPermission() {
//        DispatchQueue.main.async{
//            Task {
//                switch AVCaptureDevice.authorizationStatus(for: .video) {
//                case .authorized:
//                    self.isShowingScanner = true
//                case .notDetermined:
//                    if await AVCaptureDevice.requestAccess(for: .video) {
//                        self.isShowingScanner = true
//                    } else {
//                        self.isShowingScanner = false
//                    }
//                case .denied, .restricted:
//                    self.isShowingScanner = false
//                default: break
//                }
//            }
//        }
//    }
}

struct ScannerView_Previews: PreviewProvider {
    static var previews: some View {
        ScannerView(isShowingScanner: false){_ in }
    }
}
//#Preview {
//    ScannerView()
//}
