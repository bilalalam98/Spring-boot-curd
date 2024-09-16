package com.trmab;

import org.springframework.data.jpa.repository.JpaRepository;

//with this repo we can inject in what ever class we need and then we can perform all curd operations
public interface CustomerRepository  extends JpaRepository<Customer, Long>{

}
