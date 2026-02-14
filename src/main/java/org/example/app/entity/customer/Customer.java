package org.example.app.entity.customer;

import org.springframework.stereotype.Component;

@Component
public class Customer {
    private Long id;
    private String name;
    private String phone;
    private String address;

    // Порожній конструктор
    public Customer() {
    }

    // Конструктор з усіма полями
    public Customer(Long id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    // Методи (Setters), які шукає ваш RowMapper
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }

    // Методи (Getters), які знадобляться сервісу та контролеру
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
}
