import { Ingredient } from 'app/delectable/recipe/model/ingredient';

export class PantryItem {
    id: number;
    name: string;
    brand: string;
    ingredients: Ingredient[];
    imageSource: string;
    deleted: boolean;
    schedulable: boolean;
}
