import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[appAutoResizeTextfield]'
})
export class AutoResizeTextfieldDirective {

  constructor(private el: ElementRef) { }

  @HostListener('focus') resizeOnFocus() {
    this.resizeTextfield();
  }

  @HostListener('input') resizeOnInput() {
    this.resizeTextfield();
  }

  resizeTextfield() {
    this.el.nativeElement.style.height = "auto";
    this.el.nativeElement.style.height = (this.el.nativeElement.scrollHeight) + 'px';
  }

}
