import { FormGroup, FormBuilder, FormArray } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

export abstract class FormComponent {

	formSubmitted: boolean;
	edit: boolean;
	id: number;
	form: FormGroup;

	constructor(protected router: Router, protected formBuilder) { 
		this.formSubmitted = false;
		this.edit = false;
	}

	abstract buildFormforEdit(id: number);
	abstract buildFormforCreate();
	abstract submitForm();
	
	getFormComponent(component: string) {
		return this.form.get(component);
	}

	getFormArrayComponent(component: string) {
		return this.form.get(component) as FormArray;
	}

	formFieldInvalid(component: string) {
		return this.getFormComponent(component).invalid && (this.getFormComponent(component).dirty || this.getFormComponent(component).touched || this.formSubmitted)
	}

	formFieldRequired(component: string) {
		if (this.formFieldInvalid(component))
			return this.getFormComponent(component).errors?.required;
	}

	formFieldMin(component: string) {
		if (this.formFieldInvalid(component))
			return this.getFormComponent(component).errors?.min;
	}

	formFieldMax(component: string) {
		if (this.formFieldInvalid(component))
			return this.getFormComponent(component).errors?.max;
	}
}
