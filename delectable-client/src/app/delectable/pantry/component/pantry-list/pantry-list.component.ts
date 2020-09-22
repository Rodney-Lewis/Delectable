import { Component, OnInit } from '@angular/core';
import { PantryItem } from 'app/delectable/pantry/model/pantry';
import { PantryService } from 'app/delectable/pantry/service/pantry.service';
import { FileHandlerService } from 'app/delectable/_service/imagehandler/file-handler.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pantry-list',
  templateUrl: './pantry-list.component.html',
  styleUrls: ['./pantry-list.component.css']
})
export class PantryListComponent implements OnInit {

  jsonResponse: any;
  constructor(private pantryService: PantryService, private filehandler: FileHandlerService, private activatedRoute: ActivatedRoute) { }


  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
      this.pantryService.findAll(params.cp, params.ps, params.s).subscribe(data => {
        this.jsonResponse = JSON.parse(data);
        this.jsonResponse.content.forEach(pantryItem => {
          pantryItem.imageSource = this.filehandler.getNamedImageUrl(pantryItem.imageSource);
        });
      },
        err => {
          this.jsonResponse = JSON.parse(err.error).message;
        }
      );
    })
  }
}
