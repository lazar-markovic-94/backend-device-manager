package com.lazarmarkovic.persistence.dao;

import com.lazarmarkovic.domain.entity.HolderDevice;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "holder_device")
public class HolderDeviceDao {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "holder_uuid")
    private UUID holderUuid;

    @Column(name = "device_uuid")
    private UUID deviceUuid;

    protected HolderDeviceDao(){}

    public HolderDeviceDao(UUID uuid, UUID holderUuid, UUID deviceUuid) {
        this.uuid = uuid;
        this.holderUuid = holderUuid;
        this.deviceUuid = deviceUuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getHolderUuid() {
        return holderUuid;
    }

    public void setHolderUuid(UUID holderUuid) {
        this.holderUuid = holderUuid;
    }

    public UUID getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(UUID deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HolderDeviceDao that = (HolderDeviceDao) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(holderUuid, that.holderUuid) && Objects.equals(deviceUuid, that.deviceUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, holderUuid, deviceUuid);
    }

    public HolderDevice toDomain() {
        return new HolderDevice(
            uuid,
            holderUuid,
            deviceUuid
        );
    }
}
