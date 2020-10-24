import { Directive, HostListener, Input, ElementRef } from '@angular/core';
import { RecipeFormComponent } from '../../component/recipe-form/recipe-form.component';

@Directive({
  selector: '[appAutoRemoveInput]'
})
export class AutoRemoveInput {

  @Input() id;
  @Input() inputType: string;

  constructor(private el: ElementRef, private recipeFormComponent: RecipeFormComponent) { }

  @HostListener('blur') removeOnBlurIfEmpty() {
    if (this.el.nativeElement.value == "") {
      if (this.inputType == 'ingredient') {
        this.recipeFormComponent.removeIngredient(this.id);
      } else if (this.inputType == 'instruction') {
        this.recipeFormComponent.removeDirection(this.id);
      }
    }
  }
}
