import { Directive, ElementRef, HostListener, Renderer2, Host, Input } from '@angular/core';
import { PantryService } from 'app/delectable/pantry/service/pantry.service';
import { FormControl } from '@angular/forms';

@Directive({
  selector: '[appPantry]'
})
export class PantryDirective {

  @Input() control: FormControl;

  constructor(private pantryService: PantryService, private el: ElementRef, private renderer: Renderer2) {
    document.addEventListener("click", this.closeAllLists);
  }

  @HostListener('click') onInitClick(control: FormControl) {
    this.buildList();
  }

  @HostListener('input') onInput() {
    this.buildList();
  }

  private buildList() {
    var a, b, i;
    var val = this.el.nativeElement.value;
    var arr: any[];
    var map = new Map();

    this.getPantryItems().then(pantryItems => {
      arr = JSON.parse(pantryItems.body).content;

      for (i = 0; i < arr.length; i++) {
        if (arr[i].brand) {
          map.set(arr[i], arr[i].name + arr[i].brand)
        } else {
          map.set(arr[i], arr[i].name)
        }
      }

      a = document.createElement("DIV");
      a.setAttribute("id", this.el.nativeElement.id + "autocomplete-list");
      a.setAttribute("class", "autocomplete-items");
      this.closeAllLists();
      this.el.nativeElement.parentNode.appendChild(a);

      for (i = 0; i < arr.length; i++) {
        if (arr[i].name.substr(0, val.length).toUpperCase() == val.toUpperCase()) {
          b = document.createElement("div");
          b.innerHTML = "<strong>" + arr[i].name.substr(0, val.length) + "</strong>";
          b.innerHTML += arr[i].name.substr(val.length);
          if (arr[i].brand) {
            b.innerHTML += " (" + arr[i].brand + ")";
            b.innerHTML += "<input type='hidden' value='" + arr[i].name + " (" + arr[i].brand + ")'" + " data-id='" + arr[i].id + "'>";
          } else {
            b.innerHTML += "<input type='hidden' value='" + arr[i].name + "' data-id='" + arr[i].id + "'>";
          }
          b.addEventListener("click", event => {
            this.el.nativeElement.value = event.srcElement.getElementsByTagName("input")[0].value;
            this.control.setValue(event.srcElement.getElementsByTagName("input")[0].attributes['data-id'].value);

            /*
            this.el.nativeElement.value = event.srcElement.getElementsByTagName("input")[0].value;
            this.el.nativeElement.setAttribute("data-id", event.srcElement.getElementsByTagName("input")[0].attributes['data-id'].value);
            */
            //this.el.nativeElement.value = event.srcElement.getElementsByTagName("input")[0].value;
            //this.el.nativeElement.setProperty("textContent", event.srcElement.getElementsByTagName("input")[0].value);
            //this.renderer.setProperty(this.el.nativeElement, "defaultValue", event.srcElement.getElementsByTagName("input")[0].attributes['data-id'].value);

            this.closeAllLists();
          });
          this.renderer.appendChild(a, b);
        }
      }
    });
    return arr;
  }

  closeAllLists() {
    var x = document.getElementsByClassName("autocomplete-items");
    for (var i = 0; i < x.length; i++) {
      x[i].parentNode.removeChild(x[i]);
    }
  }

  async getPantryItems() {
    return await this.pantryService.findAll(1, 15).toPromise();
  }
}
