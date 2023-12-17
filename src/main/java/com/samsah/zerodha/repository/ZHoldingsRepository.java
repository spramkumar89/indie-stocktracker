package com.samsah.zerodha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samsah.zerodha.beans.ZHolding;

public interface ZHoldingsRepository extends JpaRepository<ZHolding, Long>{

}
