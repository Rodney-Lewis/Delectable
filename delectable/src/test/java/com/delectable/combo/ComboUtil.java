package com.delectable.combo;

import java.util.ArrayList;
import java.util.List;
import com.delectable.recipe.RecipeUtil;
import com.delectable.shared.crud.CRUSoftDeleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComboUtil {

  private ComboService service;

  @Autowired
  private RecipeUtil recipeUtil;

  public ComboUtil(CRUSoftDeleteRepository<Combo> repository) {
    this.service = new ComboService(repository);
  }

  public List<Combo> createValidCombo(int numberOfCombosToCreate, int recipesPerGroup,
      boolean markAsDeleted) {

    List<Combo> combos = new ArrayList<Combo>();

    for (int i = 0; i < numberOfCombosToCreate; i++) {
      combos.add(new Combo("Test" + i, recipeUtil.insertValidTestRecipes(recipesPerGroup, false)));
      if (markAsDeleted == true) {
        combos.get(i).setDeleted(true);
      }
    }
    return combos;
  }

  public void insertValidCombo(int numberOfCombosToCreate, int recipesPerGroup,
      boolean markAsDeleted) {
    List<Combo> combos = new ArrayList<Combo>();
    combos.addAll(createValidCombo(numberOfCombosToCreate, recipesPerGroup, markAsDeleted));
    service.createFromList(combos);
  }

  public Combo insertValidCombo(int recipesPerGroup, boolean markAsDeleted) {
    Combo combo = createValidCombo(1, recipesPerGroup, markAsDeleted).get(0);
    combo = service.create(combo);
    return combo;
  }

  public Combo getCombo(Long id) {
    return service.get(id);
  }
}
