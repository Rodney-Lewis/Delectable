package com.delectable.combo;

import com.delectable.shared.crud.CRUSoftDeleteRepository;
import com.delectable.shared.crud.CRUSoftDeleteService;

public class ComboService extends CRUSoftDeleteService<Combo> {
    public ComboService(CRUSoftDeleteRepository<Combo> repository) {
        super(repository);
    }
}
