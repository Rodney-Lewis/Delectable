import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, Validators, FormControl, FormArray } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Direction } from 'app/delectable/recipe/model/instruction';
import { FileHandlerService } from 'app/delectable/_service/imagehandler/file-handler.service';
import { RecipeService } from '../../service/recipe.service';
import { IngredientService } from '../../service/ingredient.service';
import { Ingredient } from '../../model/ingredient';
import { PantryService } from 'app/delectable/pantry/service/pantry.service';
import { PantryItem } from 'app/delectable/pantry/model/pantry';
import { FormWithImageComponent } from 'app/delectable/_component/form-with-image-component';

@Component({
  selector: 'app-recipe-form',
  templateUrl: './recipe-form.component.html',
  styleUrls: ['./recipe-form.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class RecipeFormComponent extends FormWithImageComponent implements OnInit {

  units: String[] = new Array();
  pantryItemList: PantryItem[] = new Array();
  pantryItemDisplayList: String[] = new Array();
  ngInited: boolean = false;

  constructor(formBuilder: FormBuilder, private recipeService: RecipeService, private ingredientService: IngredientService,
    private fileHandlerService: FileHandlerService, router: Router, private activatedroute: ActivatedRoute, private pantryService: PantryService) {
    super(router, formBuilder);
  }

  async ngOnInit() {
    await this.ingredientService.getUnits().toPromise().then(res => {
      this.units = res;
    })

    await this.activatedroute.paramMap.subscribe(params => {
      if (params.has('id')) {
        this.edit = true;
        this.id = Number.parseInt(params.get('id'));
        this.buildFormforEdit(this.id);
      } else {
        this.edit = false;
        this.buildFormforCreate();
      }
    })

    this.ngInited = true;
  }

  buildFormforEdit(id: number) {
    this.recipeService.findById(id).subscribe(recipe => {
      this.getFormGroupComponent('element').addControl('name', new FormControl(recipe.name, Validators.required));
      this.getFormGroupComponent('element').addControl('source', new FormControl(recipe.source, Validators.required));
      this.getFormGroupComponent('element').addControl('prepTimeHour', new FormControl(recipe.prepTimeHour, [Validators.min(0)]));
      this.getFormGroupComponent('element').addControl('prepTimeMinute', new FormControl(recipe.prepTimeMinute, [Validators.min(0), Validators.max(59)]));
      this.getFormGroupComponent('element').addControl('prepTimeSecond', new FormControl(recipe.prepTimeSecond, [Validators.min(0), Validators.max(59)]));
      this.getFormGroupComponent('element').addControl('cookTimeHour', new FormControl(recipe.cookTimeHour, [Validators.min(0)]));
      this.getFormGroupComponent('element').addControl('cookTimeMinute', new FormControl(recipe.cookTimeMinute, [Validators.min(0), Validators.max(59)]));
      this.getFormGroupComponent('element').addControl('cookTimeSecond', new FormControl(recipe.cookTimeSecond, [Validators.min(0), Validators.max(59)]));
      this.getFormGroupComponent('element').addControl('imageSource', new FormControl(recipe.imageSource));
      this.getFormGroupComponent('element').addControl('description', new FormControl(recipe.description, Validators.required));
      this.getFormGroupComponent('element').addControl('ingredients', new FormControl(recipe.ingredients));
      this.getFormGroupComponent('element').addControl('directions', new FormControl(recipe.directions));
      this.previewImage = this.fileHandlerService.getNamedImageUrl(recipe.imageSource);
      this.populateDirections(recipe.directions);
      this.populateIngredients(recipe.ingredients);
    })
  }

  buildFormforCreate() {
    this.getFormGroupComponent('element').addControl('name', new FormControl('', Validators.required));
    this.getFormGroupComponent('element').addControl('source', new FormControl(''));
    this.getFormGroupComponent('element').addControl('prepTimeHour', new FormControl(0, Validators.min(0)));
    this.getFormGroupComponent('element').addControl('prepTimeMinute', new FormControl(0, [Validators.min(0), Validators.max(59)]));
    this.getFormGroupComponent('element').addControl('prepTimeSecond', new FormControl(0, [Validators.min(0), Validators.max(59)]));
    this.getFormGroupComponent('element').addControl('cookTimeHour', new FormControl(0, [Validators.min(0)]));
    this.getFormGroupComponent('element').addControl('cookTimeMinute', new FormControl(0, [Validators.min(0), Validators.max(59)]));
    this.getFormGroupComponent('element').addControl('cookTimeSecond', new FormControl(0, [Validators.min(0), Validators.max(59)]));
    this.getFormGroupComponent('element').addControl('imageSource', new FormControl(''));
    this.getFormGroupComponent('element').addControl('description', new FormControl(''));
    this.getFormGroupComponent('element').addControl('ingredients', new FormArray([]));
    this.getFormGroupComponent('element').addControl('directions', new FormArray([]));
    this.previewImage = this.fileHandlerService.getNamedImageUrl("");
    this.addDirection();
    this.addIngredient();
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.form.invalid) {
      return;
    } else {
      if (this.getFormComponent("image.imageSourceFile").value != "") {
        if (this.edit) {
          this.recipeService.update(this.getFormComponent("element").value, this.id).subscribe();
        } else {
          this.recipeService.add(this.getFormComponent("element").value).subscribe();
        }
        const imageFormData = new FormData();
        imageFormData.append('imageMultipartFile', this.getFormComponent('image.imageMultipartFile').value);
        this.fileHandlerService.add(imageFormData).subscribe(() => {
          this.router.navigate(['/recipe/list']);
        });
      } else {
        if (this.edit) {
          this.recipeService.update(this.getFormComponent("element").value, this.id).subscribe(() => {
            this.router.navigate(['/recipe/detail', this.id]);
          });
        } else {
          this.recipeService.add(this.getFormComponent("element").value).subscribe(() => {
            this.router.navigate(['/recipe/list']);
          });
        }
      }
    }
  }

  populateIngredients(ingredients: Ingredient[]) {
    for (let ingredient of ingredients) {
      this.getFormArrayComponent('element.ingredients').push(this.formBuilder.group({
        ingredient: [ingredient.name, Validators.required],
      }));
    }
  }

  addIngredient() {
    this.getFormArrayComponent('element.ingredients').push(this.formBuilder.group({
      ingredient: [null, Validators.required],
    }));
  }

  removeIngredient(numberToRemove: number) {
    this.getFormArrayComponent('element.ingredients').removeAt(numberToRemove);
  }

  populateDirections(directions: Direction[]) {
    for (let direction of directions) {
      this.getFormArrayComponent('element.directions').push(this.formBuilder.group({
        step: [direction.step],
        instruction: [direction.instruction, Validators.required]
      }));
    }
  }

  addDirection() {
    this.getFormArrayComponent('element.directions').push(this.formBuilder.group({
      step: [this.getFormArrayComponent('element.directions').length + 1],
      instruction: ['', Validators.required]
    }));
  }

  removeDirection(numberToRemove: number) {
    this.getFormArrayComponent('element.directions').removeAt(numberToRemove);
    this.reorderSteps();
  }

  reorderSteps() {
    this.getFormArrayComponent('element.directions').controls.forEach((item, index) => {
      item.patchValue({ step: index + 1 });
    });
  }
}