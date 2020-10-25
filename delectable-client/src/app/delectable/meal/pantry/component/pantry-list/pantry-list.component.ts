import { Component, OnInit } from '@angular/core';
import { FileHandlerService } from 'app/delectable/_service/imagehandler/file-handler.service';
import { ActivatedRoute } from '@angular/router';
import { PantryService } from '../../pantry.service';

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
        this.jsonResponse = JSON.parse(data.body);
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
