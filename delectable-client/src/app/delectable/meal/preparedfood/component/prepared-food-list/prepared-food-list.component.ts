import { Component, OnInit } from '@angular/core';
import { FileHandlerService } from 'app/delectable/imagehandler/file-handler.service';
import { PreparedFoodService } from '../../prepared-food.service';
import { PreparedFood } from '../../prepared-food';
import { ConstantPool } from '@angular/compiler';

@Component({
  selector: 'app-prepared-food-list',
  templateUrl: './prepared-food-list.component.html',
  styleUrls: ['./prepared-food-list.component.css']
})
export class PreparedFoodListComponent implements OnInit {

  preparedItems: PreparedFood[];
  previewImages: string[];

  constructor(private preparedFoodService: PreparedFoodService, private fileHandlerService: FileHandlerService) { }

  ngOnInit(): void {
    this.preparedFoodService.findAll().subscribe(data => {
      console.log(data);
      this.preparedItems = data;
      this.previewImages = new Array();
      for (let preparedItem of this.preparedItems) {
        this.previewImages.push(this.fileHandlerService.getNamedImageUrl(preparedItem.imageSource));
      }
    })
  }
}
