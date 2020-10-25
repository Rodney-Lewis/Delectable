import { Directive, ElementRef, HostListener, Input } from '@angular/core';
import { FormArray } from '@angular/forms';

@Directive({
  selector: '[appAutoAddDirection]'
})
export class AutoAddDirectionDirective {

  @Input() inputClassReference: string;
  @Input() formClassReference: any;
  input: HTMLInputElement;
  inputsCollection: HTMLCollectionOf<Element>;
  stringValue: string;
  stringValueList: string[];
  inputArray: FormArray;

  constructor(private el: ElementRef) { }

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
      this.formClassReference.addDirection();
      this.inputArray = this.formClassReference.form.get('element.directions') as FormArray;
      this.inputArray.at(this.inputArray.length - 1).patchValue({
        instruction: this.stringValueList[index]
      });
      this.inputsCollection = document.getElementsByClassName(this.inputClassReference);
      this.input = this.inputsCollection.item(this.inputsCollection.length - 1) as HTMLInputElement;
      this.input.focus();
    }
  }
}
