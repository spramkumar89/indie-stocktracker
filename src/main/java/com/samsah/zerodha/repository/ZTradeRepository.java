package com.samsah.zerodha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samsah.zerodha.beans.ZTrade;

public interface ZTradeRepository extends JpaRepository<ZTrade, Long>{

}
