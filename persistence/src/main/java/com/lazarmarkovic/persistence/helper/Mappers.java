package com.lazarmarkovic.persistence.helper;

import com.lazarmarkovic.domain.entity.Device;
import com.lazarmarkovic.domain.entity.Holder;
import com.lazarmarkovic.domain.entity.HolderDevice;
import com.lazarmarkovic.persistence.dao.DeviceDao;
import com.lazarmarkovic.persistence.dao.HolderDao;
import com.lazarmarkovic.persistence.dao.HolderDeviceDao;

public class Mappers {

    public static DeviceDao toDeviceDao(Device device) {
        return new DeviceDao(
                device.uuid(),
                device.serialNumber(),
                device.phoneNumber(),
                device.model()
        );
    }

    public static HolderDao toHolderDao(Holder holder) {
        if (holder == null) {
            return null;
        }
        return new HolderDao(
                holder.uuid(),
                holder.firstName(),
                holder.lastName(),
                holder.address(),
                holder.birthday()
        );
    }

    public static HolderDeviceDao toHolderDeviceDao(HolderDevice holderDevice) {
        return new HolderDeviceDao(
                holderDevice.uuid(),
                holderDevice.holderUuid(),
                holderDevice.deviceUuid()
        );
    }
}
