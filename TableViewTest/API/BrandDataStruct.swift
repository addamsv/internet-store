import Foundation

// MARK: - BrandDatum
struct BrandDatum: Codable {
    let id: Int
    let name, createdAt, updatedAt: String
}

typealias BrandData = [BrandDatum]

// MARK: - TypeDatum
struct TypeDatum: Codable {
    let id: Int
    let name, createdAt, updatedAt: String
}

typealias TypeData = [TypeDatum]

// MARK: - DeviceDatum
struct DeviceDatum: Codable {
    let id, price, rating: Int
    let name, img, createdAt, updatedAt: String
}

struct DeviceDatumAll: Codable {
    let count: Int
    let rows: [DeviceDatum]
}


typealias DeviceData = DeviceDatumAll
