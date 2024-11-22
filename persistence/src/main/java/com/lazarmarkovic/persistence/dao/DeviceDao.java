package com.lazarmarkovic.persistence.dao;


import com.lazarmarkovic.domain.entity.Device;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "device")
public class DeviceDao {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "model")
    private String model;

    protected DeviceDao() {}

    public DeviceDao(UUID uuid, String serialNumber, String phoneNumber, String model) {
        this.uuid = uuid;
        this.serialNumber = serialNumber;
        this.phoneNumber = phoneNumber;
        this.model = model;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceDao deviceDao = (DeviceDao) o;
        return Objects.equals(uuid, deviceDao.uuid) &&
                Objects.equals(serialNumber, deviceDao.serialNumber) &&
                Objects.equals(phoneNumber, deviceDao.phoneNumber) &&
                Objects.equals(model, deviceDao.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, serialNumber, phoneNumber, model);
    }

    public Device toDomain() {
        return new Device(uuid, serialNumber, phoneNumber, model);
    }
}
