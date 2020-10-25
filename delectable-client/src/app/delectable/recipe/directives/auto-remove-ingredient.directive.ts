import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[appAutoRemoveIngredient]'
})
export class AutoRemoveIngredientDirective {

  @Input() id;
  @Input() formClassReference: any;

  constructor(private el: ElementRef) { }

  @HostListener('blur') removeOnBlurIfEmpty() {
    if (this.el.nativeElement.value == "") {
      this.formClassReference.removeIngredient(this.id);
    }
  }
}
