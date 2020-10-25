import { Component, OnInit } from '@angular/core';
import { PantryItem } from '../../pantry';
import { FileHandlerService } from 'app/delectable/_service/imagehandler/file-handler.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PantryService } from '../../pantry.service';

@Component({
  selector: 'app-pantry-detail',
  templateUrl: './pantry-detail.component.html',
  styleUrls: ['./pantry-detail.component.css']
})
export class PantryDetailComponent implements OnInit {

  pantryItem: PantryItem = new PantryItem();

  constructor(private pantryService: PantryService, private fileHandlerService: FileHandlerService, private activeRouter: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.activeRouter.paramMap.subscribe(params => {
      this.pantryService.findById(Number(params.get('id'))).subscribe(pantryItem => {
        this.pantryItem = pantryItem;
        this.pantryItem.imageSource = this.fileHandlerService.getNamedImageUrl(this.pantryItem.imageSource);
      })
    })
  }

  delete(id) {
    this.pantryService.delete(id).subscribe(() => {
      this.router.navigate(['/pantry/list']);
    })
  }

  displayDeleteButton() {
    return !this.pantryItem.deleted;
  }

}
