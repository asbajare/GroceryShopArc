package com.cg.gsm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.gsm.entities.Booking;

public interface BookingDAOInt extends JpaRepository<Booking, Integer> {
}
