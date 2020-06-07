import { Ingredient } from 'app/delectable/ingredient/ingredient';
import { Meal } from '../meal';

export class Recipe extends Meal{
    source: string;
    ingredients: Ingredient[];
}
