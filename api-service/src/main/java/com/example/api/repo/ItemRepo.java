package com.example.api.repo;

import com.example.api.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository <Item, Long>{

}
