package com.samsah.zerodha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samsah.zerodha.beans.ITrade;

public interface ITradeRepository extends JpaRepository<ITrade, Long>{

}
