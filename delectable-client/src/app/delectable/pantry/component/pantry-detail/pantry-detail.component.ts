import { Component, OnInit } from '@angular/core';
import { PantryItem } from '../../model/pantry';
import { PantryService } from '../../service/pantry.service';
import { FileHandlerService } from 'app/delectable/_service/imagehandler/file-handler.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pantry-detail',
  templateUrl: './pantry-detail.component.html',
  styleUrls: ['./pantry-detail.component.css']
})
export class PantryDetailComponent implements OnInit {

  pantryItem: PantryItem;

  constructor(private pantryService: PantryService, private fileHandlerService: FileHandlerService, private activeRouter: ActivatedRoute) { }

  ngOnInit() {
    this.activeRouter.paramMap.subscribe(params => {
      this.pantryService.findById(Number(params.get('id'))).subscribe(pantryItem => {
        this.pantryItem = pantryItem;
        this.pantryItem.imageSource = this.fileHandlerService.getNamedImageUrl(this.pantryItem.imageSource);
      })
    })
  }

}
