package net.sinou.sandbox.sbgtuto.repositories;

import org.springframework.data.repository.CrudRepository;

import net.sinou.sandbox.sbgtuto.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}