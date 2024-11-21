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

    @JoinColumn(name = "uuid")
    @ManyToOne
    private HolderDao holder;

    @JoinColumn(name = "uuid")
    @OneToOne
    private DeviceDao device;

    protected HolderDeviceDao(){}

    public HolderDeviceDao(UUID uuid, HolderDao holder, DeviceDao device) {
        this.uuid = uuid;
        this.holder = holder;
        this.device = device;
    }

    public UUID getUuid() {
        return uuid;
    }

    public HolderDao getHolder() {
        return holder;
    }

    public DeviceDao getDevice() {
        return device;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setHolder(HolderDao holder) {
        this.holder = holder;
    }

    public void setDevice(DeviceDao device) {
        this.device = device;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HolderDeviceDao that = (HolderDeviceDao) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(holder, that.holder) && Objects.equals(device, that.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, holder, device);
    }

    public HolderDevice toDomain() {
        return new HolderDevice(
            uuid,
            holder.toDomain(),
            device.toDomain()
        );
    }
}
