package com.delectable.combo;

import com.delectable.shared.crud.CRUSoftDeleteRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboRepository extends CRUSoftDeleteRepository<Combo> {
}
