import { Ingredient } from 'app/delectable/recipe/model/ingredient';

export class PantryItem {
    id: Number;
    name: String;
    brand: String;
    ingreidnets: Ingredient[];
    imageSource: String;
    deleted: boolean;
    schedulable: boolean;
}
