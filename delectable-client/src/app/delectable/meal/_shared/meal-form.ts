import { ChangeDetectorRef } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormWithImageComponent } from 'app/delectable/_component/form/image-form/form-with-image-component';
import { Ingredient } from './ingredient';
import { Direction } from './direction';

export abstract class MealForm extends FormWithImageComponent {

    constructor(router: Router, formBuilder: FormBuilder, private change: ChangeDetectorRef) {
        super(router, formBuilder);
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
