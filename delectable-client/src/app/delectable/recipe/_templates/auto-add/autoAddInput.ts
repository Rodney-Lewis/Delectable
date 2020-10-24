import { Directive, ElementRef, HostListener, Input } from '@angular/core';
import { FormArray } from '@angular/forms';
import { RecipeFormComponent } from '../../component/recipe-form/recipe-form.component';

@Directive({
  selector: '[appAutoAddInput]'
})
export class AutoAddInput {

  @Input() classReference: string;
  @Input() inputType: string;
  input: HTMLInputElement;
  inputsCollection: HTMLCollectionOf<Element>;
  stringValue: string;
  stringValueList: string[];
  inputArray: FormArray;

  constructor(private el: ElementRef, private recipeFormComponent: RecipeFormComponent) { }

  @HostListener('input') onInput() {
    this.stringValue = this.el.nativeElement.value.toString();
    this.stringValueList = this.stringValue.split(/\r?\n/);
    this.stringValueList = this.stringValueList.filter(item => {
      if (item.length > 0) {
        item.trim();
        return true;
      }
    })
    this.el.nativeElement.value = "";

    for (let index = 0; index < this.stringValueList.length; index++) {
      if (this.inputType == 'ingredient') {
        this.recipeFormComponent.addIngredient();
        this.inputArray = this.recipeFormComponent.form.get('element.ingredients') as FormArray;
        this.inputArray.at(this.inputArray.length - 1).patchValue({
          ingredient: this.stringValueList[index]
        });
      } else if (this.inputType == 'instruction') {
        this.recipeFormComponent.addDirection();
        this.inputArray = this.recipeFormComponent.form.get('element.directions') as FormArray;
        this.inputArray.at(this.inputArray.length - 1).patchValue({
          instruction: this.stringValueList[index]
        });
      }

      this.inputsCollection = document.getElementsByClassName(this.classReference);
      this.input = this.inputsCollection.item(this.inputsCollection.length - 1) as HTMLInputElement;
      this.input.focus();
    }
  }
}
