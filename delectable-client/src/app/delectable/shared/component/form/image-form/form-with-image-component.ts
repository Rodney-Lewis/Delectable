import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { FormComponent } from '../base-form/form-component';

export abstract class FormWithImageComponent extends FormComponent {
    invalidImageType: boolean;
    invalidImageSize: boolean;
    previewImage: String;
    IMAGE_MAX_SIZE: number = 10000000;

    constructor(router: Router, formBuilder: FormBuilder) {
        super(router, formBuilder);
        let image = this.formBuilder.group({
            imageSourceFile: [''],
            imageMultipartFile: []
        });
        this.form.addControl('image', image);
    }

    patchFormImageValues(file: File) {
        this.form.patchValue({
            element: { imageSource: file.name },
            image: { imageMultipartFile: file }
        });
    }

    clickElementById(elementId: string) {
        document.getElementById(elementId).click();
    }

    onFileChange(event) {
        if (event.target.files.length > 0) {
            if (event.target.files[0].type == "image/jpeg" || event.target.files[0].type == "image/png") {
                this.invalidImageType = false;
                if (event.target.files[0].size < this.IMAGE_MAX_SIZE) {
                    this.invalidImageSize = false;
                    const file = event.target.files[0];
                    this.patchFormImageValues(file);
                    this.previewFile(file);
                }
                else {
                    this.invalidImageSize = true;
                }
            } else {
                this.invalidImageType = true;
            }
        }
    }

    previewFile(file: File) {
        const reader = new FileReader();
        reader.onload = (e: any) => {
            this.previewImage = e.target.result;
        };
        reader.readAsDataURL(file);
    }

}
