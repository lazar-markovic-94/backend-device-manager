package com.lazarmarkovic.persistence.dao;

import com.lazarmarkovic.domain.entity.Holder;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "holder")
public class HolderDao {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "birthday")
    private Date birthday;

    protected HolderDao() {}

    public HolderDao(UUID uuid, String firstName, String lastName, String address, Date birthday) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthday = birthday;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HolderDao holderDao = (HolderDao) o;
        return Objects.equals(uuid, holderDao.uuid) &&
                Objects.equals(firstName, holderDao.firstName) &&
                Objects.equals(lastName, holderDao.lastName) &&
                Objects.equals(address, holderDao.address) &&
                Objects.equals(birthday, holderDao.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, firstName, lastName, address, birthday);
    }

    public Holder toDomain() {
        return new Holder(uuid, firstName, lastName, address, birthday);
    }
}

