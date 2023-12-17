package com.samsah.zerodha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samsah.zerodha.beans.ZDividend;

public interface ZDividendRepository extends JpaRepository<ZDividend, Long>{

}
