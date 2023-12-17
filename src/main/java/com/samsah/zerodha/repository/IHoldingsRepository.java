package com.samsah.zerodha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samsah.zerodha.beans.IHolding;

public interface IHoldingsRepository extends JpaRepository<IHolding, Long>{

}
