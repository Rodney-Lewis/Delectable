import { Directive, ElementRef, Host, HostListener, Renderer2 } from '@angular/core';
import { Router } from '@angular/router';

@Directive({
  selector: '[appCalendarMultiDateSelect]'
})
export class CalendarMultiDateSelectDirective {

  firstClick: boolean = false;

  active1: any;
  active2: any;

  constructor(private el: ElementRef, private renderer: Renderer2, private router: Router) { }

  @HostListener('click', ['$event.target']) onFirstClick(btn: HTMLElement) {
    if (btn.nodeName == "DIV") {
      if (this.firstClick == false) {
        this.clearActive();
        this.active1 = btn['data-date'];
        this.renderer.addClass(btn, "active");
        this.firstClick = true;
      } else {
        this.active2 = btn['data-date'];
        this.renderer.addClass(btn, "active");
        this.firstClick = false;

        if (this.active1 < this.active2) {
          this.router.navigate(['', this.active1 + '_' + this.active2]);
        } else if (this.active1 > this.active2) {
          this.router.navigate(['', this.active2 + '_' + this.active1]);
        } else if (this.active1 == this.active2) {
          this.router.navigate(['', this.active1]);
        }
      }
    }
  }

  private clearActive() {
    this.active1 = -1;
    this.active2 = -1;
    var element = this.el.nativeElement as HTMLElement;
    var elements = element.getElementsByTagName("div");

    for (let i = 0; i < elements.length; i++) {
      this.renderer.removeClass(elements[i], "active");
      this.renderer.removeClass(elements[i], "betweenactive");
    }
  }

}