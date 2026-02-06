package com.example.diplomski.repo;

import com.example.diplomski.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository <Item, Long>{

}
