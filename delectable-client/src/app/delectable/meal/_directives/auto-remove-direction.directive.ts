import { Directive, ElementRef, HostListener, Input } from '@angular/core';
import { MealForm } from '../_shared/meal-form';

@Directive({
  selector: '[appAutoRemoveDirection]'
})
export class AutoRemoveDirectionDirective {

  @Input() id;
  @Input() formClassReference: any;

  constructor(private el: ElementRef) { }

  @HostListener('blur') removeOnBlurIfEmpty() {
    if (this.el.nativeElement.value == "") {
      this.formClassReference.removeDirection(this.id);
    }
  }
}
