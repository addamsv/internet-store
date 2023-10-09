//
//  APIManager.swift
//  TableViewTest
//
//  Created by Serg on 27.05.23.
//

import Foundation

class APIManager {
    static let shared = APIManager()
    
    let hostName = "http://127.0.0.1:5500/api/"
    
    func getBrands(completion: @escaping ([String]) -> Void) {
        let url = URL(string: hostName + "brand")!
    
        let request = URLRequest(url: url)
        
        let task = URLSession.shared.dataTask(with: request) { data, response, error in
            guard let data else { return }
    
            if let brandData = try? JSONDecoder().decode(BrandData.self, from: data) {
                var line: [String] = []
                for brand in brandData {
                    line.append(brand.name)
                }
                completion(line)
            } else {
                print("not ok")
            }
        }
        task.resume()
    }
    
    func getTypes(completion: @escaping ([String]) -> Void) {
        let url = URL(string: hostName + "type")!
        let request = URLRequest(url: url)
        
        let task = URLSession.shared.dataTask(with: request) { data, response, error in
            guard let data else { return }
    
            if let typeData = try? JSONDecoder().decode(TypeData.self, from: data) {
                var line: [String] = []
                for type in typeData {
                    line.append(type.name)
                }
                completion(line)
            } else {
                print("not ok")
            }
        }
        task.resume()
    }
    
    func getDevices(completion: @escaping ([String]) -> Void) {
        let url = URL(string: hostName + "device")!
        let request = URLRequest(url: url)
        
        let task = URLSession.shared.dataTask(with: request) { data, response, error in
            guard let data else { return }
    
            if let deviceData = try? JSONDecoder().decode(DeviceData.self, from: data) {
                var line: [String] = []
                for device in deviceData.rows {
                    line.append(device.name)
                }
                completion(line)
            } else {
                print("not ok")
            }
        }
        task.resume()
    }
}
