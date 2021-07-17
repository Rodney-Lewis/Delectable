import { Directive, ElementRef, Host, HostListener, Input, OnInit, Renderer2 } from '@angular/core';

@Directive({
  selector: '[appFixedButtonToContainer]'
})
export class FixedButtonToContainerDirective implements OnInit {
  @Input() buttonBuffer;

  constructor(private el: ElementRef, private renderer: Renderer2) { }

  @HostListener('window:resize')
  ngOnInit(): void {
    if (!this.buttonBuffer) {
      this.buttonBuffer = 64;
    }
    var container = document.getElementById("scheduleContainer");
    var offset = container.clientWidth + container.offsetLeft - this.buttonBuffer;
    this.renderer.setStyle(this.el.nativeElement, "left", offset + 'px');
  }

}
