//
//  ViewController.swift
//  TableViewTest
//
//  Created by Serg on 27.05.23.

import SnapKit
import UIKit

class ViewController: UIViewController {
    private var tableView = UITableView();
    
    private var brandNamesArr: [String] = []

    override func viewDidLoad() {
        super.viewDidLoad();
        
        view.backgroundColor = UIColor(red: 255/255, green: 255/255, blue: 255/255, alpha: 1);
        
        let label = UILabel();
        label.text = "Catalog";
        label.font = UIFont.boldSystemFont(ofSize: 16.0)
        label.tag = 111;
        label.font = UIFont.systemFont(ofSize: 32);
        view.addSubview(label);
            label.snp.makeConstraints { maker in
//                maker.left.equalToSuperview().inset(50);
                maker.centerX.equalToSuperview();
                maker.top.equalToSuperview().inset(80)
            };

        view.addSubview(tableView);
        tableView.snp.makeConstraints { maker in
            maker.left.equalToSuperview().inset(10)
            maker.right.equalToSuperview().inset(10)
            maker.top.equalToSuperview().inset(120)
            maker.bottom.equalToSuperview().inset(230)
//            maker.edges.equalToSuperview();
        }
        // tableView.delegate = self;
        tableView.dataSource = self;
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "cell")
        
        // MARK: - Devices Button
        let buttonDevices = UIButton(type: .system);
        buttonDevices.setTitle("Device", for: .normal);
        buttonDevices.setTitleColor(.white, for: .normal);
        buttonDevices.backgroundColor = UIColor(
            red: 103/255, green: 80/255, blue: 164/255, alpha: 1
        );
        buttonDevices.layer.cornerRadius = 20;
        view.addSubview(buttonDevices);
            buttonDevices.snp.makeConstraints { maker in
                maker.centerX.equalToSuperview();
                maker.bottom.equalToSuperview().inset(180);
                maker.width.equalTo(150);
                maker.height.equalTo(40);
            };
        buttonDevices.addTarget(self, action: #selector(onDevicesButtonRelease), for: .touchUpInside)
        
        // MARK: - Types Button
        let buttonTypes = UIButton(type: .system);
        buttonTypes.setTitle("Types", for: .normal);
        buttonTypes.setTitleColor(.white, for: .normal);
        buttonTypes.backgroundColor = UIColor(
            red: 103/255, green: 80/255, blue: 164/255, alpha: 1
        );
        buttonTypes.layer.cornerRadius = 20;
        view.addSubview(buttonTypes);
            buttonTypes.snp.makeConstraints { maker in
                maker.centerX.equalToSuperview();
                maker.bottom.equalToSuperview().inset(130);
                maker.width.equalTo(150);
                maker.height.equalTo(40);
            };
        buttonTypes.addTarget(self, action: #selector(onTypesButtonRelease), for: .touchUpInside)
        
        
        // MARK: - Brands Button
        let button = UIButton(type: .system);
        button.setTitle("Brands", for: .normal);
        button.setTitleColor(.white, for: .normal);
        button.backgroundColor = UIColor(
            red: 103/255, green: 80/255, blue: 164/255, alpha: 1
        );
        button.layer.cornerRadius = 20;
        view.addSubview(button);
            button.snp.makeConstraints { maker in
                maker.centerX.equalToSuperview();
                maker.bottom.equalToSuperview().inset(80);
                maker.width.equalTo(150);
                maker.height.equalTo(40);
            };
        button.addTarget(self, action: #selector(onBrandsButtonRelease), for: .touchUpInside)
    }
    
    @objc private func onTypesButtonRelease() {
        APIManager.shared.getTypes { [weak self] data in
            DispatchQueue.main.async {
                guard let self else { return }
                self.brandNamesArr = data
                self.tableView.reloadData()
            }
        }
    }

    @objc private func onBrandsButtonRelease() {
        APIManager.shared.getBrands { [weak self] data in
            DispatchQueue.main.async {
                guard let self else { return }
                self.brandNamesArr = data
                self.tableView.reloadData()
            }
        }
    }
    
    @objc private func onDevicesButtonRelease() {
        APIManager.shared.getDevices { [weak self] data in
            DispatchQueue.main.async {
                guard let self else { return }

                self.brandNamesArr = data
                self.tableView.reloadData()
            }
        }
    }
}

// MARK: - UITableViewDataSource
extension ViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // number fo cells
        return brandNamesArr.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell")!
        let brandName = brandNamesArr[indexPath.row]
        cell.textLabel?.text = brandName
        return cell
    }
}
