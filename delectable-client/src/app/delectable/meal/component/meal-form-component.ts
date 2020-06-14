import { FormWithImageComponent } from 'app/delectable/component/form-with-image-component';
import { Validators } from '@angular/forms';
import { Direction } from '../instruction';

export abstract class MealFormComponent extends FormWithImageComponent {

    populateDirections(directions: Direction[], componentArray: string) {
        for (let direction of directions) {
            this.getFormArrayComponent(componentArray).push(this.formBuilder.group({
                step: [direction.step],
                instruction: [direction.instruction, Validators.required]
            }));
        }
    }

    addDirection(componentArray: string) {
        this.getFormArrayComponent(componentArray).push(this.formBuilder.group({
            step: [this.getFormArrayComponent(componentArray).length + 1],
            instruction: ['', Validators.required]
        }));
    }

    removeDirection(numberToRemove: number, componentArray: string) {
        this.getFormArrayComponent(componentArray).removeAt(numberToRemove);
        this.reorderSteps(componentArray);
    }

    reorderSteps(componentArray: string) {
        this.getFormArrayComponent(componentArray).controls.forEach((item, index) => {
            item.patchValue({ step: index + 1 });
        });
    }

}
