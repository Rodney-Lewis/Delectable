import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormBuilder, Validators, FormControl, FormArray } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { FileHandlerService } from 'app/delectable/_service/imagehandler/file-handler.service';
import { RecipeService } from '../../recipe.service';
import { FormWithImageComponent } from 'app/delectable/_component/form/image-form/form-with-image-component';
import { Ingredient } from '../../ingredient';
import { Direction } from '../../direction';

@Component({
  selector: 'app-recipe-form',
  templateUrl: './recipe-form.component.html',
  styleUrls: ['./recipe-form.component.css'],
})
export class RecipeFormComponent extends FormWithImageComponent implements OnInit {

  debugJson: boolean = false;

  constructor(formBuilder: FormBuilder, router: Router, private change: ChangeDetectorRef, private recipeService: RecipeService,
    private fileHandlerService: FileHandlerService, private activatedroute: ActivatedRoute) {
    super(router, formBuilder);
  }

  ngOnInit() {
    this.activatedroute.paramMap.subscribe(params => {
      if (params.has('id')) {
        this.edit = true;
        this.id = Number.parseInt(params.get('id'));
        this.buildFormforEdit(this.id);
      } else {
        this.edit = false;
        this.buildFormforCreate();
      }
    })
  }

  buildFormforEdit(id: number) {
    this.recipeService.findById(id).subscribe(recipe => {
      let formGroup = this.getFormGroupComponent('element');
      formGroup.addControl('name', new FormControl(recipe.name, Validators.required));
      formGroup.addControl('source', new FormControl(recipe.source));
      formGroup.addControl('description', new FormControl(recipe.description));
      formGroup.addControl('servings', new FormControl(recipe.servings, [Validators.min(0), Validators.max(99)]));
      formGroup.addControl('prepTimeHour', new FormControl(recipe.prepTimeHour, [Validators.min(0), Validators.max(72)]));
      formGroup.addControl('prepTimeMinute', new FormControl(recipe.prepTimeMinute, [Validators.min(0), Validators.max(59)]));
      formGroup.addControl('prepTimeSecond', new FormControl(recipe.prepTimeSecond, [Validators.min(0), Validators.max(59)]));
      formGroup.addControl('cookTimeHour', new FormControl(recipe.cookTimeHour, [Validators.min(0), Validators.max(72)]));
      formGroup.addControl('cookTimeMinute', new FormControl(recipe.cookTimeMinute, [Validators.min(0), Validators.max(59)]));
      formGroup.addControl('cookTimeSecond', new FormControl(recipe.cookTimeSecond, [Validators.min(0), Validators.max(59)]));
      formGroup.addControl('ingredients', new FormArray([]));
      formGroup.addControl('directions', new FormArray([]));
      formGroup.addControl('imageSource', new FormControl(recipe.imageSource));
      this.previewImage = this.fileHandlerService.getNamedImageUrl(recipe.imageSource);
      this.populateDirections(recipe.directions);
      this.populateIngredients(recipe.ingredients);
    })
  }

  buildFormforCreate() {
    let formGroup = this.getFormGroupComponent('element');
    formGroup.addControl('name', new FormControl('', Validators.required));
    formGroup.addControl('source', new FormControl(''));
    formGroup.addControl('description', new FormControl(''));
    formGroup.addControl('servings', new FormControl(1, [Validators.min(0), Validators.max(99)]));
    formGroup.addControl('prepTimeHour', new FormControl(0, [Validators.min(0), Validators.max(72)]));
    formGroup.addControl('prepTimeMinute', new FormControl(0, [Validators.min(0), Validators.max(59)]));
    formGroup.addControl('prepTimeSecond', new FormControl(0, [Validators.min(0), Validators.max(59)]));
    formGroup.addControl('cookTimeHour', new FormControl(0, [Validators.min(0), Validators.max(72)]));
    formGroup.addControl('cookTimeMinute', new FormControl(0, [Validators.min(0), Validators.max(59)]));
    formGroup.addControl('cookTimeSecond', new FormControl(0, [Validators.min(0), Validators.max(59)]));
    formGroup.addControl('ingredients', new FormArray([]));
    formGroup.addControl('directions', new FormArray([]));
    formGroup.addControl('imageSource', new FormControl(''));
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
        displayValue: [ingredient.displayValue, Validators.required],
      }));
    }
  }

  addIngredient() {
    this.getFormArrayComponent('element.ingredients').push(this.formBuilder.group({
      displayValue: [null, Validators.required],
    }));
    this.change.detectChanges();
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
      instruction: ['']
    }));
    this.change.detectChanges();
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